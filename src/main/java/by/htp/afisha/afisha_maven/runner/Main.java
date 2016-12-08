package by.htp.afisha.afisha_maven.runner;

/*import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;*/
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.afisha.afisha_maven.entity.event.*;
import by.htp.afisha.afisha_maven.logic.AfishaVisitor;
import by.htp.afisha.afisha_maven.logic.impl.ConsoleAfishaVisitor;
import by.htp.afisha.afisha_maven.logic.impl.FileAfishaVisitor;
import by.htp.afisha.afisha_maven.logic.impl.FileAfishaReader;

public class Main {

	private final static Logger Log  = LogManager.getLogger();
		
	public static void main(String[] args) {
		ResourceBundle bundleRu = ResourceBundle.getBundle("log4j2");
	
		// Afisha takes information from this file
		
	/* Event film = new Film("White shark", new Date(),"18:30","cinema", new BigDecimal(23.05), Genre.HORROR, 8.5);
		Event opera = new Opera("Madam Butterfly", new Date(),"19:30","Big Opera Theatre", new BigDecimal(10.05), new Singer("Pupkin"));
		Event exhibition = new Exhibition("Animals", new Date(),"21:30","museum", new BigDecimal(5.1), "Cat", "City");
		Afisha afisha = new Afisha("Minsk");
		System.out.println(film);
		System.out.println(opera);
		System.out.println(exhibition);
		afisha.addEvent(film);
		afisha.addEvent(opera);
		afisha.addEvent(exhibition);
		System.out.println(afisha);*/
		
		// Afisha takes information from other file
		
		SimpleDateFormat format = new SimpleDateFormat(); 
		format.applyPattern("yyyy-mm-dd"); 
		Date date = null;
		Date date1 = null;
		try {
			date = format.parse("2016-10-16");
			date1 = format.parse("2016-11-19");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		AfishaVisitor visitor1 = new FileAfishaVisitor();
		FileAfishaReader rf=new FileAfishaReader();
		String path = bundleRu.getString("path.read");
		Afisha afisha = rf.readAfisha(path);
		visitor1.displayAfisha(afisha);
		visitor1.displayAfisha(afisha,date1);
		visitor1.displayAfisha(afisha,date,date1);
		AfishaVisitor visitor = new ConsoleAfishaVisitor();
		visitor.displayAfisha(afisha);
		visitor.displayAfisha(afisha,date1);
		visitor.displayAfisha(afisha,date,date1);
		
	}
}
