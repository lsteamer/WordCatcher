package lsteamer.elmexicano.com.wordcatcher.main;

import android.os.CountDownTimer;

import lsteamer.elmexicano.com.wordcatcher.model.GameState;
import lsteamer.elmexicano.com.wordcatcher.model.WordModel;
import lsteamer.elmexicano.com.wordcatcher.util.Utils;

public class MainPresenter implements MainContract.PresenterLayer {


    private MainContract.ViewLayer vLayer;
    private WordModel model;
    private GameState gState;
    private int numberRange;

    MainPresenter(MainContract.ViewLayer view, GameState gameState, WordModel wordModel, UICountDownTimer timer) {
        this.vLayer = view;
        this.gState = gameState;
        this.model = wordModel;
        this.numberRange = gameState.getSizeOfArray();

        vLayer.setPresenter(this);

        timer.attach(this.vLayer);
        timer.start();

    }


    public void fetchNewWords() {
        //Checking if we're going to get matching words
        boolean matching = Utils.coinFlip();

        //And Set that in the GameState-Logic
        gState.setMatching(matching);

        String spanishWord, englishWord;

        //Getting our words
        if (matching) {
            //Getting two matching words
            int number = Utils.getRandomNumber(numberRange);

            spanishWord = model.getSpanishElement(number);
            englishWord = model.getEnglishElement(number);
        } else {
            //Getting two random Words
            spanishWord = model.getSpanishElement(Utils.getRandomNumber(numberRange));
            englishWord = model.getEnglishElement(Utils.getRandomNumber(numberRange));

        }

        gState.setWordEnglish(englishWord);
        gState.setWordSpanish(spanishWord);

        vLayer.updateScreenElements(gState.getScoreRoundsString(), gState.getSuccess(), englishWord, spanishWord);
    }


    public void checkResult(boolean userGuess){


    }




    public interface UICountDownTimer {
        void attach(MainContract.ViewLayer view);

        void start();

        void cancel();
    }

}


// CountDownTimer class to keep Presenter testable, time provided.
class DefaultCountDownTimer implements MainPresenter.UICountDownTimer {

    private CountDownTimer timer;

    public void attach(final MainContract.ViewLayer view) {
        timer = new CountDownTimer(46000, 1000) {
            @Override
            public void onTick(long l) {
                //Update the Timer
                view.updateScreenTime(String.valueOf(l / 1000));

            }

            @Override
            public void onFinish() {
                //End the game, show an end screen?
            }
        };
    }

    @Override
    public void start() {
        timer.start();
    }

    @Override
    public void cancel() {
        timer.cancel();
    }

}
