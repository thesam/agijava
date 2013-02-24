package agijava.view.impl;

import java.util.ArrayList;
import java.util.List;



public class View {

	List<Loop> loops;
	private int entryNo;

	public View(int entryNo) {
		this.entryNo = entryNo;
		loops = new ArrayList<Loop>();
	}

	public void addLoop(Loop loop) {
		loops.add(loop);

	}

	public List<Loop> getLoops() {
		return loops;
	}

	public int getNumber() {
		return entryNo;
	}

}
