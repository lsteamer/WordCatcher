package lsteamer.elmexicano.com.wordcatcher.main;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;

import lsteamer.elmexicano.com.wordcatcher.R;
import lsteamer.elmexicano.com.wordcatcher.main.MainFragmentView;
import lsteamer.elmexicano.com.wordcatcher.main.MainPresenter;
import lsteamer.elmexicano.com.wordcatcher.model.GameState;
import lsteamer.elmexicano.com.wordcatcher.model.WordModel;
import lsteamer.elmexicano.com.wordcatcher.util.Utils;

public class MainActivity extends AppCompatActivity {

    public static final String ACTIVITY_TAG = "MainActivity";

    private CoordinatorLayout coordinatorLayout;
    private ConstraintLayout preLayout;

    //Presenter and View Layers
    MainPresenter mPresenter;
    MainFragmentView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Layout that will show the game
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

        //Layout that showed the intro Screen
        preLayout = (ConstraintLayout) findViewById(R.id.preLayout);

    }

    protected void startGame(View view) {

        // Reading the json
        String myJson = Utils.inputStreamToString(this.getResources().openRawResource(R.raw.words_v3));
        WordModel model = new Gson().fromJson(myJson, WordModel.class);

        //Hide the first screen and show the game screen
        coordinatorLayout.setVisibility(View.VISIBLE);
        preLayout.setVisibility(View.GONE);

        //ViewLayer
        mView = (MainFragmentView) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (mView == null) {
            mView = MainFragmentView.newInstance();
            Utils.addFragmentToActivity(getSupportFragmentManager(), mView, R.id.contentFrame);
        }

        //GameState created
        GameState gameState = new GameState(model.getArraySize(), true);

        mPresenter = new MainPresenter(mView, gameState, model, new DefaultCountDownTimer());


    }
}
