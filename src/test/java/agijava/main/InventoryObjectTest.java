package agijava.main;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import agijava.main.impl.InventoryObject;

public class InventoryObjectTest {
	private InventoryObject inventoryObject;

	@Before
	public void setup() throws Exception {
		inventoryObject = new InventoryObject(100,"foo");
	}
	
	@Test
	public void canStoreRoomNumber() throws Exception {
		assertEquals(100,inventoryObject.getRoomNumber());
	}
	
	@Test
	public void canSetName() throws Exception {
		assertEquals("foo",inventoryObject.getName());
	}
}
