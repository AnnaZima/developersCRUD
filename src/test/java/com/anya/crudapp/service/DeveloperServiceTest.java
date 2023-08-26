package com.anya.crudapp.service;

import com.anya.crudapp.model.Developer;
import com.anya.crudapp.model.Skill;
import com.anya.crudapp.model.Specialty;
import com.anya.crudapp.model.Status;
import com.anya.crudapp.repository.DeveloperRepository;
import org.junit.jupiter.api.Assertions;
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
    DeveloperRepository developerRepository;
    @InjectMocks
    DeveloperService developerService;

    private Specialty getSpecialty() {
        return new Specialty(2, "java");
    }
    private List<Skill> getSkills() {
        List<Skill> skills = new ArrayList<>();
          Skill skillFirst = new Skill(3, "mockito");
          Skill skillSecond = new Skill(4, "jdbc");
          skills.add(skillFirst);
          skills.add(skillSecond);
          return skills;
    }

    private Developer getDeveloper() {
        return new Developer(1, "Ivan", "Ivanov", getSkills(), getSpecialty(), Status.ACTIVE);
    }

    @Test
    void createDeveloper() {
        List<String> nameOfSkills = new ArrayList<>();
        when(developerRepository.insert(any(Developer.class))).thenReturn(getDeveloper());
        Developer newDeveloper = developerService.createDeveloper("Ivan", "Ivanov", nameOfSkills, "java");
        Assertions.assertEquals(getDeveloper().getFirstName(), newDeveloper.getFirstName());
        Assertions.assertEquals(getDeveloper().getId(), newDeveloper.getId());

    }

    @Test
    void getDeveloperById() {
        when(developerRepository.get(1)).thenReturn(getDeveloper());
        Developer foundDeveloper = developerService.getDeveloper(1);
        Assertions.assertEquals("Ivan", foundDeveloper.getFirstName());
    }

    @Test
    void updateDeveloper() {
        List<String> nameOfSkills = new ArrayList<>();
        when(developerRepository.update(any(Developer.class))).thenReturn(getDeveloper());
        Developer updated = developerService.updateDeveloper(1, "Ivan", "Ivanov", nameOfSkills, "java");
        Assertions.assertEquals("Ivan", updated.getFirstName());
        Assertions.assertEquals("java", updated.getSpecialty().getSpecName());
    }

    @Test
    void deleteDeveloper() {
        when(developerRepository.delete(anyInt())).thenReturn(getDeveloper());
        Developer deleted = developerService.deleteDeveloper(1);
        Assertions.assertEquals("Ivanov", deleted.getLastName());
        verify(developerRepository).delete(1);

    }

    @Test
    void getAll() {
        List<Developer> developerList = new ArrayList<>();
        developerList.add(getDeveloper());
        when(developerRepository.getAll()).thenReturn(developerList);
        List<Developer> developers = developerService.getAll();
        Assertions.assertEquals(getDeveloper().getFirstName(), developers.get(0).getFirstName());
    }

    @Test
    void searchByName() {
        when(developerRepository.searchByName("Ivanov")).thenReturn(getDeveloper().getId());
        int id = developerService.getByName("Ivanov");
        Assertions.assertEquals(1, id);

    }
}
