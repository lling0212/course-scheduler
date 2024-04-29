public class Room {
    private int capacity;
    private int roomNumber;

    private String building;

    /** Creates a new Room with capacity and room number
     *
     * @param cap = capacity
     * @param roomNum = room number
     */
    public Room(int cap, int roomNum){
        capacity = cap;
        roomNumber = roomNum;
    }


    public int getCapacity(){
        return capacity;
    }
    public void setCapacity(int cap){
        capacity = cap;
    }

    public void setRoomNum(int num){
        roomNumber = num;
    }

    public int getRoomNumber(){
        return roomNumber;
    }



}
