package agijava.main;

import static org.junit.Assert.*;

import org.junit.Test;

import agijava.main.impl.InventoryObjects;

public class InventoryObjectsTest {
	@Test
	public void canBeCreated() throws Exception {
		InventoryObjects inventoryObjects = new InventoryObjects();
		assertNotNull(inventoryObjects);
	}
}
