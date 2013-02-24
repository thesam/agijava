package agijava.view;

import java.util.ArrayList;
import java.util.List;



public class Loop {
	
	private List<Cel> cels;
	
	public Loop() {
		cels = new ArrayList<Cel>();
	}

	public void addCel(Cel cel) {
		cels.add(cel);
		
	}

	public List<Cel> getCels() {
		return cels;
	}

}
