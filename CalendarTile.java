public class CalendarTile {

    private Room room;
    private Course course;

//    private TimeSlot timeSlot;

    public CalendarTile(Room room, Course course){
        this.room = room;
        this.course = course;
    }

    public Room getRoom(){
        return room;
    }
    public void setRoom(Room r){
        room = r;
    }

    public Course getCourse(){
        return course;
    }
    public void setCourse(Course c){
        course = c;
    }




}
