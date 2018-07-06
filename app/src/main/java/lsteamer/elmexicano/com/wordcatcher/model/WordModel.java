package lsteamer.elmexicano.com.wordcatcher.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WordModel  {

    //Model that gets the information from the Json and holds it

    @SerializedName("list")
    private ArrayList<WordPair> list;

    public String getFallingElement(int position){
        return list.get(position).textFalling;
    }

    public String getCompareElement(int position){
        return list.get(position).textCompare;
    }

    public ArrayList<WordPair> getList() {
        return list;
    }

    public int getArraySize(){
        return list.size();
    }


    static private class WordPair {
        @SerializedName("falling_word")
        private String textFalling;
        @SerializedName("compare_word")
        private String textCompare;
    }
}