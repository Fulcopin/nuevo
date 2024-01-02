/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject12;

import clases.AdversarialSearchTicTacToe;
import clases.GameState;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import tree.BinaryTree;

public class TicTacToeController {

    @FXML
    private GridPane gridPane;

    private BinaryTree<GameState> gameTree;
    private GameState currentGameState;
    private Button[][] buttons;

    @FXML
    public void initialize() {
        gameTree = new BinaryTree<>(new GameState());
        currentGameState = gameTree.getRoot().getContent();

        buttons = new Button[3][3];

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button button = new Button();
                button.setMinSize(100, 100);
                 final int finalRow = row; // Crear variables finales adicionales
                final int finalCol = col;
        
                button.setOnAction(e -> handleButtonClick(e));
                buttons[row][col] = button;
                gridPane.add(button, col, row);
            }
        }
    }
@FXML
private void handleButtonClick(ActionEvent event) {
    Button clickedButton = (Button) event.getSource();
    int row = GridPane.getRowIndex(clickedButton);
    int col = GridPane.getColumnIndex(clickedButton);

    // Validar que la columna sea un valor válido
    if (col < 0 || col >= 3) {
        System.out.println("Invalid row or column");
        return;
    }

    if (!currentGameState.isGameOver() && currentGameState.makeMove(row, col, "X")) {
        updateUI();

        if (!currentGameState.isGameOver()) {
            AdversarialSearchTicTacToe adversarialSearch = new AdversarialSearchTicTacToe();
            GameState computerMoveState = adversarialSearch.minMaxDecision(currentGameState);

            currentGameState.copyState(computerMoveState); // Actualizar el estado actual

            updateUI();
        }
    }
}

// Método para obtener el índice del estado
private int getIndexFromState(GameState state) {
    String[][] currentState = currentGameState.getState();
    String[][] newState = state.getState();

    for (int row = 0; row < 3; row++) {
        for (int col = 0; col < 3; col++) {
            if (currentState[row][col] == null && newState[row][col] != null) {
                return row * 3 + col;
            }
        }
    }

    throw new IllegalStateException("Fuera de Rango");
}


    private void updateUI() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText(currentGameState.getState()[row][col]);
                buttons[row][col].setDisable(currentGameState.getState()[row][col] != null);
            }
        }
    }
}
