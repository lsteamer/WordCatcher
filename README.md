# Word Catcher - Babbel Challenge

<img src="/screenshots/cover" height="200px"/> <br>
A word matching game.

Beat the clock and learn Spanish. <br>
<img src="/screenshots/1.jpg" height="400px"/> <img src="/screenshots/2.jpg" height="400px"/><img src="/screenshots/3.jpg" height="400px"/><img src="/screenshots/4.jpg" height="400px"/>
<br>


# Coder Notes
The Challenge has a MVP architecture.<br>
<img src="/screenshots/architecture.jpg"/>
<br>
It includes a contract (MainContract) with contracts to both the Presenter and the View layer(MainFragment). The Model layer is constituted of the WordModel (The word repository) and the GameState.
I got the structure from the [Android Architecture Blueprints](https://github.com/googlesamples/android-architecture/tree/todo-mvp) <br>


# Dependencies
It's using ButterKnife, Gson and Mockito.<br>
<img src="/screenshots/dependencies.jpg"/>
<br>


# Time spent
Spent 6 Hours in the actual code, with some 20 minutes of doing Unit testing and 40 minutes doing initial planning.


# Project choices (Style and time)
I chose this project because, since I'm learning German, I think that it can be something that with a couple of fixes, I can share with my classmates and build upon it.

Following are my choices of the game:

I disabled landscape mode given that the falling animation would have to totally change from Y to X. Given more time I would have given that a try, but I think it's a good stylistic choice.

Definitely with more time I would do a more exhaustive testing. Both Unit as instrument tests.

Originally I wanted to have the timer go up every time the user got a point, but in the current architecture that proved difficult given the time.

I would had also liked to have a fading Correct/Wrong legend in the middle of the screen every time the user got it right or wrong, maybe change the color of the frame.

Also, I would give more detail to the end screen. Something as simple as having a 'Let's try again' Button at the bottom.

As something that I would DEFINITELY like to do, is give permanent storage to the app. Both to be able to add words to the Wordmodel and to be able to have a highscore.

Specially the first. Given that I'm currently Learning German, I think that this would be a great way to learn vocabulary. I might give that a shot the following days.