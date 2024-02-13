--CREATE DATABASE student_information_system;

-- Students Table
CREATE TABLE IF NOT EXISTS students (
    student_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50)
);

-- Courses Table
CREATE TABLE IF NOT EXISTS courses (
    course_id SERIAL PRIMARY KEY,
    course_name VARCHAR(100),
    course_code VARCHAR(20)
);

-- Grades Table
CREATE TABLE IF NOT EXISTS grades (
    grade_id SERIAL PRIMARY KEY,
    student_id INT,
    course_id INT,
    grade INT,
    date DATE,
    CONSTRAINT fk_student
        FOREIGN KEY (student_id) 
        REFERENCES students (student_id),
    CONSTRAINT fk_course
        FOREIGN KEY (course_id)
        REFERENCES courses (course_id)
);