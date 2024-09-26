package com.shakhawat.student;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Student student) {
        service.saveStudent(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> findAllStudents() {
        return ResponseEntity.ok(service.findAllStudents());
    }

    @GetMapping("/school/{schoolId}")
    public ResponseEntity<List<Student>> findAllStudents(@PathVariable("schoolId") Integer schoolId) {
        return ResponseEntity.ok(service.findAllStudentsBySchool(schoolId));
    }

    @DeleteMapping("/school/{schoolId}")
    public ResponseEntity<String> deleteStudentsBySchoolId(@PathVariable Integer schoolId) {
        var deletedRecord = service.deleteStudentBySchoolId(schoolId);
        return ResponseEntity.status(HttpStatus.OK).body(String.join("Deleted student record(s):", " ", String.valueOf(deletedRecord)));
    }

    @DeleteMapping("/delete/{schoolId}")
    public ResponseEntity<List<Student>> deleteAllStudentBySchoolId(@PathVariable Integer schoolId) {
        var rs = service.deleteStudentsBySchoolId(schoolId);
        log.info("deleteAllStudentBySchoolId {}", schoolId);
        return ResponseEntity.status(HttpStatus.OK).body(rs);
    }

}
