package lsteamer.elmexicano.com.wordcatcher.model;

import android.graphics.Color;


public class GameState {

    //GameState and Logic

    private int score, rounds, sizeOfArray;
    private boolean matching, active, success;

    private String wordGuess, wordCompare;

    public GameState(int size, boolean state) {
        this.sizeOfArray = size;
        this.score = 0;
        this.rounds = 0;
        this.matching = true;
        this.success = true;
        this.active = state;
        this.wordGuess = "";
        this.wordCompare = "";
    }

    //Is the game Active?

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    //The result of the previous round

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    // how many rounds of the game have we played?

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

    public String getWordGuess() {
        return wordGuess;
    }

    public void setWordGuess(String wordEnglish) {
        this.wordGuess = wordEnglish;
    }

    //word in Spanish

    public String getWordCompare() {
        return wordCompare;
    }

    public void setWordCompare(String wordSpanish) {
        this.wordCompare = wordSpanish;
    }
}
