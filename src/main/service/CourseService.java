package main.service;

import main.entity.Section;
import main.entity.Student;

public interface CourseService {
    int select_or_cancel(Student student, Section section);
}
