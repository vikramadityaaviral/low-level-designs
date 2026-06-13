# Chess Board Game - Machine Coding Problem Statement

## Overview
Design and implement a console-based, two-player Chess Game Application. The game will execute programmatically via a series of coordinates or command inputs, simulate player turns, validate moves against strict chess rules, and manage the state of the board until a game-ending condition occurs.

## Core Requirements

### 1. Board and Pieces Setup
* The Grid: A standard 8x8 grid where columns are designated as files (a to h) and rows are designated as ranks (1 to 8).
* Initial State: Standard placement of all 32 pieces (16 White, 16 Black) at their respective starting coordinates.
* Piece Behaviors: Implement the distinct movement vectors, distance limits, and capturing behaviors for all standard pieces:
    * Pawn: Forward moves, diagonal captures.
    * Rook: Infinite linear horizontal or vertical movement.
    * Knight: Non-linear L-shaped hops (2x1 layout). Knights can jump over other pieces.
    * Bishop: Infinite diagonal movement.
    * Queen: Infinite linear combined movement (Rook and Bishop profiles).
    * King: Single-step movement in any direction.

### 2. Gameplay Loop
* Turn Sequence: Alternating turns starting strictly with the White player.
* Turn Invalidation: Reject any move that attempts to pass through an obstructing piece (except for Knights) or land on a square occupied by a piece of the same color.
* Piece Capture: Moving a piece to a square occupied by an opponent's piece removes the opponent's piece from the board.

### 3. Check Condition
* A player's move is strictly invalid if it leaves or puts their own King in a position of Check (under immediate threat of capture by an enemy piece).

## Input Format
The game should accept moves iteratively from standard command-line interface (CLI) inputs. Each move is defined by its starting square and target destination using standard algebraic notation coordinates.

* Format: [start_coordinate] [end_coordinate] (Example: e2 e4)
* To terminate the input stream or resign, players can type: exit

### Sample Input
```text
e2 e4
e7 e5
g1 f3
b8 c6
```

## Output Format
After every single input turn, the application must visually print the updated board state to the console and state the turn outcome.

* Board Rendering: An 8x8 text matrix using piece abbreviations (Example: WP = White Pawn, BR = Black Rook, -- = Empty Square).
* Game Status: Prompt indicating whose turn it is, or if a specific state like Check is active.

### Sample Output
```text
   a   b   c   d   e   f   g   h
8  BR  BN  BB  BQ  BK  BB  BN  BR  8
7  BP  BP  BP  BP  --  BP  BP  BP  7
6  --  --  --  --  --  --  --  --  6
5  --  --  --  --  BP  --  --  --  5
4  --  --  --  --  WP  --  --  --  4
3  --  --  --  --  --  --  --  --  3
2  WP  WP  WP  WP  --  WP  WP  WP  2
1  WR  WN  WB  WQ  WK  WB  WN  WR  1
   a   b   c   d   e   f   g   h

Black's turn to move:
```

## Constraints and Edge Cases (Scope of Core Round)
To ensure the challenge fits within a standard 90 to 120 minute machine coding window, you can apply these scope simplifications:
* No Special Moves: Castling, En Passant, and Pawn Promotion can be omitted in the initial core implementation unless explicitly requested.
* Basic Resolution: The game can conclude when a King is explicitly captured, or when a player triggers an exit or resign command.

## Evaluation Guidelines (Machine Coding Standards)
Your solution will be assessed based on professional software engineering metrics:

* Working Code: The application must be fully functional, compilation-error-free, and runnable.
* Clean Domain Modeling: Elegant representation of domain entities (Board, Square, Piece, King, Pawn, etc.) utilizing polymorphism or interfaces for piece movements.
* Validation Architecture: A central strategy or engine handler to manage move validations without sprawling, messy nested if-else loops.
* Separation of Concerns: Code separation between the Game State controller, Board presentation layer, and CLI entry point.
* Error Handling: Graceful responses to invalid coordinates (Example: z9) or illegal piece movements without crashing the process.

## Optional Extensibility Challenges (Bonus)
If you finish the core architecture early, implement these advanced rules to demonstrate exceptional design scalability:

1. Pawn Promotion: Automatically transform a pawn into a Queen when it safely reaches the furthest opposing rank (Rank 8 for White, Rank 1 for Black).
2. Castling: Implement the unique King-Rook swap rule assuming neither piece has moved yet and no squares between them are threatened.
3. Move History: Maintain an internal stack tracking all successful moves to support a complete command-line undo functionality.
