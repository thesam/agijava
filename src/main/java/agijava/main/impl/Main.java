package agijava.main.impl;

import java.io.IOException;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		GameEngine engine = GameEngineFactory.createInstance("resource/sq2/"); 
		engine.run();
	}

}
