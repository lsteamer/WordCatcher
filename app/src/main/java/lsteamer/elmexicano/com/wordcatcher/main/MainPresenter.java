package lsteamer.elmexicano.com.wordcatcher.main;

import android.os.CountDownTimer;

import lsteamer.elmexicano.com.wordcatcher.main.model.GameState;
import lsteamer.elmexicano.com.wordcatcher.main.model.WordModel;
import lsteamer.elmexicano.com.wordcatcher.util.Utils;

public class MainPresenter implements MainContract.PresenterLayer {


    private MainContract.ViewLayer vLayer;
    private WordModel model;
    private GameState gState;
    private int numberRange;
    private  UICountDownTimer timer;


    MainPresenter(MainContract.ViewLayer view, GameState gameState, WordModel wordModel, UICountDownTimer timer) {
        this.vLayer = view;
        this.gState = gameState;
        this.model = wordModel;
        this.timer = timer;

        //Size of Array for testing purposes
        if(gameState.getSizeOfArray()>0)
            this.numberRange = gameState.getSizeOfArray();
        else
            this.numberRange = 99;

        vLayer.setPresenter(this);

        this.timer.attach(this.vLayer);
        this.timer.start();

    }


    MainPresenter(MainContract.ViewLayer view, GameState gameState, WordModel wordModel) {
        this.vLayer = view;
        this.gState = gameState;
        this.model = wordModel;
        this.timer = null;

        //Size of Array for testing purposes
        if(gameState.getSizeOfArray()>0)
            this.numberRange = gameState.getSizeOfArray();
        else
            this.numberRange = 99;

        vLayer.setPresenter(this);


    }


    public void fetchNewWords() {

        //Checking if we're going to get matching words
        boolean matching = Utils.coinFlip();

        //And Set that in the GameState-Logic
        gState.setMatching(matching);

        String spanishWord, englishWord;

        int number = Utils.getRandomNumber(numberRange);
        spanishWord = model.getSpanishElement(number);

        //Getting our words
        if (matching) {
            //Getting two matching words
            englishWord = model.getEnglishElement(number);
        } else {
            //Getting two random Words
            englishWord = model.getEnglishElement(Utils.getRandomNumber(numberRange, number));

        }

        // setting the words in the gamestate
        gState.setWordEnglish(englishWord);
        gState.setWordSpanish(spanishWord);

        vLayer.updateScreenElements(gState.getScoreRoundsString(), gState.getSuccess(), gState.getSuccessColor(), gState.getWordEnglish(), gState.getWordSpanish());
    }

    public void restartGame(){

        //set the game state as active again
        activateState();

        //restart the timer
        timer.start();

        //switch from End screen to game screen
        vLayer.switchScreens();

        //Clear the values
        clearValues();

        //fetch new words
        fetchNewWords();
    }

    public void clearValues(){
        //clear the values to be able to restart the game
        gState.setScore(0);
        gState.setRounds(0);
    }

    public void checkResult(boolean userGuess) {
        //Check the result of the user
        if (userGuess == gState.isMatching())
            correctResult();
        else
            incorrectResult();
    }

    public void correctResult() {
        // If the result was correct
        gState.setSuccess(true);
        gState.updateScore();
        gState.updateRounds();
        fetchNewWords();
    }

    public void incorrectResult() {
        // incorrect result or word slid all the way down
        gState.setSuccess(false);
        gState.updateRounds();
        fetchNewWords();
    }

    public String getScoreRoundsString() {
        // get the score for the end screen
        return gState.getScoreRoundsString();
    }

    public boolean isGameActive() {
        //is game active
        return gState.isActive();
    }

    public void deactivateState() {
        //game's over
        gState.setActive(false);
    }

    public void activateState(){
        //game's restarted
        gState.setActive(true);
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

                view.gameOver();
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
