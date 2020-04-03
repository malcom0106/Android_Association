package com.example.association.Utilities;

import android.util.Log;

import androidx.annotation.Nullable;

import com.example.association.Entities.ParametreOkHttp;

import java.util.ArrayList;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CallServiceWeb {
    public static String CallServiceWeb(String url,@Nullable ArrayList<ParametreOkHttp> parametres)
    {
        String retourSW = "";
        //String url = "http://claudehenry.fr/serviceweb/LoginAdherent";
        //String url = "http://claudehenry.fr/serviceweb/bonjour";

        OkHttpClient client = new OkHttpClient();

        //Request request = new Request.Builder().url(url).get().build();

        HttpUrl.Builder httpBuider = HttpUrl.parse(url).newBuilder();
        if(parametres != null){
            if(parametres.size()>0){
                ParametreOkHttp parametreOkHttp = null;
                for(int i = 0 ;  i < parametres.size(); i++){
                    parametreOkHttp = parametres.get(i);
                    httpBuider.addQueryParameter(parametreOkHttp.getKey(), parametreOkHttp.getValue());
                }
            }
        }


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
