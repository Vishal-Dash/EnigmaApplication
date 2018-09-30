package appstore.com.enigmaapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class DisplayQuiz extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);

    }
    public void select(View v) {
        int id = v.getId();
        RadioButton rb;/*
        switch (id) {
            case R.id.rb1:
                Toast.makeText(this,"1",Toast.LENGTH_LONG).show();
                rb = (RadioButton) findViewById(R.id.rb2);
                rb.setChecked(false);
                rb = (RadioButton) findViewById(R.id.rb3);
                rb.setChecked(false);
                rb = (RadioButton) findViewById(R.id.rb4);
                rb.setChecked(false);
                break;
            case R.id.rb2:
                Toast.makeText(this,"2",Toast.LENGTH_LONG).show();
                rb = (RadioButton) findViewById(R.id.rb1);
                rb.setChecked(false);
                rb = (RadioButton) findViewById(R.id.rb3);
                rb.setChecked(false);
                rb = (RadioButton) findViewById(R.id.rb4);
                rb.setChecked(false);
                break;
            case R.id.rb3:
                Toast.makeText(this,"3",Toast.LENGTH_LONG).show();
                rb = (RadioButton) findViewById(R.id.rb2);
                rb.setChecked(false);
                rb = (RadioButton) findViewById(R.id.rb1);
                rb.setChecked(false);
                rb = (RadioButton) findViewById(R.id.rb4);
                rb.setChecked(false);
                break;
            default:
                Toast.makeText(this,"4",Toast.LENGTH_LONG).show();
                rb = (RadioButton) findViewById(R.id.rb2);
                rb.setChecked(false);
                rb = (RadioButton) findViewById(R.id.rb3);
                rb.setChecked(false);
                rb = (RadioButton) findViewById(R.id.rb1);
                rb.setChecked(false);
                break;
        }*/
        Toast.makeText(this,"Radiobutton pressed",Toast.LENGTH_LONG).show();
    }
}
