package net.uk.onetransport.android.test.onetransporttest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO Think how best to test the sync adapter.
//        BucksSyncAdapter.refresh(getApplicationContext());
    }
}
