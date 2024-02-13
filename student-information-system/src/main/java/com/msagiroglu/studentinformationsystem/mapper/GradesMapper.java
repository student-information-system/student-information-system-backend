package com.msagiroglu.studentinformationsystem.mapper;

import com.msagiroglu.studentinformationsystem.dto.GradesDto;
import com.msagiroglu.studentinformationsystem.model.Courses;
import com.msagiroglu.studentinformationsystem.model.Grades;
import com.msagiroglu.studentinformationsystem.model.Students;

public class GradesMapper {

	private GradesMapper() {
	}

	public static GradesDto toGradesDto(Grades grade) {
		if (grade == null) {
			return null;
		}
		return new GradesDto(grade.getGrade(), grade.getDate(), grade.getStudent().getStudent_id(),
				grade.getCourse().getCourse_id());
	}

	public static Grades fromGradesDto(GradesDto dto, Students student, Courses course) {
		if (dto == null) {
			return null;
		}
		Grades grade = new Grades();
		grade.setGrade(dto.getGrade());
		grade.setDate(dto.getDate());
		grade.setStudent(student);
		grade.setCourse(course);
		return grade;
	}
}
