package com.example.jocke.rostaren30;

import android.content.Context;
import android.content.ContextWrapper;

/**
 * Created by Jocke on 2018-02-19.
 */

public class rndWords extends ContextWrapper {


    int oldValueone;
    int oldValuetwo;
    int oldValuethree;
    int oldValuefour;
    int oldValuefive;
    int oldValuesix;
    int random;
    String randWord;
    String randWordTwo;
    String randWordThree;
    String randWordFour;
    String randWordFive;
    String randWordSix;

    public rndWords(Context base) {
        super(base);
    }


    public String randomWordForBtnOne() {
        String[] arrayForBtnOne = getResources().getStringArray(R.array.randWordsArrayOne);
        random = (int) (Math.random() * arrayForBtnOne.length);

        oldValueone = random;
        if (random == oldValueone) {
            random = (int) (Math.random() * arrayForBtnOne.length);
        }

        randWord = arrayForBtnOne[random];

        return "";
    }

    public String randomWordForBtnTwo() {
        String[] arrayForBtnTwo = getResources().getStringArray(R.array.randWordsArrayTwo);
        random = (int) (Math.random() * arrayForBtnTwo.length);
        oldValuetwo = random;
        if (random == oldValuetwo) {
            random = (int) (Math.random() * arrayForBtnTwo.length);
        }

        randWordTwo = arrayForBtnTwo[random];

        return "";

    }

    public String randomWordForBtnThree() {
        String[] arrayForBtnThree = getResources().getStringArray(R.array.randWordsArrayThree);
        random = (int) (Math.random() * arrayForBtnThree.length);
        oldValuethree = random;
        if (random == oldValuethree) {
            random = (int) (Math.random() * arrayForBtnThree.length);
        }


        randWordThree = arrayForBtnThree[random];

        return "";

    }

    public String randomWordForBtnFour() {
        String[] arrayForBtnFour = getResources().getStringArray(R.array.randWordsArrayFour);
        random = (int) (Math.random() * arrayForBtnFour.length);
        oldValuefour = random;
        if (random == oldValuefour) {
            random = (int) (Math.random() * arrayForBtnFour.length);
        }


        randWordFour = arrayForBtnFour[random];

        return "";

    }

    public String randomWordForBtnFive() {
        String[] arrayForBtnFive = getResources().getStringArray(R.array.randWordsArrayFive);
        random = (int) (Math.random() * arrayForBtnFive.length);
        oldValuefive = random;
        if (random == oldValuefive) {
            random = (int) (Math.random() * arrayForBtnFive.length);
        }


        randWordFive = arrayForBtnFive[random];

        return "";

    }

    public String randomWordForBtnSix() {
        String[] arrayForBtnSix = getResources().getStringArray(R.array.randWordsArraySix);
        random = (int) (Math.random() * arrayForBtnSix.length);
        oldValuesix = random;
        if (random == oldValuesix) {
            random = (int) (Math.random() * arrayForBtnSix.length);
        }

        randWordSix = arrayForBtnSix[random];

        return "";

    }


    public String getRandWordBtn1() {

        return this.randWord;
    }

    public String getRandWordTwo() {
        return this.randWordTwo;
    }

    public String getRandWordThree() {
        return this.randWordThree;
    }

    public String getRandWordFour() {
        return this.randWordFour;
    }

    public String getRandWordFive() {
        return this.randWordFive;
    }

    public String getRandWordSix() {
        return this.randWordSix;
    }

}

