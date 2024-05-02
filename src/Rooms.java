import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rooms {

    private Map<String, List<Room>> departmentToRoom = new HashMap<>();

    private Map<Integer, Room> idToRoom = new HashMap<>();
    public Rooms(){
    }

    public void addRoom(Room room) {
        String dep = room.getDepartment();
        int id = room.getRoomID();
        if (!departmentToRoom.containsKey(dep)) {
            List<Room> list = new ArrayList<>();
            list.add(room);
            departmentToRoom.put(dep, list);
        } else {
            departmentToRoom.get(dep).add(room);
        }

        idToRoom.put(id, room);
    }

    public List<Room> getRoomsByDepartment(String department) {
        return departmentToRoom.get(department);
    }

    public Room getRoomById(int id) {
        return idToRoom.get(id);
    }

}
