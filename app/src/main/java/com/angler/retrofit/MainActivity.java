package com.angler.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.angler.retrofit.http.Listener;
import com.angler.retrofit.model.Model;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Listener {

    TextView retroCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retroCall = findViewById(R.id.retro_call);

        retroCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Webservices.getSIGGroupList(MainActivity.this);
            }
        });
    }

    @Override
    public void onResponse(Object object) {
        try {
            Model.SIGGroup myGroup = (Model.SIGGroup) ((Response) object).body();
            if (myGroup != null) {
                if (myGroup.response.response_code == 1) {
                    Toast.makeText(MainActivity.this, myGroup.response.response_message, Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(MainActivity.this, myGroup.response.response_message, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
