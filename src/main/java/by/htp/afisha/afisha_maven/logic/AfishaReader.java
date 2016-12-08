package by.htp.afisha.afisha_maven.logic;

import java.io.IOException;

import by.htp.afisha.afisha_maven.entity.event.Afisha;

public interface AfishaReader {
	public Afisha readAfisha(String path) throws IOException;
}
