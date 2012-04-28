package agijava.main;

import agijava.main.impl.InventoryObject;

public interface IInventoryObjects {

	boolean playerHas(int itemNo);

	InventoryObject get(int inventoryObjectNo);

}
