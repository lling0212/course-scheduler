import java.util.List;

public class Course {
    private String courseID;
    private String department;
    private int capacity;
    private String instructorName;
    private int room;

    /**
     * Creates a new course object and assigns it to a room in its department
     *
     * @param courseID
     * @param department
     * @param capacity
     * @param instructorName
     * @param rooms = all available rooms
     */
    public Course(String courseID, String department, int capacity, String instructorName, Rooms rooms){
        this.courseID = courseID;
        this.department = department;
        this.capacity = capacity;
        this.instructorName = instructorName;
        this.room = -1; // to be overwritten in for loop below

        List<Room> departmentRooms = rooms.getRoomsByDepartment(department);

        // Assume rooms are sorted by capacity from small to large
        // Assume all courses can be matched to a room
        for (int i = 0; i < departmentRooms.size(); i++) {
            if (capacity <= departmentRooms.get(i).getCapacity()) {
                this.room = departmentRooms.get(i).getRoomID();
                break;
            }
        }

    }

    public String getCourseID(){
        return courseID;
    }
    public void setCourseID(String id){
        courseID = id;
    }

    public String getDepartment(){
        return department;
    }
    public void setDepartment(String dep){
        department = dep;
    }

    public int getCapacity(){
        return capacity;
    }
    public void setCapactiy(int cap){
        capacity = cap;
    }

    public String getInstructor(){
        return instructorName;
    }
    public void setInstructor(String name){
        instructorName = name;
    }

    public int getRoom() {return room;}
    public void setRoom(int r) { this.room = r; }

}
