package com.example.association;

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
import com.google.gson.Gson;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    EditText txtLogin;
    EditText txtPassword;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        txtLogin = findViewById(R.id.txtLogin);
        txtPassword = findViewById(R.id.txtPassword);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //On recupere les valeurs dans les textEdit
                String login = txtLogin.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                //On fait une tache asynchrone
                AsyncCallWS asyncCallWS = new AsyncCallWS(login,password);
                asyncCallWS.execute();
            }
        });
    }

    private class AsyncCallWS extends AsyncTask<String, Integer,String> {
        private String login;
        private String password;

        public AsyncCallWS(String login, String password) {
            this.login = login;
            this.password = password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... strings) {
            return CallServiceWeb(this.login, this.password);
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(!s.isEmpty()){
                try{
                    Gson gson = new Gson();

                    //Creer un adherent pour verifier les variables transmis par le WS
                    Adherents adherents = gson.fromJson(s, Adherents.class);


                    Intent intent = new Intent(context,HomeActivity.class);
                    intent.putExtra("adherent",s);
                    startActivity(intent);
                }
                catch(Exception ex){
                    Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        }
    }

    private String CallServiceWeb(String login, String password)
    {

        String retourSW = "";
        String url = "http://claudehenry.fr/serviceweb/LoginAdherent";
        //String url = "http://claudehenry.fr/serviceweb/bonjour";

        OkHttpClient client = new OkHttpClient();

        //Request request = new Request.Builder().url(url).get().build();

        HttpUrl.Builder httpBuider = HttpUrl.parse(url).newBuilder();
        httpBuider.addQueryParameter("login", login);
        httpBuider.addQueryParameter("password", password);

        Request request = new Request.Builder().url(httpBuider.build()).build();
        try{
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()){
                retourSW =  response.body().string();
            }
        }
        catch (Exception ex){

            retourSW= ex.getMessage();
        }
        Log.e("retourWS",retourSW);
        return retourSW;
    }

}
