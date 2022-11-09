package com.projectlocus.webservice.entities.enums.space;

public enum Services {
	
	STAFF(1),
	VIDEO(2),
	AUDIO(3),
	ILLUMINATION(4),
	COFFEE_BREAK(5);
	
	private int code;
	
	private Services(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static Services valueof(int code) {
		for (Services value : Services.values()) {
			if(value.getCode()==code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid Service code");
	}

}