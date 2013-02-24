package agijava.gui.impl;

public class PrioCalculator {
		
	public int getPrioBasedOnPosition(int y) {
		// Priority Band Y range
		// -------------- ----------
		// 4 -
		// 5 48 - 59
		// 6 60 - 71
		// 7 72 - 83
		// 8 84 - 95
		// 9 96 - 107
		// 10 108 - 119
		// 11 120 - 131
		// 12 132 - 143
		// 13 144 - 155
		// 14 156 - 167
		// 15 168
		// -------------- ----------
		if (y < 48) {
			return 4;
		} else if (y < 60) {
			return 5;
		} else if (y < 72) {
			return 6;
		} else if (y < 84) {
			return 7;
		} else if (y < 96) {
			return 8;
		} else if (y < 108) {
			return 9;
		} else if (y < 120) {
			return 10;
		} else if (y < 132) {
			return 11;
		} else if (y < 144) {
			return 12;
		} else if (y < 156) {
			return 13;
		} else if (y < 168) {
			return 14;
		} else {
			return 15;
		}
	}
}
