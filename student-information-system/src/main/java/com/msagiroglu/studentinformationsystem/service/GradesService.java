package com.msagiroglu.studentinformationsystem.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msagiroglu.studentinformationsystem.dto.GradesDto;
import com.msagiroglu.studentinformationsystem.model.Courses;
import com.msagiroglu.studentinformationsystem.model.Grades;
import com.msagiroglu.studentinformationsystem.model.Students;
import com.msagiroglu.studentinformationsystem.repository.CoursesRepository;
import com.msagiroglu.studentinformationsystem.repository.GradesRepository;
import com.msagiroglu.studentinformationsystem.repository.StudentsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GradesService {
	private final GradesRepository gradesRepository;
	private final StudentsRepository studentsRepository;
	private final CoursesRepository coursesRepository;

	public List<Grades> findAllGrades() {
		List<Grades> grades = gradesRepository.findAll();
		grades.forEach(grade -> {
			Hibernate.initialize(grade.getStudent());
		});
		return grades;
	}

	@Transactional(readOnly = true)
	public Optional<Grades> findGradeById(Long id) {
		return gradesRepository.findById(id);
	}

	@Transactional
	public Grades saveGrade(Grades grade, Long studentId, Long courseId) {
		Optional<Students> student = studentsRepository.findById(studentId);
		Optional<Courses> course = coursesRepository.findById(courseId);

		if (student.isPresent() && course.isPresent()) {
			grade.setStudent(student.get());
			grade.setCourse(course.get());
			return gradesRepository.save(grade);
		} else {
			throw new RuntimeException("Student or course not found");
		}
	}

	@Transactional
	public void deleteGrade(Long id) {
		gradesRepository.deleteById(id);
	}

	@Transactional
	public Grades updateGrade(GradesDto gradeDto) {
		Optional<Grades> existingGradeOpt = gradesRepository.findById(gradeDto.getGrade_id());
		if (!existingGradeOpt.isPresent()) {
			throw new RuntimeException("Grade not found with id: " + gradeDto.getGrade_id());
		}
		Grades existingGrade = existingGradeOpt.get();
		existingGrade.setGrade(gradeDto.getGrade());
		existingGrade.setDate(gradeDto.getDate());
		return gradesRepository.save(existingGrade);
	}

	@Transactional(readOnly = true)
	public List<Grades> findGradesByTeacherId(Long teacherId) {
		return gradesRepository.findByTeacherId(teacherId);
	}

}