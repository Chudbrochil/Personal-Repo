package javafxExample.animations;

import javafx.animation.AnimationTimer;

abstract public class Animation extends AnimationTimer
{
  public static final int DRAW_WIDTH = 800;
  public static final int DRAW_HEIGHT = 800;
  abstract public void handle(long now);

}
