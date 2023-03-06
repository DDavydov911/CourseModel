package ru.alvion.univer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Assessment {
    private int id;
    private int assessment;
    private Student student;
    private Course course;
    private LocalDateTime dateTime;
}
