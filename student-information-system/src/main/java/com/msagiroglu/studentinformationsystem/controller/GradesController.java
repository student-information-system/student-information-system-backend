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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msagiroglu.studentinformationsystem.dto.GradesDto;
import com.msagiroglu.studentinformationsystem.mapper.GradesMapper;
import com.msagiroglu.studentinformationsystem.model.Grades;
import com.msagiroglu.studentinformationsystem.model.Teachers;
import com.msagiroglu.studentinformationsystem.service.GradesService;
import com.msagiroglu.studentinformationsystem.service.TeacherAuthenticationService;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
@RestController
@RequestMapping(value = "/grades")
public class GradesController {

	private final GradesService gradesService;
	private final TeacherAuthenticationService teacherAuthenticationService;

	@GetMapping
	public ResponseEntity<List<GradesDto>> getAllGrades() {
		List<Grades> grades = gradesService.findAllGrades();
		List<GradesDto> gradesDtos = grades.stream().map(GradesMapper::toGradesDto).collect(Collectors.toList());
		return ResponseEntity.ok(gradesDtos);
	}

	@PostMapping
	public ResponseEntity<GradesDto> createGrade(@RequestBody GradesDto gradesDto) {
		Grades grade = gradesService.saveGrade(GradesMapper.fromGradesDto(gradesDto, null, null),
				gradesDto.getStudentId(), gradesDto.getCourseId());
		if (grade != null) {
			return new ResponseEntity<>(GradesMapper.toGradesDto(grade), HttpStatus.CREATED);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<GradesDto> getGradeById(@PathVariable Long id) {
		return gradesService.findGradeById(id).map(GradesMapper::toGradesDto).map(dto -> ResponseEntity.ok(dto))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
		if (gradesService.findGradeById(id).isPresent()) {
			gradesService.deleteGrade(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/my-grades")
	public ResponseEntity<List<GradesDto>> getGradesForAuthenticatedTeacher() {
		try {
			Teachers authenticatedTeacher = teacherAuthenticationService.getAuthenticatedTeacher();
			Long teacherId = authenticatedTeacher.getTeacherId();
			List<Grades> grades = gradesService.findGradesByTeacherId(teacherId);
			List<GradesDto> gradesDtos = grades.stream().map(GradesMapper::toGradesDto).collect(Collectors.toList());
			if (grades.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return ResponseEntity.ok(gradesDtos);
		} catch (IllegalStateException e) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateGrade(@PathVariable Long id, @RequestBody GradesDto gradesDto) {
		gradesDto.setGrade_id(id);
		Grades updatedGrade = gradesService.updateGrade(gradesDto);
		return ResponseEntity.ok(GradesMapper.toGradesDto(updatedGrade));
	}
}
