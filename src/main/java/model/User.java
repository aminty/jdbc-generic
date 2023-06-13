package model;

import base.model.BaseEntity;

public class User extends BaseEntity<Integer> {

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String natCode;

    private String phone;



    public User(String firstName, String lastName, String username, String password, String natCode, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.natCode = natCode;
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

    public User(Integer id, String firstName, String lastName,  String username, String password,String natCode, String phone) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.natCode = natCode;
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNatCode() {
        return natCode;
    }

    public void setNatCode(String natCode) {
        this.natCode = natCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", natCode='" + natCode + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
