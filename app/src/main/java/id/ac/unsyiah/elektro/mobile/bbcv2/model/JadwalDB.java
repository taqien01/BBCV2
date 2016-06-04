package id.ac.unsyiah.elektro.mobile.bbcv2.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by taqien01 on 6/1/2016.
 */
public class JadwalDB extends SQLiteOpenHelper {

    public static final int VERSI_DB = 1;
    public static final String NAMA_DB = "Bimbel";

    public static final String TABEL_JADWAL = "JADWAL";
    public static final String ID_JADWAL = "_id";
    public static final String NAMA_PENGAJAR = "nama_pengajar";
    public static final String NAMA_PELAJARAN = "nama_pelajaran";
    public static final String JAM_PELAJARAN = "jam_pelajaran";
    public static final String HARI = "hari";
    public static final String HARGA = "harga";
    public static final String TINGKAT = "tingkat";
    public static final String NOHP = "nohp";

    public JadwalDB(Context context){
        super(context, NAMA_DB, null, VERSI_DB);
    }

    public void onCreate (SQLiteDatabase db){
        final String buatDB = "CREATE TABLE IF NOT EXISTS " + TABEL_JADWAL + " ( "
                + ID_JADWAL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAMA_PENGAJAR +" TEXT, "
                + NAMA_PELAJARAN +" TEXT, "
                + JAM_PELAJARAN +" TEXT, "
                + HARI +" TEXT, "
                + HARGA +" TEXT, "
                + TINGKAT + " TEXT, "
                + NOHP + " TEXT "
                + ");";
        db.execSQL(buatDB);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer){

    }
}
