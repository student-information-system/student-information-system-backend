package com.msagiroglu.studentinformationsystem.controller;

import java.util.List;
import java.util.Optional;
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

import com.msagiroglu.studentinformationsystem.dto.StudentsDto;
import com.msagiroglu.studentinformationsystem.mapper.StudentsMapper;
import com.msagiroglu.studentinformationsystem.model.Students;
import com.msagiroglu.studentinformationsystem.service.StudentsService;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
@RestController
@RequestMapping(value = "/students")
public class StudentsController {

	private final StudentsService studentsService;

	@GetMapping
	public ResponseEntity<List<StudentsDto>> getAllStudents() {
		List<Students> students = studentsService.findAllStudents();
		List<StudentsDto> studentsDtos = students.stream().map(StudentsMapper::toStudentsDto)
				.collect(Collectors.toList());
		return ResponseEntity.ok(studentsDtos);
	}

	@PostMapping
	public ResponseEntity<StudentsDto> createStudent(@RequestBody StudentsDto studentsDto) {
		Students student = StudentsMapper.fromStudentsDto(studentsDto);
		Students savedStudent = studentsService.saveStudent(student);
		return new ResponseEntity<>(StudentsMapper.toStudentsDto(savedStudent), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<StudentsDto> updateStudent(@PathVariable Long id, @RequestBody StudentsDto studentsDto) {
		Optional<Students> existingStudent = studentsService.findStudentById(id);
		if (existingStudent.isPresent()) {
			Students updatedStudent = existingStudent.get();
			updatedStudent.setFirst_name(studentsDto.getFirst_name());
			updatedStudent.setLast_name(studentsDto.getLast_name());
			updatedStudent.setSchoolNumber(studentsDto.getSchoolNumber());
			updatedStudent.setPassword(studentsDto.getPassword());
			Students savedStudent = studentsService.saveStudent(updatedStudent);
			return ResponseEntity.ok(StudentsMapper.toStudentsDto(savedStudent));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
		if (studentsService.findStudentById(id).isPresent()) {
			studentsService.deleteStudent(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
