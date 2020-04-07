package com.example.association;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.association.Entities.ParametreOkHttp;
import com.example.association.Utilities.CallServiceWeb;
import com.example.association.Utilities.Constantes;
import com.example.association.Utilities.Functions;

import java.util.ArrayList;
import java.util.HashMap;

public class SplashActivity extends AppCompatActivity {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context = this;

        try{
            //CallServiceWeb.AsyncTask(context, , Constantes.URL_GETIDSESSION,null);
            SleepTask sleepTask = new SleepTask(Constantes.URL_GETIDSESSION, null);
            sleepTask.execute();
        }
        catch (Exception ex){
            String message = ex.getMessage();
        }
    }

    private class SleepTask extends AsyncTask<String,String,String>{

        private String _Url;
        private ArrayList<ParametreOkHttp> _Parametres;

        public SleepTask(String url,@Nullable ArrayList<ParametreOkHttp> parametres) {
            this._Url = url;
            this._Parametres = parametres;
        }

        @Override
        protected String doInBackground(String... voids) {
            String message = "";
            //Simule un appel service web
            try {
                //Thread.sleep(5000);

                message = Functions.callServiceWeb(null, "getid");
            } catch (Exception e) {
                message = e.getMessage();
            }
            return message;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            Toast.makeText(SplashActivity.this, result, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            intent.putExtra("sessionId", result);
            startActivity(intent);
            finish();

        }
    }
}
