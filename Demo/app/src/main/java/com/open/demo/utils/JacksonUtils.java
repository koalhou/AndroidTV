
package com.open.demo.utils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.open.demo.exceptions.JacksonDeserializeException;
import com.open.demo.exceptions.JacksonEncoderException;
import com.open.demo.exceptions.JacksonSerializeException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class JacksonUtils {

	/**
	 * jackson映射器
	 */
	private static ObjectMapper iMapper = new ObjectMapper();

	private static String[] DECODER_CHAR = new String[] { "\b", "\t", "\n",
			"\f", "\r", "\"", "\\" };
	private static String[] ENCODER_CHAR = new String[] { "\\b", "\\t", "\\n",
			"\\f", "\\r", "\\\"", "\\\\" };

	/**
	 * 将给定的目标对象根据指定的条件参数转换成 {@code JSON} 格式的字符串。
	 *
	 * @param obj
	 *            目标对象。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static String toJson(Object obj) throws IOException {
		return iMapper.writeValueAsString(obj);
	}

	/**
	 * 将给定的 {@code JSON} 字符串转换成指定的类型对象。<strong>此方法通常用来转换普通的 {@code JavaBean}
	 * 对象。</strong>
	 *
	 * @param json
	 *            给定的 {@code JSON} 字符串。
	 * @param clz
	 *            要转换的目标类。
	 * @return 给定的 {@code JSON} 字符串表示的指定的类型对象。
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 */
	public static <T> T fromJson(String json, Class<T> clz) throws IOException {
		T o = iMapper.readValue(json, clz);
		return o;
	}

	/**
	 * 将给定的目标对象根据指定的条件参数转换成 {@code JSON} 格式的字符串。
	 * 方法会将抛出的异常封装为服务器内部异常抛出，调用者不需处理转换过程中出现的异常
	 *
	 * @param obj
	 *            目标对象。
	 * @return 目标对象的 {@code JSON} 格式的字符串。
	 */
	public static String toJsonRuntimeException(Object obj) {
		String str = new String();
		try {
			str = iMapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new JacksonSerializeException(e);
		}
		return str;
	}

	/**
	 * 将给定的 {@code JSON} 字符串转换成指定的类型对象。<strong>此方法通常用来转换普通的 {@code JavaBean}
	 * 对象。</strong> 方法会将抛出的异常封装为服务器内部异常抛出，调用者不需处理转换过程中出现的异常
	 *
	 * @param json
	 *            给定的 {@code JSON} 字符串。
	 * @param clz
	 *            要转换的目标类。
	 * @return 给定的 {@code JSON} 字符串表示的指定的类型对象。
	 */
	public static <T> T fromJsonRuntimeException(String json, Class<T> clz) {
		T t = null;
		try {
			t = iMapper.readValue(json, clz);
		} catch (Exception e) {
			throw new JacksonDeserializeException(e);
		}
		return t;
	}

	/**
	 * 将json格式转换成map对象
	 *
	 * @param json
	 *            给定的 {@code JSON} 字符串。
	 * @return objMap 转换成功的MAP对象
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 */
	public static Map<?, ?> jsonToMap(String json) throws IOException {
		Map<?, ?> o = iMapper.readValue(json, Map.class);
		return o;
	}

	/**
	 * 将json格式转换成map对象

	 * @param json
	 *            给定的 {@code JSON} 字符串。
	 * @return objMap 转换成功的MAP对象
	 */
	public static Map<?, ?> jsonToMapRuntimeException(String json) {
		try {
			Map<?, ?> o = iMapper.readValue(json, Map.class);
			return o;
		} catch (Exception e) {
			throw new JacksonDeserializeException(e);
		}
	}

	/**
	 * 将json格式转换成list对象
	 *
	 * @param json
	 *            给定的 {@code JSON} 字符串。
	 * @return objList 转换成功的LIST对象
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 *
	 */
	public static List<?> jsonToList(String json) throws IOException {
		List<?> o = iMapper.readValue(json, List.class);
		return o;
	}

	/**
	 * 将json格式转换成list对象
	 *
	 * @param json
	 *            给定的 {@code JSON} 字符串。
	 * @return objList 转换成功的LIST对象
	 */
	public static List<?> jsonToListRuntimeException(String json) {
		try {
			List<?> o = iMapper.readValue(json, List.class);
			return o;
		} catch (Exception e) {
			throw new JacksonDeserializeException(e);
		}
	}

	/**
	 * 将传入的对象中属性为String类型的字符编码
	 *
	 * @param obj
	 *            编码对象(支持类型JavaBean,String,Collection,Map)
	 * @return 编码后的对象
	 * @throws JacksonEncoderException
	 *             jackson编码异常
	 */
	@SuppressWarnings("unchecked")
	public static Object jsonEncoder(Object obj) throws JacksonEncoderException {
//		logger.debug("obj class is:" + obj.getClass());
		if (obj instanceof String) {
			return jsonCoder((String) obj, true);
		} else if (obj instanceof Map<?, ?>) {
//			logger.debug("Object is " + obj.getClass());
			Set<?> entries = ((Map<?, ?>) obj).entrySet();
			Iterator<?> it = entries.iterator();
			while (it.hasNext()) {
				Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>) it.next();
//				logger.debug("Key is [" + entry.getKey() + "]");
				entry.setValue(jsonEncoder(entry.getValue()));
			}
		} else if (obj instanceof Collection<?>) {
//			logger.debug("Object is " + obj.getClass());
			Collection<?> c = (Collection<?>) obj;
			Iterator<?> it = c.iterator();
			while (it.hasNext()) {
				jsonEncoder(it.next());
			}
		} else {
			try {
				Field[] fields = obj.getClass().getFields();
				for (Field f : fields) {
					jsonCoder(obj, f);
				}
			} catch (IllegalAccessException e) {
//				logger.error("json编码时发生异常" + e.getMessage());
				throw new JacksonEncoderException(e);
			}
		}
		return obj;
	}

	/**
	 * @param obj
	 * @param f
	 * @throws IllegalAccessException
	 * @throws JacksonEncoderException
	 */
	@SuppressWarnings("unchecked")
	private static Object jsonCoder(Object obj, Field f)
			throws IllegalAccessException, JacksonEncoderException {
		Object o = FieldUtils.readField(f, obj);
		if (o instanceof String) {
			FieldUtils.writeField(f, obj, jsonCoder((String) o, true));
		} else if (o instanceof Collection<?>) {
//			logger.debug("Field [" + f.getName() + "] is " + o.getClass());
			Collection<?> c = (Collection<?>) o;
			Iterator<?> it = c.iterator();
			while (it.hasNext()) {
				jsonEncoder(it.next());
			}
		} else if (o instanceof Map<?, ?>) {
//			logger.debug("Field [" + f.getName() + "] is " + o.getClass());
			Set<?> entries = ((Map<?, ?>) o).entrySet();
			Iterator<?> it = entries.iterator();
			while (it.hasNext()) {
				Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>) it.next();
//				logger.debug("Key is [" + entry.getKey() + "]");
				entry.setValue(jsonEncoder(entry.getValue()));
			}
		} else if (o instanceof Integer || o instanceof Byte
				|| o instanceof Boolean || o instanceof Long
				|| o instanceof Double || o instanceof Character
				|| o instanceof Short || o instanceof Float
				|| o instanceof Number) {
			return obj;
		} else {
			throw new JacksonEncoderException("不支持类型：" + f.getClass());
		}
		return obj;
	}

	/**
	 * json转义方法</br>
	 *
	 * JacksonUtils.jsonCoder(null,true) = null;
	 * JacksonUtils.jsonCoder(null,false) = null;
	 * JacksonUtils.jsonCoder("\babc\t\"",true) = "\\babc\\t\\\"";
	 * JacksonUtils.jsonCoder("\\babc\\t\\\"",false) = "\babc\t\"";
	 *
	 * @param str
	 * @param encoder
	 * @return
	 */
	private static String jsonCoder(String str, boolean encoder) {
//		logger.debug("transferred meaning value is [" + str + "]");
		String newStr = null;
		if (encoder) {
			// encoder
			newStr = StringUtils.replaceEach(str, DECODER_CHAR, ENCODER_CHAR);
		} else {
			// decoder
			newStr = StringUtils.replaceEach(str, ENCODER_CHAR, DECODER_CHAR);
		}
//		logger.debug("transferred meaned value is  [" + newStr + "]");
		return newStr;
	}
}
