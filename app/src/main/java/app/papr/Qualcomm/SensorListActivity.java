package app.papr.Qualcomm;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SensorListActivity extends AppCompatActivity {

    private Button deregistration;
    JSONObject jsonObject,jsonObject2, sensorlist_result_json,sensordereg_result_json;
    private ArrayAdapter<String> mregDevicesArrayAdapter;
    ListView regDevicesListView;
    ArrayList<String> arrayList = new ArrayList<String>();

    String url = "http://teama-iot.calit2.net/android/device/list_view";
    String url2 = "http://teama-iot.calit2.net/android/device/deregistrate_process";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_list_activity);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.no_title);
        getSupportActionBar().setElevation(0);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mregDevicesArrayAdapter = new ArrayAdapter<String>(SensorListActivity.this, android.R.layout.simple_list_item_1, arrayList);
        regDevicesListView = (ListView) findViewById(R.id.reglist);
        regDevicesListView.setAdapter(mregDevicesArrayAdapter);
        jsonObject = new JSONObject();
        try {
            //jsonObject.put("type", "SUE-REQ");
            //앞에 프로토콜 명 써주는 게 좋을 듯 (나중에 수정)
            jsonObject.put("usn", 12/*Sequence.USN*/);
            //request
            Receive_json receive_json = new Receive_json();
            sensorlist_result_json = receive_json.getResponseOf(SensorListActivity.this, jsonObject, url);

            //resoponse
            if (sensorlist_result_json != null) {
                if (sensorlist_result_json.getInt("result_code") == 1) {
                    String ssn =sensorlist_result_json.getJSONObject("0").getString("ssn");
                    String usn = sensorlist_result_json.getJSONObject("0").getString("usn");
                    String device_name = sensorlist_result_json.getJSONObject("0").getString("device_name");
                    String mac = sensorlist_result_json.getJSONObject("0").getString("mac");
                    Log.w("EEE", device_name + mac);
                    //sharedPreference value store
                    SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString(mac,ssn);
                    editor.commit();

                    mregDevicesArrayAdapter.add(device_name + "\n" + mac);
                    if (mregDevicesArrayAdapter.getCount() == 0) {
                        String noDevices = getResources().getText(R.string.none_found).toString();
                        mregDevicesArrayAdapter.add(noDevices);
                    }
                } else {
                    Toast.makeText(SensorListActivity.this, "Not found any device.", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        deregistration = (Button) findViewById(R.id.deregistration);
        deregistration.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
            }

        });

        regDevicesListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                removeItemFromList(position);
                return true;
            }
        });
    }


    protected void removeItemFromList(final int position) {
        final int deletePosition = position;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert!");
        builder.setMessage("Do you want delete this Device?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dev_deregi(arrayList.get(position),position);

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
    public void dev_deregi(String dev_n, int po)
    {

        jsonObject2 = new JSONObject();
        try {
            StringTokenizer tokens = new StringTokenizer(dev_n, "\n");
            String dev_name = tokens.nextToken();
            String mac_address = tokens.nextToken();
            SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
            Log.e("EEEE", pref.getString(mac_address,""));

            jsonObject2.put("usn", 12/*수정*/);
            jsonObject2.put("ssn", pref.getString(mac_address,""));


            Receive_json receive_json = new Receive_json();
            sensordereg_result_json = receive_json.getResponseOf(SensorListActivity.this, jsonObject2, url2);

            if(sensordereg_result_json != null) {
                if (sensordereg_result_json.getInt("result_code")==1) {
                    arrayList.remove(po);
                    mregDevicesArrayAdapter.notifyDataSetChanged();
                    mregDevicesArrayAdapter.notifyDataSetInvalidated();
                }
                else {
                    Log.w("sensor_data_fail...","");
                    Toast.makeText(SensorListActivity.this, "Device Deregistration Fail", Toast.LENGTH_SHORT).show();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}

