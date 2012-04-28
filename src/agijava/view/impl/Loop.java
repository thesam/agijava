package agijava.view.impl;

import java.util.ArrayList;
import java.util.List;

import agijava.view.ICel;
import agijava.view.ILoop;



public class Loop implements ILoop {
	
	private List<ICel> cels;
	
	public Loop() {
		cels = new ArrayList<ICel>();
	}

	public void addCel(ICel cel) {
		cels.add(cel);
		
	}

	public List<ICel> getCels() {
		return cels;
	}

}
