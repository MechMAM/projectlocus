package com.projectlocus.webservice.enums.space;

public enum SpaceTypes {
	
	EVENT_SPACE(1),
	AUDITORIUM(2),
	MEETING_ROOM(3),
	THEATER(4),
	COUNTRY_HOUSE(5);
	
	private int code;
	
	private SpaceTypes(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static SpaceTypes valueof(int code) {
		for (SpaceTypes value : SpaceTypes.values()) {
			if(value.getCode()==code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid SpaceType code");
	}

}