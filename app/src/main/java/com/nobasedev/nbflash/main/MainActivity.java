package com.nobasedev.nbflash.main;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nobasedev.nbflash.R;

public class MainActivity extends AppCompatActivity {

    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(mainFragment == null) {
            mainFragment = MainFragment.newInstance();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.contentFrame, mainFragment);
            transaction.commit();

            //mMainPresenter = new MainPresenter()
        }
    }
}
