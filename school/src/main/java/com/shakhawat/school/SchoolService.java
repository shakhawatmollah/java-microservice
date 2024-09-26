package com.shakhawat.school;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolRepository repository;

    private final StudentClient client;

    public void saveStudent(School school) {
        repository.save(school);
    }

    public List<School> findAllSchool() {
        return repository.findAll();
    }

    public SchoolResponse findSchoolsWithStudents(Integer schoolId) {
        var school = repository.findById(schoolId)
                .orElse(
                        School.builder()
                                .name("NOT_FOUND")
                                .email("NOT_FOUND")
                                .build()
                );
        var students = client.findAllStudentsBySchool(schoolId);
        return SchoolResponse.builder()
                .name(school.getName())
                .email(school.getEmail())
                .students(students)
                .build();
    }

    @Transactional
    public String deleteStudentsBySchoolId(Integer schoolId) {
        Optional<School> school = repository.findById(schoolId);

        if (school.isPresent()) {
            try {
                String studentDeleteMsg = client.deleteStudentsBySchool(schoolId);
                repository.deleteById(schoolId);
                studentDeleteMsg = String.format("%s with School ID: %d", studentDeleteMsg, schoolId);
                log.info(studentDeleteMsg);
                return studentDeleteMsg;
            } catch (Exception e) {
                log.error("Error deleting students for school ID {}", schoolId, e);
                return "Error deleting students";
            }
        }
        return "School not found";
    }

}
