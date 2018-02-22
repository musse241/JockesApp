package com.example.jocke.rostaren30;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    boolean gameEnding;
    rndWords wordForButtons = new rndWords(this);
    public String setTimer;
    public String setEndTimer;
    private ClipboardManager cbm;
    private ClipData cb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGame();
    }

    private void startGame() {
        final Button btnStart = (Button) findViewById(R.id.buttonStart);
        final CheckBox checkBoxSv = (CheckBox) findViewById(R.id.checkBoxSv);
        final CheckBox checkBoxEn = (CheckBox) findViewById(R.id.checkBoxEn);

        checkBoxSv.setEnabled(true);
        hideEverything();

            checkBoxSv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        checkBoxEn.setChecked(false);
                        setTimer = "Tid kvar:";
                        setEndTimer = "Tiden är slut!";
                        String languageToLoad = "sv";
                        Locale locale = new Locale(languageToLoad);
                        Locale.setDefault(locale);
                        Configuration configuration = new Configuration();
                        configuration.setLocale(locale);
                        Toast.makeText(getApplicationContext(), "Swedish is set", Toast.LENGTH_SHORT).show();
                        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
                        initializeButtons();

                    }
                }
            });
            checkBoxEn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        checkBoxSv.setChecked(false);
                        setTimer = "Time left:";
                        setEndTimer = "Times up!";
                        String languageToLoad = "en-US";
                        Locale locale = new Locale(languageToLoad);
                        Locale.setDefault(locale);
                        Configuration configuration = new Configuration();
                        configuration.setLocale(locale);
                        Toast.makeText(getApplicationContext(), "english is set", Toast.LENGTH_SHORT).show();
                        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
                        initializeButtons();

                    }
                }
            });


            btnStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnStart.setVisibility(View.GONE);
                    btnStart.setEnabled(false);
                    CheckBox checkBoxSv = (CheckBox) findViewById(R.id.checkBoxSv);
                    checkBoxSv.setVisibility(View.GONE);
                    CheckBox checkBoxEn = (CheckBox) findViewById(R.id.checkBoxEn);
                    checkBoxEn.setVisibility(View.GONE);
                    checkBoxSv.setEnabled(false);
                    checkBoxEn.setEnabled(false);
                    startGameTimer();
                    showEverything();

                }
            });

    }

    private void startGameTimer() {
        new CountDownTimer(10000, 1000) {
            final TextView timeLeft = (TextView) findViewById(R.id.timerText);

            public void onTick(long millisUntilFinished) {
                timeLeft.setText("" + setTimer + millisUntilFinished / 1000+"sek");
                gameEnding = true;

                View btnRestartHide = findViewById(R.id.buttonRestart);
                btnRestartHide.setVisibility(View.GONE);
                View btnCopyHide = findViewById(R.id.buttonCopy);
                btnCopyHide.setVisibility(View.GONE);

                addRandWordWithBtnOne();
                addRandWordWithBtnTwo();
                addRandWordWithBtnThree();
                addRandWordWithBtnFour();
                addRandWordWithBtnFive();
                addRandWordWithBtnSix();
            }

            public void onFinish() {
                timeLeft.setText(setEndTimer);
                gameEnding = false;
                View btnRestartShow = findViewById(R.id.buttonRestart);

                View btnCopyShow = findViewById(R.id.buttonCopy);
                final Button btnRestart = (Button) findViewById(R.id.buttonRestart);
                final Button btnCopy = (Button) findViewById(R.id.buttonCopy);
                String stringForBtnRestart = getResources().getString(R.string.buttonRestart);
                String stringForBtnCopy = getResources().getString(R.string.buttonCopy);

                btnRestart.setText(stringForBtnRestart);
                btnCopy.setText(stringForBtnCopy);

                hideEverything();

                btnRestartShow.setVisibility(View.VISIBLE);
                btnCopyShow.setVisibility(View.VISIBLE);

                restartGame();
                copyFromTextView();

            }
        }.start();

    }

    private void restartGame() {

        final Button btnRestart = (Button) findViewById(R.id.buttonRestart);
        final TextView textField = (TextView) findViewById(R.id.textOne);


        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameEnding = true;

                showEverything();
                startGameTimer();

                initializeButtons();

                textField.setText("");

            }
        });
    }

    private void copyFromTextView() {

        final Button btnCopy = (Button) findViewById(R.id.buttonCopy);
        final TextView textField = (TextView) findViewById(R.id.textOne);

        cbm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        btnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String textToBeCopied = textField.getText().toString();
                cb = ClipData.newPlainText("", textToBeCopied);
                cbm.setPrimaryClip(cb);

            }
        });
    }

    private void addRandWordWithBtnOne() {

        final TextView textField = (TextView) findViewById(R.id.textOne);
        final Button wordAddButtonOne = (Button) findViewById(R.id.buttonOne);

        cbm = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        wordAddButtonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textField.append(" " + (wordAddButtonOne.getText()));

                wordForButtons.randomWordForBtnOne();

                wordAddButtonOne.setText(wordForButtons.getRandWordBtn1());

            }
        });


    }

    private void addRandWordWithBtnTwo() {

        final TextView textField = (TextView) findViewById(R.id.textOne);
        final Button wordAddButtonTwo = (Button) findViewById(R.id.buttonTwo);

        wordAddButtonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textField.append(" " + (wordAddButtonTwo.getText()));

                wordForButtons.randomWordForBtnTwo();

                wordAddButtonTwo.setText(wordForButtons.getRandWordTwo());

            }
        });
    }

    private void addRandWordWithBtnThree() {

        final TextView textField = (TextView) findViewById(R.id.textOne);
        final Button wordAddButtonThree = (Button) findViewById(R.id.buttonThree);

        wordAddButtonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textField.append(" " + (wordAddButtonThree.getText()));

                wordForButtons.randomWordForBtnThree();

                wordAddButtonThree.setText(wordForButtons.getRandWordThree());
            }
        });
    }

    private void addRandWordWithBtnFour() {

        final TextView textField = (TextView) findViewById(R.id.textOne);
        final Button wordAddButtonFour = (Button) findViewById(R.id.buttonFour);

        wordAddButtonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textField.append(" " + (wordAddButtonFour.getText()));

                wordForButtons.randomWordForBtnFour();

                wordAddButtonFour.setText(wordForButtons.getRandWordFour());

            }
        });
    }

    private void addRandWordWithBtnFive() {

        final TextView textField = (TextView) findViewById(R.id.textOne);
        final Button wordAddButtonFive = (Button) findViewById(R.id.buttonFive);

        wordAddButtonFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textField.append(" " + (wordAddButtonFive.getText()));

                wordForButtons.randomWordForBtnFive();

                wordAddButtonFive.setText(wordForButtons.getRandWordFive());

            }
        });
    }

    private void addRandWordWithBtnSix() {

        final TextView textField = (TextView) findViewById(R.id.textOne);
        final Button wordAddButtonSix = (Button) findViewById(R.id.buttonSix);

        wordAddButtonSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textField.append(" " + (wordAddButtonSix.getText()));

                wordForButtons.randomWordForBtnSix();

                wordAddButtonSix.setText(wordForButtons.getRandWordSix());

            }
        });
    }

    private void initializeButtons() {
        wordForButtons.randomWordForBtnOne();
        Button wordAddButtonOne = (Button) findViewById(R.id.buttonOne);
        wordAddButtonOne.setText(wordForButtons.getRandWordBtn1());

        wordForButtons.randomWordForBtnTwo();
        Button wordAddButtonTwo = (Button) findViewById(R.id.buttonTwo);
        wordAddButtonTwo.setText(wordForButtons.getRandWordTwo());

        wordForButtons.randomWordForBtnThree();
        Button wordAddButtonThree = (Button) findViewById(R.id.buttonThree);
        wordAddButtonThree.setText(wordForButtons.getRandWordThree());

        wordForButtons.randomWordForBtnFour();
        Button wordAddButtonFour = (Button) findViewById(R.id.buttonFour);
        wordAddButtonFour.setText(wordForButtons.getRandWordFour());

        wordForButtons.randomWordForBtnFive();
        Button wordAddButtonFive = (Button) findViewById(R.id.buttonFive);
        wordAddButtonFive.setText(wordForButtons.getRandWordFive());

        wordForButtons.randomWordForBtnSix();
        Button wordAddButtonSix = (Button) findViewById(R.id.buttonSix);
        wordAddButtonSix.setText(wordForButtons.getRandWordSix());

    }

    private void hideEverything() {
        Button Btn1 = (Button) findViewById(R.id.buttonOne);
        Button Btn2 = (Button) findViewById(R.id.buttonTwo);
        Button Btn3 = (Button) findViewById(R.id.buttonThree);
        Button Btn4 = (Button) findViewById(R.id.buttonFour);
        Button Btn5 = (Button) findViewById(R.id.buttonFive);
        Button Btn6 = (Button) findViewById(R.id.buttonSix);

        Btn1.setEnabled(false);
        Btn2.setEnabled(false);
        Btn3.setEnabled(false);
        Btn4.setEnabled(false);
        Btn5.setEnabled(false);
        Btn6.setEnabled(false);

        Btn1.setVisibility(View.GONE);
        Btn2.setVisibility(View.GONE);
        Btn3.setVisibility(View.GONE);
        Btn4.setVisibility(View.GONE);
        Btn5.setVisibility(View.GONE);
        Btn6.setVisibility(View.GONE);

        View btnRestartHide = findViewById(R.id.buttonRestart);
        btnRestartHide.setVisibility(View.GONE);
        View btnCopyHide = findViewById(R.id.buttonCopy);
        btnCopyHide.setVisibility(View.GONE);


    }

    private void showEverything() {

        Button Btn1 = (Button) findViewById(R.id.buttonOne);
        Button Btn2 = (Button) findViewById(R.id.buttonTwo);
        Button Btn3 = (Button) findViewById(R.id.buttonThree);
        Button Btn4 = (Button) findViewById(R.id.buttonFour);
        Button Btn5 = (Button) findViewById(R.id.buttonFive);
        Button Btn6 = (Button) findViewById(R.id.buttonSix);

        Btn1.setEnabled(true);
        Btn2.setEnabled(true);
        Btn3.setEnabled(true);
        Btn4.setEnabled(true);
        Btn5.setEnabled(true);
        Btn6.setEnabled(true);

        Btn1.setVisibility(View.VISIBLE);
        Btn2.setVisibility(View.VISIBLE);
        Btn3.setVisibility(View.VISIBLE);
        Btn4.setVisibility(View.VISIBLE);
        Btn5.setVisibility(View.VISIBLE);
        Btn6.setVisibility(View.VISIBLE);
    }


}
