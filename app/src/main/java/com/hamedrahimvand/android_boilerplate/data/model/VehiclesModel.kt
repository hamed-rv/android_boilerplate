package com.hamedrahimvand.android_boilerplate.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.hamedrahimvand.android_boilerplate.data.db.VehiclesTypeConverter

/**
 *
 *@author Hamed.Rahimvand
 *@since 6/13/20
 */
@Entity(primaryKeys = ["lat","lng"]/*Just For Mock :)*/)
@TypeConverters(VehiclesTypeConverter::class)
data class SampleModel(
    val type: VehiclesType?,
    val lat: Double,
    val lng: Double,
    val bearing: Int?,
    @SerializedName("image_url") val imageUrl: String?
)

enum class VehiclesType(val type: String) {
    PLUS("PLUS"), ECO("ECO");
}