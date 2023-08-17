package com.anya.crudapp.controller;

import com.anya.crudapp.model.Developer;
import com.anya.crudapp.service.DeveloperService;

import java.util.Arrays;
import java.util.List;

public class DeveloperController {
    DeveloperService developerService = new DeveloperService();


    public Developer saveDeveloper(String name, String surname, String skills, String specialty) {
        List<String> skillList = Arrays.stream(skills.split(" ")).toList();
        return developerService.createDeveloper(name, surname, skillList, specialty);

    }

    public Developer updateDeveloper(int id, String name, String surname, String skills, String specialty) {
        List<String> skillList = Arrays.stream(skills.split(" ")).toList();
        return developerService.updateDeveloper(id, name, surname, skillList, specialty);
    }

    public Developer getDeveloper(Integer id) {
        return developerService.getDeveloper(id);
    }

    public void deleteDeveloper(Integer id) {
        developerService.deleteDeveloper(id);
    }

    public Integer getByName(String name) {
        return developerService.getByName(name);
    }

    public List<Developer> getAll() {
        return developerService.getAll();
    }


}
