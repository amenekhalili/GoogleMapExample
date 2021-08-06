package com.example.mvp_example.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.mvp_example.R;
import com.example.mvp_example.presenter.MainActivityPresenter;

public class MainActivity extends AppCompatActivity implements MainActivityPresenter.View {

    private MainActivityPresenter Presenter;
    private TextView myTextView;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        Presenter = new MainActivityPresenter(this);
      //  myTextView = findViewById(R.id.textView);
        EditText UserName = findViewById(R.id.UseName);
        EditText email = findViewById(R.id.editTextTextEmailAddress);

        initProgressBar();

        UserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }


    private void initProgressBar(){
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleSmall);
        progressBar.setIndeterminate(true);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(Resources.getSystem().getDisplayMetrics().widthPixels,
                250);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        this.addContentView(progressBar, params);
        showProgressBar();
    }

    @Override
    public void updateUserInfoTextView(String info) {
        myTextView.setText(info);

    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgressBar() {
progressBar.setVisibility(View.INVISIBLE);
    }
}