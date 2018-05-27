package lsteamer.elmexicano.com.wordcatcher.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WordModel  {

    public ArrayList<WordPair> getList() {
        return list;
    }

    @SerializedName("list")
    public ArrayList<WordPair> list;

    static public class WordPair {
        @SerializedName("text_eng")
        public String textEng;
        @SerializedName("text_spa")
        public String textSpa;
    }
}