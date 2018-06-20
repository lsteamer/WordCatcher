package lsteamer.elmexicano.com.wordcatcher.main;

import android.os.CountDownTimer;

import java.util.Random;

import lsteamer.elmexicano.com.wordcatcher.model.GameState;
import lsteamer.elmexicano.com.wordcatcher.model.WordModel;
import lsteamer.elmexicano.com.wordcatcher.util.Utils;

public class MainPresenter implements MainContract.PresenterLayer {


    private MainContract.ViewLayer vLayer;
    private WordModel model;
    private GameState gState;
    private int numberRange;
    private  UICountDownTimer timer;
    private Random rand;

    //Constructor with timer
    MainPresenter(MainContract.ViewLayer view, GameState gameState, WordModel wordModel, UICountDownTimer timer, Random random) {
        this.vLayer = view;
        this.gState = gameState;
        this.model = wordModel;
        this.timer = timer;
        this.rand = random;

        //Size of Array for testing purposes
        if(gameState.getSizeOfArray()>0)
            this.numberRange = gameState.getSizeOfArray();
        else
            this.numberRange = 99;

        vLayer.setPresenter(this);

        this.timer.attach(this.vLayer);
        this.timer.start();

    }

    //Constructor without timer
    MainPresenter(MainContract.ViewLayer view, GameState gameState, WordModel wordModel, Random random) {
        this.vLayer = view;
        this.gState = gameState;
        this.model = wordModel;
        this.timer = null;
        this.rand = random;

        //Size of Array for testing purposes
        if(gameState.getSizeOfArray()>0)
            this.numberRange = gameState.getSizeOfArray();
        else
            this.numberRange = 99;

        vLayer.setPresenter(this);

    }


    public void fetchNewWords() {

        //Checking if we're going to get matching words (the same pair)
        boolean matching = Utils.coinFlip(rand);


        String compareWord, guessWord;
        int number = Utils.getRandomNumber(rand, numberRange);
        guessWord = model.getGuessElement(number);

        //Getting our words
        if (matching) {
            //Getting a comparing word that matches
            compareWord = model.getCompareElement(number);
        } else {
            //Getting a random comparing word
            compareWord = model.getCompareElement(Utils.getRandomNumber(rand, numberRange, number));

            //if it's not the same pair but it matches, it should be flagged as matching
            matching = compareWords(guessWord, model.getCompareElement(number));
        }


        //Set it as matching in the GameState-Logic
        gState.setMatching(matching);

        // setting the words in the gamestate
        gState.setWordGuess(guessWord);
        gState.setWordCompare(compareWord);

        vLayer.updateScreenElements(gState.getScoreRoundsString(), gState.getSuccess(), gState.getSuccessColor(), gState.getWordGuess(), gState.getWordCompare());
    }

    public boolean compareWords(String guess, String compare){

        return (guess.equals(compare));
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
        gState.setScore(0);
        gState.setRounds(0);
    }

    public void checkResult(boolean userGuess) {
        if (userGuess == gState.isMatching())
            correctResult();
        else
            incorrectResult();
    }

    public void correctResult() {
        gState.setSuccess(true);
        gState.updateScore();
        gState.updateRounds();
        fetchNewWords();
    }

    public void incorrectResult() {
        gState.setSuccess(false);
        gState.updateRounds();
        fetchNewWords();
    }

    public String getScoreRoundsString() {
        return gState.getScoreRoundsString();
    }

    public boolean isGameActive() {
        return gState.isActive();
    }

    public void deactivateState() {
        gState.setActive(false);
    }

    public void activateState(){
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
