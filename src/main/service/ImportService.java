package main.service;

import com.google.gson.JsonObject;

import java.io.IOException;

public interface ImportService {
    int import_select() throws IOException;
    int add_teacher();
    int add_student();
    int addCourse(JsonObject jsonObject)throws IOException;
    int change_status();
    int add_grade(JsonObject json);
}
