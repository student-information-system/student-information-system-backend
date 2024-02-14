CREATE TABLE IF NOT EXISTS teachers (
    teacher_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS student_teacher (
    student_id INT,
    teacher_id INT,
    PRIMARY KEY (student_id, teacher_id),
    CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES students (student_id),
    CONSTRAINT fk_teacher FOREIGN KEY (teacher_id) REFERENCES teachers (teacher_id)
);

ALTER TABLE courses
ADD COLUMN teacher_id INT,
ADD CONSTRAINT fk_teacher_course FOREIGN KEY (teacher_id) REFERENCES teachers (teacher_id);