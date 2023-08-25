package com.anya.crudapp.service;

import com.anya.crudapp.model.Specialty;
import com.anya.crudapp.repository.SpecialtyRepository;
import com.anya.crudapp.repository.jdbc.JdbcSpecRepositoryImpl;

import java.util.List;

public class SpecialtyService {
    private final SpecialtyRepository specialtyRepository;

    public SpecialtyService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    public SpecialtyService() {
        specialtyRepository = new JdbcSpecRepositoryImpl();
    }


    public Specialty saveSpecialty(Specialty specialty) {
        int existingId = specialtyRepository.searchByName(specialty.getSpecName());
        if (existingId == -1) {
            return specialtyRepository.insert(specialty);
        } else {
            specialty.setId(existingId);
            return specialty;
        }
    }

    public Specialty updateSpecialty(Specialty specialty) {
        return specialtyRepository.update(specialty);
    }

    public Specialty getSpecialty(Integer id) {
        return specialtyRepository.get(id);
    }

    public Specialty deleteSpecialty(Integer id) {
      return specialtyRepository.delete(id);
    }

    public List<Specialty> getALl() {
        return specialtyRepository.getAll();
    }

    public Integer getByName(String name) {
        return specialtyRepository.searchByName(name);
    }


}
