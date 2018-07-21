package lsteamer.elmexicano.com.wordcatcher.module_start;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;

import butterknife.ButterKnife;
import butterknife.OnClick;
import lsteamer.elmexicano.com.wordcatcher.R;
import lsteamer.elmexicano.com.wordcatcher.module_game.GameFragmentView;
import lsteamer.elmexicano.com.wordcatcher.module_game.GamePresenter;
import lsteamer.elmexicano.com.wordcatcher.model.GameState;
import lsteamer.elmexicano.com.wordcatcher.model.WordModel;
import lsteamer.elmexicano.com.wordcatcher.util.Utils;

public class StartFragmentView extends Fragment implements StartContract.ViewLayer {



    //Presenter and View Layers
    private StartContract.PresenterLayer mPresenter;
    private GamePresenter gamePresenter;
    private GameFragmentView mView;
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

    @Override
    public void setPresenter(StartContract.PresenterLayer presenter) {
        this.mPresenter = presenter;
    }


    @OnClick(R.id.startGame)
    void startGame(){

        Log.d("Am I ", "yes you");

    }

    @OnClick(R.id.settingsButton)
    void openSettings(){



    }
}
