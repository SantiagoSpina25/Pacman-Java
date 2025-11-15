# Pac-Man Java

An easy Java implementation/simulation of Pac-Man with game loop, player controls, ghost enemies, a tile-based board, scoring, and basic collision logic. The project use plain Java (AWT/Swing) for rendering and input handling.

## ✨ Features
- Contains Pac-Man main game logic
- Ghost (enemy) AI / movement
- Score tracking and win/lose conditions.
- Keyboard input for player movement.
- Basic ghost AI or movement patterns.


## 🧩 How to run (general instructions)
1. Ensure you have Java 8 or later installed and `javac`/`java` on your PATH.
2. Compile the sources from the project root, for example:
```bash
javac -d out $(find . -name "*.java")
```
3. Run the main class (replace `com.example.Main` with the detected main class):
```bash
java -cp out Pacman-Java.src.Main.Main
```

## 🎮 Controls
- Arrow keys or WASD to move Pac-Man.

## 🛡️ License
This project is distributed under the MIT license.
You are free to use, modify, and share it.
