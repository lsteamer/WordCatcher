package lsteamer.elmexicano.com.wordcatcher.main;

public interface MainContract {

    interface ViewLayer{
        void setPresenter(MainContract.PresenterLayer presenter);

        void updateScreenText(String s);
    }

    interface PresenterLayer{

    }
}
