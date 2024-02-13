package com.msagiroglu.studentinformationsystem.mapper;

import com.msagiroglu.studentinformationsystem.dto.CoursesDto;
import com.msagiroglu.studentinformationsystem.model.Courses;

public class CoursesMapper {
	private CoursesMapper() {
	}

	public static CoursesDto toCoursesDto(Courses course) {
		if (course == null) {
			return null;
		}
		return new CoursesDto(course.getCourse_id(), course.getName(), course.getCode());
	}

	public static Courses fromCoursesDto(CoursesDto dto) {
		if (dto == null) {
			return null;
		}
		Courses course = new Courses();
		course.setName(dto.getName());
		course.setCode(dto.getCode());
		return course;
	}
}
