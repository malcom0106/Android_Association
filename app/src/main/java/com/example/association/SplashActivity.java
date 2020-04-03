package com.example.association;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

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

            //Simule un appel service web
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
