package com.hzjc.hz2004.util;

import java.util.List;
import java.util.Map;
import com.hzjc.hz2004.base.controller.JSONModel;
import com.hzjc.json.LongDeserializer;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
/**
 *json操作
 * @author 刘继凤 E-mail:liujifeng#liujifeng.net Date: 2016年8月30日 下午1:35:54
 */
public class JSONUtil {
	/**
	 * 对象转成json
	 * 
	 * @author   刘继凤  E-mail:liujifeng#liujifeng.net
	 * @param obj
	 * @return 
	 * Date:    2016年8月30日 下午1:42:46
	 */
	public static String toJSON(Object obj) {
		return toJSON(obj, DateHelper.PRINT_DATETIME_STYLE2);
	}

	/**
	 * 对象转成json
	 * @author   刘继凤  E-mail:liujifeng#liujifeng.net
	 * @param obj
	 * @param dateFormat
	 * 时间格式化方式
     * @return
     */
	public static String toJSON(Object obj,String dateFormat) {
		if(CommonUtil.isEmpty(dateFormat)){
			return toJSON(obj);
		}
		Gson gson = new GsonBuilder()
				//.serializeNulls()
				.setLongSerializationPolicy(LongSerializationPolicy.STRING)
				.setDateFormat(dateFormat)
				.create();
		return gson.toJson(obj);
//		ObjectMapper mapper = new ObjectMapper();
//		//为空不序列化
//		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//		mapper.setDateFormat(new SimpleDateFormat(dateFormat));
//		//mapper.enable(SerializationFeature.INDENT_OUTPUT);//美化输出
//		try {
//			return mapper.writeValueAsString(obj);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//			return null;
//		}
	}

	/**
	 * 获取json指定key值
	 * @param json
	 * @param key
	 * @return
	 */
	public static String getJSONValue(String json,String key){
		Map<String,String>  jmap=JSONUtil.fromJSON(json,Map.class);
		if(jmap==null){
			return null;
		}
		return jmap.get(key);
	}


	/**
	 * json转成java对象
	 *
	 * @author   刘继凤  E-mail:liujifeng#liujifeng.net
	 * @param json
	 * @param clazz
	 * @return
	 * Date:    2016年8月30日 下午1:43:04
	 */
	public static <T> T fromJSON(String json, Class<T> clazz) {
		Gson gson = new GsonBuilder().
				registerTypeAdapter(Double.class, new JsonSerializer<Double>() {
					//解决gson将Integer默认转换成Double的问题
					@Override
					public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
						if (src == src.longValue()) {
							return new JsonPrimitive(src.longValue());
						}
						return new JsonPrimitive(src);
					}
				}).create();
		return gson.fromJson(json,clazz);
	}

	/**
	 * json数组转成javaList对象
	 *
	 * @author   刘继凤  E-mail:liujifeng#liujifeng.net
	 * @param json
	 * @param clazz
	 * @return
	 * Date:    2016年8月30日 下午1:43:04
	 */
	public static <T>List<T> fromArrayJSON(String json, Class<T> clazz) {
		Gson gson=new Gson();
		Type type = new TypeToken<List<T>>() {}.getType();
		List<T> list=gson.fromJson(json,type);
		List<T> newlist=new ArrayList<T>();
		for(T f:list){
			if(f.getClass()==clazz){
				newlist.add(f);
			}else{
				newlist.add(JSONUtil.fromJSON(JSONUtil.toJSON(f),clazz));
			}
		}
		return newlist;
	}

	/**
	 * JSONModel 转成指定对像的 list jsonmodel
	 *
	 * @author   刘继凤  E-mail:liujifeng#liujifeng.net
	 * @param model
	 * @param clazz
	 * @return
	 * Date:    2016年8月30日 下午1:43:04
	 */
	public static <T>JSONModel toClzJsonModel(JSONModel model, Class<T> clazz)
	{
		if(model!=null&&CommonUtil.isNotEmpty(model.getList())) {
			List<T> list =model.getList(clazz);
			model.setList(list);
		}
		return model;
	}
	
	/**
	 * 将data转换成指定的对象
	 * @param <E>
	 * @param e
	 * @return
	 */
	static public <T> List<T> getJsonData(String data, TypeToken<List<T>> toKen){
		if(data==null) return null;
		
		GsonBuilder build = new GsonBuilder().serializeNulls();
		build.setLongSerializationPolicy(LongSerializationPolicy.STRING).create();
		build.registerTypeAdapter(Long.class, new LongDeserializer());
		
		Gson gson = build.create();
		
		List<T> ex = gson.fromJson(data, toKen.getType());
		return ex;
	}
	
	static public <T> List<T> getJsonData(TypeToken<List<T>> toKen,String postData){
		if(postData==null) return null;
		GsonBuilder build = new GsonBuilder().serializeNulls();
		build.setLongSerializationPolicy(LongSerializationPolicy.STRING).create();
		build.registerTypeAdapter(Long.class, new LongDeserializer());
		
		Gson gson = build.create();
		
		List<T> ex = gson.fromJson(postData, toKen.getType());

		return ex;
	}
	
	static public <T> T getJsonData(String jsonString, String dateStyle, Class<T> classOfT){
		GsonBuilder build = new GsonBuilder().serializeNulls();
		build.setLongSerializationPolicy(LongSerializationPolicy.STRING).create();
		build.registerTypeAdapter(Long.class, new LongDeserializer());
		if(dateStyle != null) {
			build.setDateFormat(dateStyle);
		}
		
		Gson gson = build.create();
		return gson.fromJson(jsonString, classOfT);
	}
}

