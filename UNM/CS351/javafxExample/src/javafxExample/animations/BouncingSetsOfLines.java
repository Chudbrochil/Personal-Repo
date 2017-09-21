package javafxExample.animations;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class BouncingSetsOfLines extends Animation
{
  private static final int LINE_COUNT = 50;
  private static final double MAX_SPEED = 10;
  private Canvas canvas;
  private GraphicsContext gtx;

  private LineSet line1, line2, line3;

  private Random random = new Random();
  private int currentLine = 0;


  //========================================================================================
  //  Constructor of BouncingSetsOfLines
  //========================================================================================
  public BouncingSetsOfLines(Canvas canvas)
  {
    line1 = new LineSet(Color.BLUE);
    line2 = new LineSet(Color.RED);
    line3 = new LineSet(Color.GREEN);


    this.canvas = canvas;
    gtx = canvas.getGraphicsContext2D();

    gtx.setLineWidth(3);

    gtx.setFill(Color.WHITE);
    gtx.fillRect(0, 0, DRAW_WIDTH, DRAW_HEIGHT);
  }




  //========================================================================================
  // handle(long now) Method.
  // This is a callback method called by JavaFX each time the AnimationTimer fires.
  // Notice now simple this method has become in this design where each set of lines
  // knows how to update and draw itself.
  //========================================================================================
  public void handle(long now)
  {
    gtx.setFill(Color.WHITE);
    gtx.fillRect(0, 0, DRAW_WIDTH, DRAW_HEIGHT);

    int lastLine = currentLine;
    currentLine = (currentLine + 1) % LINE_COUNT;

    line1.update(currentLine, lastLine);
    line2.update(currentLine, lastLine);
    line3.update(currentLine, lastLine);
  }





  //===================================================================================
  // LineSet is an INNER CLASS of BouncingLines.
  // Since LineSet is an inner class, it has access to all the private fields of its
  //    parent class. In particular, it makes use of LINE_COUNT, random and gtx.
  //
  // However, the parent class, BouncingLines, cannot access the private fields within
  //   this class.
  //
  // For encapsulation, this class has a public update method which is responsible for
  //   calculating and saving the next line and for rendering all the lines in the set.
  //===================================================================================
  class LineSet
  {
    private static final int ENDPOINT_COUNT = 2;

    private Point[][] point = new Point[ENDPOINT_COUNT][BouncingSetsOfLines.LINE_COUNT];

    private double[] speedX = new double[ENDPOINT_COUNT];
    private double[] speedY = new double[ENDPOINT_COUNT];
    private Color lineColor;


    //===============================================================================
    // Constructor of the LineSet inner class.
    //
    // This constructor reserves memory for a set of lines and the x and y speed of
    //    the newest line in the set.
    // This constructor also sets the starting values of the line and the speed of
    //    its endpoints.
    //===============================================================================
    private LineSet(Color lineColor)
    {
      this.lineColor = lineColor;
      for (int endPoint = 0; endPoint < ENDPOINT_COUNT; endPoint++)
      {
        for (int i = 0; i < LINE_COUNT; i++)
        {
          point[endPoint][i] = new Point();
        }

        point[endPoint][0].x = random.nextDouble() * DRAW_WIDTH;
        point[endPoint][0].y = random.nextDouble() * DRAW_HEIGHT;


        speedX[endPoint] = random.nextDouble() * MAX_SPEED * 2 - MAX_SPEED;
        speedY[endPoint] = random.nextDouble() * MAX_SPEED * 2 - MAX_SPEED;
      }
    }



    //===================================================================================
    // update(int currentLine, int lastLine, GraphicsContext gtx) is a method inside the
    //     Line class which is inside the BouncingLine class.
    //
    // This method calculates the location of the new line and saves that location
    //     in the memory which holds the oldest line.
    //
    // This method also checks each endpoint of the newest line. If an endpoint is
    //    outside of the canvas boundary, then the speed of that endpoint is given a
    //    random value that is away from the boundary it hit.
    //
    // Finally, this method draws all the lines in its set.
    //
    //===================================================================================
    public void update(int currentLine, int lastLine)
    {
      for (int endPoint = 0; endPoint < ENDPOINT_COUNT; endPoint++)
      {
        point[endPoint][currentLine].x = point[endPoint][lastLine].x + speedX[endPoint];
        point[endPoint][currentLine].y = point[endPoint][lastLine].y + speedY[endPoint];
        checkBoundary(endPoint, currentLine);
      }

      gtx.setStroke(lineColor);
      for (int i = 0; i < LINE_COUNT; i++)
      {
        Point p1 = point[0][i];
        Point p2 = point[1][i];
        gtx.strokeLine(p1.x, p1.y, p2.x, p2.y);
      }
    }




    //===================================================================================
    // checkBoundary(int endPoint, int currentLine) is a method inside the Line class
    //     which is inside the BouncingLine class.
    //
    // This method checks the boundary of one endpoint of the currentLine in this set
    //     of lines.
    //
    // If the endpoint is outside the canvas boundary, then the speed of that endpoint is
    //    given a random value that is away from the boundary it hit.
    //
    //===================================================================================
    private void checkBoundary(int endPoint, int currentLine)
    {
      Point p = point[endPoint][currentLine];
      if (p.x > DRAW_WIDTH)
      {
        speedX[endPoint] = -random.nextDouble() * MAX_SPEED;
      }
      else if (p.x < 0)
      {
        speedX[endPoint] = random.nextDouble() * MAX_SPEED;
      }

      if (p.y > DRAW_HEIGHT)
      {
        speedY[endPoint] = -random.nextDouble() * MAX_SPEED;
      }
      else if (p.y < 0)
      {
        speedY[endPoint] = random.nextDouble() * MAX_SPEED;
      }
    }
  }

  //===================================================================================
  //   Point() is a simple data class with public fields and no methods.
  //===================================================================================
  class Point
  {
    public double x, y;

  }
}
