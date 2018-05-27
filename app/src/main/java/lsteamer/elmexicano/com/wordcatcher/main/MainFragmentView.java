package lsteamer.elmexicano.com.wordcatcher.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lsteamer.elmexicano.com.wordcatcher.R;

import static android.support.v4.util.Preconditions.checkNotNull;

public class MainFragmentView extends Fragment implements Animation.AnimationListener, MainContract.ViewLayer {

    public static final String VIEW_TAG = "MainFragmentView";


    private MainContract.PresenterLayer mPresenter;

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
    @BindView(R.id.wrongButton)
    Button wrongButton;
    @BindView(R.id.correctButton)
    Button correctButton;

    // Two animation Variables
    Animation animationFall, animationReset;


    public MainFragmentView() {
        // Empty public constructor
    }

    public static MainFragmentView newInstance() {
        return new MainFragmentView();
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void setPresenter(@NonNull MainContract.PresenterLayer presenter) {
        //Setting the presenter Layer
        this.mPresenter = checkNotNull(presenter);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_view, container, false);

        setAnimations();
        ButterKnife.bind(this, view);

        fallingTextView.setText("And now for something");

        fallingTextView.startAnimation(animationFall);


        mPresenter.fetchNewWords();

        return view;
    }

    @OnClick(R.id.wrongButton)
    void onClickWrongButton() {
        mPresenter.checkResult(false);
    }

    @OnClick(R.id.correctButton)
    void onClickCorrectButton() {
        mPresenter.checkResult(true);

    }


    // Updating the screen text. (atm, gets only the timer)
    @Override
    public void updateScreenTime(String s) {
        timerTextView.setText(s);
    }

    @Override
    public void updateScreenElements(String score, String result, String fallingWord, String matchWord) {
        fallingTextView.setText(fallingWord);
        scoreTextView.setText(score);
        resultTextView.setText(result);
        matchTextView.setText(matchWord);

    }


    // Following three methods are part of the Animation.AnimationListener and the fourth one is a helper of the animations

    public void setAnimations() {

        animationFall = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.falling);
        animationFall.setAnimationListener(this);

        animationReset = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.reset);
        animationReset.setAnimationListener(this);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {


        if (animation == animationFall) {

            fallingTextView.startAnimation(animationReset);

        } else if (animation == animationReset) {

            fallingTextView.startAnimation(animationFall);
        }


    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

}
