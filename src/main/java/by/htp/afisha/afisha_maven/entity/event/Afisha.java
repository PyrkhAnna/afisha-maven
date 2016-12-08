package by.htp.afisha.afisha_maven.entity.event;

import java.util.HashSet;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Afisha {

	private final static Logger Log  = LogManager.getLogger();
	private Set<Event> events;
	private final String city;
	
	public Afisha (String city) {
		events = new HashSet<Event>();
		this.city = city;
		Log.info("New afisha is created");
	}
	
	public boolean addEvent(Event event){
		if (event!=null) {
			Log.info("New event is added to afisha ");
			return events.add(event);
		}
		else {
			Log.info("New event isn't added to afisha ");
			return false;}
	}
	
	public Set<Event> getEvents(){ 
		return events;
	}

	public String getCity() {
		return this.city;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public String toString() {
		return "Afisha "+city+" [events =" + events + "]";
	}
	
}
