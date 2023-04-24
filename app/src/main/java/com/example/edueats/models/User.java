package com.example.edueats.models;

public class User {
    private int id;

    private String login;

    private String password;

    private String email;

    private String firstName;

    private String secondName;

    private String lastName;

    public User() {
    }

    public User(int id, String login, String password, String email, String firstName, String secondName, String lastName) {
        id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
