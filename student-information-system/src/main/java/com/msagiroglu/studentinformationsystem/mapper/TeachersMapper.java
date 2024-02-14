package com.msagiroglu.studentinformationsystem.mapper;

import com.msagiroglu.studentinformationsystem.dto.TeachersDto;
import com.msagiroglu.studentinformationsystem.model.Teachers;

public class TeachersMapper {

	public static TeachersDto toDto(Teachers teacher) {
		if (teacher == null) {
			return null;
		}
		return new TeachersDto(teacher.getTeacherId(), teacher.getFirstName(), teacher.getLastName(),
				teacher.getEmail(), null // bu kısmın sebebi şifre döndürmemektir.
		);
	}

	public static Teachers fromDto(TeachersDto teacherDto) {
		if (teacherDto == null) {
			return null;
		}
		Teachers teacher = new Teachers();
		teacher.setTeacherId(teacherDto.getTeacherId());
		teacher.setFirstName(teacherDto.getFirstName());
		teacher.setLastName(teacherDto.getLastName());
		teacher.setEmail(teacherDto.getEmail());
		return teacher;
	}
}