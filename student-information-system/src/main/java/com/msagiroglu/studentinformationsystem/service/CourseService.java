package com.msagiroglu.studentinformationsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msagiroglu.studentinformationsystem.model.Courses;
import com.msagiroglu.studentinformationsystem.repository.CoursesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseService {
	private final CoursesRepository coursesRepository;

	@Transactional(readOnly = true)
	public List<Courses> findAllCourses() {
		return coursesRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Courses> findCourseById(Long id) {
		return coursesRepository.findById(id);
	}

	@Transactional
	public Courses saveCourse(Courses course) {
		return coursesRepository.save(course);
	}

	@Transactional
	public void deleteCourse(Long id) {
		coursesRepository.deleteById(id);
	}
}
