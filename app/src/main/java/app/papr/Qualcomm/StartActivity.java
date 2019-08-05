package app.papr.Qualcomm;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity  extends AppCompatActivity {

    private Button Sign_in_btn;
    private Button Sign_up_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.no_title);
        getSupportActionBar().setElevation(0);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Sign_in_btn = (Button) findViewById(R.id.sign_in_btn) ;
        Sign_in_btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StartActivity.this, Sign_inActivity.class);
                //intent.putExtra(“text”,String.valueOf(editText.getText()));
                startActivity(intent);
            }
        });

        Sign_up_btn = (Button) findViewById(R.id.sign_up_btn) ;
        Sign_up_btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StartActivity.this, Sign_upActivity.class);
                //intent.putExtra(“text”,String.valueOf(editText.getText()));
                startActivity(intent);
            }
        });

    }



}
