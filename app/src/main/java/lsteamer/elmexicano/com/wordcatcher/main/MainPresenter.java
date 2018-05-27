package lsteamer.elmexicano.com.wordcatcher.main;

public class MainPresenter implements MainContract.PresenterLayer {


    MainContract.ViewLayer vLayer;

    MainPresenter(MainContract.ViewLayer view){
        this.vLayer = view;
    }
}
