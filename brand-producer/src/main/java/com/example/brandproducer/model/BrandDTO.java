package com.example.brandproducer.model;

public class BrandDTO {
    private String name;

    public BrandDTO(String name) {
        this.name = name;
    }

    public BrandDTO() {
    }

    @Override
    public String toString() {
        return "Brand{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

