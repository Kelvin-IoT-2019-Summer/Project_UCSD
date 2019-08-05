package app.papr.Qualcomm;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class Sign_inActivity extends AppCompatActivity {
    private TextView donothaveid;
    private TextView forgotpw;
    private Button login;
    private EditText email;
    private EditText password;
    JSONObject jsonObject, signin_result_json;
    private String url = "http://teama-iot.calit2.net/android/user/sign-in/process/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_activity);
        //actionbar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.no_title);
        TextView title = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.mytext);
        title.setText("Welcome Back!");
        getSupportActionBar().setElevation(0);
        //full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        donothaveid = (TextView)findViewById(R.id.dont_have);
        forgotpw = (TextView)findViewById(R.id.forgot_password);
        login = (Button)findViewById(R.id.btn_login);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);

        donothaveid.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Sign_inActivity.this, Sign_upActivity.class);
                //intent.putExtra(“text”,String.valueOf(editText.getText()));
                startActivity(intent);
            }
        });
        forgotpw.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Sign_inActivity.this, ForgottenActivity.class);
                //intent.putExtra(“text”,String.valueOf(editText.getText()));
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String email_ = email.getText().toString().trim();
                String password_ = password.getText().toString().trim();
                if (email_.isEmpty() | password_.isEmpty()) {
                    Toast.makeText(Sign_inActivity.this, "ID or Password is empty.", Toast.LENGTH_SHORT).show();
                } else {
                    jsonObject = new JSONObject();
                    try {
                        //jsonObject.put("type", "SUE-REQ");
                        //앞에 프로토콜 명 써주는 게 좋을 듯 (나중에 수정)
                        jsonObject.put("email", email.getText());
                        jsonObject.put("Password", password.getText());
                        //request
                        Receive_json receive_json = new Receive_json();
                        signin_result_json = receive_json.getResponseOf(Sign_inActivity.this, jsonObject, url);
                        //resoponse
                        if (signin_result_json != null) {
                            if (signin_result_json.getInt("result_code")==1) {
                                //USN store
                                Sequence.USN = signin_result_json.getInt("usn");
                                Sequence.EMAIL = email.getText().toString();
                                Toast.makeText(Sign_inActivity.this, "Hello :)", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Sign_inActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else if (signin_result_json.getInt("result_code")== 2) {
                                Toast.makeText(Sign_inActivity.this, "Your email not found.", Toast.LENGTH_SHORT).show();
                            } else if (signin_result_json.getInt("result_code")== 8) {
                                Toast.makeText(Sign_inActivity.this, "Authentication in email is required.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Sign_inActivity.this, "Your password is not correct.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }
        });

    }
}
