package com.jpa.vaccinationapp.vaccinationCenter;

public class AddressDTO {
    private Integer centerId;
    private String address;
    private String pincode;
    private String district;
    private String state;
    private String contactNumber;

    public AddressDTO() {
    }

    public AddressDTO(Integer centerId, String address, String pincode, String district, String state, String contactNumber) {
        this.centerId = centerId;
        this.address = address;
        this.pincode = pincode;
        this.district = district;
        this.state = state;
        this.contactNumber = contactNumber;
    }

    public Integer getCenterId() {
        return centerId;
    }

    public void setCenterId(Integer centerId) {
        this.centerId = centerId;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
