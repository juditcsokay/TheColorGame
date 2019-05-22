package view;

import logic.GameLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SmartButton extends JButton {

  private static int idCounter = 1;

  private GameLogic logic;
  private Color color;
  private int id;

  SmartButton(GameLogic logic2) {
    logic = logic2;
    addMouseListener(new MouseAdapter() {                   //TODO: fix button functionality on release
      public void mouseClicked(MouseEvent e) {
        logic.changeColor(SmartButton.this);
      }
    });

    id = idCounter;
    idCounter++;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
    setBackground(color);
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (getClass() == o.getClass()) {
      return ((SmartButton)o).getId() == id;
    }
    return false;
  }
}
