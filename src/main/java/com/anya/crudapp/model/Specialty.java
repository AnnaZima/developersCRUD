package com.anya.crudapp.model;

public class Specialty {
    private Integer id;
    private String specName;

    public Specialty(Integer id, String specName) {
        this.id = id;
        this.specName = specName;
    }

    public Specialty(String specName) {
        this.specName = specName;
    }

    public Specialty() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    @Override
    public String toString() {
        return "Specialty{" +
                "id=" + id +
                ", specName='" + specName + '\'' +
                '}';
    }
}
