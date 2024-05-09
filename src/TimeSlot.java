public class TimeSlot {

    int id;
    String startTime;
    String endTime;
    String daysInWeek;

    public TimeSlot(int id, String start, String end, String days) {
        this.id = id;
        startTime = start;
        endTime = end;
        daysInWeek = days;
    }

    public String getStartTime(){
        return startTime;
    }

    public String getDaysInWeek() { return daysInWeek; }

}
