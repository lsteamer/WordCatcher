package lsteamer.elmexicano.com.wordcatcher.model;

import java.util.ArrayList;

public class GameState {

    static final private String SUCCESS = "Correct!";
    static final private String FAILURE = "Wrong";
    static final private String GO = "Let's go!";

    private int score, rounds, sizeOfArray;
    private boolean matching, active, success;

    private String wordEnglish, wordSpanish;

    public GameState(int size, boolean state) {
        this.sizeOfArray = size;
        this.score = 0;
        this.rounds = 1;
        this.matching = true;
        this.success = true;
        this.active = state;
        this.wordEnglish = "";
        this.wordSpanish = "";
    }

    //Is the game Active?

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    //was the last guess a success?

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getSuccess() {
        if(rounds==1)
            return GO;
        else if (success)
            return SUCCESS;
        else
            return FAILURE;
    }

    //is the current pair of words a match?


    public boolean isMatching() {
        return matching;
    }

    public void setMatching(boolean matching) {
        this.matching = matching;
    }


    //What's the size of the Array?

    public int getSizeOfArray() {
        return sizeOfArray;
    }

    public void setSizeOfArray(int sizeOfArray) {
        this.sizeOfArray = sizeOfArray;
    }

    // how many wounds of the game have we played?

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public void updateRounds() {
        this.rounds++;
    }

    //what's the current score?

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void updateScore() {
        this.score++;
    }

    //get a String showing the Rounds and the score

    public String getScoreRoundsString() {
        return String.valueOf(score) + " / " + String.valueOf(rounds);
    }

    //word in English

    public String getWordEnglish() {
        return wordEnglish;
    }

    public void setWordEnglish(String wordEnglish) {
        this.wordEnglish = wordEnglish;
    }

    //word in Spanish

    public String getWordSpanish() {
        return wordSpanish;
    }

    public void setWordSpanish(String wordSpanish) {
        this.wordSpanish = wordSpanish;
    }
}
