package com.example.puzzlegame.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.puzzlegame.R;
import com.example.puzzlegame.adapter.MyBaseAdapter;
import com.example.puzzlegame.databinding.ActivityMainBinding;
import com.example.puzzlegame.listener.CommonActionListener;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.example.puzzlegame.util.AppConstant.KEY_CORRECT_COUNT;
import static com.example.puzzlegame.util.AppConstant.KEY_TOTAL_COUNT;
import static com.example.puzzlegame.util.AppConstant.KEY_WRONG_COUNT;

public class MainActivity extends AppCompatActivity implements CommonActionListener {

    private final Context mContext = this;
    private ActivityMainBinding binding;
    private MyBaseAdapter adapter;
    private List<String> list;
    public int correctCount = 0, wrongCount = 0;
    public int totalTryCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView((Activity) mContext, R.layout.activity_main);

        init();

    }

    public void init() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        char charAlphabet = alphabet.charAt(rnd.nextInt(alphabet.length()));

        binding.tvDispCharacter.setText(String.valueOf(charAlphabet));

        random();

    }

    public void random() {
        list = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

        String listString = new Gson().toJson(list);
        Log.d("before Shuffle : ", listString);

        Collections.shuffle(list);

        adapter = new MyBaseAdapter(mContext, list, R.layout.item_row_layout_button, this);
        binding.rvButtonView.setAdapter(adapter);
        binding.rvButtonView.setItemAnimator(new DefaultItemAnimator());
        binding.rvButtonView.setLayoutManager(new GridLayoutManager(mContext, 5));

    }

    @Override
    public void onClick(Integer pos, String buttonName) {
        String getText = list.get(pos).trim();
        String selectedButtonName = buttonName;
        Log.d("GetFromListPos", getText);
        Log.d("SelectedButtonTag", selectedButtonName);


        //region Check Charater Correct or Not.
        if (selectedButtonName.equalsIgnoreCase(binding.tvDispCharacter.getText().toString())) {
            Toast.makeText(mContext, "Correct", Toast.LENGTH_SHORT).show();
            binding.tvTotalCorrectValue.setText(String.valueOf(++correctCount));

        } else {
            Toast.makeText(mContext, "Wrong", Toast.LENGTH_SHORT).show();
            binding.tvTotalWrongValue.setText(String.valueOf(++wrongCount));
        }

        totalTryCount = correctCount + wrongCount;
        if (totalTryCount == 10) {
            new Handler().postDelayed(() -> {

                Intent intent = new Intent(mContext, ResultActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(KEY_CORRECT_COUNT, correctCount);
                intent.putExtra(KEY_TOTAL_COUNT, totalTryCount);
                intent.putExtra(KEY_WRONG_COUNT, wrongCount);
                startActivity(intent);
            }, 1600);
        } else {

            new Handler().postDelayed(() -> {
                init();
            }, 1800);

        }
    }
}
