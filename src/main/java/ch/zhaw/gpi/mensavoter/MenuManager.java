package ch.zhaw.gpi.mensavoter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

@Component
public class MenuManager {
   private Menu [] menus;

   public void loadMenus(String date) {
      try {
         Gson gson = new GsonBuilder().create();
         File file = ResourceUtils.getFile("classpath:" + date+".json");
         FileInputStream fis = new FileInputStream(file);
         Reader reader =  new InputStreamReader(fis, "UTF8");         
         menus = gson.fromJson(reader, Menu[].class);
         reader.close(); 
         System.out.println("loaded "+menus.length+" menus");
      } catch (Exception e) {
          System.out.println("failed to load menus for "+date);
      }
   }
   
	public Menu getMenu(int no) {
		if (no < menus.length) {
			return menus[no];
		}
		return null;
	}
	
	public int getMenuCount() {
		if (menus == null) {
			return 0;
		} 
		return menus.length;
	}
}