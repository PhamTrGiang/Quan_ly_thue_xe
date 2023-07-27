package com.example.quan_ly_thue_xe.Model;

public class Users {
    private String name,indentification,phone_number,password,id;
    private int status;
    byte[] image;

    public Users() {
    }


    public Users(String name, String indentification, String phone_number, String password, String id, int status) {
        this.name = name;
        this.indentification = indentification;
        this.phone_number = phone_number;
        this.password = password;
        this.id = id;
        this.status = status;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndentification() {
        return indentification;
    }

    public void setIndentification(String indentification) {
        this.indentification = indentification;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
