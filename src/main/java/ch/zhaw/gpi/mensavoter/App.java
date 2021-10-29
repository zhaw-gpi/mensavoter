package ch.zhaw.gpi.mensavoter;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.google.gson.Gson;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;

@SpringBootApplication
public class App implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // In Konsole ausgeben
        Menu m1 = new Menu("Favorite", "Poulet", "mit Kartoffeln");
        m1.setPrice(7.0, 8.0, 14.0);
        m1.printMenu();
        
        // JSON lesen und ausgeben
        Gson gson = new Gson();
        File file = ResourceUtils.getFile("classpath:20201120.json");
        FileInputStream fileInputStream = new FileInputStream(file);
        Reader reader = new InputStreamReader(fileInputStream, "UTF8");
        
        Menu[] menus = gson.fromJson(reader, Menu[].class);
        reader.close();

        for (int i=0; i<menus.length; i++) {
            menus[i].printMenu();
            System.out.println("");
        }
    }
}