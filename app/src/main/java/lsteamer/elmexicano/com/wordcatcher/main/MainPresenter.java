package lsteamer.elmexicano.com.wordcatcher.main;

import android.os.CountDownTimer;

public class MainPresenter implements MainContract.PresenterLayer {


    MainContract.ViewLayer vLayer;

    MainPresenter(MainContract.ViewLayer view, UICountDownTimer timer){
        this.vLayer = view;
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
                view.updatetScreenText(String.valueOf(l/1000));

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
