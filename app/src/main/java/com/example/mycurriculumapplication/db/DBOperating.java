package com.example.mycurriculumapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mycurriculumapplication.db.DBHelper;

public class DBOperating {
    String TAG="DBOperating";
    DBHelper dbHelper;
    SQLiteDatabase db;
    public DBOperating(Context context){
        openDB(context);
    }
    public void openDB(Context context){
        dbHelper=new DBHelper(context);
        db= dbHelper.getWritableDatabase();
    }
    public void closeDB(){
        dbHelper.close();
    }
    //新增資料
    public void addTableNameData(String name,int useTable){
        ContentValues values = new ContentValues();
        values.put(DBHelper.tableTableFieldName, name);
        values.put(DBHelper.tableTableFieldUseTable, useTable);
        db.insert(DBHelper.tableTableName, null, values);
    }
    public void addCourseTableData(int tableNameId,int sessionId,int weekId,String courseName){
        try {
            ContentValues values = new ContentValues();
            values.put(DBHelper.courseTableFieldTableId, tableNameId);
            values.put(DBHelper.courseTableFieldSessionId, sessionId);
            values.put(DBHelper.courseTableFieldWeekId, weekId);
            values.put(DBHelper.courseTableFieldSubjectId, courseName);
            db.insert(DBHelper.courseTableName, null, values);
        }catch (SQLException e){
            Log.e(TAG,"addCourseTableData : "+e);
        }
    }
    //刪除課表
    public void delTableData(int tableId){
        db.delete(DBHelper.tableTableName, DBHelper.tableTableFieldId + "=" + tableId, null);
        db.delete(DBHelper.courseTableName, DBHelper.courseTableFieldTableId + "=" + tableId, null);
    }
    //刪除課表的課程
    public void delCourseTableData(int tableId,int sessionId,int weekId){
        db.delete(DBHelper.courseTableName,
                DBHelper.courseTableFieldTableId + "=" + tableId+" AND "+DBHelper.courseTableFieldSessionId+" = "+sessionId+" AND "+DBHelper.courseTableFieldWeekId+" = "+weekId,
                null);
    }
    //修改課表名稱
    public void updateTableTableDataName(int tableId,String tableName){
        ContentValues values = new ContentValues();
        values.put(DBHelper.tableTableFieldName, tableName);
        db.update(DBHelper.tableTableName, values, DBHelper.tableTableFieldId + "=" + tableId, null);
    }
    //修改課表的課程名稱
    public void updateCourseTableDataSubject(int tableId,int sessionId,int weekId,int subjectId){
        ContentValues values = new ContentValues();
        values.put(DBHelper.courseTableFieldSubjectId, subjectId);
        db.update(DBHelper.courseTableName, values,
                DBHelper.courseTableFieldTableId + "=" + tableId+
                        " AND "+DBHelper.courseTableFieldWeekId+" = "+weekId+
                        " AND "+DBHelper.courseTableFieldSessionId+" = "+sessionId,
                null);
    }
    public void selectTableTableData(){
        Cursor cursor;
        cursor = db.query(DBHelper.tableTableName,DBHelper.tableTableField,null,null,null,null,null);
        try{
            while (cursor != null && cursor.moveToNext()) {
                int id=cursor.getInt(cursor.getColumnIndex(DBHelper.tableTableFieldId));
                String name =cursor.getString(cursor.getColumnIndex(DBHelper.tableTableFieldName));
                int useTable=cursor.getInt(cursor.getColumnIndex(DBHelper.tableTableFieldUseTable));
                Log.i(TAG, "queryData selectTableName ="+id+","+name+","+useTable);
            }
        } catch (SQLException e) {
            Log.e(TAG,"queryData exception selectTableName:", e);
        }
    }
    public void selectSessionTableData(){
        Cursor cursor;
        cursor = db.query(DBHelper.sessionTableName,DBHelper.sessionTableField,null,null,null,null,null);
        try{
            while (cursor != null && cursor.moveToNext()) {
                int id=cursor.getInt(cursor.getColumnIndex(DBHelper.sessionTableFieldId));
                String name =cursor.getString(cursor.getColumnIndex(DBHelper.sessionTableFieldSession));
                Log.i(TAG, "queryData student selectSession="+id+","+name);
            }
        } catch (SQLException e) {
            Log.e(TAG,"queryData exception selectSession:", e);
        }
    }
    public void selectWeekNameTableData(){
        Cursor cursor;
        cursor = db.query(DBHelper.weekTableName,DBHelper.weekTableField,null,null,null,null,null);
        try{
            while (cursor != null && cursor.moveToNext()) {
                int id=cursor.getInt(cursor.getColumnIndex(DBHelper.weekTableFieldId));
                String name =cursor.getString(cursor.getColumnIndex(DBHelper.weekTableFieldWeek));
                Log.i(TAG, "queryData selectWeek ="+id+","+name);
            }
        } catch (SQLException e) {
            Log.e(TAG,"queryData exception selectWeek :", e);
        }
    }
    public void selectCourseTableData(){
        Cursor cursor;
        cursor = db.query(DBHelper.courseTableName,DBHelper.courseTableField,null,null,null,null,null);
        try{
            while (cursor != null && cursor.moveToNext()) {
                int tableNameId=cursor.getInt(cursor.getColumnIndex(DBHelper.courseTableFieldTableId));
                int sessionId=cursor.getInt(cursor.getColumnIndex(DBHelper.courseTableFieldSessionId));
                int weekId=cursor.getInt(cursor.getColumnIndex(DBHelper.courseTableFieldWeekId));
                int courseName =cursor.getInt(cursor.getColumnIndex(DBHelper.courseTableFieldSubjectId));
                Log.i(TAG, "queryData selectCourse ="+tableNameId+","+sessionId+","+weekId+","+courseName);
            }
        } catch (SQLException e) {
            Log.e(TAG,"queryData exception selectCourse :", e);
        }
    }
    public void addSubjectTableData(String subjectName,String teacher,String className,String color){
        try {
            ContentValues values = new ContentValues();
            values.put(DBHelper.subjectTableFieldName, subjectName);
            values.put(DBHelper.subjectTableTableFieldTeacher, teacher);
            values.put(DBHelper.subjectTableTableFieldClassName, className);
            values.put(DBHelper.subjectTableTableFieldColor, color);
            db.insert(DBHelper.subjectTableName, null, values);
        }catch (SQLException e){
            Log.e(TAG,"addCourseTableData : "+e);
        }
    }
    public void selectSubjectTableData(int subjectId){
        Cursor cursor;
        cursor = db.query(DBHelper.subjectTableName,DBHelper.subjectTableField,DBHelper.sessionTableFieldId+" = "+subjectId,null,null,null,null);
        try{
            while (cursor != null && cursor.moveToNext()) {
                int id=cursor.getInt(cursor.getColumnIndex(DBHelper.subjectTableFieldId));
                String name=cursor.getString(cursor.getColumnIndex(DBHelper.subjectTableFieldName));
                String teacher=cursor.getString(cursor.getColumnIndex(DBHelper.subjectTableTableFieldTeacher));
                String className =cursor.getString(cursor.getColumnIndex(DBHelper.subjectTableTableFieldClassName));
                String color =cursor.getString(cursor.getColumnIndex(DBHelper.subjectTableTableFieldColor));
                Log.i(TAG, "queryData selectSubject ="+id+","+name+","+teacher+","+className+","+color);
            }
        } catch (SQLException e) {
            Log.e(TAG,"queryData exception selectSubject :", e);
        }
    }
}
