package com.example.addcontacts;

public class MyData {
    String name;
    String address;
    byte[] image;
    String mobile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setImage(byte[] image) {
        this.image=image;
    }

    public byte[] getImage() {
        return image;
    }

    public void setMobile(String toString) {
        this.mobile=toString;
    }

    public String getMobile() {
        return mobile;
    }
}
