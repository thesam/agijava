package agijava.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import agijava.view.ILoop;
import agijava.view.IView;
import agijava.view.impl.View;

public class ViewFactory {

	private Resource res;

	public ViewFactory(Resource res) throws IOException {
		this.res = res;
	}

	public IView getView() {
		View view = new View(res.getEntryNumber());

		List<Integer> rawData = res.getRawData();

		List<Integer> loopOffsets = readLoopOffsets(rawData);

		int loopNo = 0;
		for (Integer loopOffset : loopOffsets) {
			LoopFactory loopBuilder = new LoopFactory();
			ILoop loop = loopBuilder.readLoop(rawData, loopNo, loopOffset);
			view.addLoop(loop);
			loopNo++;
		}
		return view;
	}

	private List<Integer> readLoopOffsets(List<Integer> rawData) {
		int fileOffset = 0;
		List<Integer> loopOffsets = new ArrayList<Integer>();
		@SuppressWarnings("unused")
		int unknown1 = rawData.get(fileOffset++);
		@SuppressWarnings("unused")
		int unknown2 = rawData.get(fileOffset++);
		int numberOfLoops = rawData.get(fileOffset++);
		@SuppressWarnings("unused")
		int description1 = rawData.get(fileOffset++);
		@SuppressWarnings("unused")
		int description2 = rawData.get(fileOffset++);
		for (int i = 0; i < numberOfLoops; i++) {
			int loop1 = rawData.get(fileOffset++) & 0xff;
			int loop2 = rawData.get(fileOffset++) & 0xff;
			int newOffset = ((loop2 << 8) | loop1);
			loopOffsets.add(new Integer(newOffset));
		}
		return loopOffsets;
	}

}
