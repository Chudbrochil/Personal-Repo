package javafxExample.animations;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

import java.util.Random;

public class RandomWalk extends Animation
{
  private Canvas canvas;
  private GraphicsContext gtx;
  private Point point;
  private int delta;
  private int direction;
  private static final int[] dx = {1, 0, -1, 0};
  private static final int[] dy = {0, 1, 0, -1};
  private int step;
  private static final int size = 25;
  private Random random = new Random();
  private int centerX, centerY;
  private int limit = 400;

  private int paletteIdx = 0;


  private static final Color background = Color.rgb(255, 200, 200);
  private PixelWriter pixelWriter;

  public RandomWalk(Canvas canvas)
  {
    this.canvas = canvas;
    gtx = canvas.getGraphicsContext2D();

    step = 0;
    delta = 5;
    direction = 0;

    gtx.setLineWidth(3);

    gtx.setFill(background);
    gtx.fillRect(0, 0, DRAW_WIDTH, DRAW_HEIGHT);

    pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();

    centerX = DRAW_WIDTH / 2;
    centerY = DRAW_HEIGHT / 2;
    point = new Point(centerX,centerY);


    gtx.setStroke(Color.RED);
    gtx.strokeOval(centerX - limit, centerY - limit, limit * 2, limit * 2);
    gtx.setStroke(Color.BLUE);
  }


  public void handle(long now)
  {

    for (int i = 0; i < 30; i++)
    {
      step++;

      //Uncomment if you want to erase the point each update
      //pixelWriter.setColor(point.x, point.y,background);


      int dx = centerX - point.x;
      int dy = centerY - point.y;

      if (Math.sqrt(dx * dx + dy * dy) >= limit)
      {
        System.out.println(step);
        stop();
        break;
      }
      double r = random.nextDouble();
      if (r < 0.25) { point.y -= 1; }
      else if (r < 0.5) { point.y += 1; }
      else if (r < 0.75) { point.x += 1; }
      else { point.x -= 1;}

      pixelWriter.setColor(point.x, point.y,Color.BLUE);
    }
  }



  //===================================================================================
  //   Point() is a simple data class with public fields and no methods.
  //===================================================================================
  class Point
  {
    public int x, y;

    public Point(int x, int y)
    {
      this.x = x;
      this.y = y;
    }
  }

}
