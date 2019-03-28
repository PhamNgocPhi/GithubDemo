package com.systena.githupdemo.data.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.room.TypeConverter;

public class ListIntegerConverter {
    static Gson gson = new Gson();

    @TypeConverter
    public static List<Integer> toList(String list) {
        Type listType = new TypeToken<List<Integer>>() {
        }.getType();
        return list == null ? null : gson.fromJson(list, listType);
    }

    @TypeConverter
    public static String toString(List<Integer> data) {
        return gson.toJson(data);
    }
}
