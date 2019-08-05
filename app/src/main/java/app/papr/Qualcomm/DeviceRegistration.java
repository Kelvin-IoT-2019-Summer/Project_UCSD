package app.papr.Qualcomm;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class DeviceRegistration  extends AppCompatActivity {

    private Button Register;
    private Button Close;
    private EditText dev_nickname;
    JSONObject jsonObject, devreg_result_json;
    String url = "http://teama-iot.calit2.net/qi/device/registrate_process";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_registration_activity);
//        getSupportActionBar().setCustomView(R.layout.no_title);
//        TextView title = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.mytext);
//        title.setText("Register your Deive");
//        getSupportActionBar().setElevation(0);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //nickname 초기화
        dev_nickname = (EditText)findViewById(R.id.dev_nickname);
        dev_nickname.setText(Sequence.dev_name);

        Register = (Button) findViewById(R.id.register) ;
        Register.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sequence.dev_nickname = dev_nickname.getText().toString().trim();

                Log.e("EEEEEE",Sequence.dev_nickname);
                Log.e("EEEEEE",Sequence.dev_name);
                Log.e("EEEEEE",Sequence.mac_addr);

                if (Sequence.dev_nickname.equals("")|Sequence.dev_name.equals("")|Sequence.mac_addr.equals("")) {
                    Toast.makeText(DeviceRegistration.this, "Please Input your device nickname.", Toast.LENGTH_SHORT).show();
                } else {
                    jsonObject = new JSONObject();
                    try {
                        //jsonObject.put("type", "SUE-REQ");
                        //앞에 프로토콜 명 써주는 게 좋을 듯 (나중에 수정)
                        jsonObject.put("usn", Sequence.USN);
                        jsonObject.put("device_name", Sequence.dev_nickname);
                        jsonObject.put("mac", Sequence.mac_addr);
                        //request
                        Receive_json receive_json = new Receive_json();
                        devreg_result_json = receive_json.getResponseOf(DeviceRegistration.this, jsonObject, url);
                        //resoponse
                        if (devreg_result_json!= null) {
                            if (devreg_result_json.getInt("result_code")==1) {
                                //USN store
                                Toast.makeText(DeviceRegistration.this, "Registration Success.", Toast.LENGTH_SHORT).show();
                                finish();
                            } else if (devreg_result_json.getInt("result_code")== 6) {
                                Toast.makeText(DeviceRegistration.this, "Your device has already registered.", Toast.LENGTH_SHORT).show();
                                finish();
                            }  else {
                                Toast.makeText(DeviceRegistration.this, "Unexpected Error.", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }
       });

        Close = (Button) findViewById(R.id.close) ;
        Close.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    //바깥레이어 클릭시 안닫히게
    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction()== MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    //안드로이드 백버튼 막기
    @Override
    public void onBackPressed(){
        return;
    }


}
