import java.util.List;
import java.util.Objects;

public class Course {
    //test
    private String courseID;
    private String department;
    private int capacity;
    private String instructorName;
    private int room;
    private String building;

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
        if (departmentRooms != null) {
            for (int i = 0; i < departmentRooms.size(); i++) {
                if (capacity <= departmentRooms.get(i).getCapacity()) {
                    this.room = departmentRooms.get(i).getRoomID();
                    this.building = departmentRooms.get(i).getBuilding();
                    break;
                }
            }
        }
    }
    public String getBuilding(){
        return building;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Course course = (Course) o;
        return capacity == course.capacity && room == course.room && Objects.equals(courseID, course.courseID) && Objects.equals(department, course.department) && Objects.equals(instructorName, course.instructorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseID, department, capacity, instructorName, room);
    }

    @Override
    public String toString() {
        return "CourseID: " + courseID + "\n"
                + "Department: " + department + "\n"
                + "Instructor: " + instructorName + "\n";
    }

}
