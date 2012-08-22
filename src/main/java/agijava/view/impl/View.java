package agijava.view.impl;

import java.util.ArrayList;
import java.util.List;

import agijava.view.ILoop;
import agijava.view.IView;



public class View implements IView {

	List<ILoop> loops;
	private int entryNo;

	public View(int entryNo) {
		this.entryNo = entryNo;
		loops = new ArrayList<ILoop>();
	}

	public void addLoop(ILoop loop) {
		loops.add(loop);

	}

	public List<ILoop> getLoops() {
		return loops;
	}

	@Override
	public int getNumber() {
		return entryNo;
	}

}
