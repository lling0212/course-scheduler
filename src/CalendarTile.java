public class CalendarTile {

    private Room room;
    private Course course;
    private TimeSlot timeSlot;


    public CalendarTile(Room room, Course course, TimeSlot time){
        this.room = room;
        this.course = course;
        timeSlot = time;
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

    public TimeSlot getTimeSlot(){
        return timeSlot;
    }
    public void setTimeSlot(TimeSlot ts){
        timeSlot = ts;
    }

    public String toString(){
        String blank = "";
        blank += course.getCourseID();
        blank += ", ";
        blank += room.getRoomNumber();
        blank += ", ";
        blank += timeSlot.getStartTime();
        blank += ", ";

        return blank;
    }



}
