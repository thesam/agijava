package agijava.main;

import static org.junit.Assert.*;

import org.junit.Test;

public class InventoryObjectsTest {
	@Test
	public void canBeCreated() throws Exception {
		InventoryObjects inventoryObjects = new InventoryObjects();
		assertNotNull(inventoryObjects);
	}
}
