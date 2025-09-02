package com.example.Taller1.data

import android.content.Context
import com.example.Taller1.model.RemotePiloto
import com.example.Taller1.model.RemotePilotoItem
import org.json.JSONArray

fun loadRemotePilotosFromAssets(context: Context): RemotePiloto {
    val lista = RemotePiloto()

    val jsonString = context.assets.open("F1.json")
        .bufferedReader()
        .use { it.readText() }

    val array = JSONArray(jsonString)
    for (i in 0 until array.length()) {
        val obj = array.getJSONObject(i)

        lista.add(
            RemotePilotoItem(
                broadcast_name = obj.optString("broadcast_name", ""),
                country_code   = if (obj.isNull("country_code")) null else obj.optString("country_code", ""),
                driver_number  = if (obj.isNull("driver_number")) -1 else obj.optInt("driver_number"),
                first_name     = obj.optString("first_name", ""),
                full_name      = obj.optString("full_name", "Desconocido"),
                headshot_url   = obj.optString("headshot_url", ""),
                last_name      = obj.optString("last_name", ""),
                meeting_key    = if (obj.isNull("meeting_key")) 0 else obj.optInt("meeting_key"),
                name_acronym   = obj.optString("name_acronym", ""),
                session_key    = if (obj.isNull("session_key")) 0 else obj.optInt("session_key"),
                team_colour    = obj.optString("team_colour", ""),
                team_name      = obj.optString("team_name", "")
            )
        )
    }
    return lista
}


fun normalizeTeamColor(raw: String): String =
    raw.trim().let { if (it.isEmpty()) "#999999" else if (it.startsWith("#")) it else "#$it" }
