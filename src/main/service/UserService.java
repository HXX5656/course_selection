package main.service;

import com.google.gson.JsonObject;

public interface UserService {

    JsonObject get_student_courseInfo();
    JsonObject get_teacher_info();
    JsonObject showStudents();
    JsonObject showApplications();
    JsonObject student_applications();
    JsonObject generate_teachers();
    JsonObject generate_students();
    JsonObject generate_courses();
}
