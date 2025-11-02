# GUI Pong Game

A classic two-player implementation of the arcade game Pong, built with Java Swing.

This project recreates the simple yet addictive gameplay of Pong, where two players control paddles to hit a ball back and forth. It's a great example of real-time input handling, collision detection, and game state management in Java.



## Features

-   **Two-Player Local Gameplay:** Player 1 (left) uses 'W' and 'S' keys, while Player 2 (right) uses the Up and Down Arrow keys.
-   **Scoring System:** The first player to let the ball pass their paddle concedes a point.
-   **Real-time Ball Physics:** The ball bounces realistically off the paddles and the top/bottom walls.
-   **Game Reset:** The ball resets in the center after a point is scored.
-   **Clean, Minimalist UI:** A retro-inspired design that focuses on gameplay.

## How to Play

1.  Run the game, and a window will appear.
2.  **Player 1 (Left Paddle):**
    -   Press `W` to move up.
    -   Press `S` to move down.
3.  **Player 2 (Right Paddle):**
    -   Press the `Up Arrow` to move up.
    -   Press the `Down Arrow` to move down.
4.  The objective is to hit the ball past your opponent's paddle to score a point.
5.  The game continues indefinitely.

## How to Compile and Run

You will need the Java Development Kit (JDK) installed. No external libraries are needed.

1.  **Save the Code:**
    Save the Java code into a file named `PongGame.java`.

2.  **Open a Terminal or Command Prompt:**
    Navigate to the directory where you saved the file.

3.  **Compile the Code:**
    ```bash
    javac PongGame.java
    ```

4.  **Run the Game:**
    ```bash
    java PongGame
    ```

A new window will open with the game. Have fun!
