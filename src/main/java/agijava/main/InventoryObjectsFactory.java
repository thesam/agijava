package agijava.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import agijava.io.RawByteArray;

public class InventoryObjectsFactory {

	private static final int FIRST_ENTRY_OFFSET = 3;
	private static String ENCRYPTION_KEY = "Avis Durgan";

	public static InventoryObjects createFromFile(String path)
			throws IOException {
		RawByteArray raw = readAndDecryptFile(path);
		InventoryObjects inv = createFromByteArray(raw);
		return inv;
	}

	private static RawByteArray readAndDecryptFile(String path)
			throws IOException {
		FileInputStream inputStream = new FileInputStream(path);
		List<Integer> fileContent = new ArrayList<Integer>();
		int nextByte = inputStream.read();
		int currentOffset = 0;
		while (nextByte != -1) {
			int decrp = decrypt(nextByte, currentOffset);
			currentOffset++;
			fileContent.add(decrp);
			nextByte = inputStream.read();
		}
		inputStream.close();
		RawByteArray raw = new RawByteArray(fileContent);
		return raw;
	}

	public static InventoryObjects createFromByteArray(RawByteArray raw) {
		int namesSectionOffset = getNamesSectionOffset(raw);	
		raw.getNextAndStep(); // max number (discarded)
		HashMap<Integer, Integer> roomNumbers = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> offsets = new HashMap<Integer, Integer>();
		readRoomNumbersAndOffsets(raw, offsets, roomNumbers, namesSectionOffset);
		InventoryObjects inv = readNamesAndCreateInventoryObjects(raw, offsets,
				roomNumbers);
		return inv;
	}

	private static int getNamesSectionOffset(RawByteArray raw) {
		int namesOffset = raw.getNextUInt16AndStep();
		namesOffset += FIRST_ENTRY_OFFSET; // offsets are counted from the start
		return namesOffset;
	}

	private static void readRoomNumbersAndOffsets(RawByteArray raw,
			HashMap<Integer, Integer> offsets,
			HashMap<Integer, Integer> roomNumbers, int namesOffset) {
		int currentItem = 0;
		raw.setStopOffset(namesOffset);
		while (raw.getCurrentOffset() < raw.getStopOffset()) {
			int itemNameOffset = getItemNameSectionOffset(raw);
			int startingRoomNumber = raw.getNextAndStep();
			roomNumbers.put(currentItem, startingRoomNumber);
			offsets.put(currentItem, itemNameOffset);
			currentItem++;
		}
	}

	private static int getItemNameSectionOffset(RawByteArray raw) {
		int namesOffset = raw.getNextUInt16AndStep();
		namesOffset += FIRST_ENTRY_OFFSET; // offsets are counted from the start
		return namesOffset;
	}

	private static InventoryObjects readNamesAndCreateInventoryObjects(
			RawByteArray raw, HashMap<Integer, Integer> offsets,
			HashMap<Integer, Integer> roomNumbers) {
		int currentItem = 0;
		InventoryObjects inv = new InventoryObjects();
		while (raw.getCurrentOffset() < raw.getSize()) {
			String name = readItemName(raw, offsets, currentItem);
			InventoryObject inventoryObject = new InventoryObject(roomNumbers.get(currentItem),name);
			inv.add(currentItem, inventoryObject);
			currentItem++;
		}
		return inv;
	}

	private static String readItemName(RawByteArray raw,
			HashMap<Integer, Integer> offsets, int currentItem) {
		int nextOffset = getNextOffset(offsets, currentItem);
		String name = readString(raw, nextOffset);
		return name;
	}

	private static String readString(RawByteArray raw, int nextOffset) {
		String name = "";
		while (raw.getCurrentOffset() < nextOffset) {
			int currentByte = raw.getNextAndStep();
			if (currentByte == -1 || currentByte == 0) {
				break;
			} else {
				name += new Character((char) currentByte);
			}
		}
		return name;
	}

	private static int getNextOffset(HashMap<Integer, Integer> offsets,
			int currentItem) {
		int nextOffset;
		try {
			nextOffset = offsets.get(currentItem + 1);
		} catch (Exception e) {
			nextOffset = Integer.MAX_VALUE;
		}
		return nextOffset;
	}

	public static int decrypt(int encryptedByte, int currentOffset) {
		char token = ENCRYPTION_KEY.charAt(currentOffset
				% ENCRYPTION_KEY.length());
		return encryptedByte ^ token;
	}

}
