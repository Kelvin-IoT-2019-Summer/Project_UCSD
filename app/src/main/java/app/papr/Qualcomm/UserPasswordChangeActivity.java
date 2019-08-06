package app.papr.Qualcomm;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserPasswordChangeActivity extends AppCompatActivity {
    JSONObject jsonObject, pwchange_result_json;
    private Button change;
    private TextView announce1,announce2;
    private String issamepw;
    private EditText old_password,new_password,re_password;
    private String url = "http://teama-iot.calit2.net/android/user/change_password/process";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_pw_change_activity);
        //full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //action bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.no_title);
        TextView title = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.mytext);
        getSupportActionBar().setElevation(0);
        title.setText("Password Change!");

        issamepw = " ";

        old_password= (EditText)findViewById(R.id.old_password);
        new_password = (EditText)findViewById(R.id.new_password);
        re_password = (EditText)findViewById(R.id.re_password);
        announce1 = (TextView)findViewById(R.id.passwordannounce1);
        announce2 = (TextView)findViewById(R.id.passwordannounce2);
        change = (Button)findViewById(R.id.change);
        new_password.addTextChangedListener(new TextWatcher() {
            @Override //입력하기 전에 호출되는 API
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                announce1.setText("Minimun of 8 characters in length");
            }
            @Override //EditText에 변화가 있을 때
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                announce1.setTextColor(Color.BLACK);
                Matcher matcher = VALID_PASSWOLD_REGEX_ALPHA_NUM.matcher(text);
                if(matcher.matches()==false){
                    announce1.setTextColor(Color.RED);
                    announce1.setText("Your password must contain at least one\nuppercase, one lowercase, one number, and special character");
                }else {
                    announce1.setTextColor(Color.BLACK);
                    announce1.setText("secure password");
                }
            }
            @Override //입력이 끝났을 때
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                if(text.length()==0){
                    announce1.setTextColor(Color.BLACK);
                    announce1.setText("Input your Password");
                }
                else{
                    issamepw = text;
                }
            }
        });

        re_password.addTextChangedListener(new TextWatcher() {
            @Override //입력하기 전에 호출되는 API
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override //EditText에 변화가 있을 때
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override //입력이 끝났을 때
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                if (text.equals(issamepw.trim())){
                    announce2.setTextColor(Color.BLACK);
                    announce2.setText("password match");
                }else{
                    announce2.setTextColor(Color.RED);
                    announce2.setText("password mismatch");
                }
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String old_password_ = old_password.getText().toString().trim();
                String new_password_= new_password.getText().toString().trim();
                String re_password_= re_password.getText().toString().trim();

                if(old_password_.isEmpty()|new_password_.isEmpty()|re_password_.isEmpty()) {
                    Toast.makeText(UserPasswordChangeActivity.this, "Input your All Information.", Toast.LENGTH_SHORT).show();
                }
                else{
                        Matcher matcher = VALID_PASSWOLD_REGEX_ALPHA_NUM.matcher(new_password_);
                        if(matcher.matches()==true) {
                            //Check Password
                            if (new_password_.equals(re_password_)) {
                                jsonObject = new JSONObject();
                                try {
                                    //jsonObject.put("type", "SUE-REQ");
                                    //앞에 프로토콜 명 써주는 게 좋을 듯 (나중에 수정)
                                    jsonObject.put("usn",Sequence.USN);
                                    jsonObject.put("old_password", old_password.getText());
                                    jsonObject.put("new_password", new_password.getText());

                                    //request
                                    Receive_json receive_json = new Receive_json();

                                    pwchange_result_json= receive_json.getResponseOf(UserPasswordChangeActivity.this, jsonObject, url);

                                    //resoponse
                                    if (pwchange_result_json!= null) {
                                        if (pwchange_result_json.getInt("result_code")==1){
                                            Toast.makeText(UserPasswordChangeActivity.this, "Password change success.", Toast.LENGTH_SHORT).show();
                                            finish();
                                        } else {
                                            Toast.makeText(UserPasswordChangeActivity.this, "Original password is wrong", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Toast.makeText(UserPasswordChangeActivity.this, "Check your Password and Repeat Password.", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(UserPasswordChangeActivity.this, "Check your Password format.", Toast.LENGTH_SHORT).show();
                        }

                }

            }
        });

    }

    public static final Pattern VALID_PASSWOLD_REGEX_ALPHA_NUM = Pattern.compile("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).{8,}");




}
