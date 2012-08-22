package agijava.main.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import agijava.io.RawByteArray;

public class InventoryObjectsFactory {

	private static String ENCRYPTION_KEY = "Avis Durgan";
	private static int currentOffset;

	public static InventoryObjects createInstance(String path)
			throws IOException {
		currentOffset = 0;
		InventoryObjects inv = new InventoryObjects();
		FileInputStream inputStream = new FileInputStream(path);
		List<Integer> fileContent = new ArrayList<Integer>();
		int nextByte = inputStream.read();
		while (nextByte != -1) {
			int decrp = decrypt(nextByte);
			currentOffset++;
			fileContent.add(decrp);
			nextByte = inputStream.read();
		}
		RawByteArray raw = new RawByteArray(fileContent);
		int offset1 = raw.getNextAndStep();
		int offset2 = raw.getNextAndStep();
		@SuppressWarnings("unused")
		int maxNumber = raw.getNextAndStep();
//		
		int namesOffset = (offset2 << 8) | offset1;
		namesOffset += 3; //offsets are counted from the start of the first entry (offset 3)
		int currentItem = 0;
		HashMap<Integer,InventoryObject> objects = new HashMap<Integer,InventoryObject>();
		HashMap<Integer,Integer> offsets = new HashMap<Integer,Integer>();
		raw.setStopOffset(namesOffset);
		while (raw.getNextOffsetToBeRead() < raw.getStopOffset()) {
			int itemNameOffset1 = raw.getNextAndStep();
			int itemNameOffset2 = raw.getNextAndStep();
			int itemNameOffset = (itemNameOffset2 << 8) | itemNameOffset1;
			itemNameOffset += 3;
			int startingRoomNumber = raw.getNextAndStep();
			InventoryObject inventoryObject = new InventoryObject();
			inventoryObject.setRoomNumber(startingRoomNumber);
			objects.put(currentItem,inventoryObject);
			offsets.put(currentItem,itemNameOffset);
			currentItem++;
		}
		currentItem = 0;
		while (raw.getNextOffsetToBeRead() < raw.getSize()) {
			int nextOffset = 0;
			try {
				nextOffset = offsets.get(currentItem+1);
			} catch (Exception e) {
				nextOffset = Integer.MAX_VALUE;
			}
			String name = "";
			while (raw.getNextOffsetToBeRead() < nextOffset) {
				int currentByte = raw.getNextAndStep();
				if (currentByte == -1 || currentByte == 0) {
					break;
				}
				name += new Character((char) currentByte );
			}
			InventoryObject inventoryObject = objects.get(currentItem);
			inventoryObject.setName(name);
			inv.add(currentItem,inventoryObject);
			currentItem++;
		}
		return inv;
	}

//	private static int readNextByte(FileInputStream inputStream)
//			throws IOException {
//		int encryptedByte = inputStream.read() & 0xff;
//		int decrupted = decrypt(encryptedByte);
//		currentOffset++;
//		return decrupted;
//	}

	private static int decrypt(int encryptedByte) {
		char token = ENCRYPTION_KEY.charAt(currentOffset 
				% ENCRYPTION_KEY.length());
		return encryptedByte ^ token;
	}

}
