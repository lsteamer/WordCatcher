package lsteamer.elmexicano.com.wordcatcher.model;

import java.util.List;

public class WordWrapper {

    private List<Words> words;

    public String getEnglishElement(int position){
        return words.get(position).getTextEng();
    }

    public String getSpanishElement(int position){
        return words.get(position).getTextEng();
    }

    public List<Words> getWordsList(){
        return words;
    }

    public void setWordList(List<Words> wordsList){
        this.words = wordsList;
    }

    public int getArraySize(){
        return words.size();
    }
}
