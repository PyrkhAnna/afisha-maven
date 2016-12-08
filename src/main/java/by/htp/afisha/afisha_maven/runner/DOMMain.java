package by.htp.afisha.afisha_maven.runner;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import by.htp.afisha.afisha_maven.entity.event.Event;
import by.htp.afisha.afisha_maven.entity.event.Exhibition;
import by.htp.afisha.afisha_maven.entity.event.Film;
import by.htp.afisha.afisha_maven.entity.event.Genre;
import by.htp.afisha.afisha_maven.entity.event.Opera;
import by.htp.afisha.afisha_maven.entity.event.Singer;

public class DOMMain {

	public static void main(String[] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		
		Document document;
		Element afishaRoot;
		
		NodeList eventsNode = null;
		try{
			builder = factory.newDocumentBuilder();
			document = builder.parse(new InputSource("files/afisha.xml"));
			
			afishaRoot = document.getDocumentElement(); //root element
			System.out.println(afishaRoot.getAttribute("city"));
			
			eventsNode = afishaRoot.getElementsByTagName("events"); // get all events as Nodes (tags) ������ ��� ��� ���������� ���������
			
			 for (int i=0; i < eventsNode.getLength(); i++) {
				Element events = (Element) eventsNode.item(i); // get concrete event tag as Element from events tags �������� ��������� ���� � �������� ��� � ��������
				
				NodeList allEvents = events.getChildNodes(); // get all event tags(opera,film,exhibition)
				
				for (int j=0; j < allEvents.getLength(); j++) {
					Node eventNode = allEvents.item(j); // get concrete event Node (�������� ����� � �.�. � ���� ���� �����)
					
					Element eventElement;
					
					if (eventNode.getNodeType()==1){ //1 ������� ��� ���� �������� ������ ���������, � �� ������ �� ����������� ������� (������ ������������ ����� ����� ����������, ������� ���� ������������ ��� ����)
						eventElement = (Element) eventNode;
						Event eventEntity = buildEvent(eventElement, eventNode.getNodeName());
						System.out.println(eventEntity);
					}
				}
			 }
		} catch (SAXException|ParserConfigurationException|ParseException|IOException e){
			e.printStackTrace();
		}
		
	}
	
	public static String getSingleElementContent(Element element, String tagName){
		NodeList list = element.getElementsByTagName(tagName);
		Element el = (Element) list.item(0);
		String content = el.getTextContent().trim();
		
		return content;
	}

	public static Event buildEvent (Element element, String nodeName) throws ParseException {
		Event event =null;
		System.out.println("nodeName "+nodeName);
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		
		switch (nodeName) {
		case "opera":  
			Opera opera = new Opera();
			opera.setTitle(getSingleElementContent(element, "title")); 
			opera.setDate(df.parse(getSingleElementContent(element, "date")));
			opera.setTime(getSingleElementContent(element, "time"));
			opera.setPlace(getSingleElementContent(element, "place"));
			opera.setCost(new BigDecimal(getSingleElementContent(element, "cost")));
			opera.setSinger(new Singer(getSingleElementContent(element, "singer")));
			event = opera;
			break;
    	case "film":  
			Film film = new Film();
			film.setTitle(getSingleElementContent(element, "title")); 
			film.setDate(df.parse(getSingleElementContent(element, "date")));
			film.setTime(getSingleElementContent(element, "time"));
			film.setPlace(getSingleElementContent(element, "place"));
			film.setCost(new BigDecimal(getSingleElementContent(element, "cost")));
			film.setGenre(Genre.valueOf(getSingleElementContent(element, "genre").toUpperCase()));
			film.setRating(Double.parseDouble(getSingleElementContent(element, "rating")));
			event = film;
			break;
    	case "exhibition":  
    		Exhibition exhibition = new Exhibition();
    		exhibition.setTitle(getSingleElementContent(element, "title")); 
    		exhibition.setDate(df.parse(getSingleElementContent(element, "date")));
    		exhibition.setTime(getSingleElementContent(element, "time"));
    		exhibition.setPlace(getSingleElementContent(element, "place"));
    		exhibition.setCost(new BigDecimal(getSingleElementContent(element, "cost")));
			exhibition.setTopic(getSingleElementContent(element, "topic"));
			exhibition.setAuthor(getSingleElementContent(element, "author"));
			event = exhibition;
			break;
		}
		return event;
	}
	
}
