/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author fulco
 */
import java.util.ArrayList;

public class AdversarialSearchTicTacToe {

    public GameState minMaxDecision(GameState state) {
        ArrayList<GameState> possibleMoves = successorsOf(state);
        int max = Integer.MIN_VALUE;
        GameState bestMove = null;

        for (GameState nextState : possibleMoves) {
            int value = minValue(nextState);
            if (value > max) {
                max = value;
                bestMove = nextState;
            }
        }

        return bestMove;
    }

    private int maxValue(GameState state) {
        if (state.isGameOver()) {
            return utilityOf(state);
        }

        int v = Integer.MIN_VALUE;

        for (GameState possibleMove : successorsOf(state)) {
            v = Math.max(v, minValue(possibleMove));
        }

        return v;
    }

    private int minValue(GameState state) {
        if (state.isGameOver()) {
            return utilityOf(state);
        }

        int v = Integer.MAX_VALUE;

        for (GameState possibleMove : successorsOf(state)) {
            v = Math.min(v, maxValue(possibleMove));
        }

        return v;
    }

    private int utilityOf(GameState state) {
        if (state.isGameOver()) {
            String winner = state.getWinner();
            if (winner.equals("X")) {
                return 1; // Jugador X ganó
            } else if (winner.equals("O")) {
                return -1; // Jugador O ganó
            } else {
                return 0; // Empate
            }
        } else {
            // El juego aún no ha terminado
            return 0;
        }
    }

    private ArrayList<GameState> successorsOf(GameState state) {
        ArrayList<GameState> possibleMoves = new ArrayList<>();

        if (!state.isGameOver()) {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (state.getState()[row][col] == null) {
                        // Copiar el estado actual y realizar el movimiento
                        GameState successor = new GameState();
                        successor.copyState(state);
                        successor.makeMove(row, col, state.getCurrentPlayer());
                        possibleMoves.add(successor);
                    }
                }
            }
        }

        return possibleMoves;
    }
}
