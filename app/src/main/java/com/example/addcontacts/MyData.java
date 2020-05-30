package com.example.addcontacts;

public class MyData {
    String name;
    String address;
    byte[] image;

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
}
