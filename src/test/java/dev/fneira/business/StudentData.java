package dev.fneira.business;

public class StudentData {

    private String name;
    private String lastName;

    public String getName() {
        return name;
    }

    public StudentData setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public StudentData setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public StudentData setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public StudentData setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public StudentData setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public StudentData setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
        return this;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public StudentData setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
        return this;
    }

    public String getState() {
        return state;
    }

    public StudentData setState(String state) {
        this.state = state;
        return this;
    }

    public String getCity() {
        return city;
    }

    public StudentData setCity(String city) {
        this.city = city;
        return this;
    }

    private String email;
    private String gender;
    private String mobile;
    private String dateBirth;
    private String currentAddress;
    private String state;
    private String city;

    public StudentData() {
    }


}
