package agijava.gui;

import java.awt.Color;

public class DefaultAgiPalette {
	private final int colors[] = {
		new Color(rescale6BitColor(0x00), rescale6BitColor(0x00),
				rescale6BitColor(0x00)).getRGB(),
		new Color(rescale6BitColor(0x00), rescale6BitColor(0x00),
				rescale6BitColor(0x2A)).getRGB(),
		new Color(rescale6BitColor(0x00), rescale6BitColor(0x2A),
				rescale6BitColor(0x00)).getRGB(),
		new Color(rescale6BitColor(0x00), rescale6BitColor(0x2A),
				rescale6BitColor(0x2A)).getRGB(),
		new Color(rescale6BitColor(0x2A), rescale6BitColor(0x00),
				rescale6BitColor(0x00)).getRGB(),
		new Color(rescale6BitColor(0x2A), rescale6BitColor(0x00),
				rescale6BitColor(0x2A)).getRGB(),
		new Color(rescale6BitColor(0x2A), rescale6BitColor(0x15),
				rescale6BitColor(0x00)).getRGB(),
		new Color(rescale6BitColor(0x2A), rescale6BitColor(0x2A),
				rescale6BitColor(0x2A)).getRGB(),
		new Color(rescale6BitColor(0x15), rescale6BitColor(0x15),
				rescale6BitColor(0x15)).getRGB(),
		new Color(rescale6BitColor(0x15), rescale6BitColor(0x15),
				rescale6BitColor(0x3F)).getRGB(),
		new Color(rescale6BitColor(0x15), rescale6BitColor(0x3F),
				rescale6BitColor(0x15)).getRGB(),
		new Color(rescale6BitColor(0x15), rescale6BitColor(0x3F),
				rescale6BitColor(0x3F)).getRGB(),
		new Color(rescale6BitColor(0x3F), rescale6BitColor(0x15),
				rescale6BitColor(0x15)).getRGB(),
		new Color(rescale6BitColor(0x3F), rescale6BitColor(0x15),
				rescale6BitColor(0x3F)).getRGB(),
		new Color(rescale6BitColor(0x3F), rescale6BitColor(0x3F),
				rescale6BitColor(0x15)).getRGB(),
		new Color(rescale6BitColor(0x3F), rescale6BitColor(0x3F),
				rescale6BitColor(0x3F)).getRGB() };
	
		public int getColorRgb(int index) {
			return colors[index];
		}
		
		private float rescale6BitColor(int i) {
			return ((float) i / 0x3f);
		}

		public int getIndex(int rgb) {
			for (int i = 0; i < colors.length; i++) {
				if (colors[i] == rgb) {
					return i;
				}
			}
			return -1;
		}
}
