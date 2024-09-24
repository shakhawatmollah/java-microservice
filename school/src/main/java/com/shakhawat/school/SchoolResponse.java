package com.shakhawat.school;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SchoolResponse {

    private String name;

    private String email;

    List<Student> students;

}
