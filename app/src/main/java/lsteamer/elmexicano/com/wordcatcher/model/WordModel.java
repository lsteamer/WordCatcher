package lsteamer.elmexicano.com.wordcatcher.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WordModel  {

    //Model that gets the information from the Json and holds it

    @SerializedName("list")
    public ArrayList<WordPair> list;

    public String getEnglishElement(int position){
        return list.get(position).textEng;
    }

    public String getSpanishElement(int position){
        return list.get(position).textSpa;
    }

    public ArrayList<WordPair> getList() {
        return list;
    }

    public int getArraySize(){
        return list.size();
    }


    static public class WordPair {
        @SerializedName("text_eng")
        public String textEng;
        @SerializedName("text_spa")
        public String textSpa;
    }
}