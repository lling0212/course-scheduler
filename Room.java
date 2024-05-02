public class Room {
    private int capacity;
    private int roomID;
    private String roomNumber;

    private String building;

    /** Creates a new Room with capacity and room number
     *
     * @param cap = capacity
     * @param roomNum = room number
     */
    public Room(int cap, String roomNum, String building){
        capacity = cap;
        roomNumber = roomNum;
        this.building = building;
    }


    public int getCapacity(){
        return capacity;
    }
    public void setCapacity(int cap){
        capacity = cap;
    }

    public void setRoomNum(String num){
        roomNumber = num;
    }

    public String getRoomNumber(){
        return roomNumber;
    }

    public void setRoomID(int num){
        roomID = num;
    }

    public int getRoomID(){
        return roomID;
    }

    public void setBuilding(String num){
        building = num;
    }

    public String getBuilding(){
        return building;
    }




}
