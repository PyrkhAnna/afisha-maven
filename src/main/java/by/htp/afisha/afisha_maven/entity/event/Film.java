package by.htp.afisha.afisha_maven.entity.event;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Film extends Event{
	private Genre genre;
	private double rating;
	private final static Logger Log  = LogManager.getLogger();
	
	public Film() {
		super();
		Log.info("New film is created");

	}
	public Film(String title, Date date, String time, String place, BigDecimal cost, Genre genre, double rating) {
		super(title, date, time, place, cost);
		this.genre = genre;
		this.rating = rating;
		Log.info("New film is created");

	}
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		long temp;
		temp = Double.doubleToLongBits(rating);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Film other = (Film) obj;
		if (genre != other.genre)
			return false;
		if (Double.doubleToLongBits(rating) != Double.doubleToLongBits(other.rating))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Film "+super.toString()+"[genre=" + genre + ", rating=" + rating + "]";
	}
	
}
