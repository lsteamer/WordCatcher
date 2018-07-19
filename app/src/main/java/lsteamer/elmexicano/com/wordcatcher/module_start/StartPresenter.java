package lsteamer.elmexicano.com.wordcatcher.module_start;

import lsteamer.elmexicano.com.wordcatcher.model.GameState;
import lsteamer.elmexicano.com.wordcatcher.model.WordModel;
import lsteamer.elmexicano.com.wordcatcher.module_game.GameFragmentView;
import lsteamer.elmexicano.com.wordcatcher.module_game.GamePresenter;

public class StartPresenter {


    //Presenter and View Layers
    private GamePresenter mGamePresenter;
    private GameFragmentView mGameView;
    private WordModel model;
    private GameState gameState;

    private StartFragmentView mView;

    public StartPresenter(GamePresenter mGamePresenter, GameFragmentView mGameView, WordModel model, GameState gameState, StartFragmentView mView) {
        this.mGamePresenter = mGamePresenter;
        this.mGameView = mGameView;
        this.model = model;
        this.gameState = gameState;
        this.mView = mView;


        //mView.setPresenter(this);
    }
}
