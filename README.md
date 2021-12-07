# README

Welcome to the Fall 2021 Team 1 Github Repository

### Instructions
Use the arrow keys to move the player character around the screen. The Z key interacts with the world, such as talking to NPC's or picking up items.  The spacebar allows the player to attack. Press X to change zones when the player one of the edges of the screen. Press C to enter cheat mode.

### Work Completed
Gameplay: Basic gameplay is working, the player can move around the screen, interact with NPC's or items, and attack and kill enemies. The game screen is finished as well, it displays the appropriate amount of hearts for the player's health as well as the current score and current location. The two enemies implemented in the game have fully working AI. All 10 zones are complete and can be explored by the player. Collision is fully implemented. Some easter eggs are added. The player can be killed by enemies upon which a death animation plays and a game over screen is displayed. 



Serialization: The saving and loading feature is working for both the world and the high scores (although high scores are not displayed yet). On saving, the player's position, world location, health, and score are all saved to a text file. The values can be retrieved when loading to return the world to the state it was in.


High Score Screen: The HighScore screen is fully functioning. It will pull information from the file HIGHSCORES.txt and display themf in their appropriate order. When a game is completed and the player goes back to the Start screen and clicks the Highscore button, the Highscore screen updates itself to show the most recent addition. Also, when the game is run, the program will check to see if a HIGHSCORE.txt already exists so that it can pull its info. If it does not exist, than it creates a Highscore list of 0 points for each name called '-', and then writes it to the newly created file HIGHSCORE.txt and saves it for use later as new highscores are recorded.




### Alpha Video Recording
https://bju-my.sharepoint.com/:v:/g/personal/afox797_students_bju_edu/EfU9zCAlrFFOkRbVOCCbCLoBkiTCjz10Mq_4NJfYzHMnCg?e=LGhVuT

### Beta Video Recording
https://bju-my.sharepoint.com/:v:/g/personal/afox797_students_bju_edu/EVV4JrgpbaNKt3lk10dEJwcBouJK_jJpLKv2cZjjRHPgcQ?e=Mwq9EU


### Expenses
| Member Name | Username | Hours Invested | Hours Remaining |
|-------------|----------|----------------|-----------------|
| Andrew Fox | afox797 | 44:30 | 5:30 |
| Joshua Douglas | Josh-Douglas | 26:15 | 23:45 |
| David Goff | dgoff448 | 44:30 | 5:30 |
  
[Andrew Fox Journal](https://github.com/bjucps209/fall2021-team1/wiki/Andrew-Fox-Journal)  
[Joshua Douglas Journal](https://github.com/bjucps209/fall2021-team1/wiki/Joshua-Douglas-Journal)  
[David Goff Journal](https://github.com/bjucps209/fall2021-team1/wiki/David-Goff-Journal)
