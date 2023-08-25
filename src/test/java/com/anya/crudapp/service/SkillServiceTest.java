package com.anya.crudapp.service;

import com.anya.crudapp.model.Skill;
import com.anya.crudapp.repository.SkillRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SkillServiceTest {

    @Mock
    Skill skill;
    @Mock
    SkillRepository skillRepository;
    @InjectMocks
    SkillService skillService;

    @BeforeEach
    void init() {
        skill = new Skill();
        skill.setId(1);
        skill.setName("mockito");
    }

    @Test
    void saveSkillNotFoundByName() {
        Mockito.when(skillRepository.searchByName(any(String.class))).thenReturn(-1);
        Mockito.when(skillRepository.insert(any(Skill.class))).thenReturn(skill);
        Skill newSkill = skillService.saveSkill("mockito");
        Assertions.assertEquals(1, newSkill.getId());
        Assertions.assertEquals("mockito", newSkill.getName());
    }

    @Test
    void saveSkillFoundByName() {
        Mockito.when(skillRepository.searchByName("mockito")).thenReturn(1);
        Skill newSkill = skillService.saveSkill("mockito");
        Assertions.assertEquals(1, newSkill.getId());
        Assertions.assertEquals("mockito", newSkill.getName());
    }

    @Test
    void getSkill() {
        Mockito.when(skillRepository.get(anyInt())).thenReturn(skill);
        Skill skill = skillService.getSkill(1);
        Assertions.assertEquals(1, skill.getId());
        Assertions.assertEquals("mockito", skill.getName());
    }

    @Test
    void updateSkill() {
        Mockito.when(skillRepository.update(skill)).thenReturn(skill);
        Skill skillChange = skillService.updateSkill(skill);
        Assertions.assertEquals(1, skillChange.getId());
        Assertions.assertEquals("mockito", skill.getName());

    }

    @Test
    void deleteSkill() {
        Mockito.when(skillRepository.delete(1)).thenReturn(skill);
        Skill deletedSkill = skillService.deleteSkill(1);
        verify(skillRepository).delete(anyInt());
        Assertions.assertEquals("mockito", deletedSkill.getName());
    }

    @Test
    void getAll() {
        List<Skill> skillList = new ArrayList<>();
        skillList.add(skill);
        when(skillRepository.getAll()).thenReturn(skillList);
        List<Skill> skills = skillService.getAllSkills();
        Assertions.assertNotNull(skills);
        Assertions.assertEquals(skill, skills.get(0));
    }

    @Test
    void searchByName() {
        when(skillRepository.searchByName(anyString())).thenReturn(1);
        Integer id = skillService.getByName("mockito");
        Assertions.assertEquals(1, id);
    }


}
