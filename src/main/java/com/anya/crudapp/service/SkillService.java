package com.anya.crudapp.service;

import com.anya.crudapp.model.Skill;
import com.anya.crudapp.repository.SkillRepository;
import com.anya.crudapp.repository.jdbc.JdbcSkillRepositoryImpl;

import java.util.List;

public class SkillService {
    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public SkillService() {
        skillRepository = new JdbcSkillRepositoryImpl();
    }

    public Skill saveSkill(String name) {
        Skill skill = new Skill(name);
        int existingId = skillRepository.searchByName(name);
        if (existingId == -1) {
            return skillRepository.insert(skill);
        } else {
            skill.setId(existingId);
            return skill;
        }
    }

    public Skill getSkill(Integer id) {
        return skillRepository.get(id);
    }

    public Skill deleteSkill(Integer id) {
       return skillRepository.delete(id);
    }

    public Skill updateSkill(Skill skill) {
        return skillRepository.update(skill);
    }

    public List<Skill> getAllSkills() {
        return skillRepository.getAll();
    }

    public Integer getByName(String name) {
        return skillRepository.searchByName(name);
    }


}
