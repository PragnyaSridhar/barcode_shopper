package com.example.p7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION = 2;

        private static final String DATABASE_NAME = "product_db";

        private static int dbCorruptFlag = 0;

        public static int getDbCorruptFlag() {
            return dbCorruptFlag;
        }

        public DatabaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION, new DatabaseErrorHandler() {
                @Override
                public void onCorruption(SQLiteDatabase dbObj) {
                    dbCorruptFlag = 1;
                }
            });


        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(productDB.CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + productDB.TABLE_NAME);

            onCreate(db);
        }

        public long insertNote(String barcode,String name,Double price) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(productDB.COLUMN_NAME, name);
            values.put(productDB.COLUMN_PRICE, price);
            values.put(productDB.COLUMN_BARCODE, barcode);



            long id = db.insert(productDB.TABLE_NAME, null, values);

            db.close();

            return id;
        }

        public product getNote(String id) {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(productDB.TABLE_NAME,
                    new String[]{productDB.COLUMN_NAME, productDB.COLUMN_PRICE},
                    productDB.COLUMN_BARCODE + "=?",
                    new String[]{String.valueOf(id)}, null, null, null, null);
            //new String[]{String.valueOf(id)}
            if (cursor != null)
                cursor.moveToFirst();

            productDB note = new productDB(
                    cursor.getString(cursor.getColumnIndex("barcode")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getDouble(cursor.getColumnIndex("price")));
            product p= new product();
            p.barcode=note.getBarcode();
            p.name=note.getName();
            p.price=note.getPrice();


            cursor.close();

            return p;
        }

        public List<productDB> getAllNotes() {
            List<productDB> notes = new ArrayList<>();

            String selectQuery = "SELECT  * FROM " + productDB.TABLE_NAME ;

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()) {
                do {
                    productDB note = new productDB();
                    note.setBarcode(cursor.getString(cursor.getColumnIndex(productDB.COLUMN_BARCODE)));
                    note.setName(cursor.getString(cursor.getColumnIndex(productDB.COLUMN_NAME)));
                    note.setPrice(cursor.getDouble(cursor.getColumnIndex(productDB.COLUMN_PRICE)));

                    notes.add(note);
                } while (cursor.moveToNext());
            }

            db.close();
            return notes;
        }

        public int getNotesCount() {
            String countQuery = "SELECT  * FROM " + productDB.TABLE_NAME;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);

            int count = cursor.getCount();
            cursor.close();
            return count;
        }

        public int updateNote(productDB note) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(productDB.COLUMN_NAME, note.getName());
            return db.update(productDB.TABLE_NAME, values, productDB.COLUMN_BARCODE + " = ?",
                    new String[]{String.valueOf(note.getBarcode())});
        }

        public void deleteNote(productDB note) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(productDB.TABLE_NAME, productDB.COLUMN_BARCODE + " = ?",
                    new String[]{String.valueOf(note.getBarcode())});
            db.close();
        }
    }
