package com.yordanm.securityspringboot.service;

import com.yordanm.securityspringboot.domain.dto.StudentDto;
import com.yordanm.securityspringboot.domain.model.Student;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private ModelMapper modelMapper;

    List<Student> students = List.of(
            new Student("81f2c154-47e4-4417-8ff1-7d41791a5215", "Jordan"),
            new Student("91f2c154-47e4-4417-8ff1-7d41791a5215", "Martin")
    );

    public Student getStudent(String studentId) {
        return students.stream()
                .filter(s -> studentId.equals(s.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Id %s not found", studentId)));
    }

    public List<Student> getAll() {
        return students;
    }
}
