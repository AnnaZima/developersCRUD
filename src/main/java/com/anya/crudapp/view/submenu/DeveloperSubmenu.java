package com.anya.crudapp.view.submenu;

import com.anya.crudapp.view.DeveloperView;

import java.util.Scanner;

public class DeveloperSubmenu {
    Scanner scanner = new Scanner(System.in);
    DeveloperView developerView = new DeveloperView();

    private final String subMenuMessage = "Выберете комманду:\n" +
            " 1. Создать разработчика\n" +
            " 2. Редактировать разработчика\n" +
            " 3. Найти разработчика\n" +
            " 4. Удалить разработчика\n" +
            " 5. Вывести список всех разработчиков\n" +
            " 6. Получить ID разработчика по имени\n" +
            " 7. Выйти из меню";

    public void runner() {
        boolean isExit = false;
        do {
            System.out.println("+++++++++++++++++++++++++++++++++++++++");
            System.out.println(subMenuMessage);
            System.out.println("+++++++++++++++++++++++++++++++++++++++");

            String response = scanner.next();

            switch (response) {
                case "1" -> developerView.createDeveloper();
                case "2" -> developerView.updateDeveloper();
                case "3" -> developerView.getDeveloper();
                case "4" -> developerView.deleteDeveloper();
                case "5" -> developerView.getAll();
                case "6" -> developerView.getByName();
                case "7" -> isExit = true;
                default -> System.out.println("Некорректное значение");
            }

        } while (!isExit);
    }
}