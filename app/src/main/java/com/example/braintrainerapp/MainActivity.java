package com.example.braintrainerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timerTextView, problemTextView, pointTextView, answerTextView;
    Button qtn1, qtn2, qtn3, qtn4, startButton;
    androidx.gridlayout.widget.GridLayout mygridLayout;

    int answer;
    int answerPosition;
    int numberOfCorrectAnswers = 0;
    int numberOfQuestionsGenerated = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qtn1 = (Button) findViewById(R.id.button);
        qtn2 = (Button) findViewById(R.id.button2);
        qtn3 = (Button) findViewById(R.id.button3);
        qtn4 = (Button) findViewById(R.id.button4);
        startButton = (Button) findViewById(R.id.button5);

        timerTextView = (TextView) findViewById(R.id.timerTextView);
        problemTextView = (TextView) findViewById(R.id.problemTextView);
        pointTextView = (TextView) findViewById(R.id.pointTextView);
        answerTextView = (TextView) findViewById(R.id.answerTextView);
        mygridLayout = findViewById(R.id.gridLayout);
        mygridLayout = findViewById(R.id.gridLayout);
        for (int i = 0; i < mygridLayout.getChildCount(); i++) {
            mygridLayout.getChildAt(i).setEnabled(false);
        }
        pointTextView.setText("0/0");

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mygridLayout.setEnabled(true);
                answerTextView.setText("");
                generateRandomNumber();
                for (int i = 0; i < mygridLayout.getChildCount(); i++) {
                    mygridLayout.getChildAt(i).setEnabled(true);
                }
                startTimer();
            }
        });


    }

    public void checkAnswer(View view) {

        if (view.getTag().toString().equals(Integer.toString(answerPosition))) {
            numberOfCorrectAnswers++;
            answerTextView.setText("Correct");
            answerTextView.setBackgroundColor(Color.GREEN);
        } else {
            answerTextView.setText("Wrong");
            answerTextView.setBackgroundColor(Color.RED);
        }
        pointTextView.setText(Integer.toString(numberOfCorrectAnswers) + "/" + Integer.toString(numberOfQuestionsGenerated));
        generateRandomNumber();
    }

    private void generateRandomNumber() {
        numberOfQuestionsGenerated++;
        Random random = new Random();
        int i = random.nextInt(50);
        int j = random.nextInt(50);
        int k = random.nextInt(50);

        int position = random.nextInt(4);
        if (position == 0) {
            position = 1;
        }
        answerPosition = position;
        answer= i + j;
        String question = Integer.toString(i) + " + " + Integer.toString(j) + "= ?";
        String q1 ="", q2 ="", q3 = "", q4 = "";
        if (position == 1) {
            q1 = Integer.toString(answer);
            q2 = Integer.toString(i);
            q3 = Integer.toString(j);
            q4 = Integer.toString(k);
        } else if (position == 2) {
            q1 = Integer.toString(i);
            q2 = Integer.toString(answer);
            q3 = Integer.toString(j);
            q4 = Integer.toString(k);
        } else if (position == 3) {
            q1 = Integer.toString(j);
            q2 = Integer.toString(i);
            q3 = Integer.toString(answer);
            q4 = Integer.toString(k);
        } else if (position == 4) {
            q1 = Integer.toString(j);
            q2 = Integer.toString(i);
            q3 = Integer.toString(k);
            q4 = Integer.toString(answer);
        }
        qtn1.setText(q1);
        qtn2.setText(q2);
        qtn3.setText(q3);
        qtn4.setText(q4);
        problemTextView.setText(question);
    }

    private void startTimer() {
        new CountDownTimer(30000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                 timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
                 startButton.setEnabled(false);
            }

            @Override
            public void onFinish() {
                 timerTextView.setText("30s");
                 answerTextView.setText("Your Score : " + String.valueOf(numberOfCorrectAnswers));
                 problemTextView.setText("0");
                 pointTextView.setText("0");
                 startButton.setEnabled(true);
                 numberOfCorrectAnswers = 0;
                 numberOfQuestionsGenerated = 0;
                for (int i = 0; i < mygridLayout.getChildCount(); i++) {
                    mygridLayout.getChildAt(i).setEnabled(false);
                }
            }
        }.start();
    }


}
