package com.stepasha.notetaker.sql

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataHelper(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS $TABLE_NAME (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, note TEXT," +
                    " date DATETIME)"
        db?.execSQL(CREATE_TABLE)

    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {


    }


    fun addNote(title: String, note: String, date: String): Boolean {
        val db = this.writableDatabase
        val value = ContentValues()
        value.put("title", title)
        value.put("note", note)
        value.put("date", date)
        val result: Long = db.insert(TABLE_NAME, null, value)

        return result >= 0

    }

    fun readAllNote(): Cursor {
        val db = this.writableDatabase
        val res: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME ORDER BY date DESC", null)
        return res


    }
    fun readOneNote(id: String): Cursor {
        val db = this.writableDatabase
        val res: Cursor =
            db.rawQuery("SELECT * FROM $TABLE_NAME WHERE id = '$id'", null)
        return res
    }

    fun updateNote(id: String, title: String, note: String, date: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("title", title)
        contentValues.put("note", note)
        contentValues.put("date", date)
        db.update(TABLE_NAME, contentValues, " id ='$id'", null)

        return true

    }


    fun deleteNote(id: String): Boolean {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, " id ='$id'", null)
        return true

    }

    fun searchNote(query: String): Cursor {
        val db = this.writableDatabase
        return db.rawQuery(
            "SELECT * FROM $TABLE_NAME WHERE title like'%$query%' OR note like'%$query%'",
            null)
    }

    companion object {
        private const val DB_NAME = "Notes.db"
        private const val DB_VERSION = 1
        private const val TABLE_NAME = "note"
    }
}