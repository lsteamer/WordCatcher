package lsteamer.elmexicano.com.wordcatcher.module_game;

import android.os.CountDownTimer;

import java.util.Random;

import lsteamer.elmexicano.com.wordcatcher.model.GameState;
import lsteamer.elmexicano.com.wordcatcher.model.WordModel;
import lsteamer.elmexicano.com.wordcatcher.util.Utils;

public class GamePresenter implements GameContract.PresenterLayer {


    private GameContract.ViewLayer vLayer;
    private WordModel model;
    private GameState gState;
    private int numberRange;
    private UICountDownTimer timer;
    private Random rand;

    //Constructor with timer
    public GamePresenter(GameContract.ViewLayer view, GameState gameState, WordModel wordModel, Random random, UICountDownTimer timer) {
        this.vLayer = view;
        this.gState = gameState;
        this.model = wordModel;
        this.timer = timer;
        this.rand = random;

        //Size of Array for testing purposes
        if (gameState.getSizeOfArray() > 0)
            this.numberRange = gameState.getSizeOfArray();
        else
            this.numberRange = 99;

        vLayer.setPresenter(this);

        this.timer.attach(this.vLayer);
        this.timer.start();

    }

    //Constructor without timer
    public GamePresenter(GameContract.ViewLayer view, GameState gameState, WordModel wordModel, Random random) {
        this.vLayer = view;
        this.gState = gameState;
        this.model = wordModel;
        this.timer = null;
        this.rand = random;

        //Size of Array for testing purposes
        if (gameState.getSizeOfArray() > 0)
            this.numberRange = gameState.getSizeOfArray();
        else
            this.numberRange = 99;

        vLayer.setPresenter(this);

    }


    public void fetchNewWords() {

        //Checking if we're going to get matching words (the same pair)
        boolean matching = Utils.coinFlip(rand);


        String compareWord, fallingWord;
        int number = Utils.getRandomNumber(rand, numberRange);
        fallingWord = model.getFallingElement(number);



        //Getting our words
        if (matching) {
            //Getting a comparing word that matches
            compareWord = model.getCompareElement(number);
        } else {
            //Getting a random comparing word
            compareWord = model.getCompareElement(Utils.getRandomNumber(rand, numberRange, number));

            //if it's not the same pair but it matches, it should be flagged as matching
            matching = compareWords(compareWord, model.getCompareElement(number));
        }


        //Set it as matching in the GameState-Logic
        gState.setMatching(matching);

        // setting the words in the gamestate
        gState.setWordFalling(fallingWord);
        gState.setWordCompare(compareWord);

        vLayer.updateScreenElements(gState.getScore(), gState.getRounds(), gState.isSuccess(), gState.getWordFalling(), gState.getWordCompare());
    }

    private boolean compareWords(String guess, String compare) {
        return(guess.equals(compare));

    }

    public void restartGame() {

        //set the game state as active again
        activateGameState();

        //restart the timer
        timer.start();

        //switch from End screen to game screen
        vLayer.switchScreens();

        //Clear the values
        clearValues();

        //fetch new words
        fetchNewWords();
    }

    private void clearValues() {
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


    public boolean isGameActive() {
        return gState.isActive();
    }

    public void deactivateGameState() {
        gState.setActive(false);
    }

    private void activateGameState() {
        gState.setActive(true);
    }

    public boolean isGameFirstRound(){ return gState.getRounds()==0;}


    public interface UICountDownTimer {
        void attach(GameContract.ViewLayer view);

        void start();

        void cancel();
    }

}


// CountDownTimer class to keep Presenter testable, time provided.
class DefaultCountDownTimer implements GamePresenter.UICountDownTimer {

    private CountDownTimer timer;

    public void attach(final GameContract.ViewLayer view) {
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
