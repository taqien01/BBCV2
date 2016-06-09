package id.ac.unsyiah.elektro.mobile.bbcv2.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 6/8/2016.
 */
public class LoginDB extends SQLiteOpenHelper {

    public static final int VERSI_DB = 1;
    public static final String NAMA_DB = "Bimbel";

    public static final String TABEL_DAFTAR = "USER";
    public static final String ID_DAFTAR = "_id";
    public static final String NAMA_DAFTAR= "name";
    public static final String EMAIL_DAFTAR = "email";
    public static final String PASSWORD_DAFTAR = "password";


    public LoginDB(Context context){
        super(context, NAMA_DB, null, VERSI_DB);
    }

    public void onCreate (SQLiteDatabase db){
        final String baruDB = "CREATE TABLE IF NOT EXISTS " + TABEL_DAFTAR + " ( "
                + ID_DAFTAR + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAMA_DAFTAR +" TEXT, "
                + EMAIL_DAFTAR +" TEXT, "
                + PASSWORD_DAFTAR +" PASSWORD, "

                + ");";
        db.execSQL(baruDB);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer){

    }
}