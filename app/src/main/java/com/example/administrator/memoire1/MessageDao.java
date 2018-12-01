package com.example.administrator.memoire1;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class MessageDao extends Activity{
    SQLiteDatabase db;
    public MessageDao(SQLiteDatabase db) {
        this.db = db;
    }
    public boolean save(Object objects) {
        try {
            Message message = (Message) objects;
            ContentValues values = new ContentValues();
            values.put("date",String.valueOf(message.getDate()));
            values.put("title", message.getTitle());
            values.put("content", message.getContent());
            long index = db.insert("T_Message", null, values);
            if (index > 0)
                return true;
            else {
                return false;
            }
        }
        catch (Exception e)
        {
            return false;
        }
    }
    public boolean update(Object objects) {
        try {
            Message message = (Message) objects;
            ContentValues values = new ContentValues();
            values.put("date",String.valueOf(message.getDate()));
            values.put("title", message.getTitle());
            values.put("content", message.getContent());
//            db.rawQuery("update T_Message set title = 'test' ",new String[]{String.valueOf(message.getId())});
            long index = db.update("T_Message",values,"id=?",new String[]{String.valueOf(message.getId())});
            query();
            if (index > 0)
                return true;
            else {
                return false;
            }

        }
        catch (Exception e) {
            return false;
        }
    }
    public boolean delete(String id) {
        try {
            int index = db.delete("T_Message", "id=?", new String[]{id});
            if (index > 0)
                return true;
            else {
                return false;
            }
        }
        catch (Exception e) {
            return false;
        }
    }
    public List query() {
        Cursor cursor = db.rawQuery("select * from T_Message",new String[]{});
        //Cursor cursor=db.query(false,"T_Message",new String[]{"id","date","title","content"},null,null,null,null,null,null);
        List<Message> list =new ArrayList<Message>();
        while(cursor.moveToNext()){
            Message message = new Message(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
            list.add(message);
        }
        return list;
    }
    public List visit(String id_query) {
        Cursor cursor = db.rawQuery("select * from T_Message where id = ?",new String[]{id_query});        //Cursor cursor=db.query("T_Message",new String[]{"id","date","title","content"},"id=?",new String[]{id_query},null,null,null);
        List<Message> list =new ArrayList<Message>();
        while(cursor.moveToNext()){
            Message message = new Message(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
            list.add(message);
        }
        return list;
    }
}

