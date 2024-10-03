package com.wajeez.wizardsandelixirs.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wajeez.wizardsandelixirs.data.local.model.IngredientEntity
import com.wajeez.wizardsandelixirs.data.local.model.InventorEntity

class Converters {
    @TypeConverter
    fun fromIngredientsList(value: List<IngredientEntity>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toIngredientsList(value: String): List<IngredientEntity> {
        val listType = object : TypeToken<List<IngredientEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromInventorsList(value: List<InventorEntity>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toInventorsList(value: String): List<InventorEntity> {
        val listType = object : TypeToken<List<InventorEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }
}