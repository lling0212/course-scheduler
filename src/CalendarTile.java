public class CalendarTile {

    private Room room;
    private Course course;
    private TimeSlot timeSlot;


    public CalendarTile(Room room, Course course, TimeSlot time){
        this.room = room;
        this.course = course;
        timeSlot = time;
    }

    public Course getCourse(){
        return course;
    }

    public TimeSlot getTimeSlot(){
        return timeSlot;
    }


    /**
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
