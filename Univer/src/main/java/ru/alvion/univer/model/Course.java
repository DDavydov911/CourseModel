package ru.alvion.univer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Course {
    private int id;
    private String courseName;
    private float price;
    private Professor professor;
    private List<Student> students;
}
