package by.htp.afisha.afisha_maven.entity.event;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Opera extends Event{
	private Singer singer;
	private final static Logger Log  = LogManager.getLogger();
	
	public Opera() {
		super();
		Log.info("New opera is created");
	}

	public Opera(String title, Date date, String time, String place, BigDecimal cost, Singer singer) {
		super(title, date, time, place, cost);
		this.singer = singer;
		Log.info("New opera is created");

	}

	public Singer getSinger() {
		return singer;
	}

	public void setSinger(Singer singer) {
		this.singer = singer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((singer == null) ? 0 : singer.hashCode());
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
		Opera other = (Opera) obj;
		if (singer == null) {
			if (other.singer != null)
				return false;
		} else if (!singer.equals(other.singer))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Opera "+super.toString()+"[singer=" + singer + "]";
	}
	
}
