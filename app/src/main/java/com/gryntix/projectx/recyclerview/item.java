package com.gryntix.projectx.recyclerview;

public class item {
    private String name;
    private String description;
    private String phone;
    private int Image;

    public item(String name, String description, String phone, int image) {
        this.name = name;
        this.description = description;
        this.phone = phone;
        Image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}
