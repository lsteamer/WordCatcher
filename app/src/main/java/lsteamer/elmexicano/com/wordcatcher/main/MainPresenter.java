package lsteamer.elmexicano.com.wordcatcher.main;

import android.os.CountDownTimer;

import lsteamer.elmexicano.com.wordcatcher.model.WordModel;

public class MainPresenter implements MainContract.PresenterLayer {


    private MainContract.ViewLayer vLayer;
    private WordModel model;

    MainPresenter(MainContract.ViewLayer view, WordModel wordModel, UICountDownTimer timer){
        this.vLayer = view;
        this.model = wordModel;
        vLayer.setPresenter(this);

        timer.attach(this.vLayer);
        timer.start();
    }

    public interface UICountDownTimer{
        void attach(MainContract.ViewLayer view);
        void start();
        void cancel();
    }

}

class DefaultCountDownTimer implements MainPresenter.UICountDownTimer {

    private CountDownTimer timer;

    public void attach(final MainContract.ViewLayer view){
        timer = new CountDownTimer(46000, 1000) {
            @Override
            public void onTick(long l) {
                //Update the Timer
                view.updateScreenText(String.valueOf(l/1000));

            }

            @Override
            public void onFinish() {
                //End the game, show an end screen?
            }
        };
    }

    @Override
    public void start() {
        timer.start();
    }

    @Override
    public void cancel() {
        timer.cancel();
    }

}
