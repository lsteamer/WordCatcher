package lsteamer.elmexicano.com.wordcatcher.main;

public interface MainContract {

    interface ViewLayer{
        void setPresenter(MainContract.PresenterLayer presenter);

        void updateScreenTime(String time);

        void updateScreenElements(String score, String result, String fallingWord, String matchWord);
    }

    interface PresenterLayer{

        void checkResult(boolean guess);

        void fetchNewWords();

        void incorrectResult();

        void correctResult();
    }
}
