package com.example.association.Utilities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.association.Entities.ParametreOkHttp;
import com.example.association.MainActivity;
import com.example.association.SplashActivity;

import java.util.ArrayList;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CallServiceWeb {
    public static String CallServiceWeb(String url,@Nullable ArrayList<ParametreOkHttp> parameters)
    {
        String retourSW = "";
        //String url = "http://claudehenry.fr/serviceweb/LoginAdherent";
        //String url = "http://claudehenry.fr/serviceweb/bonjour";

        OkHttpClient client = new OkHttpClient();


        HttpUrl.Builder httpBuider = HttpUrl.parse(url).newBuilder();
        if(parameters != null){
            if(parameters.size()>0){
                ParametreOkHttp parametreOkHttp = null;
                for(int i = 0 ;  i < parameters.size(); i++){
                    parametreOkHttp = parameters.get(i);
                    httpBuider.addQueryParameter(parametreOkHttp.getKey(), parametreOkHttp.getValue());
                }
            }
        }


        Request request =
                new Request.Builder()
                .url(httpBuider.build())
                .build();

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

    /* public static class AsyncTask extends android.os.AsyncTask<String,String,String> {

        private AppCompatActivity _ActivityCurrent;
        private String _Url;
        private ArrayList<ParametreOkHttp> _Parametres;
        private Context _context;

        public AsyncTask(Context context, AppCompatActivity activity  , String url, @Nullable ArrayList<ParametreOkHttp> parametres) {
            this._ActivityCurrent = activity;
            this._context = context;
            this._Url = url;
            this._Parametres = parametres;
        }

        @Override
        protected String doInBackground(String... voids) {
            String message = "";
            //Simule un appel service web
            try {
                //Thread.sleep(5000);
                message = CallServiceWeb.CallServiceWeb(_Url, _Parametres);
            } catch (Exception e) {
                message = e.getMessage();
            }
            return message;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            Toast.makeText(_context, result, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(_context, _ActivityCurrent.getClass());
            intent.putExtra("Resultat", result);
            _context.startActivity(intent);
        }
    } */
}
