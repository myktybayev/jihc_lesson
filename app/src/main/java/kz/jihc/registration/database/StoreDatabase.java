package kz.jihc.registration.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StoreDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "registration.db";
    private static final int DATABASE_VERSION = 3;

    public static final String TABLE_STUDENTS = "students";
    public static final String TABLE_GROUPS = "user_groups";

    public static final String COLUMN_INFO = "user_full_name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_GROUP_ID = "group_id";

    public static final String COLUMN_GINFO = "group_name";
    public static final String COLUMN_SUM = "group_sum";

    Context context;

    public StoreDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_STUDENTS + "(" +
                COLUMN_INFO + " TEXT, " +
                COLUMN_EMAIL + " TEXT , " +
                COLUMN_GROUP_ID + " TEXT , " +
                COLUMN_PASSWORD + " TEXT )");

        db.execSQL("CREATE TABLE " + TABLE_GROUPS + "(" +
                COLUMN_GINFO + " TEXT, " +
                COLUMN_SUM + " INTEGER , " +
                COLUMN_GROUP_ID + " TEXT )");

        initGroups(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUPS);

        onCreate(db);
    }

    public void initGroups(SQLiteDatabase db){

        ContentValues group1 = new ContentValues();
        group1.put(COLUMN_GINFO, "IT");
        group1.put(COLUMN_SUM, 25);
        group1.put(COLUMN_GROUP_ID, "3E");
        db.insert(TABLE_GROUPS, null, group1);
        
        ContentValues group2 = new ContentValues();
        group2.put(COLUMN_GINFO, "IS");
        group2.put(COLUMN_SUM, 24);
        group2.put(COLUMN_GROUP_ID, "3F");
        db.insert(TABLE_GROUPS, null, group2);


        ContentValues group3 = new ContentValues();
        group3.put(COLUMN_GINFO, "Buhgalter");
        group3.put(COLUMN_SUM, 24);
        group3.put(COLUMN_GROUP_ID, "3D");
        db.insert(TABLE_GROUPS, null, group3);

        ContentValues group4 = new ContentValues();
        group4.put(COLUMN_GINFO, "Filologi");
        group4.put(COLUMN_SUM, 25);
        group4.put(COLUMN_GROUP_ID, "3A");
        db.insert(TABLE_GROUPS, null, group4);

    }

}