package agijava.gui;

import java.awt.Font;
import java.io.InputStream;

public class ConsoleFontFactory {

	public Font createFont() {
		String fName = "/fonts/pcsenior/pcsenior.ttf";
		InputStream is = ConsoleFontFactory.class.getResourceAsStream(fName);
		Font font = null;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return font;
	}

}
