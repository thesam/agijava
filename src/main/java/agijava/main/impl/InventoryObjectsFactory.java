package agijava.main.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

	private static InventoryObjects createFromByteArray(
			RawByteArray raw) {
		int namesOffset = raw.getNextUInt16AndStep();
		raw.getNextAndStep(); // max number (discarded)
		namesOffset += FIRST_ENTRY_OFFSET; //offsets are counted from the start of the first entry (offset 3)
		HashMap<Integer,Integer> roomNumbers= new HashMap<Integer,Integer>();
		HashMap<Integer,Integer> offsets = new HashMap<Integer,Integer>();
		raw.setStopOffset(namesOffset);
		readRoomNumbersAndOffsets(raw, offsets,roomNumbers);
		InventoryObjects inv = createInventoryObjects(raw, offsets, roomNumbers);
		return inv;
	}

	private static InventoryObjects createInventoryObjects(RawByteArray raw,
			HashMap<Integer, Integer> offsets, HashMap<Integer, Integer> roomNumbers) {
		int currentItem = 0;
		InventoryObjects inv = new InventoryObjects();
		while (raw.getNextOffsetToBeRead() < raw.getSize()) {
			String name = readItemName(raw, offsets, currentItem);
			InventoryObject inventoryObject = new InventoryObject();
			inventoryObject.setRoomNumber(roomNumbers.get(currentItem));
			inventoryObject.setName(name);
			inv.add(currentItem,inventoryObject);
			currentItem++;
		}
		return inv;
	}

	private static String readItemName(RawByteArray raw,
			HashMap<Integer, Integer> offsets, int currentItem) {
		int nextOffset;
		try {
			nextOffset = offsets.get(currentItem+1);
		} catch (Exception e) {
			nextOffset = Integer.MAX_VALUE;
		}
		String name = "";
		while (raw.getNextOffsetToBeRead() < nextOffset) {
			int currentByte = raw.getNextAndStep();
			if (currentByte != -1 && currentByte != 0) {
				name += new Character((char) currentByte );
			}
		}
		return name;
	}

	private static void readRoomNumbersAndOffsets(RawByteArray raw,
			HashMap<Integer, Integer> offsets, HashMap<Integer, Integer> roomNumbers) {
		int currentItem = 0;
		while (raw.getNextOffsetToBeRead() < raw.getStopOffset()) {
			int itemNameOffset = raw.getNextUInt16AndStep();
			itemNameOffset += FIRST_ENTRY_OFFSET;
			int startingRoomNumber = raw.getNextAndStep();
			roomNumbers.put(currentItem,startingRoomNumber);
			offsets.put(currentItem,itemNameOffset);
			currentItem++;
		}
	}

	private static RawByteArray readAndDecryptFile(String path)
			throws IOException {
		FileInputStream inputStream = new FileInputStream(path);
		List<Integer> fileContent = new ArrayList<Integer>();
		int nextByte = inputStream.read();
		int currentOffset = 0;
		while (nextByte != -1) {
			int decrp = decrypt(nextByte,currentOffset);
			currentOffset++;
			fileContent.add(decrp);
			nextByte = inputStream.read();
		}
		inputStream.close();
		RawByteArray raw = new RawByteArray(fileContent);
		return raw;
	}

	private static int decrypt(int encryptedByte, int currentOffset) {
		char token = ENCRYPTION_KEY.charAt(currentOffset 
				% ENCRYPTION_KEY.length());
		return encryptedByte ^ token;
	}

}
