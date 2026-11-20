// Model class for Attendance record //Divyansha

class Attendance {
    private int id;
    private String studentName;
    private String date;      // yyyy-MM-dd
    private String status;    // Present or Absent

    public Attendance(int id, String studentName, String date, String status) {
        this.id = id;
        this.studentName = studentName;
        this.date = date;
        this.status = status;
    }
//getters

    public int getId() { return id; }
    public String getStudentName() { return studentName; }
    public String getDate() { return date; }
    public String getStatus() { return status; }
    
//setters

    public void setStudentName(String studentName) { this.studentName = studentName; }
    public void setDate(String date) { this.date = date; }
    public void setStatus(String status) { this.status = status; }
    
// Returns a formatted string to store in file

    @Override
    public String toString() {
        return id + "|" + studentName + "|" + date + "|" + status;
    }
}
