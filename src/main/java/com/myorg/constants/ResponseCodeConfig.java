package com.myorg.constants;

import com.myorg.base.objects.ResponseCode;

public enum ResponseCodeConfig implements ResponseCode{
	
	FETCH_USER_SUCCESS("1000","User Fetched Successfully");
	
	private final String code;
	private final String descrption;

	ResponseCodeConfig(String code, String descrption) {
		this.code = code;
		this.descrption = descrption;
	}

	
	public String getCode() {
		return code;
	}
	
	public String getDescrption() {
		return descrption;
	}

}
