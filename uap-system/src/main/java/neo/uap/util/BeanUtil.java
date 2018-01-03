package neo.uap.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NullNode;
import neo.uap.domain.common.Modifiable;
import org.apache.commons.codec.binary.StringUtils;

import java.lang.reflect.Field;
import java.util.Iterator;

public class BeanUtil {
    public static void setChange(Object target, JsonNode change) {
        Iterator<String> iter = change.fieldNames();
        Field[] fields = target.getClass().getDeclaredFields();

        while (iter.hasNext()) {
            String fieldName = iter.next();
            JsonNode node = change.get(fieldName);
            Field f = getDeclaredField(fields, fieldName);

            if (f != null && f.isAnnotationPresent(Modifiable.class)) {
                setChangedValue(target, f, node);
            }
        }
    }

    private static Field getDeclaredField(Field[] fields, String fieldName) {
        for (Field f : fields) {
            if (StringUtils.equals(f.getName(), fieldName)) {
                return f;
            }
        }
        return null;
    }

    private static void setChangedValue(Object target, Field f, JsonNode node) {
        try {
            f.setAccessible(true);

            if (node instanceof NullNode) {
                f.set(target, null);
            } else if (f.getType().equals(String.class)) {
                f.set(target, node.textValue());
            } else if (f.getType().equals(Integer.class)) {
                f.setInt(target, node.intValue());
            } else if (f.getType().equals(Long.class)) {
                f.setLong(target, node.longValue());
            } else if (f.getType().equals(Float.class)) {
                f.setFloat(target, node.floatValue());
            } else if (f.getType().equals(Double.class)) {
                f.setDouble(target, node.doubleValue());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
