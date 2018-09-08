package lsteamer.elmexicano.com.wordcatcher.module_game;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import java.util.Random;

import lsteamer.elmexicano.com.wordcatcher.R;
import lsteamer.elmexicano.com.wordcatcher.model.GameState;
import lsteamer.elmexicano.com.wordcatcher.model.WordModel;
import lsteamer.elmexicano.com.wordcatcher.util.Utils;

public class GameActivity extends AppCompatActivity {


    private GamePresenter mPresenter;
    private GameFragmentView mView;

    private WordModel model;
    private GameState gameState;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        //Reading the model
        model = Utils.readJsonFile(this, R.raw.deutsch_b1_verben);

        //GameState created
        gameState = new GameState(model.getArraySize(), true);



        //ViewLayer
        mView = (GameFragmentView) getSupportFragmentManager().findFragmentById(R.id.gameContentFrame);
        if (mView == null) {
            mView = GameFragmentView.newInstance();
            Utils.addFragmentToActivity(getSupportFragmentManager(), mView, R.id.gameContentFrame);
        }

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //Without timer
        mPresenter = new GamePresenter(mView, gameState, model, new Random());

    }
}
