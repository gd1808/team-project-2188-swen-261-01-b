---
geometry: margin=1in
---
# PROJECT Design Documentation

## Team Information
* Team name: The B Team
* Team members
  * Gunther Kroth
  * Gabriel Diaz
  * Kyle Heberger
  * Andrew Lee

## Executive Summary

This project tasks the development team to create a web application that allows players to play, replay, and spectate checkers games using American rules.

### Purpose
The purpose of this project is to allow players to play and spectate checkers games, and replay the game that was just played in the session. The end user wants to watch and play American rule checkers games with another user. The most important user group is the group of players that are waiting to play or spectate games.

### Glossary and Acronyms

| Term | Definition |
|------|------------|
| VO | Value Object |


## Requirements

This section describes the features of the application.




### Definition of MVP

Players interested in playing checkers on the WebCheckers App will sign in before the play. When they are done playing, they will sign out.
Once a player is signed in, a list of online players is presented to the user.
The signed in player will have the option to challenge other users to games.
All games played on the WebCheckers App follow the American Rules of checkers.
During a game, a player has the option to resign at any time.
Once the end of game is reached based on the American Rules of checkers, the game ends and an end game screen is shown to the user.

### MVP Features

Player Sign-in: Using a unique username consisting of alpha-numeric characters, users create their own WebCheckers account.
Player Lobby: There is a list of players displayed to the currently signed in player that allows them to see all online players and have the option to challenge them to a game.
Starting a Game: By selecting another user who is also signed on to WebCheckers, the two players will be brought into a checkers game.
Playing of Turns: Players have to option to move their pieces around the board before they submit their final decision.
Waiting for opponents: While waiting for an opponent to take their turn, a user has to option to resign from a game at any point.
Ending the Game: Once an end game condition is reached based on the American rules for playing checkers - one team has no pieces left, one team has no more moves, or someone has resigned - the game ends and the players can exit the game.

### Roadmap of Enhancements

Replay Mode: Game can be saved by a user and reviewed at a later date.
Spectator Mode: Other players mey view an on-going game that they are not participating in.

## Application Domain

This section describes the application domain.

![The WebCheckers Domain Model](domain-model.png)

The User Interface entity is the lens in which the user views and interacts with all the other 
entities. This interface is what displays the Checkers Game entity.

The Checkers Game can be played or spectated by the Player entity, and the Board entity is used to
actually play the game.

The Piece entity can be of two types, regular or king, and each of these pieces reside within a 
Square entity that is on the game board. Each Player entity has control of 12 pieces at the start of
the game, and they are the ones that can change which square the pieces are placed on.

The Square entity can be either red or black, with black being the actually playable spaces on
the board. The black squares can be either normal or of type King Row, which would mean they are
spaces at either end of the board where the regular pieces can be promoted to kings.

## Architecture and Design

This section describes the application architecture.

### Summary

The following Tiers/Layers model shows a high-level view of the webapp's architecture.

![The Tiers & Layers of the Architecture](architecture-tiers-and-layers.png)

As a web application, the user interacts with the system using a
browser.  The client-side of the UI is composed of HTML pages with
some minimal CSS for styling the page.  There is also some JavaScript
that has been provided to the team by the architect.

The server-side tiers include the UI Tier that is composed of UI Controllers and Views.
Controllers are built using the Spark framework and View are built using the FreeMarker framework.  The Application and Model tiers are built using plain-old Java objects (POJOs).

Details of the components within these tiers are supplied below.


### Overview of User Interface

This section describes the web interface flow; this is how the user views and interacts
with the WebCheckers application.

![The WebCheckers Web Interface Statechart](user-interface.png)

The first thing the user sees is the web application's home page. This page provides a link to the
sign in page as well as the number of currently online users.

After moving to the sign in page, the user will be able to input a username of their choosing inside
of the textbox provided on the page. Clicking the submit button will try and assign that chosen
username to the player and redirect them back to the homepage as a logged in user. It may fail,
however, if the username is taken or invalid. This will result in a prompt explaining why the login
failed, and will let them retry.

Once a valid name is chosen, the user will be redirected back to the homepage, but this time a list
of online players will be shown that the user can choose from to start a game with. If the other user
available, their name will be clickable and it will say available next to their name. If they are 
busy, it will say busy next to their name and they cannot be clicked.

If the user clicks on an available player's name, they will be redirected to the game page, where
the other user they clicked on will be redirected to as well. This page shows the full checkerboard
with the users pieces at the bottom of the screen. From here, the game will be able to be played.

### UI Tier
> _Provide a summary of the Server-side UI tier of your architecture.
> Describe the types of components in the tier and describe their
> responsibilities.  This should be a narrative description, i.e. it has
> a flow or "story line" that the reader can follow._

> _At appropriate places as part of this narrative provide one or more
> static models (UML class structure or object diagrams) with some
> details such as critical attributes and methods._

> _You must also provide any dynamic models, such as statechart and
> sequence diagrams, as is relevant to a particular aspect of the design
> that you are describing.  For example, in WebCheckers you might create
> a sequence diagram of the `POST /validateMove` HTTP request processing
> or you might show a statechart diagram if the Game component uses a
> state machine to manage the game._

> _If a dynamic model, such as a statechart describes a feature that is
> not mostly in this tier and cuts across multiple tiers, you can
> consider placing the narrative description of that feature in a
> separate section for describing significant features. Place this after
> you describe the design of the three tiers._


### Application Tier
> _Provide a summary of the Application tier of your architecture. This
> section will follow the same instructions that are given for the UI
> Tier above._


### Model Tier
> _Provide a summary of the Application tier of your architecture. This
> section will follow the same instructions that are given for the UI
> Tier above._

### Design Improvements
> _Discuss design improvements that you would make if the project were
> to continue. These improvement should be based on your direct
> analysis of where there are problems in the code base which could be
> addressed with design changes, and describe those suggested design
> improvements. After completion of the Code metrics exercise, you
> will also discuss the resutling metric measurements.  Indicate the
> hot spots the metrics identified in your code base, and your
> suggested design improvements to address those hot spots._

## Testing
When testing the various features of the application, two approaches were taken. The first is a more high-level test that examines the implementation of user stories. Acceptance criteria were defined for each user story that would verify its completeness.

Unit testing was used to ensure the accuracy of the code behind the implementation. To encourage a bug-free experience for users, components that directly effect the user's checkers experienced were focused on.

### Acceptance Testing
> _Report on the number of user stories that have passed all their
> acceptance criteria tests, the number that have some acceptance
> criteria tests failing, and the number of user stories that
> have not had any testing yet. Highlight the issues found during
> acceptance testing and if there are any concerns._

### Unit Testing and Code Coverage
> _Discuss your unit testing strategy. Report on the code coverage
> achieved from unit testing of the code base. Discuss the team's
> coverage targets, why you selected those values, and how well your
> code coverage met your targets. If there are any anomalies, discuss
> those._
