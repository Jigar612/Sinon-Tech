package com.example.puzzlegame.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.puzzlegame.R;
import com.example.puzzlegame.databinding.ActivityResultBinding;

import static com.example.puzzlegame.util.AppConstant.KEY_CORRECT_COUNT;
import static com.example.puzzlegame.util.AppConstant.KEY_TOTAL_COUNT;
import static com.example.puzzlegame.util.AppConstant.KEY_WRONG_COUNT;

public class ResultActivity extends AppCompatActivity {
    private final Context mContext = this;
    private ActivityResultBinding binding;
    public int correctCount = 0, wrongCount = 0;
    public int totalTryCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView((Activity) mContext, R.layout.activity_result);

        Intent intent = getIntent();
        correctCount = intent.getIntExtra(KEY_CORRECT_COUNT, 0);
        wrongCount = intent.getIntExtra(KEY_WRONG_COUNT, 0);
        totalTryCount = intent.getIntExtra(KEY_TOTAL_COUNT, 0);


        binding.tvTotalAttempt.setText("Your total guess is " + totalTryCount);
        binding.tvTotalCorrectValue.setText(String.valueOf(correctCount));
        binding.tvTotalWrongValue.setText(String.valueOf(wrongCount));
    }
}