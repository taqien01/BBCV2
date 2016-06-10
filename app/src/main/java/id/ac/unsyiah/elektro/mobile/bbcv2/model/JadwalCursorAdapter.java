package id.ac.unsyiah.elektro.mobile.bbcv2.model;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import id.ac.unsyiah.elektro.mobile.bbcv2.R;

/**
 * Created by taqien01 on 6/1/2016.
 */
public class JadwalCursorAdapter extends CursorAdapter {
    public JadwalCursorAdapter(Context context, Cursor cursor){
        super(context, cursor, 0);
    }

    public View newView(Context context, Cursor cursor, ViewGroup parent){
        return LayoutInflater.from(context).inflate(R.layout.satu_jadwal, parent, false);

    }

    public void bindView(View view, Context context, Cursor cursor){
        String nama = cursor.getString(cursor.getColumnIndexOrThrow(JadwalDB.NAMA_PENGAJAR));
        TextView txtNama = (TextView) view.findViewById(R.id.txtDrawer);
        txtNama.setText(nama);

        String jam = cursor.getString(cursor.getColumnIndexOrThrow(JadwalDB.JAM_PELAJARAN));
        TextView txtJam = (TextView) view.findViewById(R.id.txtJam);
        txtJam.setText(jam);

        String hari = cursor.getString(cursor.getColumnIndexOrThrow(JadwalDB.HARI));
        TextView txtHari = (TextView) view.findViewById(R.id.txtHari);
        txtHari.setText(hari);

        String tingkatan = cursor.getString(cursor.getColumnIndexOrThrow(JadwalDB.TINGKAT));
        TextView txtTingkatan = (TextView) view.findViewById(R.id.txtTingkatan);
        txtTingkatan.setText(tingkatan);
    }
}
