package main.service;

import main.entity.Application;
import main.entity.Section;
import main.entity.Student;

public interface CourseService {
    String select_or_cancel();
    int apply();
    String confirm_apply();
    int refuse_apply();
    int delete_section();
}


