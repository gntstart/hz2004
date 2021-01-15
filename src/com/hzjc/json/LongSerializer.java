package com.hzjc.json;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class LongSerializer implements JsonSerializer<Long> {
	public JsonElement serialize(Long arg0, Type arg1,JsonSerializationContext arg2) {
		return new JsonPrimitive(String.valueOf(arg0));
	} 
}
