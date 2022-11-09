package com.projectlocus.webservice.entities.enums.space;

public enum Accessibilities {
	
	STEPLESS_ENTRY(1),
	DOOR_LARGER_81CM(2),
	STEPLESS_STAGE(3),
	ACESSIBLE_BATHROOM(4),
	PWD_PARKING(5);
	
	private int code;
	
	private Accessibilities(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static Accessibilities valueof(int code) {
		for (Accessibilities value : Accessibilities.values()) {
			if(value.getCode()==code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid Accessibility code");
	}

}