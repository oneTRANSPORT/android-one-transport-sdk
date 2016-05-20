package net.uk.onetransport.android.test.onetransporttest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import net.uk.onetransport.android.county.bucks.sync.SyncAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SyncAdapter.refresh(getApplicationContext());
    }
}
