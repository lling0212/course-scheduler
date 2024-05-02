import java.util.HashMap;

public class Rooms {

    private HashMap<String, Room> departmentToRoom = new HashMap<String, Room>();

    private HashMap<Integer, Room> idToRoom = new HashMap<Integer, Room>();
    public Rooms(){
    }

    public void setDepartment(String department, Room room){
        departmentToRoom.put(department, room);
    }
    public Room getDepartmentRoom(String department){
        return departmentToRoom.get(department);
    }
    public void setRoomID(int id, Room room){
        idToRoom.put(id, room);
    }
    public Room getRoomById(int id){
        return idToRoom.get(id);
    }







}
