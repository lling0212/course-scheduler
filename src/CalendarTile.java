public class CalendarTile {

    private Room room;
    private Course course;
    private TimeSlot timeSlot;


    public CalendarTile(Room room, Course course, TimeSlot time){
        this.room = room;
        this.course = course;
        timeSlot = time;
    }

//    public Room getRoom(){
//        return room;
//    }
//    public void setRoom(Room r){
//        room = r;
//    }

    public Course getCourse(){
        return course;
    }
//    public void setCourse(Course c){
//        course = c;
//    }

    public TimeSlot getTimeSlot(){
        return timeSlot;
    }
//    public void setTimeSlot(TimeSlot ts){
//        timeSlot = ts;
//    }


    /**
     * TODO
     * Change this based on CLI design
     * @return the calendar tile in formatted string representation
     */
    public String toString(){
        String blank = "";
        blank += course.toString();
        blank += "Location: ";
        blank += room.getRoomNumber();
        blank += ", ";
        blank += room.getBuilding();
        blank += "\n";
        return blank;
    }


}
