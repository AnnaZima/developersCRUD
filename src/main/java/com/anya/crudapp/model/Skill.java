package com.anya.crudapp.model;

public class Skill {
    private Integer id;
    private String name;

    public Skill(Integer id, String name) {
        this.name = name;
    }

    public Skill(String name) {
        this.name = name;
    }

    public Skill() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
