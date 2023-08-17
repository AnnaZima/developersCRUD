package com.anya.crudapp.controller;

import com.anya.crudapp.model.Specialty;
import com.anya.crudapp.service.SpecialtyService;

import java.util.List;

public class SpecialtyController {
    SpecialtyService specialtyService = new SpecialtyService();

    public Specialty getSpecialty(Integer id) {
        return specialtyService.getSpecialty(id);
    }

    public Specialty createSpecialty(String name) {
        Specialty specialty = new Specialty(name);
        return specialtyService.saveSpecialty(specialty);
    }

    public Specialty changeSpecialty(Specialty spec) {
        return specialtyService.updateSpecialty(spec);
    }

    public void deleteSpecialty(Integer id) {
        specialtyService.deleteSpecialty(id);
    }


    public List<Specialty> getAll() {
        return specialtyService.getALl();
    }

    public Integer getByName(String name) {
        return specialtyService.getByName(name);
    }
}
