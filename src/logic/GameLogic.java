package logic;

import view.SmartButton;
import static view.Appearance.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameLogic {


  public void changeColor(SmartButton b){
    changeButtonColor(b);
    changeNeighborsColor(b);
    checkIfGameWon();
  }

  private void showGameWonDialog() {
    final int NEW_GAME = 0;
    final int EXIT_GAME = 1;

    Object[] options = {"New Game!", "Close the game"};
    int selectedOption = JOptionPane.showOptionDialog(null, "Congratulations! You win the game! :)", "Congratulations!",

            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,

            null, options, options[0]);

    if (selectedOption == NEW_GAME) {
      randomizeButtonColors();
      checkIfGameWon();
    } else if (selectedOption == EXIT_GAME) {
      getFrame().dispatchEvent(new WindowEvent(getFrame(), WindowEvent.WINDOW_CLOSING));
    }
  }

  private int getColorIndexByButtonColor(SmartButton b) {
    int colorIndex = 0;
    for (int i = 0; i < getColors().length; i++) {
      if (b.getColor() == getColors()[i]) {
        colorIndex = i;
      }
    }
    return colorIndex;
  }

  private void changeButtonColor(SmartButton b) {
    int colorIndex = getColorIndexByButtonColor(b);
    if (colorIndex == 2) {
      b.setColor(getColors()[0]);
    } else {
      b.setColor(getColors()[++colorIndex]);
    }
  }

  private List<SmartButton> getNeighborsListOfButton(SmartButton b) {
    SmartButton[][] buttons = getButtons();
    List<SmartButton> neighbors = new ArrayList<>();

    for (int i = 0; i < buttons.length; i++) {
      for (int j = 0; j < buttons[0].length; j++) {
        if (b.equals(buttons[i][j])) {
          if (i > 0) {
            SmartButton upperNeighbor = buttons[i - 1][j];
            neighbors.add(upperNeighbor);
          }
          if (i < getSize() - 1) {
            SmartButton lowerNeighbor = buttons[i + 1][j];
            neighbors.add(lowerNeighbor);
          }
          if (j > 0) {
            SmartButton leftNeighbor = buttons[i][j - 1];
            neighbors.add(leftNeighbor);
          }
          if (j < getSize() - 1) {
            SmartButton rightNeighbor = buttons[i][j + 1];
            neighbors.add(rightNeighbor);
          }
        }
      }
    }
    return neighbors;
  }

  private void changeNeighborsColor(SmartButton b) {
    List<SmartButton> neighbors = getNeighborsListOfButton(b);
    for (SmartButton neighbor : neighbors) {
      changeButtonColor(neighbor);
    }
  }

  public void checkIfGameWon() {
    SmartButton[][] buttons = getButtons();
    Color firstColor = buttons[0][0].getColor();

    for (int i = 0; i < buttons.length; i++) {
      for (int j = 0; j < buttons[i].length; j++) {
        if (!firstColor.equals(buttons[i][j].getColor())) {
          return;
        }
      }
    }
    showGameWonDialog();
  }

  public void randomizeButtonColors() {
    SmartButton[][] buttons = getButtons();
    Random rand = new Random();
    Color color;

    for (int i = 0; i < buttons.length; i++) {
      for (int j = 0; j < buttons[i].length; j++) {
        int randomColorIndex = rand.nextInt(getColors().length);
        color = getColors()[randomColorIndex];
        buttons[i][j].setColor(color);
      }
    }
  }
}
