package main.entity;

public class Department {
    private String dep_id;
    private String dep_name;

    public Department(String dep_id, String dep_name) {
        this.dep_id = dep_id;
        this.dep_name = dep_name;
    }

    public String getDep_id() {
        return dep_id;
    }

    public void setDep_id(String dep_id) {
        this.dep_id = dep_id;
    }

    public String getDep_name() {
        return dep_name;
    }

    public void setDep_name(String dep_name) {
        this.dep_name = dep_name;
    }
}
