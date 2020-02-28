package com.hugosilva.curso.ws.dto;

import com.hugosilva.curso.ws.domain.User;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class UserDto implements Serializable {
    private static final Long serialVersionUID = 1L;

    private String id;
    private String firstName;
    private String lastName ;
    private String email;

    public UserDto() { }

    public UserDto(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();

    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
