package com.msagiroglu.studentinformationsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msagiroglu.studentinformationsystem.model.Students;
import com.msagiroglu.studentinformationsystem.repository.StudentsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentsService {
	private final StudentsRepository studentsRepository;

	@Transactional(readOnly = true)
	public List<Students> findAllStudents() {
		return studentsRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Students> findStudentById(Long id) {
		return studentsRepository.findById(id);
	}

	@Transactional
	public Students saveStudent(Students student) {
		return studentsRepository.save(student);
	}

	@Transactional
	public void deleteStudent(Long id) {
		studentsRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Optional<Students> findStudentBySchoolNumber(String schoolNumber) {
		return studentsRepository.findBySchoolNumber(schoolNumber);
	}
}
