package com.example.my_api.Models;

public class ProductData {
    private Integer connection;
    private Integer productaddd;

    public ProductData(Integer connection, Integer productaddd) {
        this.connection = connection;
        this.productaddd = productaddd;
    }

    public Integer getConnection() {
        return connection;
    }

    public void setConnection(Integer connection) {
        this.connection = connection;
    }

    public Integer getProductaddd() {
        return productaddd;
    }

    public void setProductaddd(Integer productaddd) {
        this.productaddd = productaddd;
    }

    @Override
    public String toString() {
        return
                "connection=" + connection +
                ", productaddd=" + productaddd ;
    }
}
