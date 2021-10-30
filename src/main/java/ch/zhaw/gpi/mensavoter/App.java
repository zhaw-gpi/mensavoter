package ch.zhaw.gpi.mensavoter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    private MenuManager menuManager;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {        
        // JSON lesen und ausgeben
        menuManager.loadMenus("20201120");
        Integer menuCount = menuManager.getMenuCount();
        System.out.println("Anzahl Menus: " + menuCount);
        for (int i = 0; i < menuCount; i++) {
            menuManager.getMenu(i).printMenu();
            System.out.println("");
        }

        // Liken und Likes ausgeben
        menuManager.getMenu(0).like();
        menuManager.getMenu(1).like();
        menuManager.getMenu(1).like();
        System.out.println("Likes von Menu 1: " + menuManager.getMenu(0).getLikes());
        System.out.println("Likes von Menu 2: " + menuManager.getMenu(1).getLikes());

        // Kommentare erfassen und ausgeben
        menuManager.getMenu(0).addComment("Chicken wie Schuhsohle");
        menuManager.getMenu(0).addComment("Auch Trockenreis braucht Wasser!!");
        System.out.println(menuManager.getMenu(0).getComments());
    }
}