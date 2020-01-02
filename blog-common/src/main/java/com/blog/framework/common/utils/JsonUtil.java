package com.blog.framework.common.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author liuzw
 */
public class JsonUtil {

	private JsonUtil() {
	}

	private static Gson gson;

	public static synchronized Gson newInstance() {
		if (gson == null) {
			gson = new Gson();
		}
		return gson;
	}

	public static String toJson(Object obj) {
		return newInstance().toJson(obj);
	}

	public static <T> T toBean(String json, Class<T> clz) {
		return newInstance().fromJson(json, clz);
	}

	public static <T> Map<String, T> toMap(String json, Class<T> clz) {
		Map<String, JsonObject> map = newInstance().fromJson(json, new TypeToken<Map<String, JsonObject>>() {
		}.getType());
		Map<String, T> result = new HashMap<>(map.size());
		for (Map.Entry<String, JsonObject> entry : map.entrySet()) {
			result.put(entry.getKey(), newInstance().fromJson(entry.getValue(), clz));
		}
		return result;
	}


	public static <T> List<T> toList(String json, Class<T> clz) {
		JsonArray array = new JsonParser().parse(json).getAsJsonArray();
		List<T> list = new ArrayList<>();
		for (final JsonElement elem : array) {
			list.add(newInstance().fromJson(elem, clz));
		}
		return list;
	}

}
