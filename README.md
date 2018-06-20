# Word Catcher
Branch master contains a stable build.


<img src="/screenshots/cover.png" height="200px"/> <br>
A word matching game.

Learn Languages while playing <br>
<img src="/screenshots/1.png" height="400px"/> <img src="/screenshots/2.png" height="400px"/><img src="/screenshots/3.png" height="400px"/><img src="/screenshots/4.png" height="400px"/>
<br>


# Coder Notes
The Challenge has a MVP architecture.<br>
<img src="/screenshots/architecture.png"/>
<br>
It includes a contract (MainContract) with contracts to both the Presenter and the View layer(MainFragment). The Model layer is constituted of the WordModel (The word repository) and the GameState.
I got the structure from the [Android Architecture Blueprints](https://github.com/googlesamples/android-architecture/tree/todo-mvp) <br>


# Dependencies
It's using ButterKnife, Gson and Mockito.<br>
<img src="/screenshots/dependencies.png"/>
<br>


# Upgrades coming:
Complete unit testing.<br>
Dagger 2 implementation.<br>
No-time-limit option.<br>
Different languages.<br>
Different levels.<br>
Custom dictionaries.<br>
High Scores.<br>