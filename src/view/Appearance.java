package view;

import logic.GameLogic;
import javax.swing.*;
import java.awt.*;

public class Appearance {

  private static Color rose  = new Color(235,90,91);
  private static Color orange = new Color(249,194,57);
  private static Color turquoise = new Color(111,194,183);

  private static final Color[] COLORS = {turquoise, orange, rose};

  private static int size;
  private static SmartButton buttons[][];
  private static JFrame frame;

  private final int FRAME_HEIGHT = 300;
  private final int FRAME_WIDTH = 300;

  private final int MIN_SIZE = 20;
  private final int MAX_SIZE = 100;


  private Appearance() {

    frame = new JFrame("The color game");
    receiveInputFromUser();
    GameLogic logic = new GameLogic();
    createButtonMatrixWithSize();
    fillButtonMatrix(logic);
    logic.randomizeButtonColors();
    addButtonsToFrame();
    adjustFrame();
    logic.checkIfGameWon();
  }

  public static void main(String[] args) {

    new Appearance();

  }

  private void receiveInputFromUser(){
    String code = JOptionPane.showInputDialog(
            frame,
            "Enter the size of the game you want (number between "+ MIN_SIZE + " and " + MAX_SIZE  + "!)",
            "The color game",
            JOptionPane.WARNING_MESSAGE
    );

    if (code.equals("")) {
      System.exit(0);
    }

    while (Integer.parseInt(code) < MIN_SIZE || Integer.parseInt(code) > MAX_SIZE) {
      code = JOptionPane.showInputDialog(
              frame,
              "Error! Please give a number between "+ MIN_SIZE + " and " + MAX_SIZE  + "!",
              "The color game",
              JOptionPane.WARNING_MESSAGE
      );
    }
    size = Integer.parseInt(code);
  }

  private void createButtonMatrixWithSize() {
    buttons = new SmartButton[size][size];
  }

  private void fillButtonMatrix(GameLogic logic) {
    for (int i = 0; i < buttons.length; i++) {
      for (int j = 0; j < buttons[i].length; j++) {
        buttons[i][j] = new SmartButton(logic);
      }
    }
  }

  private void addButtonsToFrame() {
    for (int i = 0; i < buttons.length; i++) {
      for (int j = 0; j < buttons[i].length; j++) {
        frame.add(buttons[i][j]);
      }
    }
  }

  private void adjustFrame(){
    frame.setLayout(new GridLayout(size,size));
    frame.setSize(FRAME_WIDTH,FRAME_HEIGHT);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

  public static Color[] getColors() {
    return COLORS;
  }

  public static SmartButton[][] getButtons() {
    return buttons;
  }

  public static JFrame getFrame() {
    return frame;
  }

  public static int getSize() {
    return size;
  }
}
