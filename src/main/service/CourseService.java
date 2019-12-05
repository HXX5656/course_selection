package main.service;

import main.entity.Application;
import main.entity.Section;
import main.entity.Student;

public interface CourseService {
    int select_or_cancel();
    int apply();
    int confirm_apply();
    int refuse_apply();
}


