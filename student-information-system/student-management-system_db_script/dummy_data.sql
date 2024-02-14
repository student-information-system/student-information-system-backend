INSERT INTO teachers (first_name, last_name, email, password) VALUES ('admin', 'admin', 'admin', '$2y$10$JuHIzGvR9Fv8GOELA9xPgOMjwS5iqvP4uWKd4M4vkIoace.Aa0/W.'); /*-password=123-*/
INSERT INTO students (first_name, last_name, school_number, password) VALUES
('Ali', 'Yılmaz', 'S001', 'ali123'),
('Ayşe', 'Kaya', 'S002', 'ayse123'),
('Mehmet', 'Öztürk', 'S003', 'mehmet123');

-- Dersleri Ekleme
INSERT INTO courses (course_name, course_code, teacher_id) VALUES
('Matematik', 'MAT101', 1),
('Fizik', 'FIZ102', 1),
('Kimya', 'KIM103', 1);

-- Notları Ekleme
INSERT INTO grades (student_id, course_id, grade, date) VALUES
(1, 1, 85, '2023-09-01'),
(2, 1, 90, '2023-09-01'),
(1, 2, 70, '2023-09-01'),
(2, 2, 100, '2023-09-01'),
(3, 3, 95, '2023-09-01');

-- Öğretmenin derslerini güncelleme
UPDATE courses SET teacher_id = (SELECT teacher_id FROM teachers WHERE email = 'ogretmen@email.com') WHERE course_id IN (1, 2, 3);

-- Öğretmen ve öğrenci ilişkisini ekleme
INSERT INTO student_teacher (student_id, teacher_id) VALUES
(1, (SELECT teacher_id FROM teachers WHERE email = 'ogretmen@email.com')),
(2, (SELECT teacher_id FROM teachers WHERE email = 'ogretmen@email.com')),
(3, (SELECT teacher_id FROM teachers WHERE email = 'ogretmen@email.com'));