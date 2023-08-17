package com.anya.crudapp.view.submenu;
import com.anya.crudapp.view.SpecialtyView;
import java.util.Scanner;

public class SpecialtySubMenu {
    SpecialtyView specialtyView = new SpecialtyView();
    Scanner scanner = new Scanner(System.in);

    private final String subMenuMessage = "Выберете комманду:\n" +
            " 1. Создать специальность\n" +
            " 2. Редактировать специальность\n" +
            " 3. Найти специальность по id\n" +
            " 4. Удалить специальность\n" +
            " 5. Вывести список специальностей\n" +
            " 6. Найти специальность по имени\n" +
            " 7. Выйти из меню";

    public void runner() {
        boolean isExit = false;
        do {
            System.out.println("+++++++++++++++++++++++++++++++++++++++");
            System.out.println(subMenuMessage);
            System.out.println("+++++++++++++++++++++++++++++++++++++++");

            String response = scanner.next();

            switch (response) {
                case "1" -> specialtyView.createSpecialty();
                case "2" -> specialtyView.changeSpecialty();
                case "3" -> specialtyView.getSpecialty();
                case "4" -> specialtyView.deleteSpecialty();
                case "5" -> specialtyView.getAll();
                case "6" -> specialtyView.getSpecialtyByName();
                case "7" -> isExit = true;
                default -> System.out.println("Некорректное значение");
            }
        } while (!isExit);
    }
}
