package lsteamer.elmexicano.com.wordcatcher.module_game;

public interface GameContract {

    interface ViewLayer {
        void setPresenter(GameContract.PresenterLayer presenter);

        void updateScreenTime(String time);

        void updateScreenElements(int score, int rounds, boolean result, String fallingWord, String matchWord);

        void setAnimations();

        void gameOver();

        void switchScreens();
    }

    interface PresenterLayer {

        void checkResult(boolean guess);

        void fetchNewWords();

        void incorrectResult();

        void correctResult();

        void deactivateGameState();

        boolean isGameActive();

        void restartGame();

        boolean isGameFirstRound();

    }
}
