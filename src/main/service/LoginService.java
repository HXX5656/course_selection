package main.service;

import com.google.gson.JsonObject;

public interface LoginService {
    JsonObject login();
    JsonObject modify();
}
