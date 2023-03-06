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
public class CourseProgress {
    private int id;
    private Student student;
    private Course course;
    private List<Assessment> assessments;
}
