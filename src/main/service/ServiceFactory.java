package main.service;

import com.google.gson.JsonObject;

public class ServiceFactory {
    public static ImportService getImportServiceInstance(JsonObject param) {
        return new ImportServiceImpl(param);
    }
}
