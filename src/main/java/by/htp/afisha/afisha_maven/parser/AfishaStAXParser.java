package by.htp.afisha.afisha_maven.parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

import by.htp.afisha.afisha_maven.entity.event.Afisha;
import by.htp.afisha.afisha_maven.entity.event.AfishaTagName;
import by.htp.afisha.afisha_maven.entity.event.Event;
import by.htp.afisha.afisha_maven.entity.event.Exhibition;
import by.htp.afisha.afisha_maven.entity.event.Film;
import by.htp.afisha.afisha_maven.entity.event.Genre;
import by.htp.afisha.afisha_maven.entity.event.Opera;
import by.htp.afisha.afisha_maven.entity.event.Singer;

public class AfishaStAXParser {
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd"); 
	
	public static void main(String[] args) {
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		try {
			InputStream input = new FileInputStream("files/afisha.xml");
			XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
			List <Event> afishaList = process(reader);
			for (Event event:afishaList) {
				System.out.println(event);
				
			}
		} catch (XMLStreamException | FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	private static List<Event> process(XMLStreamReader reader) throws XMLStreamException {
		List <Event> afishaList = new ArrayList<Event>();
		Afisha afisha  = null;
		Event event = null;
		AfishaTagName elementName = null;
		while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
				case XMLStreamConstants.START_ELEMENT:
					elementName = AfishaTagName.getElementTagName(reader.getLocalName());
					switch (elementName) {
						case AFISHA:
							String attr = reader.getAttributeValue(reader.getNamespaceURI(), reader.getLocalName());
							if (attr!=null) afisha = new Afisha(attr);
							break;
						case FILM:  event = new Film();
							break;
						case OPERA:  event = new Opera();
							break;
						case EXHIBITION:  event = new Exhibition();
						break;	
					}
					break;
				case XMLStreamConstants.CHARACTERS:
					String text = reader.getText().trim();
					if (text.isEmpty()) {
						break;
					}
					switch(elementName){
						case TITLE:  
							event.setTitle(text);
							break;
						case DATE:  
							try {
								event.setDate(format.parse(text));
							} catch (ParseException e) {
								e.printStackTrace();
							}
							break;
						case TIME:  
							event.setTime(text);
							break;
						case PLACE:  
							event.setPlace(text);
							break;
						case COST:  
							event.setCost(new BigDecimal(Double.parseDouble(text)));
							break;
						case GENRE:  
							((Film) event).setGenre(Genre.valueOf(text.toUpperCase()));
							break;
						case RATING:  
							((Film) event).setRating(Double.parseDouble(text));
							break;
						case SINGER: 
							((Opera) event).setSinger(new Singer(text));
							break;
						case TOPIC:  
							((Exhibition) event).setTopic(text);
							break;
						case AUTHOR: 
							((Exhibition) event).setAuthor(text);
							break;
					}
					break;
			case XMLStreamConstants.END_ELEMENT:
				elementName = AfishaTagName.getElementTagName(reader.getLocalName());
				if (elementName.equals("FILM")||elementName.equals("OPERA")||elementName.equals("EXHIBITION")) {
					afishaList.add(event);
					afisha.addEvent(event);
				}
				break;
			}
		}
		return afishaList;
	}

}
