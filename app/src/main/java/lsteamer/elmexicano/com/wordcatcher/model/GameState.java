package lsteamer.elmexicano.com.wordcatcher.model;

public class GameState {

    //GameState and Logic

    private int score, rounds, sizeOfArray;
    private boolean matching, active, success;

    private String wordFalling, wordCompare;

    public GameState(int size, boolean state) {
        this.sizeOfArray = size;
        this.score = 0;
        this.rounds = 0;
        this.matching = true;
        this.success = true;
        this.active = state;
        this.wordFalling = "";
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

    //word in English

    public String getWordFalling() {
        return wordFalling;
    }

    public void setWordFalling(String wordEnglish) {
        this.wordFalling = wordEnglish;
    }

    //word in Spanish

    public String getWordCompare() {
        return wordCompare;
    }

    public void setWordCompare(String wordSpanish) {
        this.wordCompare = wordSpanish;
    }
}
