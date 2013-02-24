package agijava.main.impl;

public class InventoryObject {

	private int roomNumber;
	private final String name;

	public InventoryObject(int roomNumber, String name) {
		this.roomNumber = roomNumber;
		this.name = name;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public String getName() {
		return name;
	}

	public void setRoomNumber(int playerInventoryRoom) {
		this.roomNumber = playerInventoryRoom;
	}

}
