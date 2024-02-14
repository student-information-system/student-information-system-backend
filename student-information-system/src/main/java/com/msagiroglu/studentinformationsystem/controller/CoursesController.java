package com.msagiroglu.studentinformationsystem.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msagiroglu.studentinformationsystem.dto.CoursesDto;
import com.msagiroglu.studentinformationsystem.mapper.CoursesMapper;
import com.msagiroglu.studentinformationsystem.model.Courses;
import com.msagiroglu.studentinformationsystem.service.CourseService;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
@RestController
@RequestMapping(value = "/courses")
public class CoursesController {

	private final CourseService courseService;

	@GetMapping
	public ResponseEntity<List<CoursesDto>> getAllCourses() {
		List<Courses> courses = courseService.findAllCourses();
		List<CoursesDto> coursesDtos = courses.stream().map(CoursesMapper::toCoursesDto).collect(Collectors.toList());
		return ResponseEntity.ok(coursesDtos);
	}

	@PostMapping
	public ResponseEntity<CoursesDto> createCourse(@RequestBody CoursesDto coursesDto) {
		Courses course = CoursesMapper.fromCoursesDto(coursesDto);
		Courses savedCourse = courseService.saveCourse(course);
		return new ResponseEntity<>(CoursesMapper.toCoursesDto(savedCourse), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CoursesDto> getCourseById(@PathVariable Long id) {
		return courseService.findCourseById(id).map(CoursesMapper::toCoursesDto).map(dto -> ResponseEntity.ok(dto))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
		if (courseService.findCourseById(id).isPresent()) {
			courseService.deleteCourse(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
