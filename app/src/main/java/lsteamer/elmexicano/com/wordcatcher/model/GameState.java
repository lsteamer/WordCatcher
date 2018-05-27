package lsteamer.elmexicano.com.wordcatcher.model;

import java.util.ArrayList;

public class GameState {

    private int timer, score;
    private boolean matching;

    private ArrayList<WordModel.WordPair> list;

    public GameState(int timer, int score, ArrayList<WordModel.WordPair> list) {
        this.timer = timer;
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

    public String getTimerString() {
        return String.valueOf(timer);
    }

    public void setTimer(int timer) {
        this.timer = timer;
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

    public String getEnglishElement(int position){
        return list.get(position).textEng;
    }

    public String getSpanishElement(int position){
        return list.get(position).textSpa;
    }
}
