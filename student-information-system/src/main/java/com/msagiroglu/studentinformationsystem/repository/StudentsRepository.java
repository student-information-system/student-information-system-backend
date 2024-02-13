package com.msagiroglu.studentinformationsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msagiroglu.studentinformationsystem.model.Students;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Long> {
	Optional<Students> findBySchoolNumber(String schoolNumber);
}