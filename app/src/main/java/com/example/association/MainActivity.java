package com.example.association;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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
        Session.setId(intent.getStringExtra("sessionid"));
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
                parametresOkHttp.add(new ParametreOkHttp("idsession" , idsession));


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


        private void openCustomAlertDialog(Context _context){

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(_context);

            alertDialogBuilder.setTitle("Créditer compte");

            LayoutInflater li = LayoutInflater.from(_context);
            View view = li.inflate(R.layout.custom, null);

            final EditText txtMontant = view.findViewById(R.id.txtMontant);

            alertDialogBuilder.setView(view);

            alertDialogBuilder.setPositiveButton("Oui",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            String montant = txtMontant.getText().toString().trim();
                            adherent.setSolde(adherent.getSolde() + Double.parseDouble(montant));
                            Session.setAdherent(adherent);
                        }
                    });

            alertDialogBuilder.setNegativeButton("Fermer", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }
}
