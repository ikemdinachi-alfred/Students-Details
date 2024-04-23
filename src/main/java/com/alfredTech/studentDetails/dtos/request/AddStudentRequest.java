package com.alfredTech.studentDetails.dtos.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class AddStudentRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String cohort;
}
