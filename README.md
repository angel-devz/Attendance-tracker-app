# Attendance Tracker (Console-Based)

A simple Java console application to **track student attendance**. Records are saved to a text file, and you can add, view, update and delete them (CRUD). Built as an academic project to practise file handling, modular programming and error handling in Java.

## Features

- Add an attendance record (ID, student name, date, status)
- List all records in a table
- Update or delete a record by ID
- Data is saved in `data/attendance.txt`, so it stays after you close the app
- Input validation (numbers, `Present` / `Absent`)

## How to Run

You need Java 14 or newer.

```bash
git clone https://github.com/angel-devz/Attendance-tracker-app.git
cd Attendance-tracker-app
javac *.java
java AttendanceTrackerApp
```

Or open the folder in IntelliJ IDEA / VS Code and run `AttendanceTrackerApp.java`.

## Example

```
=== Welcome to Attendance Tracker ===

--- Menu ---
1. Add Attendance
2. List Attendances
3. Update Attendance
4. Delete Attendance
5. Exit
Enter your choice: 2

-- Attendance Records --
ID    Student Name         Date         Status
-------------------------------------------------
1     Rahul                2026-11-20   Present
```

## Project Structure

```
Attendance-tracker-app/
├── Attendance.java            # Model class for one attendance record
├── AttendanceDAO.java         # File-based CRUD operations
├── AttendanceTrackerApp.java  # Main class with the console menu
└── data/attendance.txt        # Created automatically on first run
```

## Team

| Member    | Contribution                 |
|-----------|------------------------------|
| Divyansha | Attendance model class       |
| Vanshika  | File I/O and CRUD logic      |
| Anandee   | Update functionality         |
| Angel     | Delete functionality         |
