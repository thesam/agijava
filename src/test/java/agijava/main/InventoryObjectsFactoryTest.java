package agijava.main;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import agijava.io.RawByteArray;

public class InventoryObjectsFactoryTest {
	@Test
	public void canCreateEmptyInventoryObjects() throws Exception {
		List<Integer> bytes = new ArrayList<Integer>();
		bytes.add(0);
		bytes.add(0);
		bytes.add(0);
		RawByteArray raw = new RawByteArray(bytes);
		InventoryObjects inventoryObjects = InventoryObjectsFactory.createFromByteArray(raw);
		assertNotNull(inventoryObjects);
	}
	
	@Test
	public void canParseOneInventoryObject() throws Exception {
		List<Integer> bytes = new ArrayList<Integer>();
		bytes.add(3);
		bytes.add(0); // names section offset
		
		bytes.add(0); // maxNumber
		
		bytes.add(3); // first item name offset
		bytes.add(0);
		
		bytes.add(10); // room number
		
		bytes.add((int)'a'); // first item name
		bytes.add(0); // null terminator
		RawByteArray raw = new RawByteArray(bytes);
		InventoryObjects inventoryObjects = InventoryObjectsFactory.createFromByteArray(raw);
		InventoryObject inventoryObject = inventoryObjects.get(0);
		assertEquals("a",inventoryObject.getName());
		assertEquals(10,inventoryObject.getRoomNumber());
	}
	
	@Test
	public void canDecryptBytes() throws Exception {
		assertEquals(0,InventoryObjectsFactory.decrypt('A', 0));
		assertEquals(55,InventoryObjectsFactory.decrypt('A', 1));
		assertEquals(40,InventoryObjectsFactory.decrypt('A', 2));
		assertEquals(50,InventoryObjectsFactory.decrypt('A', 3));
		assertEquals(97,InventoryObjectsFactory.decrypt('A', 4));
		assertEquals(5,InventoryObjectsFactory.decrypt('A', 5));
		assertEquals(52,InventoryObjectsFactory.decrypt('A', 6));
		assertEquals(51,InventoryObjectsFactory.decrypt('A', 7));
		assertEquals(38,InventoryObjectsFactory.decrypt('A', 8));
		assertEquals(32,InventoryObjectsFactory.decrypt('A', 9));
		assertEquals(47,InventoryObjectsFactory.decrypt('A', 10));
		assertEquals(0,InventoryObjectsFactory.decrypt('A', 11));
	}
}
