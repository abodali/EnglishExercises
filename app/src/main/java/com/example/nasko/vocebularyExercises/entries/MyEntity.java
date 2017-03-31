package com.example.nasko.vocebularyExercises.entries;

/**
 * Created by nasko on 28.3.2017 г..
 */

public class MyEntity {

    private int id;
    private String type;
    private String engWord;
    private String bgWord;
    private int isLearned;
    private int trueIntroducedТimes;

    public MyEntity(int id, String type, String engWord, String bgWord) {
        this.id = id;
        this.type = type;
        this.engWord = engWord;
        this.bgWord = bgWord;
    }

    public MyEntity(int id, String type, String engWord, String bgWord, int isLearned, int trueIntroducedТimes) {
        this.id = id;
        this.type = type;
        this.engWord = engWord;
        this.bgWord = bgWord;
        this.isLearned = isLearned;
        this.trueIntroducedТimes = trueIntroducedТimes;
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

    public int getIsLearned() {
        return isLearned;
    }

    public void setIsLearned(int isLearned) {
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
        return "ID: " + getId() + ", " + getType() + ", " + getEngWord() + ", " + getBgWord() + ", " + getIsLearned() + ", " + getTrueIntroducedТimes();
    }
}
