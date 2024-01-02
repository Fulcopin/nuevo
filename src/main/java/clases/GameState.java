/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import tree.BinaryNode;

/**
 *
 * @author fulco
 */
public class GameState {
    private BinaryNode<GameState> node;
    private String[][] state;
    private String currentPlayer;

    public GameState() {
        state = new String[3][3];
        currentPlayer = "X";
    }
public GameState(GameState original) {
        this.state = new String[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                this.state[row][col] = original.state[row][col];
            }
        }
        this.currentPlayer = original.currentPlayer;
    }
public BinaryNode<GameState> getNode() {
        return node;
    }

    public void setNode(BinaryNode<GameState> node) {
        this.node = node;
    }
    public String[][] getState() {
        return state;
    }

    public String getStateIndex(int index) {
        int row = index / 3;
        int col = index % 3;
        return state[row][col];
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }
  public void copyState(GameState original) {
        // Llama al constructor de copia
        GameState copy = new GameState(original);
        // Asigna la copia al estado actual
        this.state = copy.state;
        this.currentPlayer = copy.currentPlayer;
    }
   public boolean makeMove(int row, int col, String player) {
    

    if (row < 0 || row >= 3 || col < 0 || col >= 3) {
        System.out.println("Movimiento Invalido");
        return false;
    }

    if (state[row][col] == null) {
        state[row][col] = player;
        currentPlayer = (player.equals("X")) ? "O" : "X";
        return true;
    } else {
        System.out.println("Casilla ocupada");
        return false;
    }
}

    public boolean isGameOver() {
        return checkForWinner() || isBoardFull();
    }

    public String getWinner() {
        if (checkForWinner()) {
            return currentPlayer.equals("X") ? "O" : "X";
        }
        return "Draw";
    }

    private boolean checkForWinner() {
        
        return checkRows() || checkColumns() || checkDiagonals();
    }

    private boolean checkRows() {
        for (int row = 0; row < 3; row++) {
            if (checkLine(state[row][0], state[row][1], state[row][2])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns() {
        for (int col = 0; col < 3; col++) {
            if (checkLine(state[0][col], state[1][col], state[2][col])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals() {
        return checkLine(state[0][0], state[1][1], state[2][2]) ||
               checkLine(state[0][2], state[1][1], state[2][0]);
    }

    private boolean checkLine(String cell1, String cell2, String cell3) {
        return cell1 != null && cell1.equals(cell2) && cell2.equals(cell3);
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (state[row][col] == null) {
                    return false;
                }
            }
        }
        return true;
    }
}

