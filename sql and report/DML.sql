CREATE DATABASE IF NOT EXISTS cs202_project;
USE cs202_project;

SET SQL_SAFE_UPDATES=0;
SET foreign_key_checks = 0;

DELETE FROM UserTypeLookup;
DELETE FROM User;
DELETE FROM Patient;
DELETE FROM Manager;
DELETE FROM Nurse;
DELETE FROM Doctor;
DELETE FROM Appointment;
DELETE FROM Room;


INSERT INTO UserTypeLookup (UserTypeCode, UserType) VALUES
    (1, 'Manager'),
    (2, 'Doctor'),
    (3, 'Nurse'),
    (4, 'Patient');


INSERT INTO User (email, password_hash, password_salt, UserName, userTypeCode) VALUES
	('admin',SHA2(CONCAT('123456', RAND()), 256), -- Hashing with salt
    RAND(), 'AdminMertcan',1),
    ('manager@example.com',  SHA2(CONCAT('user_password', RAND()), 256), -- Hashing with salt
    RAND(), 'NejatIsler', 1),
    ('doctor@example.com',  SHA2(CONCAT('user_password', RAND()), 256), -- Hashing with salt
    RAND(), 'MehmetOz', 2),
    ('nurse@example.com',  SHA2(CONCAT('u3ser_password', RAND()), 256), -- Hashing with salt
    RAND(), 'MargotRobbie', 3),
    ('patient@example.com',  SHA2(CONCAT('use22r_password', RAND()), 256), -- Hashing with salt
    RAND(), 'RamboOkan', 4),
    ('manager2@example.com',  SHA2(CONCAT('use51r_password', RAND()), 256), -- Hashing with salt
    RAND(),  'RyanGosling', 1),
    ('doctor2@example.com',  SHA2(CONCAT('u3ser_password', RAND()), 256), -- Hashing with salt
    RAND(), 'DoctorDisrespect', 2),
    ('nurse2@example.com',  SHA2(CONCAT('user_password11', RAND()), 256), -- Hashing with salt
    RAND(), 'DuaLipa', 3),
    ('patient2@example.com',  SHA2(CONCAT('user33_password', RAND()), 256), -- Hashing with salt
    RAND(), 'Mertcan', 4),
    ('manager3@example.com', SHA2(CONCAT('user_passwo12rd', RAND()), 256), -- Hashing with salt
    RAND(),  'SteveJobs', 1),
    ('doctor3@example.com',  SHA2(CONCAT('user_password', RAND()), 256), -- Hashing with salt
    RAND(), 'SaglamKafa', 2);


INSERT INTO Patient (PatientName,DOB) VALUES
    ('RamboOkan','1993-08-18'),
    ('Mertcan','1980-04-05');
   


INSERT INTO Manager (ManagerName) VALUES ('NejatIsler'), ('RyanGosling'), ('SteveJobs');
INSERT INTO Nurse (NurseName) VALUES ('MargotRobbie'), ('DuaLipa');
INSERT INTO Doctor (DoctorName, expertise) VALUES ('MehmetOz','Gynecology'), ('DoctorDisrespect','Dermatology'), ('SaglamKafa','Oncology');


INSERT INTO Room (RoomType, RoomName, Availability) VALUES
    ('Standard', 'Room6', 1),
    ('VIP', 'Room7', 1),
    ('Standard', 'Room8', 1),
    ('VIP', 'Room9', 1),
    ('Standard', 'Room10', 1);


INSERT INTO Appointment (StartTime, EndTime, Status_, PatientID, NurseID, DoctorID, RoomID) VALUES
    ('2023-02-05 14:00:00','2023-02-07 14:00:00', 'Scheduled', 3, 1, 3, 3),
    ('2023-02-20 10:45:00','2023-02-28 14:00:00', 'Completed', 4, 2, 1, 4),
    ('2023-03-10 11:30:00','2023-05-05 14:00:00', 'Scheduled', 5, 2, 2, 2),
    ('2023-03-25 13:15:00','2023-04-05 14:00:00', 'Scheduled', 1, 3, 1, 1),
    ('2023-04-08 16:45:00','2023-04-23 14:00:00', 'Scheduled', 2, 1, 2, 5),
    ('2023-04-23 09:30:00','2023-05-05 14:00:00','Completed', 3, 2, 3, 3),
    ('2023-05-15 12:00:00','2023-06-05 14:00:00', 'Scheduled', 4, 3, 1, 2),
    ('2023-05-30 15:15:00','2023-07-05 14:00:00', 'Scheduled', 5, 1, 2, 5),
    ('2023-06-10 08:30:00','2023-06-12 14:00:00', 'Completed', 1, 2, 3, 1),
    ('2023-06-25 11:00:00','2023-06-26 14:00:00', 'Scheduled', 2, 3, 1, 4);
 
 





SET foreign_key_checks = 1;
SET SQL_SAFE_UPDATES=1;