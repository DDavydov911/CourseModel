package ru.alvion.univer.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Professor {
    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    private float payment;
    private List<Course> courses;
}
