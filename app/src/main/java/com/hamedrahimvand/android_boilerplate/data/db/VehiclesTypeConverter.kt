package com.hamedrahimvand.android_boilerplate.data.db

import androidx.room.TypeConverter
import com.hamedrahimvand.android_boilerplate.data.model.VehiclesType

/**
 *
 *@author Hamed.Rahimvand
 *@since 6/13/20
 */
object VehiclesTypeConverter {
    @TypeConverter
    @JvmStatic
    fun typeToString(vehiclesType: VehiclesType): String? {
        return try {
            val builder = StringBuilder()
            builder.append(vehiclesType.type)
            builder.toString()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    @TypeConverter
    @JvmStatic
    fun stringToType(data: String?): VehiclesType? {
        try {
            if (data == null) return null
            return if (data == VehiclesType.ECO.type)
                VehiclesType.ECO
            else
                VehiclesType.PLUS
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}