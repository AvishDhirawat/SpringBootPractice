package com.firstspring.project.firstprojectspring.versioning;

public class PersonV1 {
    public String name;

    public PersonV1(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "PersonV1{" +
                "name='" + name + '\'' +
                '}';
    }

}
