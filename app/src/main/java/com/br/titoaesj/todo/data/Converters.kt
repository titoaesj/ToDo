package com.br.titoaesj.todo.data

import androidx.room.TypeConverter
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.*

/**
 * Projeto ToDo
 * Desenvolvido por Tito Albino Evangelista da Silva Junior
 * Criado em 25 de março de 2022.
 */
class Converters {

    @TypeConverter
    fun stringFromLocalDateTime(value: String): LocalDateTime {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        return LocalDateTime.parse(value, formatter)
    }

    @TypeConverter
    fun localDateTimeToString(date: LocalDateTime): String {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
    }

    @TypeConverter
    fun stringFromCalendar(value: String): Calendar {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale("pt", "BR"))
        val c = Calendar.getInstance()
        c.time = simpleDateFormat.parse(value)!!
        return c
    }

    @TypeConverter
    fun calendarToString(date: Calendar): String {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale("pt", "BR"))
        return simpleDateFormat.format(date.time).toString()
    }


}