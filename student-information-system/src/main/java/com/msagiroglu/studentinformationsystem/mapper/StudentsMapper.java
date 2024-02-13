package com.msagiroglu.studentinformationsystem.mapper;

import com.msagiroglu.studentinformationsystem.dto.StudentsDto;
import com.msagiroglu.studentinformationsystem.model.Students;

public class StudentsMapper {
	private StudentsMapper() {
	}

	public static StudentsDto toStudentsDto(Students student) {
		if (student == null) {
			return null;
		}

		StudentsDto dto = new StudentsDto();
		dto.setFirst_name(student.getFirst_name());
		dto.setLast_name(student.getLast_name());
		dto.setSchoolNumber(student.getSchoolNumber());
		dto.setPassword(student.getPassword());
		return dto;
	}

	public static Students fromStudentsDto(StudentsDto dto) {
		if (dto == null) {
			return null;
		}

		Students student = new Students();
		student.setFirst_name(dto.getFirst_name());
		student.setLast_name(dto.getLast_name());
		student.setSchoolNumber(dto.getSchoolNumber());
		student.setPassword(dto.getPassword());
		return student;
	}
}
