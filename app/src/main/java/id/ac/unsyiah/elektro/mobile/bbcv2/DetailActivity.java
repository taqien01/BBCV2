package id.ac.unsyiah.elektro.mobile.bbcv2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import id.ac.unsyiah.elektro.mobile.bbcv2.model.JadwalDB;

/**
 * Created by taqien01 on 6/2/2016.
 */
public class DetailActivity extends AppCompatActivity {
    Button call;
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_detail);

        Intent pesan = getIntent();
        id = pesan.getLongExtra("id",0);

        SQLiteOpenHelper jadwalDB = new JadwalDB(this);
        SQLiteDatabase db = jadwalDB.getReadableDatabase();

        Cursor cursor = db.query(JadwalDB.TABEL_JADWAL,
                new String[] {JadwalDB.NAMA_PENGAJAR,
                              JadwalDB.TINGKAT,
                              JadwalDB.HARGA,
                              JadwalDB.NOHP,
                              JadwalDB.HARI,
                              JadwalDB.JAM_PELAJARAN},
                JadwalDB.ID_JADWAL+"="+Long.toString(id),
                null,
                null,
                null,
                null);
        cursor.moveToFirst();

        nama = cursor.getString(cursor.getColumnIndexOrThrow(JadwalDB.NAMA_PENGAJAR));
        tingkat = cursor.getString(cursor.getColumnIndexOrThrow(JadwalDB.TINGKAT));
        harga = cursor.getString(cursor.getColumnIndexOrThrow(JadwalDB.HARGA));
        nohp = cursor.getString(cursor.getColumnIndexOrThrow(JadwalDB.NOHP));
        hari = cursor.getString(cursor.getColumnIndexOrThrow(JadwalDB.HARI));
        jam = cursor.getString(cursor.getColumnIndexOrThrow(JadwalDB.JAM_PELAJARAN));

        cursor.close();
        db.close();

        TextView txtNama = (TextView) findViewById(R.id.txtNama);
        txtNama.setText(nama);

        TextView txtTingkat = (TextView) findViewById(R.id.txtTingkat);
        txtTingkat.setText("Tentor "+tingkat);

        TextView txtHarga = (TextView) findViewById(R.id.txtHarga);
        txtHarga.setText("Harga : Rp. "+harga);

        TextView txtHp = (TextView) findViewById(R.id.txtHp);
        txtHp.setText("Kontak : "+nohp);

        TextView txtHari = (TextView) findViewById(R.id.txtHari);
        txtHari.setText("Hari Mengajar : "+hari);

        TextView txtJam = (TextView) findViewById(R.id.txtJam);
        txtJam.setText("Jam Mengajar : "+jam);




    }

    public  void  call (View view){
        dialContactPhone(nohp);
    }
    private void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }

    private long id;
    private String nama;
    private String tingkat;
    private String harga;
    private String nohp;
    private String hari;
    private String jam;
}
