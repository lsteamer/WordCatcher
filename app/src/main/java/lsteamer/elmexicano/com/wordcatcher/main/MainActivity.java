package lsteamer.elmexicano.com.wordcatcher.main;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lsteamer.elmexicano.com.wordcatcher.R;
import lsteamer.elmexicano.com.wordcatcher.model.GameState;
import lsteamer.elmexicano.com.wordcatcher.model.WordModel;
import lsteamer.elmexicano.com.wordcatcher.util.Utils;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.preLayout)
    ConstraintLayout preLayout;

    //Presenter and View Layers
    private MainPresenter mPresenter;
    private MainFragmentView mView;

    private WordModel model;
    private GameState gameState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Binding the views
        ButterKnife.bind(this);

        //Reading the model
        model = Utils.readJsonFile(this, R.raw.deutsch_b1_verben);

        //GameState created
        gameState = new GameState(model.getArraySize(), false);



    }

    @OnClick(R.id.startGame)
    void startGame() {

        gameState.setActive(true);

        //Hide the first screen and show the game screen
        coordinatorLayout.setVisibility(View.VISIBLE);
        preLayout.setVisibility(View.GONE);

        //ViewLayer
        mView = (MainFragmentView) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (mView == null) {
            mView = MainFragmentView.newInstance();
            Utils.addFragmentToActivity(getSupportFragmentManager(), mView, R.id.contentFrame);
        }

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        
        //Without timer
        mPresenter = new MainPresenter(mView, gameState, model, new Random());


    }
}
