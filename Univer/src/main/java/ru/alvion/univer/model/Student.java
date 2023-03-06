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
public class Student {
    private int recordBookNumber;
    private String name;
    private String address;
    private String phoneNumber;
    private float avgPerformance;
    private List<Course> courses;
}
