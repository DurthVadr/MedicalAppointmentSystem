CREATE DATABASE IF NOT EXISTS cs202_project;
USE cs202_project;

SET foreign_key_checks = 0;



DROP TABLE IF EXISTS RoomAssignment;
DROP TABLE IF EXISTS DoctorSchedule;
DROP TABLE IF EXISTS Nurse;
DROP TABLE IF EXISTS Manager;
DROP TABLE IF EXISTS Appointment;
DROP TABLE IF EXISTS Patient;
DROP TABLE IF EXISTS Room;
DROP TABLE IF EXISTS Doctor;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS UserTypeLookup;  -- Added this line

CREATE TABLE UserTypeLookup (  -- maps the usertype
    UserTypeCode INT NOT NULL PRIMARY KEY,
    UserType VARCHAR(50) NOT NULL
);



CREATE TABLE User (
    UserID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255),
    password_hash VARCHAR(255) NOT NULL,
    password_salt VARCHAR(255) NOT NULL,
    UserName VARCHAR(255),
    userTypeCode INT,  
    FOREIGN KEY (userTypeCode) REFERENCES UserTypeLookup(UserTypeCode)  -- Added this line
);

CREATE TABLE Patient (
    PatientID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    DOB VARCHAR(255) NOT NULL,
    PatientName VARCHAR(255),

    FOREIGN KEY (PatientID) REFERENCES User(UserID)
);

CREATE TABLE Manager (
    ManagerID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ManagerName VARCHAR(255),

    FOREIGN KEY (ManagerID) REFERENCES User(UserID)
);

CREATE TABLE Nurse (
    NurseID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	NurseName VARCHAR(255),

    FOREIGN KEY (NurseID) REFERENCES User(UserID)
);

CREATE TABLE Doctor (
    DoctorID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    DoctorName VARCHAR(255),
    expertise VARCHAR(100) NOT NULL,
   
    FOREIGN KEY (DoctorID) REFERENCES User(UserID)
);

CREATE TABLE Appointment (
    AppointmentID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    StartTime DATETIME NOT NULL,
    EndTime DATETIME,
    Status_ VARCHAR(50),
    PatientID INT,
    NurseID INT,
    DoctorID INT,
    RoomID INT,
    FOREIGN KEY (PatientID) REFERENCES Patient(PatientID),
    FOREIGN KEY (DoctorID) REFERENCES Doctor(DoctorID)  ON DELETE CASCADE,
    FOREIGN KEY (NurseID) REFERENCES Nurse(NurseID),
    FOREIGN KEY (RoomID) REFERENCES Room(RoomID)  ON DELETE RESTRICT
);

CREATE TABLE Room (
    RoomID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    RoomType VARCHAR(50),
    RoomName VARCHAR(255),
    Availability BIT NOT NULL,  -- BOOLEAN
    FOREIGN KEY (RoomID) REFERENCES Appointment(AppointmentID)
);




SET foreign_key_checks = 1;