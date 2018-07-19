package lsteamer.elmexicano.com.wordcatcher.start;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Random;

import butterknife.ButterKnife;
import butterknife.OnClick;
import lsteamer.elmexicano.com.wordcatcher.R;
import lsteamer.elmexicano.com.wordcatcher.main.MainFragmentView;
import lsteamer.elmexicano.com.wordcatcher.main.MainPresenter;
import lsteamer.elmexicano.com.wordcatcher.model.GameState;
import lsteamer.elmexicano.com.wordcatcher.model.WordModel;
import lsteamer.elmexicano.com.wordcatcher.util.Utils;

public class StartFragmentView extends Fragment {



    //Presenter and View Layers
    private MainPresenter mPresenter;
    private MainFragmentView mView;
    private WordModel model;
    private GameState gameState;

    public StartFragmentView(){
    }

    public static StartFragmentView newInstance(){return new StartFragmentView();}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_fragment_view, container, false);


        ButterKnife.bind(this,view);

        return view;
    }


    @OnClick(R.id.startGame)
    void startGame(){

        Log.d("Am I ", "yes you");

        //ViewLayer
        mView = (MainFragmentView) getActivity().getSupportFragmentManager().findFragmentById(R.id.startContentFrame);
        if (mView == null) {
            mView = MainFragmentView.newInstance();
            Utils.addFragmentToActivity(getActivity().getSupportFragmentManager(), mView, R.id.startContentFrame);
        }

        //Reading the model
        model = Utils.readJsonFile(getContext(), R.raw.deutsch_b1_verben);

        //GameState created
        gameState = new GameState(model.getArraySize(), false);

        gameState.setActive(true);

        //Without timer
        mPresenter = new MainPresenter(mView, gameState, model, new Random());

    }

    @OnClick(R.id.settingsButton)
    void openSettings(){

        Log.d("Am I ", "yes you");
        Toast.makeText(getContext(), "WATE",Toast.LENGTH_LONG).show();


    }
}
