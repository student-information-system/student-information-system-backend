package com.msagiroglu.studentinformationsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msagiroglu.studentinformationsystem.model.Teachers;

@Repository
public interface TeachersRepository extends JpaRepository<Teachers, Long> {
	Optional<Teachers> findByEmail(String email);
}