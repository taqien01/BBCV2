package id.ac.unsyiah.elektro.mobile.bbcv2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import id.ac.unsyiah.elektro.mobile.bbcv2.model.JadwalCursorAdapter;
import id.ac.unsyiah.elektro.mobile.bbcv2.model.JadwalDB;

/**
 * Created by taqien01 on 6/2/2016.
 */
public class ListJadwalActivity extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_jadwal);

        Intent pesan = getIntent();
        pelajaran = pesan.getStringExtra("var1");

        ListView listJadwal = (ListView) findViewById(R.id.listJadwal);

        SQLiteOpenHelper jadwalDB = new JadwalDB(this);
        db = jadwalDB.getReadableDatabase();

        cursor = db.query(JadwalDB.TABEL_JADWAL,
                new String[]{JadwalDB.NAMA_PENGAJAR,
                             JadwalDB.ID_JADWAL,
                             JadwalDB.JAM_PELAJARAN,
                             JadwalDB.HARI,
                             JadwalDB.TINGKAT},
                             JadwalDB.NAMA_PELAJARAN +"= '"+pelajaran+"'",
                null,
                null,
                null,
                null);


        if(db == null){
            ImageView img = (ImageView) findViewById(R.id.imageKosong);
            img.setVisibility(View.VISIBLE);
        }


        cursor.moveToFirst();

        JadwalCursorAdapter jadwalCursorAdapter = new JadwalCursorAdapter(this, cursor);
        listJadwal.setAdapter(jadwalCursorAdapter);



        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                        clickItemListBarang (listView, view, position, id);
                    }
                };
        listJadwal.setOnItemClickListener(itemClickListener);
    }

    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }

    public void clickItemListBarang(AdapterView<?> listView, View v, int position, long id){
        Intent pesan = new Intent(getApplicationContext(), DetailActivity.class);
        pesan.putExtra("id",id);
        startActivity(pesan);
    }

    private String pelajaran;
    private SQLiteDatabase db;
    private Cursor cursor;
}
