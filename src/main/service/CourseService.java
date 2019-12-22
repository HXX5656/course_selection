package main.service;

import com.google.gson.JsonObject;
import main.entity.Application;
import main.entity.Section;
import main.entity.Student;

public interface CourseService {
    String select_or_cancel();
    int apply();
    String confirm_apply();
    int refuse_apply();
    JsonObject courseList();
    JsonObject teach_courses();
    int delete_section();
}


