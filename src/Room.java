public class Room {

    private int roomID;
    private int capacity;
    private String roomNumber;
    private String department;
    private String building;

    /** Creates a new Room with capacity and room number
     *
     * @param cap = capacity
     * @param roomNum = room number
     * @param building = name of building
     * @param department = name of department
     */
    public Room(int roomID, int cap, String roomNum, String building, String department){
        this.roomID = roomID;
        capacity = cap;
        roomNumber = roomNum;
        this.building = building;
        this.department = department;
    }

    public int getCapacity(){
        return capacity;
    }

    public String getRoomNumber(){
        return roomNumber;
    }

    public int getRoomID(){
        return roomID;
    }

    public String getBuilding(){
        return building;
    }

    public String getDepartment() {
        return department;
    }


}
