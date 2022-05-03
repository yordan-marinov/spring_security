package com.yordanm.securityspringboot.controller;

import com.yordanm.securityspringboot.domain.dto.StudentDto;
import com.yordanm.securityspringboot.domain.model.Student;
import com.yordanm.securityspringboot.service.StudentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/students")
@AllArgsConstructor
public class StudentController {

    private StudentService studentService;
    private ModelMapper modelMapper;

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable String studentId) {
        final Student student = studentService.getStudent(studentId);
        var result = modelMapper.map(student, StudentDto.class);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    private ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> result = studentService.getAll()
                .stream()
                .map(s -> modelMapper.map(s, StudentDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

}
