package com.riningan.glowtextview;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;

import com.riningan.widget.GlowTextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private GlowTextView mGlowTextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGlowTextView = (GlowTextView) findViewById(R.id.glowTextView);

        SeekBar seekbar = (SeekBar) findViewById(R.id.seekBar);
        seekbar.setProgress((int) mGlowTextView.getGlowRadius());
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mGlowTextView.setGlowRadius(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mGlowTextView.setTypeface(null, Typeface.BOLD);
                } else {
                    mGlowTextView.setTypeface(null, Typeface.NORMAL);
                }
            }
        });

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                mGlowTextView.setGlowColor(Color.WHITE);
                break;
            case R.id.btn2:
                mGlowTextView.setGlowColor(Color.RED);
                break;
            case R.id.btn3:
                mGlowTextView.setGlowColor(Color.GREEN);
                break;
        }
    }
}
