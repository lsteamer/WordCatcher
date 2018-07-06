package lsteamer.elmexicano.com.wordcatcher.main;

public interface MainContract {

    interface ViewLayer {
        void setPresenter(MainContract.PresenterLayer presenter);

        void updateScreenTime(String time);

        void updateScreenElements(String score, boolean result, String fallingWord, String matchWord);

        void setAnimations();

        void gameOver();

        void switchScreens();
    }

    interface PresenterLayer {

        void checkResult(boolean guess);

        void fetchNewWords();

        void incorrectResult();

        void correctResult();

        void deactivateState();

        boolean isGameActive();

        String getScoreRoundsString();

        void restartGame();

        boolean isGameFirstRound();
    }
}
