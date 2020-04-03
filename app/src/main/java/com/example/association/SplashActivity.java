package com.example.association;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.association.Utilities.CallServiceWeb;
import com.example.association.Utilities.Constantes;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        try{
            SleepTask sleepTask = new SleepTask();
            sleepTask.execute();
        }
        catch (Exception ex){
            String message = ex.getMessage();
        }
    }
    private class SleepTask extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... voids) {
            String message = "";
            //Simule un appel service web
            try {
                //Thread.sleep(5000);
                message = CallServiceWeb.CallServiceWeb(Constantes.URL_GETIDSESSION, null);
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
