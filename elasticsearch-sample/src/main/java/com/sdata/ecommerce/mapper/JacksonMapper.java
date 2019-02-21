package com.sdata.ecommerce.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import org.apache.commons.logging.Log;

import java.io.IOException;
import java.util.List;

import static java.util.Collections.emptyList;

public class JacksonMapper {

    private static final ObjectMapper defaultObjectMapper = newDefaultMapper();
    private static volatile ObjectMapper objectMapper = null;

    private static ObjectMapper newDefaultMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    public static ObjectMapper mapper() {
        if (objectMapper == null) {
            return defaultObjectMapper;
        } else {
            return objectMapper;
        }
    }

    public static <T> List<T> desList(String jsonArrayStr, TypeReference<List<T>> typeReference, Log logger) {
        try {
            return mapper().readValue(jsonArrayStr, typeReference);
        } catch (Exception e) {
            logger.warn("error occurs while deserialize jsonListStr: " + jsonArrayStr + ", {}", e);
            return emptyList();
        }
    }

    public static <T> T cloneObject(Object t, Class<T> cls) {
        return fromJson(toJson(t), cls);
    }

    public static <T> T fromJson(String jsonStr, Class<T> cls) {
        try {
            return mapper().readValue(jsonStr, cls);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <A> A fromJson(JsonNode json, Class<A> clazz) {
        try {
            return mapper().treeToValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonNode toJson(final Object data) {
        try {
            return mapper().valueToTree(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String toJsonStr(Object o) {
        return generateJson(o, false);
    }

    public static String prettyPrint(Object o) {
        return generateJson(o, true);
    }

    private static String generateJson(Object o, boolean prettyPrint) {
        try {
            ObjectWriter writer = mapper().writer();
            if (prettyPrint) {
                writer = writer.with(SerializationFeature.INDENT_OUTPUT);
            }
            return writer.writeValueAsString(o);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonNode parse(String src) {
        try {
            return mapper().readTree(src);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }
}
