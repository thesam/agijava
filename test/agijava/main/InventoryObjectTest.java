package agijava.main;

import static org.junit.Assert.*;

import org.junit.Test;

import agijava.main.impl.InventoryObject;

public class InventoryObjectTest {
	private InventoryObject inventoryObject;

	@Test
	public void anInventoryObject() throws Exception {
		inventoryObject = new InventoryObject();
	}
	
	@Test
	public void canStoreRoomNumber() throws Exception {
		anInventoryObject();
		inventoryObject.setRoomNumber(100);
		assertEquals(100,inventoryObject.getRoomNumber());
	}
	
	@Test
	public void canSetName() throws Exception {
		anInventoryObject();
		inventoryObject.setName("foo");
		assertEquals("foo",inventoryObject.getName());
	}
}
