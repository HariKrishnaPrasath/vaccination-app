package com.jpa.vaccinationapp.admin;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId;
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;
    @Pattern(regexp = "^[+]?[0-9]{10,15}$", message = "Invalid phone number format")
    private String phoneNumber;
    @NotBlank(message = "Name cant be null, it should contain chars")
    @Pattern(regexp = "[a-zA-Z ]{3,16}", message = "Name should contain min 3 & max 16 chars , no digits and special chars allowed.")
    private String adminName;
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}", message = "Must contain at least one  number and one uppercase and lowercase letter, and at least 8 or more characters")
    private String password;
    @NotBlank(message = "Admin type cannot be blank")
    private String adminType;



    public Admin(Integer adminId, String email, String adminName, String password, String adminType) {
        this.adminId = adminId;
        this.email = email;
        this.adminName = adminName;
        this.password = password;
        this.adminType = adminType;
    }

    public Admin() {
    }

    public Admin(String email, String adminName, String password, String adminType) {
        this.email = email;
        this.adminName = adminName;
        this.password = password;
        this.adminType = adminType;
    }

    public Admin(Integer adminId, String email, String phoneNumber, String adminName, String password, String adminType) {

        this.adminId = adminId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.adminName = adminName;
        this.password = password;
        this.adminType = adminType;
    }

    public Admin(String email, String phoneNumber, String adminName, String password, String adminType) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.adminName = adminName;
        this.password = password;
        this.adminType = adminType;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdminType() {
        return adminType;
    }

    public void setAdminType(String adminType) {
        this.adminType = adminType;
    }

}
