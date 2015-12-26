package com.bus.projectbus.api;

import android.util.Log;

import com.bus.projectbus.LogInActivity;
import com.bus.projectbus.entity.UserEntity;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import org.json.JSONObject;

import java.lang.reflect.Type;

class UserDeserialiser implements JsonDeserializer<UserEntity>
{
    @Override
    public UserEntity deserialize(JsonElement json, Type typeOfT,
                             JsonDeserializationContext context)
            throws JsonParseException
    {
        JsonObject jsonObject = (JsonObject) json;
        Log.d("teeee", jsonObject.toString());
        if (jsonObject.get("error") != null)
        {
            throw new JsonParseException("Error!");
        }

        return new Gson().fromJson(json, UserEntity.class);
    }
}
