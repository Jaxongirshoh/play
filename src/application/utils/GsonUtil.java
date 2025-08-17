package application.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

public final class GsonUtil {
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private GsonUtil(){
        throw new IllegalStateException("Utility class");
    }

    public static String  objectToJson(Object object){
        return GSON.toJson(object);
    }

    public static byte[] objectToByteArray(String json){
        return json.getBytes(Charset.defaultCharset());
    }

    public static <T> T fromJsonToObject(InputStream in, Class<T> clazz){
        return GSON.fromJson(new InputStreamReader(in), clazz);
    }





}
