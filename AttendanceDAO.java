import java.io.*;
import java.util.*;

// DAO class for file-based CRUD operations //Vanshika
class AttendanceDAO {
    private final File file = new File("data/attendance.txt");

    public AttendanceDAO() {

    // Create directory if it doesn't exist
    
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    // Add attendance record
    public void addAttendance(Attendance a) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(a.toString());
            writer.newLine();
        }
    }
// Retrieve all records from file

    // Get all attendance records
    //throws IOException
    
    public List<Attendance> getAllAttendances() throws IOException {
        List<Attendance> list = new ArrayList<>();
        if (!file.exists()) return list;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String date = parts[2];
                    String status = parts[3];
                    list.add(new Attendance(id, name, date, status));
                }
            }
        }
        return list;
    }

    // Update attendance record by ID //Anandee
   
    public boolean updateAttendance(Attendance updated) throws IOException {
        List<Attendance> list = getAllAttendances();
        boolean updatedFlag = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Attendance a : list) {
                if (a.getId() == updated.getId()) {
                    writer.write(updated.toString());
                    updatedFlag = true;
                } else {
                    writer.write(a.toString());
                }
                writer.newLine();
            }
        }
        return updatedFlag;
    }

   
 // Delete attendance record by ID //Angel
   
    public boolean deleteAttendance(int id) throws IOException {
        List<Attendance> list = getAllAttendances();
        boolean deleted = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Attendance a : list) {
                if (a.getId() == id) {
                    deleted = true;
                    continue;
                }
                writer.write(a.toString());
                writer.newLine();
            }
        }
        return deleted;
    }
}
