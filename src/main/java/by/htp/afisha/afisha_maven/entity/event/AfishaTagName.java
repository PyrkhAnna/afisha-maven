package by.htp.afisha.afisha_maven.entity.event;

public enum AfishaTagName {
	AFISHA, EVENTS, FILM, OPERA, EXHIBITION, TITLE, DATE, TIME, PLACE, COST, GENRE, RATING, SINGER, TOPIC, AUTHOR;
	
	public static AfishaTagName getElementTagName(String element){
		switch (element) {
		case "afisha": return AFISHA;
		case "events": return EVENTS;
		case "film": return FILM;
		case "opera": return  OPERA;
		case "exhibition": return EXHIBITION;
		case "title": return TITLE;
		case "date": return DATE;
		case "time": return TIME;
		case "place": return PLACE;
		case "cost": return COST;
		case "genre": return GENRE;
		case "rating": return RATING;
		case "singer": return SINGER;
		case "topic": return TOPIC;
		case "author": return AUTHOR;
		default: throw new EnumConstantNotPresentException (AfishaTagName.class, element);
		}
	}
}
