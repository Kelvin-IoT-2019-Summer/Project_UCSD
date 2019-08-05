package app.papr.Qualcomm;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.PhoneNumberUtils;
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

import androidx.versionedparcelable.VersionedParcelize;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sign_upActivity extends AppCompatActivity {
    JSONObject jsonObject, signup_result_json;
    private EditText firstname,lastname,nickname,email,password,check_password,birth,phone,state,city;
    private Button register;
    private TextView announce,announce2;
    private String issamepw;
    private RadioGroup gender;
    private RadioButton female,male;
    private String sex;
    private String url = "http://teama-iot.calit2.net/android/user/sign-up/process";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);
        //full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //action bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.no_title);
        TextView title = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.mytext);
        getSupportActionBar().setElevation(0);
        title.setText("Create an Account!");

        issamepw = " ";
        firstname = (EditText)findViewById(R.id.firstname);
        lastname = (EditText)findViewById(R.id.lastname);
        nickname = (EditText)findViewById(R.id.nickname);
        email= (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        check_password = (EditText)findViewById(R.id.passwordcheck);
        announce = (TextView)findViewById(R.id.passwordannounce);
        announce2 = (TextView)findViewById(R.id.passwordannounce2);
        birth= (EditText)findViewById(R.id.birth);
        phone = (EditText) findViewById(R.id.phone);
        state= (EditText)findViewById(R.id.state);
        city= (EditText)findViewById(R.id.city);
        register = (Button)findViewById(R.id.submit);

        phone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        gender = (RadioGroup) findViewById(R.id.gender);
        female = (RadioButton)findViewById(R.id.female);
        male= (RadioButton)findViewById(R.id.male);
        sex = "F";

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                switch (checkedId){
                    case R.id.male:
                        sex = "M";
                        break;
                    case R.id.female:
                        sex = "F";
                        break;
                }
            }
        });


        password.addTextChangedListener(new TextWatcher() {
            @Override //입력하기 전에 호출되는 API
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                announce.setText("Minimun of 8 characters in length");
            }
            @Override //EditText에 변화가 있을 때
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                announce.setTextColor(Color.BLACK);
                Matcher matcher = VALID_PASSWOLD_REGEX_ALPHA_NUM.matcher(text);
                if(matcher.matches()==false){
                    announce.setTextColor(Color.RED);
                    announce.setText("Your password must contain at least one\nuppercase, one lowercase, one number, and special character");
                }else {
                    announce.setTextColor(Color.BLACK);
                    announce.setText("secure password");
                }
            }
            @Override //입력이 끝났을 때
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                if(text.length()==0){
                    announce.setTextColor(Color.BLACK);
                    announce.setText("Input your Password");
                }
                else{
                    issamepw = text;
                }
            }
        });

        check_password.addTextChangedListener(new TextWatcher() {
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

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname_ = firstname.getText().toString().trim();
                String lastname_ = lastname.getText().toString().trim();
                String email_ = email.getText().toString().trim();
                String password_ = password.getText().toString().trim();
                String password_confirm_ = check_password.getText().toString().trim();
                String birth_ = birth.getText().toString().trim();
                String phone_ = phone.getText().toString().trim();
                String state_ = state.getText().toString().trim();
                String city_ = city.getText().toString().trim();

                if(firstname_.isEmpty()|lastname_.isEmpty()|email_.isEmpty()|password_.isEmpty()|password_confirm_.isEmpty()|birth_.isEmpty()|phone_.isEmpty()|state_.isEmpty()|city_.isEmpty()) {
                    Toast.makeText(Sign_upActivity.this, "Input your All Information.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Matcher matcher = VALID_EMAIL_REGEX_GOL_COM.matcher(email_);
                    if(matcher.matches()==true) {
                        matcher = VALID_PASSWOLD_REGEX_ALPHA_NUM.matcher(password_);
                        if(matcher.matches()==true) {
                            //Check Password
                            if (password_.equals(password_confirm_)) {
                                jsonObject = new JSONObject();
                                try {
                                    //jsonObject.put("type", "SUE-REQ");
                                    //앞에 프로토콜 명 써주는 게 좋을 듯 (나중에 수정)
                                    jsonObject.put("Email", email.getText());
                                    jsonObject.put("FirstName", password.getText());
                                    jsonObject.put("LastName", lastname.getText());
                                    jsonObject.put("nickname", nickname.getText());
                                    jsonObject.put("Password", password.getText());
                                    jsonObject.put("birth", birth.getText());
                                    jsonObject.put("phone", phone.getText());
                                    jsonObject.put("gender", sex);
                                    jsonObject.put("state", state.getText());
                                    jsonObject.put("city", city.getText());
                                    jsonObject.put("authorized_code", "0");

                                    //request
                                    Receive_json receive_json = new Receive_json();
                                    signup_result_json = receive_json.getResponseOf(Sign_upActivity.this, jsonObject, url);

                                    //resoponse
                                    if (signup_result_json != null) {
                                        if (signup_result_json.getString("error").equals("none")) {
                                            Toast.makeText(Sign_upActivity.this, "Send Authentication E-mail.", Toast.LENGTH_SHORT).show();
                                            finish();
                                        } else {
                                            Toast.makeText(Sign_upActivity.this, "Duplicated email address", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Toast.makeText(Sign_upActivity.this, "Check your Password and Repeat Password.", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(Sign_upActivity.this, "Check your Password format.", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(Sign_upActivity.this, "Check your Email format.", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

    }


    public static final Pattern VALID_EMAIL_REGEX_GOL_COM = Pattern.compile("^[0-9a-zA-Z][0-9a-zA-Z\\_\\-\\.\\+]+[0-9a-zA-Z]@[0-9a-zA-Z][0-9a-zA-Z\\_\\-]*[0-9a-zA-Z](\\.[a-zA-Z]{2,6}){1,2}$");
    public static final Pattern VALID_PASSWOLD_REGEX_ALPHA_NUM = Pattern.compile("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).{8,}");




}
