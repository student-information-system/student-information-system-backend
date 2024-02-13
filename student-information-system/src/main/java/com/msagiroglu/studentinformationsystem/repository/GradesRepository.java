package com.msagiroglu.studentinformationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msagiroglu.studentinformationsystem.model.Grades;

@Repository
public interface GradesRepository extends JpaRepository<Grades, Long> {

}