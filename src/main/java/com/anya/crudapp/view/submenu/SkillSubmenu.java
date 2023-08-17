package com.anya.crudapp.view.submenu;

import com.anya.crudapp.view.SkillView;

import java.util.Scanner;

public class SkillSubmenu {
    Scanner scanner = new Scanner(System.in);
    SkillView skillView = new SkillView();

    private final String subMenuMessage = "Выберете комманду:\n" +
            " 1. Создать умение\n" +
            " 2. Редактировать умение\n" +
            " 3. Найти умение\n" +
            " 4. Удалить умение\n" +
            " 5. Вывести список умений\n" +
            " 6. Найти умение по имени\n" +
            " 7. Выйти из меню";

    public void runner() {
        boolean isExit = false;
        do {
            System.out.println("+++++++++++++++++++++++++++++++++++++++");
            System.out.println(subMenuMessage);
            System.out.println("+++++++++++++++++++++++++++++++++++++++");

            String response = scanner.next();

            switch (response) {
                case "1" -> skillView.createSkill();
                case "2" -> skillView.updateSkill();
                case "3" -> skillView.getSkillById();
                case "4" -> skillView.deleteSkill();
                case "5" -> skillView.getAll();
                case "6" -> skillView.getByName();
                case "7" -> isExit = true;
                default -> System.out.println("Некорректное значение");
            }

        } while (!isExit);
    }
}
