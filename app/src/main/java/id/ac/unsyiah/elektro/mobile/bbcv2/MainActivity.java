package id.ac.unsyiah.elektro.mobile.bbcv2;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        checkLogin();
    }

    public void checkLogin(){

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        ImageView img = (ImageView) findViewById(R.id.imageView);
        SharedPreferences login =
                getSharedPreferences("id.ac.unsyiah.elektro.mobile.bbcv2.PREF_BERSAMA", Context.MODE_PRIVATE);


        if(login.getBoolean("cekLogin", true)){

            nav_Menu.findItem(R.id.nav_setting).setVisible(false);
            nav_Menu.findItem(R.id.nav_tambah).setVisible(false);
            nav_Menu.findItem(R.id.nav_logout).setVisible(false);
        }
        else{
            //nav_Menu.findItem(R.id.nav_Daftar).setVisible(false);
            nav_Menu.findItem(R.id.nav_login).setVisible(false);
            //img.setImageResource(R.drawable.fadmol);
            //setContentView(img);
           // TextView txtNama = (TextView) findViewById(R.id.textNama);
            //TextView txtEmail = (TextView) findViewById(R.id.textView);
            //txtNama.setText("avavab");
            //txtEmail.setText("avava");

        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        SharedPreferences login2 =
                getSharedPreferences("id.ac.unsyiah.elektro.mobile.bbcv2.PREF_BERSAMA", Context.MODE_PRIVATE);


        //String nama = txtNama.getText().toString();

        if (id == R.id.nav_tambah) {
            Intent pesan = new Intent(getApplicationContext(), TambahJadwal.class);
            //pesan.putExtra("var1",nama);
            startActivity(pesan);

        } else if (id == R.id.nav_setting) {

        }else if (id == R.id.nav_login) {
            Intent login = new Intent(getApplicationContext(), MainActivityLogin.class);
            startActivity(login);

            login2.edit().putBoolean("cekLogin", false).apply();
            finish();

        }else if (id == R.id.nav_logout) {
            Intent intent = getIntent();
            startActivity(intent);

            login2.edit().putBoolean("cekLogin", true).apply();
            finish();
        }//else if (id == R.id.nav_Daftar) {
           // Intent daftar = new Intent(getApplicationContext(), DaftarActivity.class);
           // startActivity(daftar);
           // finish();
           // login2.edit().putBoolean("cekLogin", false).apply();
            //Intent daftar = new Intent(getApplicationContext(), DaftarActivity.class);
            //startActivity(daftar);

      //  }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClick(View view){
        final Intent pesan = new Intent(getApplicationContext(), ListJadwalActivity.class);
        Button ipa = (Button) findViewById(R.id.btnIpa);
        Button ips = (Button) findViewById(R.id.btnIps);
        Button mtk = (Button) findViewById(R.id.btnMtk);
        Button ing = (Button) findViewById(R.id.btnIng);
        Button kimia = (Button) findViewById(R.id.btnKimia);
        Button fisika = (Button) findViewById(R.id.btnFisika);

        ipa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pesan.putExtra("var1", "IPA");
                startActivity(pesan);
            }
        });

        ips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pesan.putExtra("var1", "IPS");
                startActivity(pesan);
            }
        });

        mtk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pesan.putExtra("var1", "MATEMATIKA");
                startActivity(pesan);
            }
        });

        ing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pesan.putExtra("var1","B. INGGRIS");
                startActivity(pesan);
            }
        });

        kimia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pesan.putExtra("var1","KIMIA");
                startActivity(pesan);
            }
        });

        fisika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pesan.putExtra("var1", "FISIKA");
                startActivity(pesan);
            }
        });
    }
}
