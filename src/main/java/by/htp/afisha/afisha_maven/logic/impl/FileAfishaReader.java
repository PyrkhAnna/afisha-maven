package by.htp.afisha.afisha_maven.logic.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.afisha.afisha_maven.entity.event.Afisha;
import by.htp.afisha.afisha_maven.entity.event.Exhibition;
import by.htp.afisha.afisha_maven.entity.event.Film;
import by.htp.afisha.afisha_maven.entity.event.Genre;
import by.htp.afisha.afisha_maven.entity.event.Opera;
import by.htp.afisha.afisha_maven.entity.event.Singer;
import by.htp.afisha.afisha_maven.logic.AfishaReader;

public class FileAfishaReader implements AfishaReader{
	private final static Logger Log  = LogManager.getLogger();
	
	@Override
	public Afisha readAfisha(String path){
		Log.info("Afisha is read from file");
		File file = new File(path);
		FileReader fr = null;
		Afisha afisha = null;
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line,city = null;
			if((line = br.readLine()) != null){
				city = line;
			   }
			afisha = new Afisha(city);
		
			SimpleDateFormat format = new SimpleDateFormat(); 
			format.applyPattern("dd.MM.yyyy"); 
			Date date;
			BigDecimal cost;
			Singer singer;
			Opera opera;
			Film film;
			Exhibition exhibition;
			while((line = br.readLine()) != null){
				String[] str = line.split(";");
				switch (str[0]) {
					case "Opera":  
						opera = new Opera();
						opera.setTitle(str[1]); 
						date = format.parse(str[2]);
						opera.setDate(date);
						opera.setTime(str[3]);
						opera.setPlace(str[4]);
						cost = new BigDecimal(str[5]);
						opera.setCost(cost);
						singer = new Singer(str[6]);
						opera.setSinger(singer);
						afisha.addEvent(opera);
		        	break;
					case "Film":  
						film = new Film();
						film.setTitle(str[1]); 
						date = format.parse(str[2]);
						film.setDate(date);
						film.setTime(str[3]);
						film.setPlace(str[4]);
						double d=Double.parseDouble(str[5]);
						cost = new BigDecimal(d);
						film.setCost(cost);
						film.setGenre(Genre.valueOf(str[6]));
						film.setRating(Double.parseDouble(str[7]));
						afisha.addEvent(film);
					break;
					case "Exhibition":  
						exhibition = new Exhibition();
						exhibition.setTitle(str[1]); 
						date = format.parse(str[2]);
						exhibition.setDate(date);
						exhibition.setTime(str[3]);
						exhibition.setPlace(str[4]);
						cost = new BigDecimal(str[5]);
						exhibition.setCost(cost);
						exhibition.setTopic(str[6]);
						exhibition.setAuthor(str[7]);
						afisha.addEvent(exhibition);
					break;
				}
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (ParseException e2) {
			e2.printStackTrace();
		} catch (IOException e) {
			Log.debug(e);
		}
		
		return afisha;
	}

}
