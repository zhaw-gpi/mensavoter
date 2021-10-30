package ch.zhaw.gpi.mensavoter;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Menu {
	
	private String type;
	@Id
	private String title;
	private String description;
	private double [] prices = new double[3];
	private int likes;
	private ArrayList<String> comments = new ArrayList<String>();

	public Menu(String type, String title, String description) {
		this.type = type;
		this.title = title;
		this.description = description;
	}
	
	public Menu() {
	}
	
	public void setPrice(double stud, double intern, double extern) {
		prices[0] = stud;
		prices[1] = intern;
		prices[2] = extern;
	}
	
	public int getLikes() {
		return likes;
	}
	
	public void like() {
		likes++;
	}
	
	public void addComment(String comment) {
		comments.add(comment);
	}
	
	public String getComments() {
		String c = "";
		for (String s: comments) {
			c += "-"+s+"<br>";
		}
		return c;
	}
	
	public String getPrices() {
		return prices[0]+" STUD, "+prices[1]+" INT, "+prices[2]+" EXT";
	}
	
	public String getType() {
		return type;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
}
