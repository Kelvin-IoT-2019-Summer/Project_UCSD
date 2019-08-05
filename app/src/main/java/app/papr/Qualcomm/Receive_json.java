package app.papr.Qualcomm;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Receive_json {

    ProgressDialog waitingDialog;
    Handler handler = new Handler();
    JSONObject jsonObject = new JSONObject();
    String url = "";
    BufferedReader bf;
    InputStream in;
    Context context;
//    private static Receive_json instance = new Receive_json();
//    public static Receive_json getInstance() {
//        return instance;
//    }


    public JSONObject getResponseOf(Context ctx, JSONObject sendMsg,String url) {
        this.url = url;
        context = ctx;
        try {
            waitingDialog = new ProgressDialog(ctx);
            waitingDialog.setMessage("Now loading...");
            waitingDialog.setIndeterminate(false);
            waitingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            waitingDialog.setCancelable(false);
            waitingDialog.show();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    waitingDialog.dismiss();
                    waitingDialog.cancel();
                }
            }, 1500);

            DisplayLoadingComm dlc = new DisplayLoadingComm();
            JSONObject receivedMsg = dlc.execute(sendMsg).get();

            Log.w("test received from svr", receivedMsg.toString());
            return receivedMsg;

        } catch (Exception ex) {
            ex.printStackTrace();
            Log.w("test", "Transmit failed....");
            Toast.makeText(context, "Transmit failed....", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    public class DisplayLoadingComm extends AsyncTask<JSONObject, Void, JSONObject> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected JSONObject doInBackground(JSONObject... jsonObjects) {
            String data = "";
            HttpURLConnection httpURLConnection = null;
            String msg = jsonObjects[0].toString();
            try {
                httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
                httpURLConnection.setReadTimeout(5000 /*milliseconds*/);
                httpURLConnection.setConnectTimeout(5000 /* milliseconds */);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                //make some HTTP header nicety
                httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
                httpURLConnection.setRequestProperty("X-Requested-With", "XMLHttpRequest");
                httpURLConnection.setFixedLengthStreamingMode(msg.getBytes().length);
                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                wr.writeBytes(msg);
                wr.flush();
                wr.close();
                Log.w("test", "Data sent...." + msg);

                in = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(in);

                bf = new BufferedReader(new InputStreamReader(in));
                data = bf.readLine();
                if(!data.endsWith("}"))
                    data = data+"}]}";

                bf.close();
                in.close();

//                int inputStreamData = inputStreamReader.read();
//                while (inputStreamData != null) {
//                    char current = (char) inputStreamData;
//                    inputStreamData = inputStreamReader.read();
//                    data += current;
//                }
                Log.w("test", "Data received...." + data);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            }

            try {
                jsonObject = new JSONObject(data);
            } catch (Exception ex) {
                ex.printStackTrace();
                jsonObject = null;
                Log.w("test", "Transmit failed....");
                Toast.makeText(context, "Transmit failed....", Toast.LENGTH_SHORT).show();
            }
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            super.onPostExecute(result);
            waitingDialog.cancel();
        }
    }
}