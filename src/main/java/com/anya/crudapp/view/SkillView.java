package com.anya.crudapp.view;

import com.anya.crudapp.controller.SkillController;
import com.anya.crudapp.model.Skill;
import java.util.Scanner;

public class SkillView {
    SkillController skillController = new SkillController();

    Scanner scanner = new Scanner(System.in);
    private final String SKILL_NAME = "Введите название умения";
    private final String SKILL_ID = "Введите ID";

    public void getSkillById() {
        System.out.println(SKILL_ID);
        int id = scanner.nextInt();
        scanner.skip("\n");
        System.out.println(skillController.getSkill(id));
    }

    public void createSkill() {
        System.out.println(SKILL_NAME);
        String name = scanner.nextLine();
        Skill skill = skillController.createSkill(name);
        System.out.println("Запись создана: " + skill);
    }

    public void updateSkill() {
        System.out.println(SKILL_ID);
        int id = scanner.nextInt();
        scanner.skip("\n");
        System.out.println("Введите изменение");
        String name = scanner.nextLine();
        Skill skill = new Skill(name);
        skill.setId(id);
        System.out.println("Запись изменена: " + skillController.updateSkill(skill));
    }

    public void deleteSkill() {
        System.out.println(SKILL_ID);
        int id = scanner.nextInt();
       Skill skill = skillController.deleteSkill(id);
        System.out.println("Элемент " + skill +  " удален");
    }

    public void getAll() {
        System.out.println("Список всех умений: ");
        System.out.println(skillController.getAll());
    }

    public void getByName() {
        System.out.println(SKILL_NAME);
        String name = scanner.nextLine();
        System.out.println("ID искомого умения: " + skillController.getByName(name));
    }

}
