# Snake & Ladder Board Game - Machine Coding Problem Statement

## Overview
Design and implement a multi-player, console-based **Snake and Ladder Application**. The game will execute programmatically based on user configurations and simulate turns automatically until a definitive winner emerges.

---

## Core Requirements

### 1. Board Configurations
* **Standard Grid Size**: The board consists of a fixed 10 × 10 layout (totaling 100 cells), sequentially numbered from 1 to 100.
* **Special Jumpers**: The board will contain an arbitrary number of snakes and ladders, dynamically specified by the input.

### 2. Gameplay Entities
* **Dice**: A standard 6-sided dice that yields a random number from 1 to 6 upon simulation.
* **Players**: Multiple participants can play. Each player begins off-board at position `0` and cycles through turns in a round-robin format.

### 3. Movement Rules
* **Exact Win Condition**: A player must land precisely on cell `100` to win. If the current position plus the dice roll exceeds 100, the piece remains stationary for that turn.
* **Snake Sliders**: If a piece lands on a cell containing a snake's head, it slides down immediately to the snake's tail.
* **Ladder Climbers**: If a piece lands on a cell containing the bottom of a ladder, it immediately advances to the ladder's top.
* **No Chains**: Jumpers do not form cascading chains. If a ladder drops a player onto another ladder's base or a snake's head, no secondary movement triggers on that turn.
* **Shared Cells**: Multiple players can seamlessly occupy the exact same cell simultaneously.

---

## Input Format
The application must digest input from standard command-line interface (CLI) or a text file using this precise structure:

1. **Snakes**: An integer `S` (number of snakes), followed by `S` lines where each line contains two integers: `head_position` and `tail_position` (\(head\_position > tail\_position\)).
2. **Ladders**: An integer `L` (number of ladders), followed by `L` lines where each line contains two integers: `start_position` and `end_position` (\(start\_position < end\_position\)).
3. **Players**: An integer `P` (number of players), followed by `P` lines, each containing a unique alphanumeric string representing the player's name.

### Sample Input
```text
3
62 5
33 6
98 64
3
2 37
27 46
51 91
2
Alice
Bob
```

---

## Output Format
Print the chronological logs of each dice roll. Conclude with a definitive victory declaration using this template:

* **Turn Log Format**: `<player_name> rolled a <dice_value>`
* **Simple turn based movement**: `<player_name> moved from <initial_position> to <final_position>`
* **Snake/Ladder based movement**: `<player_name> moves up/down a ladder/snake from <initial_position> to <final_position>`
* **Winner Announcement Format**: `<player_name> wins the game!`

### Sample Output
```text
Alice rolled a 6
Alice moved from 0 to 6
Bob rolled a 2
Bob moved from 0 to 2
Bob moved up a ladder from 2 to 37
Alice rolled a 4 
Alice moved from 6 to 10
...
Alice rolled a 2
Alice moved from 98 to 100
Alice wins the game!
```

---

## Constraints & Edge Cases
* **Infinite Loops Avoidance**: You can safely assume the input configurations will never form infinite loops between loops of snakes and ladders.
* **Validation Bounds**: $1 \le S, L \le 20$, and $2 \le P \le 10$.
* **No Jumper Misalignments**: No snake head or ladder base will ever reside at cell `100` or cell `0`. No two jumper will share an identical start cell.

---

## Evaluation Guidelines (Machine Coding Standards)
Your submission will be carefully reviewed using the following engineering benchmarks:

* [ ] **Working Code**: The application must be fully functional, compilation-error-free, and runnable.
* [ ] **Object-Oriented Design**: Correct mapping of real-world entities into clean abstractions (e.g., `Board`, `Player`, `Dice`, `Jumper`).
* [ ] **Separation of Concerns**: Decouple input/output processing logic entirely from core algorithmic gameplay states.
* [ ] **Extensibility**: The codebase must accommodate feature variations (such as the optional additions below) with minimal modifications to the core business logic.
* [ ] **No Monolithic Files**: Do not lump all your classes into a single code file.

---

## Optional Extensibility Challenges (Bonus)
*If you complete the core architecture within the time limit, try expanding the software footprint:*

1. **Cascading Turns (Lucky Sixes)**: Rolling a `6` rewards that player with an immediate consecutive bonus turn. However, hitting three consecutive `6`s cancels all progress made during that turn sequence entirely.
2. **Custom Boards**: Allow variables for customized game dimensions (e.g., $M \times N$ size grids) and a customized number of dice ($D$).
3. **Continuous Placement**: Instead of halting at the first winner, keep the automation running continuously until all players finish to generate a final ranked leaderboard.
