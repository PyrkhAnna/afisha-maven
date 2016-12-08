package by.htp.afisha.afisha_maven.parser;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import by.htp.afisha.afisha_maven.entity.event.Afisha;
import by.htp.afisha.afisha_maven.entity.event.Event;
import by.htp.afisha.afisha_maven.entity.event.Exhibition;
import by.htp.afisha.afisha_maven.entity.event.Film;
import by.htp.afisha.afisha_maven.entity.event.Genre;
import by.htp.afisha.afisha_maven.entity.event.Opera;
import by.htp.afisha.afisha_maven.entity.event.Singer;

public class AfishaXMLHandler extends DefaultHandler{
	private Afisha afisha;
	private String value;
	private Event event;
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd"); 
	
	
	@Override
	public void startDocument() throws SAXException {
		System.out.println("startDoc");
		super.startDocument();
	}
	@Override
	public void endDocument() throws SAXException {
		System.out.println("endDoc");
		System.out.println(afisha);
		super.endDocument();
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.println(qName);
		if (qName.equals("afisha")){
			String type = attributes.getValue("city");
			if (type!=null) {afisha = new Afisha(type); System.out.println(type);}
		}
		value = qName;
		if (value!=null){
			switch (value){
				case "film":  event = new Film();
				break;
				case "opera":  event = new Opera();
				break;
				case "exhibition":  event = new Exhibition();
				break;
			}
		}
	}
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("end  "+qName);
		if (qName.equals("film")||qName.equals("opera")||qName.equals("exhibition")) {
			afisha.addEvent(event);
		}
		value = "";
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		switch(value){
			case "title":  
				event.setTitle(new String(ch, start, length));
			break;
			case "date":  
				try {
					event.setDate(format.parse(new String(ch, start, length)));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			break;
			case "time":  
				event.setTime(new String(ch, start, length));
			break;
			case "place":  event.setPlace(new String(ch, start, length));
			break;
			case "cost":  
				event.setCost(new BigDecimal(Double.parseDouble(new String(ch, start, length))));
			break;
			case "genre":  
				String s = new String(ch, start, length);
				((Film) event).setGenre(Genre.valueOf(s.toUpperCase()));
			break;
			case "rating":  
				((Film) event).setRating(Double.parseDouble(new String(ch, start, length)));
			break;
			case "singer": 
				((Opera) event).setSinger(new Singer(new String(ch, start, length)));
			break;
			case "topic":  
				((Exhibition) event).setTopic(new String(ch, start, length));
			break;
			case "author": 
				((Exhibition) event).setAuthor(new String(ch, start, length));
			break;
		}
	}
}
