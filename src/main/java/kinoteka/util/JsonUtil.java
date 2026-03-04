package kinoteka.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.List;

public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T fromJson(File file, Class<T> clazz) {
        try {
            return mapper.readValue(file, clazz);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> List<T> fromJson(File file, TypeReference<List<T>> ref) {
        try {
            return mapper.readValue(file, ref);
        } catch (Exception e) {
            return null;
        }
    }

    public static void toJson(File file, Object obj) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, obj);
        } catch (Exception ignored) {}
    }
}
