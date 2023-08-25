package com.anya.crudapp.view;

import com.anya.crudapp.controller.DeveloperController;
import com.anya.crudapp.controller.SkillController;
import com.anya.crudapp.model.Developer;

import java.sql.SQLOutput;
import java.util.Scanner;

public class DeveloperView {
    DeveloperController developerController = new DeveloperController();

    Scanner scanner = new Scanner(System.in);
    private final String DEV_NAME = "Введите имя";
    private final String DEV_SURNAME = "Введите фамилию";
    private final String DEV_ID = "Введите ID";
    private final String DEV_SKILLS = "Введите навыки через пробел";
    private final String DEV_SPEC = "Введите специализацию";


    public void createDeveloper() {
        System.out.println(DEV_NAME);
        String name = scanner.nextLine();
        System.out.println(DEV_SURNAME);
        String surname = scanner.nextLine();
        System.out.println(DEV_SKILLS);
        String skills = scanner.nextLine();
        System.out.println(DEV_SPEC);
        String specialty = scanner.nextLine();
        System.out.println(developerController.saveDeveloper(name, surname, skills, specialty));
    }

    public void updateDeveloper() {
        System.out.println(DEV_ID);
        int id = scanner.nextInt();
        scanner.skip("\n");
        System.out.println(DEV_NAME);
        String name = scanner.nextLine();
        System.out.println(DEV_SURNAME);
        String surname = scanner.nextLine();
        System.out.println(DEV_SKILLS);
        String skills = scanner.nextLine();
        System.out.println(DEV_SPEC);
        String specialty = scanner.nextLine();
        System.out.println(developerController.updateDeveloper(id, name, surname, skills, specialty));
    }

    public void getDeveloper() {
        System.out.println(DEV_ID);
        int id = scanner.nextInt();
        System.out.println(developerController.getDeveloper(id));
    }

    public void deleteDeveloper() {
        System.out.println(DEV_ID);
        int id = scanner.nextInt();
        Developer developer = developerController.deleteDeveloper(id);
        System.out.println("Элемент " + developer +  " удален");
    }

    public void getAll() {
        System.out.println("Список всех разработчиков: ");
        System.out.println(developerController.getAll());
    }

    public void getByName() {
        System.out.println("Введите имя и фамилию разработчика через пробел: ");
        String nameDeveloper = scanner.nextLine();
        System.out.println("ID искомого разработчика: " + developerController.getByName(nameDeveloper));
    }

}
