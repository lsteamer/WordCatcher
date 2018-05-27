package lsteamer.elmexicano.com.wordcatcher.main;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import lsteamer.elmexicano.com.wordcatcher.R;
import lsteamer.elmexicano.com.wordcatcher.main.MainFragmentView;
import lsteamer.elmexicano.com.wordcatcher.main.MainPresenter;
import lsteamer.elmexicano.com.wordcatcher.util.Utils;

public class MainActivity extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayout;
    private ConstraintLayout preLayout;

    MainPresenter mPresenter;
    MainFragmentView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        preLayout = (ConstraintLayout) findViewById(R.id.preLayout);

    }

    protected void startGame(View view){

        coordinatorLayout.setVisibility(View.VISIBLE);
        preLayout.setVisibility(View.GONE);

        mView = (MainFragmentView) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if(mView == null){
            mView = MainFragmentView.newInstance();
            Utils.addFragmentToActivity(getSupportFragmentManager(), mView, R.id.contentFrame);
        }


        mPresenter = new MainPresenter(mView);


    }
}
