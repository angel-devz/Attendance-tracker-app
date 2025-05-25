This is a simple console-based Attendance Tracker application built using Java. It allows users to manage student attendance records through a clean and interactive command-line interface.

FEATURES:

- Add attendance records (ID, name, date, status)
- View all attendance records
- Update existing attendance records
- Delete attendance records by ID
- File-based data persistence (stored in data/attendance.txt)
- Input validation for clean data entry

PROJECT STRUCTURE:

- Attendance.java – Model class representing an attendance record
- AttendanceDAO.java – Handles file-based CRUD operations
- AttendanceTrackerApp.java – Main application with menu-driven interface

  HOW IT WORKS:

- Add Attendance: Enter student ID, name, date (yyyy-MM-dd format), and status (Present or Absent)
- List Attendance: View all saved attendance records in a formatted table
- Update Attendance: Modify details of a record by entering its ID
- Delete Attendance: Remove an attendance record by providing its ID
- Exit: Close the application
