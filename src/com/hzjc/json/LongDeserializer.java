package com.hzjc.json;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class LongDeserializer implements JsonDeserializer<java.lang.Long> {
	public Long deserialize(JsonElement arg0, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		if(arg0.getAsString()==null || arg0.getAsString().trim().equals("")){
			return null;
		}
		
		return new Long(arg0.getAsString());
	}
}
