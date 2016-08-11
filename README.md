# Macho Squad: Back to Trapistan

#####- 1. Description
#####- 2. Roadmap and Current State
#####- 3. Future Goals
#####- 4. Pictures


## 1. Description

Macho Squad is a text adventure game set in an over the top, 1980's sci fi, hypermasculine environment.  The game is played in a simple manner, by reading the current situation, selecting an available choice, and progressing to the next point.  Success and failure is determined by checking choice difficulty versus character stats and items, and failures will reduce character hit points.

Content may be generated using the included Python script (TextAdventureContentGenerator.py), using the following terminology:
Node - Current story position.  Has attached text, and optionally image, and animation information.
Choice - Bridge between current and next node.  Contains information regarding difficulty, required items, attached nodes, etc.
Popup - Appears after a choice is selected.  Contains text, and optionally image and animation.  Describes the results of an attempted choice.

## 2. Roadmap and Current State

* Python script - completed
* Nodes, choices, and popups ingest into database when version is incremented - complete
* Character selection allows for different statistics, and includes a manly name generator - complete
* Image tags (entered into script) map to images hosted online - complete
* Animation tags map to different page change animations - incomplete
* Old games can be loaded, and saves can be deleted - complete
* Sound effects at important points - incomplete

## 3. Future Goals

* Complete content
* Improve statistic selection interface
* Animate launch page
* Move DB ingestion into a seperate thread, and start it as soon as the app is opened

## 4. Pictures

<img src="/Screenshots/machosquad1.png" alt="Drawing" width= "200"/>
<img src="/Screenshots/machosquad2.png" alt="Drawing" width= "200"/>
<img src="/Screenshots/machosquad3.png" alt="Drawing" width= "200"/>
<img src="/Screenshots/machosquad4.png" alt="Drawing" width= "200"/>
