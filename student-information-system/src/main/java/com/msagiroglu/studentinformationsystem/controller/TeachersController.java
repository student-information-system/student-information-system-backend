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

import com.msagiroglu.studentinformationsystem.dto.TeachersDto;
import com.msagiroglu.studentinformationsystem.mapper.TeachersMapper;
import com.msagiroglu.studentinformationsystem.model.Teachers;
import com.msagiroglu.studentinformationsystem.service.TeachersService;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
@RestController
@RequestMapping(value = "/teachers")
public class TeachersController {

	private final TeachersService teachersService;

	@GetMapping
	public ResponseEntity<List<TeachersDto>> getAllTeachers() {
		List<Teachers> teachers = teachersService.findAllTeachers();
		List<TeachersDto> teacherDtos = teachers.stream().map(TeachersMapper::toDto).collect(Collectors.toList());
		return ResponseEntity.ok(teacherDtos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TeachersDto> getTeacherById(@PathVariable Long id) {
		return teachersService.findTeacherById(id).map(TeachersMapper::toDto).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<TeachersDto> createTeacher(@RequestBody TeachersDto teacherDto) {
		Teachers teacher = TeachersMapper.fromDto(teacherDto);
		Teachers savedTeacher = teachersService.saveTeacher(teacher);
		return new ResponseEntity<>(TeachersMapper.toDto(savedTeacher), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TeachersDto> updateTeacher(@PathVariable Long id, @RequestBody TeachersDto teacherDto) {
		teacherDto.setTeacherId(id);
		Teachers teacher = TeachersMapper.fromDto(teacherDto);
		Teachers updatedTeacher = teachersService.updateTeacher(teacher);
		if (updatedTeacher != null) {
			return ResponseEntity.ok(TeachersMapper.toDto(updatedTeacher));
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
		try {
			teachersService.deleteTeacher(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}