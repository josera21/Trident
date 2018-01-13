package com.example.joser.trident;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

public class Reglas extends AppCompatActivity {

    TextView tv8;
    TextView tv9;
    TextView tv10;
    TextView tv11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reglas);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv8 = (TextView)findViewById(R.id.textView8);
        tv9 = (TextView)findViewById(R.id.textView9);
        tv10 = (TextView)findViewById(R.id.textView10);
        tv11 = (TextView)findViewById(R.id.textView11);

        tv8.setMovementMethod(new ScrollingMovementMethod());
        tv9.setMovementMethod(new ScrollingMovementMethod());
        tv10.setMovementMethod(new ScrollingMovementMethod());
        tv11.setMovementMethod(new ScrollingMovementMethod());
    }

    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        switch (menuItem.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
            default:
               return super.onOptionsItemSelected(menuItem);
        }
    }

}
