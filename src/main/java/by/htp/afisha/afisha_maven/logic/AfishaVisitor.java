package by.htp.afisha.afisha_maven.logic;

import java.util.Date;

import by.htp.afisha.afisha_maven.entity.event.Afisha;

public interface AfishaVisitor {
	public void displayAfisha(Afisha afisha);
	public void displayAfisha(Afisha afisha, Date date);
	public void displayAfisha(Afisha afisha, Date begin, Date end);
	
}
