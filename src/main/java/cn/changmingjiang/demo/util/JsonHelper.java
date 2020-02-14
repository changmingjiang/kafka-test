package cn.changmingjiang.demo.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Log4j2
public class JsonHelper {

    public JsonHelper() {
    }

    private static ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

    /**
     * @MethodName: json2Map
     * @File: JsonHelper.java
     * @Description: json字符串转化为Map
     * @date: 2014-5-20 下午3:51:40
     */
    public static Map<String, Object> json2Map(String json) {
        Map<String, Object> map = new HashMap<>();

        if ( StringUtils.hasText(json)) {
            try {
                map = getObjectMapper().readValue(json, Map.class);
            } catch (Exception e) {
                log.error(json + "转化为Map失败", e.getMessage(), e);
            }
        }
        return map;
    }

    /**
     * @MethodName: json2Array
     * @File: JsonHelper.java
     * @Description: json字符串转化为对象数组
     * @date: 2014-5-20 下午4:05:38
     */
    public static <T> T json2Array(String json, Class<T> valueType) {
        if ( StringUtils.hasText(json)) {
            try {
                return getObjectMapper().readValue(json, valueType);
            } catch (Exception e) {
                log.error(json + "转化为" + valueType + "数组失败", e.getMessage(), e);
            }
        }
        return null;
    }

    /**
     * @MethodName: json2Object
     * @File: JsonHelper.java
     * @Description: json字符串转化为java对象
     * @date: 2014-5-20 下午4:12:59
     */
    public static <T> T json2Object(String json, Class<T> valueType) {
        if ( StringUtils.hasText(json)) {
            try {
                return getObjectMapper().readValue(json, valueType);
            } catch (Exception e) {
                log.error(json + "转化为" + valueType + "对象失败", e.getMessage(), e);
            }
        }
        return null;
    }

    public static String object2Json(Object object) {
        if (object != null) {
            try {
                return getObjectMapper().writeValueAsString(object);
            } catch (Exception e) {
                log.error(object + "转化为json字符串失败", e.getMessage(), e);
            }
        }
        return null;
    }

    public static <T> T json2Array(String json, TypeReference<T> valueType) {
        if ( StringUtils.hasText(json)) {
            try {
                return getObjectMapper().readValue(json, valueType);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static <T> T map2Object(Map map, Class<T> valueType) {
        if ( !CollectionUtils.isEmpty(map)) {
            try {
                return getObjectMapper().convertValue(map, valueType);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Map object2Map(Object obj) {
        if (obj != null) {
            try {
                return getObjectMapper().convertValue(obj, Map.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
