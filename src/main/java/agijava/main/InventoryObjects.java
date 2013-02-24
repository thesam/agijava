package agijava.main;

import java.util.HashMap;

public class InventoryObjects {

	private HashMap<Integer, InventoryObject> objects;

	public InventoryObjects() {
		this.objects = new HashMap<Integer,InventoryObject>();
	}
	
	public InventoryObject get(int inventoryObjectNo) {
		return objects.get(inventoryObjectNo);
	}

	public void add(Integer number, InventoryObject inventoryObject) {
		objects.put(number,inventoryObject);
	}

	public boolean playerHas(int itemNo) {
		InventoryObject inventoryObject = objects.get(itemNo);
		if (inventoryObject != null) {
			return inventoryObject.getRoomNumber() == GameEngine.PLAYER_INVENTORY_ROOM;
		} else {
			return false;
		}
	}

}
