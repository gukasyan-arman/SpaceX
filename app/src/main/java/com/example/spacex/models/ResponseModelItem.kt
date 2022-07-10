package com.example.spacex.models

import java.io.Serializable

data class ResponseModelItem(
    val auto_update: Boolean,
    val capsules: List<Any>,
    val cores: List<Core>,
    val crew: List<Any>,
    val date_local: String,
    val date_precision: String,
    val date_unix: Int,
    val date_utc: String,
    val details: String,
    val failures: List<Failure>,
    val fairings: Fairings,
    val flight_number: Int,
    val id: String,
    val launch_library_id: Any,
    val launchpad: String,
    val links: Links,
    val name: String,
    val net: Boolean,
    val payloads: List<String>,
    val rocket: String,
    val ships: List<Any>,
    val static_fire_date_unix: Int,
    val static_fire_date_utc: String,
    val success: Boolean,
    val tbd: Boolean,
    val upcoming: Boolean,
    val window: Int
) : Serializable{
    override fun hashCode(): Int {
        var result = id.hashCode()
        if(cores.isNullOrEmpty()){
            result = 31 * result + cores.hashCode()
        }
        return result
    }
}

