package a.com;

import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetchData extends AsyncTask<Void,Void,Void> {
    private String pin;
    public  String data="";

    public fetchData(String pin) {
        this.pin = pin;
    }
//    String dataParsed="";
//    String singleParsed="";
    @Override
    protected Void doInBackground(Void... voids) {
        String web_address = "https://api.postalpincode.in/pincode/";
        web_address=web_address+pin;
        try {
            URL url = new URL(web_address);
            HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader((new InputStreamReader(inputStream)));
            String line="";
            while(line!=null)
            {
                line=bufferedReader.readLine();
                data=data + line;
            }
            MainActivity.details=data;

//            JSONArray JA = new JSONArray(data);
//            for(int i=0;i<JA.length();i++)
//            {
//                JSONObject JO = (JSONObject) JA.get(i);
//                singleParsed="Status"+ JO.get("Status" + " " );
//
//                dataParsed=dataParsed+singleParsed+"\n";
//            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        //Character flag = data.charAt(14);
        if( data.charAt(14)=='o') {
            MainActivity.datas.setText("Wrong Pincode Entered");
        }
        else
        {
            int j;
            for(int i=0;i<data.length();i++)
            {
                if(data.substring(i,i+8).equals("District"))
                {
                    for(j=i+12;data.charAt(j)!=',';j++);
                    MainActivity.district=data.substring(i+11,j-1);
                }
                if(data.substring(i,i+5).equals("State"))
                {
                    for(j=i+9;data.charAt(j)!=',';j++);
                    MainActivity.state=data.substring(i+8,j-1);
                    break;
                }

            }
            MainActivity.textView_state.setText("State : "+MainActivity.state);
            MainActivity.textView_district.setText("District : " + MainActivity.district);
            MainActivity.datas.setText("State and District found");
        }
        super.onPostExecute(aVoid);
    }
}
