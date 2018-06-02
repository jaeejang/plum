package org.plum.tools.ui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Utils {

	
	public static List<String> Seasons(int round){
		ArrayList<String> seasons = new ArrayList<String>();
		Calendar date = Calendar.getInstance();
		for (int i = 0; i < (round); i++) {
			seasons.add(SeasonFormat(date));
			date.add(Calendar.MONTH, -3);
		}
		return seasons;
		
	}
	
	private static String SeasonFormat(Calendar calendar){
		int year = calendar.get(Calendar.YEAR);
		int season = calendar.get(Calendar.MONTH)/3 + 1;		
		return String.format("%d0%d", year,season);
	}
}
