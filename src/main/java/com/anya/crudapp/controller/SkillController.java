package com.anya.crudapp.controller;

import com.anya.crudapp.model.Skill;
import com.anya.crudapp.service.SkillService;

import java.util.List;

public class SkillController {
    SkillService skillService = new SkillService();

    public Skill createSkill(String name) {
        return skillService.saveSkill(name);
    }

    public Skill getSkill(Integer id) {
        return skillService.getSkill(id);
    }

    public Skill updateSkill(Skill object) {
        return skillService.updateSkill(object);
    }

    public Skill deleteSkill(Integer id) {
        return skillService.deleteSkill(id);
    }

    public List<Skill> getAll() {
        return skillService.getAllSkills();
    }

    public Integer getByName(String name) {
        return skillService.getByName(name);
    }

}
