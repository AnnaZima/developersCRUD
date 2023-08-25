package com.anya.crudapp.service;

import com.anya.crudapp.model.Specialty;
import com.anya.crudapp.repository.SpecialtyRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class SpecialtyServiceTest {
    @Mock
    Specialty specialtyJava;
    @Mock
    Specialty specialtyQA;
    @Mock
    SpecialtyRepository specialtyRMock;
    @InjectMocks
    SpecialtyService specialtyService;


    @BeforeEach
    void initSpecialty() {
        specialtyService = new SpecialtyService(specialtyRMock);
        specialtyJava = new Specialty(1, "java");
        specialtyQA = new Specialty(4, "QA");
    }

    @Test
    void saveSpecialty() {
        when(specialtyRMock.insert(any(Specialty.class))).thenReturn(specialtyQA);
        when(specialtyRMock.searchByName(any(String.class))).thenReturn(-1);
        Specialty addSpecialty = specialtyService.saveSpecialty(specialtyQA);
        Assertions.assertEquals(4, addSpecialty.getId());
        Assertions.assertEquals("QA", addSpecialty.getSpecName());
    }

    @Test
    void getSpecialtyById() {
        when(specialtyRMock.get(any(Integer.class))).thenReturn(specialtyJava);
        Specialty getSpec = specialtyService.getSpecialty(1);
        Assertions.assertInstanceOf(Specialty.class, getSpec);
        Assertions.assertEquals("java", getSpec.getSpecName());
    }

    @Test
    void getSpecialtyById1() {
        when(specialtyRMock.get(1)).thenReturn(specialtyJava);
        Specialty getSpec = specialtyService.getSpecialty(1);
        Assertions.assertEquals("java", getSpec.getSpecName());
    }

    @Test
    void updateSpecialty() {
        when(specialtyRMock.update(any(Specialty.class))).thenReturn(specialtyQA);
        Specialty specialty = specialtyService.updateSpecialty(specialtyQA);
        Assertions.assertEquals("QA", specialty.getSpecName());
    }

    @Test
    void deleteSpecialty() {
        when(specialtyRMock.delete(1)).thenReturn(specialtyJava);
        Specialty specialty = specialtyService.deleteSpecialty(1);
        verify(specialtyRMock).delete(anyInt());
        Assertions.assertEquals(1, specialty.getId());
    }

    @Test
    void getByName() {
        when(specialtyRMock.searchByName("java")).thenReturn(1);
        Integer id = specialtyService.getByName("java");
        Assertions.assertEquals(1, id);
    }

    @Test
    void getAll() {
      List<Specialty> specialtyList = new ArrayList<>();
      specialtyList.add(specialtyJava);
      specialtyList.add(specialtyQA);
      when(specialtyRMock.getAll()).thenReturn(specialtyList);
      List<Specialty> specialties = specialtyService.getALl();
      Assertions.assertNotNull(specialties);
      Assertions.assertEquals(specialtyJava, specialties.get(0));
    }

}
