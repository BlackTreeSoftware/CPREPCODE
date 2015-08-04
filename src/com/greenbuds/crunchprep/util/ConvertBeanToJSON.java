/**
 * 
 */
package com.greenbuds.crunchprep.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * @author rrajulapati
 * 
 */
public class ConvertBeanToJSON {

	public static JSONObject ConvertObjectToMap(Object obj)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, JSONException {
		JSONObject jsonObject = new JSONObject();
		Class<?> pomclass = obj.getClass();
		pomclass = obj.getClass();
		Method[] methods = obj.getClass().getMethods();

		for (Method m : methods) {
			if (m.getName().startsWith("get")
					&& !m.getName().startsWith("getClass")) {
				Object value = (Object) m.invoke(obj);
				jsonObject.put(m.getName().substring(3), value);
			}
		}
		
		return jsonObject;
	}

}
