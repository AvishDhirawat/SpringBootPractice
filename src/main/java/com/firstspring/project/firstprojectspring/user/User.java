package com.firstspring.project.firstprojectspring.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class User {
    private Integer id;
    @Size(min = 2, message = "Name should have at least 2 characters.") // Added size restrictions

    @JsonProperty("user_name")
    private String name;
    @Past(message = "Birth Date should be in the past.") // Restrictions for BirthDate
    @JsonProperty("birth_date")
    private LocalDate birthdate;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public User(Integer id, String name, LocalDate birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }

}
