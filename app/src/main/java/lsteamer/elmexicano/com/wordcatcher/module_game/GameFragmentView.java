package lsteamer.elmexicano.com.wordcatcher.module_game;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lsteamer.elmexicano.com.wordcatcher.R;

import static android.support.v4.util.Preconditions.checkNotNull;

public class GameFragmentView extends Fragment implements Animation.AnimationListener, GameContract.ViewLayer {


    private GameContract.PresenterLayer mPresenter;

    // Binding View Elements with Butterknife
    @BindView(R.id.timerTextView)
    TextView timerTextView;
    @BindView(R.id.scoreTextView)
    TextView scoreTextView;
    @BindView(R.id.resultTextView)
    TextView resultTextView;
    @BindView(R.id.fallingTextView)
    TextView fallingTextView;
    @BindView(R.id.matchTextView)
    TextView matchTextView;
    @BindView(R.id.layoutBottom)
    ConstraintLayout constraintLayoutBottom;
    @BindView(R.id.linearLayoutEndScreen)
    LinearLayout linearLayoutEndScreen;
    @BindView(R.id.scoreFinalTextView)
    TextView scoreFinalTextView;

    // Two animation Variables
    Animation animationFall, animationReset;


    public GameFragmentView() {
    }

    public static GameFragmentView newInstance() {
        return new GameFragmentView();
    }


    @Override
    public void setPresenter(@NonNull GameContract.PresenterLayer presenter) {
        this.mPresenter = checkNotNull(presenter);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.play_fragment_view, container, false);

        //setting the animation
        setAnimations();

        ButterKnife.bind(this, view);

        //Starting the animation
        fallingTextView.startAnimation(animationFall);

        //fetching the words that will be displayed
        mPresenter.fetchNewWords();

        return view;
    }

    @OnClick(R.id.wrongButton)
    void onClickWrongButton() {
        mPresenter.checkResult(false);

        fallingTextView.startAnimation(animationReset);
    }

    @OnClick(R.id.correctButton)
    void onClickCorrectButton() {
        mPresenter.checkResult(true);

        fallingTextView.startAnimation(animationReset);
    }

    @OnClick(R.id.restartGameButton)
    void restartGameButtonPressed() {
        mPresenter.restartGame();
    }

    @Override
    public void updateScreenTime(String s) {
        timerTextView.setText(s);
    }

    @Override
    public void updateScreenElements(int score, int rounds, boolean result, String fallingWord, String matchWord) {
        if (mPresenter.isGameActive()) {
            //If the game is active update screen elements

            //words to be matched
            fallingTextView.setText(fallingWord);
            matchTextView.setText(matchWord);

            //current score
            setScoreRoundsString(score, rounds);

            //current result
            setResultTitle(result);

        }
    }


    public void setResultTitle(boolean result) {
        if(mPresenter.isGameFirstRound()) {
            resultTextView.setText(getString(R.string.start_message));
            resultTextView.setTextColor(Color.BLACK);
        }
        else if(result) {
            resultTextView.setText(getString(R.string.success));
            resultTextView.setTextColor(getResources().getColor(R.color.colorGreen));
        }
        else {
            resultTextView.setText(getString(R.string.failure));
            resultTextView.setTextColor(Color.RED);
        }
    }

    public void setScoreRoundsString(int score, int rounds) {
        String scoreAndRounds = String.valueOf(score) + " / " + String.valueOf(rounds);
        scoreTextView.setText(scoreAndRounds);
    }

    public void gameOver() {

        //we deactivate the state
        mPresenter.deactivateGameState();


        updateScreenTime(getResources().getString(R.string.zero));

        switchScreens();

        scoreFinalTextView.setText(scoreTextView.getText());
    }


    //Switches between the game and the end screen
    public void switchScreens() {

        if (mPresenter.isGameActive()) {
            //if game is active

            //hide the End screen
            linearLayoutEndScreen.setVisibility(View.GONE);

            //restart the animation
            fallingTextView.startAnimation(animationFall);

            //set the Falling text the Layout at the bottom and the result view as active
            fallingTextView.setVisibility(View.VISIBLE);
            constraintLayoutBottom.setVisibility(View.VISIBLE);
            resultTextView.setVisibility(View.VISIBLE);
        } else {
            //if the game is inactive

            //clear animation and hide views
            fallingTextView.clearAnimation();
            fallingTextView.setVisibility(View.GONE);
            constraintLayoutBottom.setVisibility(View.GONE);
            resultTextView.setVisibility(View.GONE);

            //make the End screen visible
            linearLayoutEndScreen.setVisibility(View.VISIBLE);
        }
    }


    // Following methods are part of the Animation.AnimationListener or helpers for the animations
    public void setAnimations() {

        animationFall = AnimationUtils.loadAnimation(Objects.requireNonNull(getActivity()).getApplicationContext(), R.anim.falling);
        animationFall.setAnimationListener(this);

        animationReset = AnimationUtils.loadAnimation(Objects.requireNonNull(getActivity()).getApplicationContext(), R.anim.reset);
        animationReset.setAnimationListener(this);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }


    @Override
    public void onAnimationEnd(Animation animation) {

        //If the game is active (It has started and it hasn't ended)
        if (mPresenter.isGameActive())
            if (animation == animationFall) {
                //If we're coming out of the Fall

                //Reset it
                fallingTextView.startAnimation(animationReset);

                //Flag it as an incorrect Result
                mPresenter.incorrectResult();


            } else if (animation == animationReset) {
                //Otherwise set it to fall again
                fallingTextView.startAnimation(animationFall);
            }


    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

}
