package com.example.mycurriculumapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private final static String dbNamae="table.db";
    private final static int dbVersion=1;

    public final String[] weekDays = {"周一", "周二", "周三", "周四", "周五", "週六", "周日"};
    public final int sessionNamber=15;

    public final static String tableTableName="table_tb";
    public final static String tableTableFieldId="id";
    public final static String tableTableFieldName="name";
    public final static String tableTableFieldUseTable="use_table";
    public final static String[] tableTableField={tableTableFieldId,tableTableFieldName,tableTableFieldUseTable};

    public final static String weekTableName="week_tb";
    public final static String weekTableFieldId="id";
    public final static String weekTableFieldWeek="week";
    public final static String[] weekTableField={weekTableFieldId,weekTableFieldWeek};

    public final static String sessionTableName ="session_tb";
    public final static String sessionTableFieldId ="id";
    public final static String sessionTableFieldSession ="session";
    public final static String[] sessionTableField={sessionTableFieldId,sessionTableFieldSession};

    public final static String subjectTableName="subject_tb";
    public final static String subjectTableFieldId="id";
    public final static String subjectTableFieldName="name";
    public final static String subjectTableTableFieldTeacher="teacher";
    public final static String subjectTableTableFieldClassName="class_name";
    public final static String subjectTableTableFieldColor="color";
    public final static String[] subjectTableField={subjectTableFieldId,subjectTableFieldName,subjectTableTableFieldTeacher,subjectTableTableFieldClassName,subjectTableTableFieldColor};

    public final static String courseTableName="course_tb";
    public final static String courseTableFieldTableId="table_id";
    public final static String courseTableFieldWeekId="week_id";
    public final static String courseTableFieldSessionId="session_id";
    public final static String courseTableFieldSubjectId="subject_id";
    public final static String[] courseTableField={courseTableFieldSessionId,courseTableFieldWeekId,courseTableFieldTableId,courseTableFieldSubjectId};

    public DBHelper(@Nullable Context context) {
        this(context, dbNamae, null, dbVersion);
    }

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String sqlTableTable = "CREATE TABLE IF NOT EXISTS " + tableTableName + " ( " +
                tableTableFieldId+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                tableTableFieldName+" TEXT, " +
                tableTableFieldUseTable+" integer " +
                ");";
        sqLiteDatabase.execSQL(sqlTableTable);
        final String sqlTableWeekNameTable = "CREATE TABLE IF NOT EXISTS " + weekTableName + " ( " +
                weekTableFieldId+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                weekTableFieldWeek+" TEXT " +
                ");";
        sqLiteDatabase.execSQL(sqlTableWeekNameTable);
        final String sqlTableSessionTable = "CREATE TABLE IF NOT EXISTS " + sessionTableName + " ( " +
                sessionTableFieldId+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                sessionTableFieldSession+" TEXT " +
                ");";
        sqLiteDatabase.execSQL(sqlTableSessionTable);
        final String sqlTableSubjectTable = "CREATE TABLE IF NOT EXISTS " + subjectTableName + " ( " +
                subjectTableFieldId+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                subjectTableFieldName+" TEXT, " +
                subjectTableTableFieldTeacher+" TEXT, " +
                subjectTableTableFieldClassName+" TEXT, " +
                subjectTableTableFieldColor+" TEXT " +
                ");";
        sqLiteDatabase.execSQL(sqlTableSubjectTable);
        final String sqlTableCourseTable = "CREATE TABLE IF NOT EXISTS " + courseTableName + " ( " +
                courseTableFieldTableId+" integer, " +
                courseTableFieldWeekId+" integer, " +
                courseTableFieldSessionId+" integer, " +
                courseTableFieldSubjectId+" integer, "+
                "FOREIGN KEY ("+courseTableFieldTableId+") REFERENCES "+tableTableName+" ("+tableTableFieldId+"), " +
                "FOREIGN KEY ("+courseTableFieldWeekId+") REFERENCES "+weekTableName+" ("+weekTableFieldId+"), " +
                "FOREIGN KEY ("+courseTableFieldSessionId+") REFERENCES "+sessionTableName+" ("+sessionTableFieldId+"), " +
                "FOREIGN KEY ("+courseTableFieldSubjectId+") REFERENCES "+subjectTableName+" ("+subjectTableFieldId+"), " +
                "PRIMARY KEY ("+courseTableFieldTableId+","+courseTableFieldWeekId+","+courseTableFieldSessionId+") "+
                ");";
        sqLiteDatabase.execSQL(sqlTableCourseTable);

        initWeekTableData(sqLiteDatabase);
        initSessionTableData(sqLiteDatabase);
        initTableTable(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    private void initWeekTableData(SQLiteDatabase database){
        for (String weekNAme: weekDays){
            ContentValues values = new ContentValues();
            values.put("week", weekNAme);
            database.insert(weekTableName,null,values);
        }
    }
    private void initSessionTableData(SQLiteDatabase database){
        for (int i=1;i<=sessionNamber;i++){
            ContentValues values = new ContentValues();
            values.put("session", i);
            database.insert(sessionTableName,null,values);
        }
    }
    private void initTableTableData(SQLiteDatabase database){
        for (int i=1;i<=sessionNamber;i++){
            ContentValues values = new ContentValues();
            values.put("session", i);
            database.insert(sessionTableName,null,values);
        }
    }
    private void initTableTable(SQLiteDatabase database){
        ContentValues values = new ContentValues();
        values.put(DBHelper.tableTableFieldName, "我的課表");
        values.put(DBHelper.tableTableFieldUseTable, 1);
        database.insert(DBHelper.tableTableName, null, values);
    }
}
