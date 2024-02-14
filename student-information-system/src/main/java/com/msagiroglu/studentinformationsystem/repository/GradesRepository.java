package com.msagiroglu.studentinformationsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.msagiroglu.studentinformationsystem.model.Grades;

@Repository
public interface GradesRepository extends JpaRepository<Grades, Long> {
	@Query("SELECT g FROM Grades g JOIN g.course c WHERE c.teacher.teacherId = :teacherId")
	List<Grades> findByTeacherId(Long teacherId);

	@Override
	@EntityGraph(attributePaths = { "student", "course" })
	List<Grades> findAll();
}