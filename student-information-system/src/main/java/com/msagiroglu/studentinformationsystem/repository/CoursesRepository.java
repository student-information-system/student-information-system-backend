package com.msagiroglu.studentinformationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msagiroglu.studentinformationsystem.model.Courses;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Long> {

}
