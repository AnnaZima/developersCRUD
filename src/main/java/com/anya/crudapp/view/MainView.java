package com.anya.crudapp.view;

import com.anya.crudapp.model.Skill;
import com.anya.crudapp.view.submenu.DeveloperSubmenu;
import com.anya.crudapp.view.submenu.SkillSubmenu;
import com.anya.crudapp.view.submenu.SpecialtySubMenu;

import java.util.Scanner;

public class MainView {
    Scanner scanner = new Scanner(System.in);

    DeveloperSubmenu developerSubmenu = new DeveloperSubmenu();
    SkillSubmenu skillSubmenu = new SkillSubmenu();
    SpecialtySubMenu specialtySubMenu = new SpecialtySubMenu();


    String menu = "1 - Управление разработчиками\n" +
            "2 - Управление умениями\n" +
            "3 - Управление специальностями\n" +
            "4 - Выход";

    public void start() {
        System.out.println();
        boolean isExit = false;
        while (true) {
            System.out.println("+++++++++++++++++++++++++++++++++++++++");
            System.out.println(menu);
            System.out.println("+++++++++++++++++++++++++++++++++++++++");

            String response = scanner.next();

            switch (response) {
                case "1" -> developerSubmenu.runner();
                case "2" -> skillSubmenu.runner();
                case "3" -> specialtySubMenu.runner();
                case "4" -> isExit = true;
                default -> System.out.println("Некорректное значение");
            }

            if (isExit)
                break;
        }

    }
}
