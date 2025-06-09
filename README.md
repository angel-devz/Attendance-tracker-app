import java.io.*;
import java.util.*;

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

    public int getId() { return id; }
    public String getStudentName() { return studentName; }
    public String getDate() { return date; }
    public String getStatus() { return status; }

    public void setStudentName(String studentName) { this.studentName = studentName; }
    public void setDate(String date) { this.date = date; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return id + "|" + studentName + "|" + date + "|" + status;
    }
}

// DAO class for file-based CRUD operations //Vanshika


class AttendanceDAO {
    private final File file = new File("data/attendance.txt");

    public AttendanceDAO() {
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

    // Get all attendance records
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

public class AttendanceTrackerApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final AttendanceDAO dao = new AttendanceDAO();

    public static void main(String[] args) {
        System.out.println("=== Welcome to Attendance Tracker ===");
        boolean running = true;

        while (running) {
            printMenu();
            int choice = getIntInput("Enter your choice: ");
            switch (choice) {
                case 1 -> addAttendance();
                case 2 -> listAttendances();
                case 3 -> updateAttendance();
                case 4 -> deleteAttendance();
                case 5 -> running = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        System.out.println("Thank you for using Attendance Tracker. Goodbye!");
    }

    private static void printMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Add Attendance");
        System.out.println("2. List Attendances");
        System.out.println("3. Update Attendance");
        System.out.println("4. Delete Attendance");
        System.out.println("5. Exit");
    }

    private static void addAttendance() {
        System.out.println("\n-- Add New Attendance --");
        int id = getIntInput("Enter record ID: ");
        String name = getStringInput("Enter student name: ");
        String date = getStringInput("Enter date (yyyy-MM-dd): ");
        String status = getStatusInput("Enter status (Present/Absent): ");

        Attendance a = new Attendance(id, name, date, status);
        try {
            dao.addAttendance(a);
            System.out.println("Attendance added successfully!");
        } catch (IOException e) {
            System.out.println("Error saving attendance: " + e.getMessage());
        }
    }

    private static void listAttendances() {
        System.out.println("\n-- Attendance Records --");
        try {
            List<Attendance> list = dao.getAllAttendances();
            if (list.isEmpty()) {
                System.out.println("No attendance records found.");
                return;
            }
            System.out.printf("%-5s %-20s %-12s %-8s%n", "ID", "Student Name", "Date", "Status");
            System.out.println("-------------------------------------------------");
            for (Attendance a : list) {
                System.out.printf("%-5d %-20s %-12s %-8s%n", a.getId(), a.getStudentName(), a.getDate(), a.getStatus());
            }
        } catch (IOException e) {
            System.out.println("Error reading attendance records: " + e.getMessage());
        }
    }

    private static void updateAttendance() {
        System.out.println("\n-- Update Attendance --");
        int id = getIntInput("Enter ID to update: ");
        String name = getStringInput("Enter new student name: ");
        String date = getStringInput("Enter new date (yyyy-MM-dd): ");
        String status = getStatusInput("Enter new status (Present/Absent): ");

        Attendance updated = new Attendance(id, name, date, status);
        try {
            boolean success = dao.updateAttendance(updated);
            if (success) System.out.println("Attendance updated successfully!");
            else System.out.println("Record with ID " + id + " not found.");
        } catch (IOException e) {
            System.out.println("Error updating attendance: " + e.getMessage());
        }
    }

    private static void deleteAttendance() {
        System.out.println("\n-- Delete Attendance --");
        int id = getIntInput("Enter ID to delete: ");
        try {
            boolean success = dao.deleteAttendance(id);
            if (success) System.out.println("Attendance deleted successfully!");
            else System.out.println("Record with ID " + id + " not found.");
        } catch (IOException e) {
            System.out.println("Error deleting attendance: " + e.getMessage());
        }
    }

    // Input helper methods
    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer, please try again.");
            }
        }
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static String getStatusInput(String prompt) {
        while (true) {
            String status = getStringInput(prompt);
            if (status.equalsIgnoreCase("Present") || status.equalsIgnoreCase("Absent")) {
                return status.substring(0, 1).toUpperCase() + status.substring(1).toLowerCase();
            }
            System.out.println("Invalid status. Enter 'Present' or 'Absent'.");
        }
    }
}

 #1. Attendance Tracker (Console-Based)

A simple Java-based console application that allows users to **track student attendance**, store data in a file, and perform basic CRUD operations (Create, Read, Update, Delete). This project is modular, beginner-friendly, and designed to simulate real-world file-based record management.

---

## Project Overview

This project is built as part of an academic assignment to demonstrate file handling, modular programming, and error management using Java.  
It allows:
- Adding new attendance entries
- Viewing existing records
- Updating or deleting records by ID
- Persistent storage using `.txt` file

---

## Features

- Add attendance record (ID, student name, date, status)
- List all attendance records in tabular form
- Update attendance by ID
- Delete attendance by ID
- Persistent storage in a local `.txt` file
- Input validation and error handling

---

#2. Open in IntelliJ IDEA or any Java IDE

#3. Run AttendanceTrackerApp.java

#4. Use the interactive console menu to:
--Add
--View
--Update
--Delete records

## File Structure

attendance-tracker/
├── data/
│   └── attendance.txt       # Stores attendance records
├── Attendance.java          # Model class for attendance data
├── AttendanceDAO.java       # Handles file-based CRUD operations
├── AttendanceTrackerApp.java# Main app class (user interface)
└── README.md                # Project documentation

## Credits
Divyansha – Attendance model class
Vanshika – File I/O and CRUD logic
Anandee – Update functionality
Angel – Delete functionality

## Folder Structure
--Code files and resources are organized clearly.
--The data/ folder holds the text file used as a database.
--Java files are modularized into model, DAO, and UI logic.

## Status
 --Core feature implementation
 --File I/O handling
 --Robust input validation
 --Proper documentation
 --Console-based working UI
