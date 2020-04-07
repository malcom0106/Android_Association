package com.example.association;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.association.Entities.Adherent;
import com.example.association.Entities.Adherents;
import com.example.association.Entities.ParametreOkHttp;
import com.example.association.Entities.ParametresOkHttp;
import com.example.association.Utilities.CallServiceWeb;
import com.example.association.Utilities.Constantes;
import com.example.association.Utilities.Session;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    EditText txtLogin;
    EditText txtPassword;
    Adherent adherent;
    Context context;
    String idsession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        Intent intent = getIntent();
        Session.setId(intent.getStringExtra("idsession"));
        txtLogin = findViewById(R.id.txtLogin);
        txtPassword = findViewById(R.id.txtPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        idsession = Session.getId();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //On recupere les valeurs dans les textEdit
                ParametresOkHttp parametresOkHttp = new ParametresOkHttp();
                parametresOkHttp.add(new ParametreOkHttp("login" , txtLogin.getText().toString().trim()));
                parametresOkHttp.add(new ParametreOkHttp("password" , txtPassword.getText().toString().trim()));

                //On fait une tache asynchrone
                AsyncCallWS asyncCallWS = new AsyncCallWS(Constantes.URL_GETLOGIN,parametresOkHttp);
                asyncCallWS.execute();
            }
        });
    }

    private class AsyncCallWS extends AsyncTask<String, Integer,String> {

        private String url;
        private ParametresOkHttp parametresOkHttp;

        public AsyncCallWS(String url,@Nullable ParametresOkHttp parametresOkHttp) {
            this.url = url;
            this.parametresOkHttp = parametresOkHttp;
        }

        @Override
        protected String doInBackground(String... strings) {
            return CallServiceWeb.CallServiceWeb(this.url, this.parametresOkHttp);
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(!s.isEmpty() || !s.equals("\"\"")){
                try{
                    Gson gson = new Gson();

                    try{

                        //Creer un adherent pour verifier les variables transmis par le WS
                        adherent = gson.fromJson(s, Adherent.class);

                        // print staff object
                        //System.out.println(adherent);
                        if(adherent == null){
                            finish();
                        }else{
                            //Associer l'adherent à une session
                            Session.setAdherent(adherent);
                            Session.setId(idsession);

                            //Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
                            //Redirect vers HomeActivity
                            Intent intent = new Intent(context,HomeActivity.class);
                            startActivity(intent);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
                catch(Exception ex){
                    Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(context, "Identifiants Incorrects", Toast.LENGTH_LONG).show();
                }
            } 
            else {
                Toast.makeText(context, "Identifiants Incorrects", Toast.LENGTH_LONG).show();
            }
            //Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        }
    }
}
