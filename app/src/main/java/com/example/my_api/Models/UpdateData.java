package com.example.my_api.Models;

public class UpdateData {
    private  Integer connection;
    private Integer result;
    private String id;
    private String name;
    private String price;
    private String description;
    private String imagedata;
    private String imagename;

    public UpdateData(int connection,int result,String id, String name, String price, String description, String imagedata, String imagename) {
        this.connection=connection;
        this.result=result;
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imagedata = imagedata;
        this.imagename = imagename;
    }

    public int getConnection() {
        return connection;
    }

    public void setConnection(int connection) {
        this.connection = connection;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagedata() {
        return imagedata;
    }

    public void setImagedata(String imagedata) {
        this.imagedata = imagedata;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    @Override
    public String toString() {
        return
                "connection=" + connection +
                ", result=" + result +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                ", imagedata='" + imagedata + '\'' +
                ", imagename='" + imagename ;
    }
}
