package com.study.test;

import java.util.Random;
import java.util.Scanner;

public class Test {
    private static final int SIZE = 4;
    private static final int WINNING_SCORE = 2048;

    private int[][] board;
    private int score;
    private boolean gameWon;
    private Random random;
    private Scanner scanner;

    public Test() {
        board = new int[SIZE][SIZE];
        score = 0;
        gameWon = false;
        random = new Random();
        scanner = new Scanner(System.in);
        addRandomTile();
        addRandomTile();
    }

    private void addRandomTile() {
        int row, col;
        do {
            row = random.nextInt(SIZE);
            col = random.nextInt(SIZE);
        } while (board[row][col] != 0);
        board[row][col] = (random.nextInt(2) + 1) * 2; // Generate 2 or 4
    }

    private void printBoard() {
        System.out.println("Score: " + score);
        for (int[] row : board) {
            for (int cell : row) {
                System.out.printf("%-6d", cell);
            }
            System.out.println();
        }
    }

    private boolean isBoardFull() {
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private void mergeTiles(int[] tiles) {
        for (int i = 0; i < SIZE - 1; i++) {
            if (tiles[i] == tiles[i + 1]) {
                tiles[i] *= 2;
                score += tiles[i];
                if (tiles[i] == WINNING_SCORE) {
                    gameWon = true;
                }
                tiles[i + 1] = 0;
            }
        }
    }

    private void moveLeft() {
        for (int[] row : board) {
            mergeTiles(row);
            int[] newRow = new int[SIZE];
            int index = 0;
            for (int cell : row) {
                if (cell != 0) {
                    newRow[index++] = cell;
                }
            }
            System.arraycopy(newRow, 0, row, 0, SIZE);
        }
    }

    private void moveRight() {
        for (int[] row : board) {
            reverseArray(row);
            mergeTiles(row);
            reverseArray(row);
        }
    }

    private void moveUp() {
        transposeBoard();
        moveLeft();
        transposeBoard();
    }

    private void moveDown() {
        transposeBoard();
        moveRight();
        transposeBoard();
    }

    private void reverseArray(int[] array) {
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
    }

    private void transposeBoard() {
        int[][] newBoard = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                newBoard[j][i] = board[i][j];
            }
        }
        board = newBoard;
    }

    private boolean isMovePossible() {
        if (!isBoardFull()) {
            return true;
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE - 1; j++) {
                if (board[i][j] == board[i][j + 1] || board[j][i] == board[j + 1][i]) {
                    return true;
                }
            }
        }
        return false;
    }

    private void playGame() {
        while (!gameWon && isMovePossible()) {
            printBoard();
            System.out.println("Enter your move (W/A/S/D): ");
            String move = scanner.nextLine().trim().toUpperCase();
            switch (move) {
                case "W":
                    moveUp();
                    break;
                case "A":
                    moveLeft();
                    break;
                case "S":
                    moveDown();
                    break;
                case "D":
                    moveRight();
                    break;
                default:
                    System.out.println("Invalid move! Use W/A/S/D.");
            }
            if (!move.equals("W") && !move.equals("A") && !move.equals("S") && !move.equals("D")) {
                continue;
            }
            if (!isBoardFull()) {
                addRandomTile();
            }
        }
        printBoard();
        if (gameWon) {
            System.out.println("Congratulations! You won the game!");
        } else {
            System.out.println("Game over! You have no moves left.");
        }
    }

    public static void main(String[] args) {
        Test game = new Test();
        game.playGame();
    }
}
