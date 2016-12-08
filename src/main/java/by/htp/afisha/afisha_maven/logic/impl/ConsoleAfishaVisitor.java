package by.htp.afisha.afisha_maven.logic.impl;

import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.htp.afisha.afisha_maven.entity.event.Afisha;
import by.htp.afisha.afisha_maven.entity.event.Event;
import by.htp.afisha.afisha_maven.logic.AfishaVisitor;

public class ConsoleAfishaVisitor implements AfishaVisitor {
	private final static Logger Log  = LogManager.getLogger();
	
	public void displayAfisha(Afisha afisha){
		for (Event event:afisha.getEvents()){
			System.out.println(event);
		}
		Log.info("Afisha is displayed on console");

	}
	public void displayAfisha(Afisha afisha, Date date){
		for (Event event:afisha.getEvents()){
			if (date.getYear()==event.getDate().getYear())
				if (date.getMonth()==event.getDate().getMonth())
					if (date.getDay()==event.getDate().getDay()) 
						System.out.println(event);
		}
		Log.info("Afisha is displayed on console for certain day");

	}
	public void displayAfisha(Afisha afisha, Date begin, Date end){
		for (Event event:afisha.getEvents()){
			if (begin.before(event.getDate())&&end.after(event.getDate()))
				System.out.println(event);
		}
		Log.info("Afisha is displayed on console for certain period of time");

	}
}
