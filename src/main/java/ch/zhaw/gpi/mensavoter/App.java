package ch.zhaw.gpi.mensavoter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    private MenuRepository mr;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {        
        // Initiale Daten in DB persistieren (Alternative wäre data.sql, was aber mit Arrays ... ist)
        Menu m = new Menu("Dessert", "Obstsalat", "Ein typisches 80er-Jahre-Dessert");
        m.setPrice(4.0, 5.0, 9.0);
        m.like();
        m.addComment("Saugut");
        mr.save(m);

        m = new Menu("Favorite", "Hot and Spicy Chili-Lemon-chicken", "Trockenreis, Broccoli mit Peperoncini");
        m.setPrice(7.0,8.0,14.0);
        mr.save(m);

        m = new Menu("Kitchen", "Lachstranche auf grilliertem Kohlrabi", "Senfrahmsauce, Kartoffeln Risolee");
        m.setPrice(12.0,13.0,19.0);
        mr.save(m);

        m = new Menu("Green", "Orientalischer Couscous", "Minzjoghurt, Spinat Falafel, Orientalisches Gemüse, Pfefferminze");
        m.setPrice(7.0,8.0,14.0);
        mr.save(m);


        // Daten lesen und in Konsole ausgeben
        List<Menu> menus = mr.findAll();
        menus.forEach(menu -> System.out.println(menu.getNiceOutput()));
    }
}