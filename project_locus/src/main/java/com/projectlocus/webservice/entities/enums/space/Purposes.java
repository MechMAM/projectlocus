package com.projectlocus.webservice.entities.enums.space;

public enum Purposes {
	
	LECTURES(1),
	CONVENTIONS(2),
	GRADUATIONS(3),
	SEMINARS(4),
	RECITALS(5),
	BUSINESS_MEETINGS(6);
	
	private int code;
	
	private Purposes(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static Purposes valueof(int code) {
		for (Purposes value : Purposes.values()) {
			if(value.getCode()==code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid Purpose code");
	}

}
