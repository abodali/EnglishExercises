package com.example.nasko.englishexercises.entries;

import java.util.ArrayList;

/**
 * Created by nasko on 28.3.2017 г..
 */

public class MyEntity {

    private int id;
    private String type;
    private String engWord;
    private String bgWord;
    private String isLearned;
    private int trueIntroducedТimes;

    public MyEntity(int id, String type, String engWord, String bgWord) {
        this.id = id;
        this.type = type;
        this.engWord = engWord;
        this.bgWord = bgWord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEngWord() {
        return engWord;
    }

    public void setEngWord(String engWord) {
        this.engWord = engWord;
    }

    public String getBgWord() {
        return bgWord;
    }

    public void setBgWord(String bgWord) {
        this.bgWord = bgWord;
    }

    public String getIsLearned() {
        return isLearned;
    }

    public void setIsLearned(String isLearned) {
        this.isLearned = isLearned;
    }

    public int getTrueIntroducedТimes() {
        return trueIntroducedТimes;
    }

    public void setTrueIntroducedТimes(int rueIntroducedТimes) {
        this.trueIntroducedТimes = rueIntroducedТimes;
    }

    @Override
    public String toString(){
        return "ID: " + getId() + ", " + getType() + ", " + getEngWord() +", " + getBgWord();
    }
}
