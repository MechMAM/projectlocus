package com.projectlocus.webservice.entities.enums.space;

public enum Differentials {
	
	FAMILY_BATHROOM(1),
	KIDS_PLACE(2),
	BABY_ROOM(3);
	
	private int code;
	
	private Differentials(int code) {
		this.code = code;		
	}
	
	public int getCode() {
		return code;
	}
	
	public static Differentials valueOf(int code) {
		for (Differentials value : Differentials.values()){
			if (value.getCode() == code) {
				return value;				
			}			
		}
		throw new IllegalArgumentException("Invalid Differentials code");
	}

}
