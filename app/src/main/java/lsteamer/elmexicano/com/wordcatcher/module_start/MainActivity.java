package lsteamer.elmexicano.com.wordcatcher.module_start;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import lsteamer.elmexicano.com.wordcatcher.R;
import lsteamer.elmexicano.com.wordcatcher.model.GameState;
import lsteamer.elmexicano.com.wordcatcher.model.WordModel;
import lsteamer.elmexicano.com.wordcatcher.util.Utils;

public class MainActivity extends AppCompatActivity {


    //todo CLEAN the layouts dude

    //Presenter and View Layers
    private StartFragmentView mStartView;
    private StartPresenter mStartPresenter;

    private WordModel model;
    private GameState gameState;


    //todo get a Settings activity going.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

        //Reading the model
        model = Utils.readJsonFile(this, R.raw.deutsch_b1_verben);

        //GameState created
        gameState = new GameState(model.getArraySize(), false);

        mStartView = (StartFragmentView) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (mStartView == null) {
            mStartView = StartFragmentView.newInstance();
            Utils.addFragmentToActivity(getSupportFragmentManager(), mStartView, R.id.contentFrame);
        }


    }

}
