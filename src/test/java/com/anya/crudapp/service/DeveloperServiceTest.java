package com.anya.crudapp.service;

import com.anya.crudapp.model.Developer;
import com.anya.crudapp.model.Skill;
import com.anya.crudapp.model.Specialty;
import com.anya.crudapp.model.Status;
import com.anya.crudapp.repository.DeveloperRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class DeveloperServiceTest {

    @Mock
    Specialty specialty;
    @Mock
    List<Skill> skillList;
    @InjectMocks
    Developer developer;
    @Mock
    DeveloperRepository developerRepository;
    @InjectMocks
    DeveloperService developerService;
//    @Mock
//    SpecialtyRepository specialtyRepository;
//    @Mock
//    SkillRepository skillRepository;

    @BeforeEach
    void init() {
        specialty = new Specialty();
        specialty.setId(2);
        specialty.setSpecName("java");
        skillList = new ArrayList<>();
        Skill skill = new Skill(3, "mockito");
        skillList.add(skill);
        developer = new Developer(1, "Ivan", "Ivanov", skillList, specialty, Status.ACTIVE);
    }

    @Test
    void createDeveloper() {
        List<String> nameOfSkills = new ArrayList<>();
        when(developerRepository.insert(any(Developer.class))).thenReturn(developer);
        Developer newDeveloper = developerService.createDeveloper("Ivan", "Ivanov", nameOfSkills, "java");
        Assertions.assertEquals(developer.getFirstName(), newDeveloper.getFirstName());
        Assertions.assertEquals(developer.getId(), newDeveloper.getId());

    }

    @Test
    void getDeveloper() {
        when(developerRepository.get(1)).thenReturn(developer);
        Developer foundDeveloper = developerService.getDeveloper(1);
        Assertions.assertEquals("Ivan", foundDeveloper.getFirstName());
    }

    @Test
    void updateDeveloper() {
        List<String> nameOfSkills = new ArrayList<>();
        //  when(specialtyRepository.insert(any(Specialty.class))).thenReturn(specialty);
        when(developerRepository.update(any(Developer.class))).thenReturn(developer);
        Developer updated = developerService.updateDeveloper(1, "Ivan", "Ivanov", nameOfSkills, "java");
        Assertions.assertEquals("Ivan", updated.getFirstName());
        Assertions.assertEquals("java", developer.getSpecialty().getSpecName());
    }

    @Test
    void deleteDeveloper() {
        when(developerRepository.delete(anyInt())).thenReturn(developer);
        Developer deleted = developerService.deleteDeveloper(1);
        Assertions.assertEquals("Ivanov", deleted.getLastName());
        verify(developerRepository).delete(1);

    }

    @Test
    void getAll() {
        List<Developer> developerList = new ArrayList<>();
        developerList.add(developer);
        when(developerRepository.getAll()).thenReturn(developerList);
        List<Developer> developers = developerService.getAll();
        Assertions.assertEquals(developer, developers.get(0));
    }

    @Test
    void searchByName() {
        when(developerRepository.searchByName("Ivanov")).thenReturn(developer.getId());
        int id = developerService.getByName("Ivanov");
        Assertions.assertEquals(1, id);

    }


}
