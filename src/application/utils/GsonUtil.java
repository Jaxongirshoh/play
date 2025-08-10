package application.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class GsonUtil {
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private GsonUtil(){
        throw new IllegalStateException("Utility class");
    }


}
