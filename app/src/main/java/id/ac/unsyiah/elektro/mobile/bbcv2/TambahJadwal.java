package id.ac.unsyiah.elektro.mobile.bbcv2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import id.ac.unsyiah.elektro.mobile.bbcv2.model.JadwalDB;

/**
 * Created by taqien01 on 6/2/2016.
 */
public class TambahJadwal extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_jadwal);
    }

    public void clickTambah(View view){
        Intent pesan = getIntent();
        String nama = pesan.getStringExtra("var1");

        Spinner spnPelajaran = (Spinner) findViewById(R.id.spinnerPelajaran);
        String pelajaran = spnPelajaran.getSelectedItem().toString();

        ArrayList<String> hari = new ArrayList<String>();
        CheckBox senin = (CheckBox) findViewById(R.id.checkBox);
        CheckBox selasa = (CheckBox) findViewById(R.id.checkBox2);
        CheckBox rabu = (CheckBox) findViewById(R.id.checkBox3);
        CheckBox kamis = (CheckBox) findViewById(R.id.checkBox4);
        CheckBox jumat = (CheckBox) findViewById(R.id.checkBox5);
        CheckBox sabtu = (CheckBox) findViewById(R.id.checkBox6);
        if(senin.isChecked())
            hari.add("Senin");
        if(selasa.isChecked())
            hari.add("Selasa");
        if(rabu.isChecked())
            hari.add("Rabu");
        if(kamis.isChecked())
            hari.add("Kamis");
        if(jumat.isChecked())
            hari.add("Jumat");
        if(sabtu.isChecked())
            hari.add("Sabtu");

        String hariStr = "";
        boolean pertama = true;
        for (String hari2 : hari){
            hariStr = hariStr + hari2+", ";
        }

        TextView txtJam = (TextView) findViewById(R.id.editJam);
        String jam = txtJam.getText().toString();

        TextView txtHarga = (TextView) findViewById(R.id.editHarga);
        String harga = txtHarga.getText().toString();

        Spinner spnTingkat = (Spinner) findViewById(R.id.spinnerTingkat);
        String tingkat = spnTingkat.getSelectedItem().toString();

        TextView txtHp = (TextView) findViewById(R.id.editHp);
        String hp = txtHp.getText().toString();

        SQLiteOpenHelper jadwalDB = new JadwalDB(this);
        SQLiteDatabase db = jadwalDB.getWritableDatabase();

        ContentValues jadwalBaru = new ContentValues();
        jadwalBaru.put(JadwalDB.NAMA_PENGAJAR, nama);
        jadwalBaru.put(JadwalDB.HARI, hariStr);
        jadwalBaru.put(JadwalDB.NAMA_PELAJARAN, pelajaran);
        jadwalBaru.put(JadwalDB.JAM_PELAJARAN, jam);
        jadwalBaru.put(JadwalDB.HARGA, harga);
        jadwalBaru.put(JadwalDB.TINGKAT, tingkat);
        jadwalBaru.put(JadwalDB.NOHP, hp);

        db.insert(JadwalDB.TABEL_JADWAL, null, jadwalBaru);
        db.close();

        Intent pesan2 = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(pesan2);
        finish();
    }
}
