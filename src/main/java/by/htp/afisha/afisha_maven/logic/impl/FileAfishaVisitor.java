package by.htp.afisha.afisha_maven.logic.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.afisha.afisha_maven.entity.event.Afisha;
import by.htp.afisha.afisha_maven.entity.event.Event;
import by.htp.afisha.afisha_maven.logic.AfishaVisitor;

public class FileAfishaVisitor implements AfishaVisitor{
	private final static Logger Log  = LogManager.getLogger();
	private ResourceBundle bundleRu = ResourceBundle.getBundle("log4j2");
	private String path = bundleRu.getString("path.write");

	@Override
	public void displayAfisha(Afisha afisha) {
		File file = new File(path);
		FileWriter fw = null;
		try {
			fw = new FileWriter(file,true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(afisha.getCity()+"\r\n");
			bw.flush();
			for (Event event:afisha.getEvents()){
				bw.write(event.toString()+"\r\n");
				bw.flush();
			}
		} catch (IOException e) {
			Log.debug(e);
		}
		Log.info("Afisha is wroten in file");
	}

	@Override
	public void displayAfisha(Afisha afisha, Date date) {
		File file = new File(path);
		FileWriter fw = null;
		try {
			fw = new FileWriter(file,true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(afisha.getCity()+"\r\n");
			bw.flush();
			for (Event event:afisha.getEvents()){
				if (date.getYear()==event.getDate().getYear())
					if (date.getMonth()==event.getDate().getMonth())
						if (date.getDay()==event.getDate().getDay()) {
							bw.write(event.toString()+"\r\n");
							bw.flush();
						}
			}
		} catch (IOException e) {
			Log.debug(e);
		}
		Log.info("Afisha is wroten in file for certain day");
	}

	@Override
	public void displayAfisha(Afisha afisha, Date begin, Date end) {
		File file = new File(path);
		FileWriter fw = null;
		try {
			fw = new FileWriter(file,true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(afisha.getCity()+"\r\n");
			bw.flush();
			for (Event event:afisha.getEvents()){
				if (begin.before(event.getDate())&&end.after(event.getDate())){
					bw.write(event.toString()+"\r\n");
					bw.flush();
				}
			}
		} catch (IOException e) {
			Log.debug(e);
		}
		Log.info("Afisha is wroten in file for certain period of time");
	}
}
