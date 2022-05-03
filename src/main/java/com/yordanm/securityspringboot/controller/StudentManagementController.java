package com.yordanm.securityspringboot.controller;

import com.yordanm.securityspringboot.domain.dto.StudentDto;
import com.yordanm.securityspringboot.domain.model.Student;
import com.yordanm.securityspringboot.service.StudentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("management/api/v1/students")
@AllArgsConstructor
public class StudentManagementController {

    private StudentService studentService;
    private ModelMapper modelMapper;

    @GetMapping("/{studentId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable String studentId) {
        final Student student = studentService.getStudent(studentId);
        var result = modelMapper.map(student, StudentDto.class);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    private ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> result = studentService.getAll()
                .stream()
                .map(s -> modelMapper.map(s, StudentDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    private ResponseEntity<StudentDto> registerStudent(@RequestBody StudentDto studentDto) {
        System.out.println(studentDto);
        return ResponseEntity.ok(studentDto);

    }

    @PutMapping("/{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    private ResponseEntity<StudentDto> updateStudentById(@PathVariable String studentId, @RequestBody StudentDto studentDto) {
        System.out.println(studentId);
        System.out.println("--------------------");
        System.out.println(studentDto);

        return ResponseEntity.ok(studentDto);
    }

    @DeleteMapping("/{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    private ResponseEntity<String> deleteStudentById(@PathVariable String studentId) {
        System.out.println(studentService.getStudent(studentId));
        return ResponseEntity.ok(studentId);
    }
}
