package by.htp.afisha.afisha_maven.entity.event;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Exhibition extends Event{
	private String topic;
	private String author;
	private final static Logger Log  = LogManager.getLogger();
	
	public Exhibition() {
		super();
		Log.info("New exhibition is created");
	
	}
	public Exhibition(String title, Date date, String time, String place, BigDecimal cost, String topic, String author) {
		super(title, date, time, place, cost);
		this.topic = topic;
		this.author = author;
		Log.info("New exhibition is created");
	}

	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((topic == null) ? 0 : topic.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exhibition other = (Exhibition) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (topic == null) {
			if (other.topic != null)
				return false;
		} else if (!topic.equals(other.topic))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Exhibition "+super.toString()+"[topic=" + topic + ", author=" + author + "]";
	}
	
}
