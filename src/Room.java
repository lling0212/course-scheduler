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
//    public void setCapacity(int cap){
//        capacity = cap;
//    }

//    public void setRoomNum(String num){
//        roomNumber = num;
//    }
    public String getRoomNumber(){
        return roomNumber;
    }

//    public void setRoomID(int num){
//        roomID = num;
//    }
    public int getRoomID(){
        return roomID;
    }

//    public void setBuilding(String num){
//        building = num;
//    }
    public String getBuilding(){
        return building;
    }

    public String getDepartment() {
        return department;
    }
//    public void setDepartment(String department) {
//        this.department = department;
//    }


}
