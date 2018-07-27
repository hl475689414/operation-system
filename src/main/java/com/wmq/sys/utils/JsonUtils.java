package com.wmq.sys.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import java.util.List;

public class JsonUtils {

	/**
	 * JSON字符串转Bean对象
	 * 
	 * @param json
	 * @param t
	 * @return
	 */
	public static <T> T JsonToBean(String json, Class<T> t) throws Exception {
		JSONObject jsonObject = null;
		T bean = null;
		jsonObject = JSONObject.fromObject(json);
		bean = (T) JSONObject.toBean(jsonObject, t);
		return bean;
	}

	/**
	 * JSON字符串转List
	 * 
	 * @param json
	 * @return
	 */
	public static List JsonToList(String json) throws Exception{
		JSONArray jsonArray = null;
		List list = null;
		jsonArray = (JSONArray) JSONSerializer.toJSON(json);
		return (List) JSONSerializer.toJava(jsonArray);
	}
	
	/**
	 * JSON字符串转JSONArray
	 * 
	 * @param json
	 * @return
	 */
	public static JSONArray JsonToJSONArray(String json) throws Exception{
		return JSONArray.fromObject(json);
	}

	/**
	 * 对象转JSON
	 * 
	 * @param object
	 * @return
	 */
	public static String BeanToJson(Object object)  throws Exception {
		JSONObject jsonObject = JSONObject.fromObject(object);
		return jsonObject.toString();
	}

	/**
	 * List转JSON
	 * 
	 * @param object
	 * @return
	 */
	public static String ListToJson(List list)  throws Exception {
		JSONArray json = new JSONArray();
		json.addAll(list);
		return json.toString();
	}
}
