package com.anya.crudapp.service;

import com.anya.crudapp.model.Developer;
import com.anya.crudapp.model.Skill;
import com.anya.crudapp.model.Specialty;
import com.anya.crudapp.repository.DeveloperRepository;
import com.anya.crudapp.repository.jdbc.JdbcDevRepositoryImpl;

import java.util.List;

public class DeveloperService {
    private final SkillService skillService = new SkillService();
    private final SpecialtyService specialtyService = new SpecialtyService();
    private final DeveloperRepository developerRepository;

    public DeveloperService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    public DeveloperService() {
        developerRepository = new JdbcDevRepositoryImpl();
    }

    public Developer createDeveloper(String name, String surname, List<String> skills, String specialty) {
        Developer developer = new Developer();
        developer.setFirstName(name);
        developer.setLastName(surname);
        Specialty devSpecialty = new Specialty(specialty);

        int specialtyId = specialtyService.saveSpecialty(devSpecialty).getId();
        devSpecialty.setId(specialtyId);
        developer.setSpecialty(devSpecialty);
        List<Skill> skillList = skills.stream().map(skillService::saveSkill).toList();

        developer.setSkills(skillList);
        return developerRepository.insert(developer);
    }

    public Developer getDeveloper(Integer id) {
        return developerRepository.get(id);
    }

    public Developer updateDeveloper(int id, String name, String surname, List<String> skills, String specialty) {
        Developer developer = new Developer();
        developer.setId(id);
        developer.setFirstName(name);
        developer.setLastName(surname);

        Specialty devSpecialty = new Specialty(specialty);
        int specialtyId = specialtyService.saveSpecialty(devSpecialty).getId();
        devSpecialty.setId(specialtyId);
        developer.setSpecialty(devSpecialty);

        List<Skill> skillList = skills.stream().map(skillService::saveSkill).toList();
        developer.setSkills(skillList);
        return developerRepository.update(developer);
    }

    public Developer deleteDeveloper(Integer id) {
       return developerRepository.delete(id);
    }

    public List<Developer> getAll() {
        return developerRepository.getAll();
    }

    public Integer getByName(String name) {
        return developerRepository.searchByName(name);
    }

}
