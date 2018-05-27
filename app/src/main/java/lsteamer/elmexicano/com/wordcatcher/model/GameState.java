package lsteamer.elmexicano.com.wordcatcher.model;

import java.util.ArrayList;

public class GameState {

    private int score;
    private boolean matching;

    private String wordEnglish, wordSpanish;

    private ArrayList<WordModel.WordPair> list;

    public GameState(int score, ArrayList<WordModel.WordPair> list) {
        this.score = score;
        this.list = list;
        this.matching = true;
    }

    public boolean isMatching() {
        return matching;
    }

    public void setMatching(boolean matching) {
        this.matching = matching;
    }


    public String getScoreString() {
        return String.valueOf(score);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ArrayList<WordModel.WordPair> getList() {
        return list;
    }

    public void setList(ArrayList<WordModel.WordPair> list) {
        this.list = list;
    }

    public String getWordEnglish() {
        return wordEnglish;
    }

    public void setWordEnglish(String wordEnglish) {
        this.wordEnglish = wordEnglish;
    }

    public String getWordSpanish() {
        return wordSpanish;
    }

    public void setWordSpanish(String wordSpanish) {
        this.wordSpanish = wordSpanish;
    }
}
