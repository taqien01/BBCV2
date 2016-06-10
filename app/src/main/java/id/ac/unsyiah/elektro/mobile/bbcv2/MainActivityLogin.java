package id.ac.unsyiah.elektro.mobile.bbcv2;

/**
 * Created by User on 6/9/2016.
 */

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivityLogin extends Activity {
    Button btnSignIn,btnSignUp;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivitylogin);

        // create a instance of SQLite Database
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        // Get The Refference Of Buttons
        btnSignIn=(Button)findViewById(R.id.buttonSignIN);
        btnSignUp=(Button)findViewById(R.id.buttonSignUP);

        // Set OnClick Listener on SignUp button
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                /// Create Intent for SignUpActivity  abd Start The Activity
                Intent intentSignUP=new Intent(getApplicationContext(),DaftarActivity.class);
                startActivity(intentSignUP);
            }
        });
    }
    // Methos to handleClick Event of Sign In Button
    public void signIn(View V)

    {
        final Dialog dialog = new Dialog(MainActivityLogin.this);
        dialog.setContentView(R.layout.loginactivity);
        dialog.setTitle("Login");

        // get the Refferences of views
        final EditText editTextEmail =(EditText)dialog.findViewById(R.id.editTextEmailToLogin);
        final  EditText editTextPassword=(EditText)dialog.findViewById(R.id.editTextPasswordToLogin);

        Button btnSignIn=(Button)dialog.findViewById(R.id.buttonSignIn);

        // Set On ClickListener
        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // get The User name and Password
                String userName= editTextEmail.getText().toString();
                String password=editTextPassword.getText().toString();

                // fetch the Password form database for respective user name
                String storedPassword=loginDataBaseAdapter.getSinlgeEntry(userName);


                // check if the Stored password matches with  Password entered by user
                if(password.equals(storedPassword))
                {
                    Toast.makeText(MainActivityLogin.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                    dialog.dismiss();

                    SharedPreferences login2 =
                            getSharedPreferences("id.ac.unsyiah.elektro.mobile.bbcv2.PREF_BERSAMA", Context.MODE_PRIVATE);
                    login2.edit().putBoolean("cekLogin", false).apply();
                    login2.edit().putString("user", userName).apply();

                    Intent masuk=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(masuk);
                    finish();
                }
                else
                {
                    Toast.makeText(MainActivityLogin.this, "Email or Password does not match", Toast.LENGTH_LONG).show();
                }
            }
        });

        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        loginDataBaseAdapter.close();
    }
}