package agijava.io;

import java.util.ArrayList;
import java.util.List;

import agijava.view.impl.Cel;
import agijava.view.impl.Loop;

public class LoopFactory {

	public Loop readLoop(List<Integer> rawData, int loopNo, Integer loopOffset) {
		Loop loop = new Loop();
		List<Integer> celOffsets = readCelOffsets(rawData, loopOffset);
		for (Integer celOffset : celOffsets) {
			CelFactory celBuilder = new CelFactory();
			Cel cel = celBuilder.getCel(rawData, loopNo, celOffset);
			loop.addCel(cel);
		}
		return loop;
	}
	
	private List<Integer> readCelOffsets(List<Integer> rawData,
			Integer loopOffset) {
		int thisOffset = loopOffset;
		int numberOfCels = rawData.get(thisOffset++);
		List<Integer> celOffsets = new ArrayList<Integer>();
		for (int cel = 0; cel < numberOfCels; cel++) {
			int celPos1 = rawData.get(thisOffset++) & 0xff;
			int celPos2 = rawData.get(thisOffset++) & 0xff;
			int celPos = (celPos2 << 8) | celPos1;
			celPos = loopOffset + celPos;
			celOffsets.add(celPos);
		}
		return celOffsets;
	}

}
