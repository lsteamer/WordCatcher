package lsteamer.elmexicano.com.wordcatcher.main;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import lsteamer.elmexicano.com.wordcatcher.main.model.GameState;
import lsteamer.elmexicano.com.wordcatcher.main.model.WordModel;

import static org.mockito.Mockito.verify;

public class MainPresenterTest {

    @Mock
    private MainContract.ViewLayer mView;

    @Mock
    private GameState gameState;

    @Mock
    private WordModel model;

    @Mock
    private DefaultCountDownTimer timer;


    private MainPresenter mPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mPresenter = new MainPresenter(mView, gameState, model, timer);

    }

    @Test
    public void fetchNewWords() {
        mPresenter.fetchNewWords();

        verify(mView).updateScreenElements(gameState.getScoreRoundsString(), gameState.getSuccess(), gameState.getSuccessColor(), gameState.getWordEnglish(), gameState.getWordSpanish());

    }

    @Test
    public void getScoreRoundsString() {
        mPresenter.getScoreRoundsString();
        verify(gameState).getScoreRoundsString();
    }

    @Test
    public void isGameActive() {
        mPresenter.isGameActive();
        verify(gameState).isActive();
    }

    @Test
    public void deactivateState() {
        mPresenter.deactivateState();
        verify(gameState).setActive(false);

    }
}