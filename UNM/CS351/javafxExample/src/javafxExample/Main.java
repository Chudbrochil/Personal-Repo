package javafxExample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafxExample.animations.*;


public class Main extends Application implements EventHandler<ActionEvent>
{
  private Text labelDescription = new Text("A JavaFX Text widget can be used for multi-line"+
  "messages. JavaFX can word-wrap at word boundaries.");

  private Label labelInput = new Label("Enter Message:");

  private Label labelErrorMsg = new Label();
  private Label labelErrorKey = new Label();
  private TextField textMsg = new TextField();

  private boolean msgGood = false;

  private Button buttonDraw1, buttonDraw2;

  private static final int MAX_MSG_LENGTH = 150;
  private static final int MIN_WORD_COUNT = 3;
  private static final int FONT_SIZE = 28;

  private static final int WINDOW_WIDTH = 800;
  private static final int WINDOW_HEIGHT = 500;


  private Canvas canvas;

  private Animation myAnimation;

  @Override
  public void start(Stage stage) throws Exception
  {
    buttonDraw1 = new Button("Draw 1");
    buttonDraw1.setOnAction(this);
    buttonDraw1.setDisable(true);

    buttonDraw2 = new Button("Draw 2");
    buttonDraw2.setOnAction(this);
    buttonDraw2.setDisable(true);

    textMsg.textProperty().addListener((observable) -> update());


    labelDescription.setFont(Font.font("Verdana", FONT_SIZE));
    labelInput.setFont(Font.font("Verdana", FONT_SIZE));

    labelDescription.setWrappingWidth(WINDOW_WIDTH - 20);

    labelErrorMsg.setFont(Font.font("Verdana", FONT_SIZE));
    labelErrorKey.setFont(Font.font("Verdana", FONT_SIZE));
    textMsg.setFont(Font.font("Verdana", FONT_SIZE));

    labelErrorMsg.setTextFill(Color.RED);
    labelErrorKey.setTextFill(Color.RED);


    Tooltip msgToolTip = new Tooltip(
      "Must have at least " + MIN_WORD_COUNT + " words.\n" +
      "Must be at least one full sentence.\n" +
      "Must be under " + MAX_MSG_LENGTH +" characters."
    );
    msgToolTip.setStyle("-fx-background-radius: 7 7 7 7;");
    Tooltip.install(textMsg, msgToolTip);

    canvas = new Canvas(Animation.DRAW_WIDTH, Animation.DRAW_HEIGHT);


    VBox vBox = new VBox();
    vBox.setPadding(new Insets(10, 10, 10, 10)); //margins (top/right/bottom/left) Default = (0,0,0,0).
    vBox.setSpacing(15); //Spacing in pixels between elements. Default = 0.

    HBox hBoxForMsg = new HBox();
    hBoxForMsg.setSpacing(15); //Spacing in pixels between elements.  Default = 0.
    hBoxForMsg.getChildren().addAll(labelInput, textMsg);

    HBox hBoxButtons = new HBox();
    hBoxButtons.setSpacing(15); //Spacing in pixels between elements.  Default = 0.
    hBoxButtons.getChildren().addAll(buttonDraw1, buttonDraw2);


    vBox.getChildren().addAll(labelDescription, hBoxForMsg, labelErrorMsg, labelErrorKey,
      hBoxButtons, canvas);

    Scene scene = new Scene(vBox, WINDOW_WIDTH, WINDOW_HEIGHT);

    stage.setScene(scene);
    stage.show();
  }

  private void draw1()
  {
    if(myAnimation != null) {
      myAnimation.stop();
    }
    myAnimation = new BouncingSetsOfLines(canvas);
    myAnimation.start();
  }

  private void draw2()
  {
    if(myAnimation != null) {
      myAnimation.stop();
    }
    myAnimation = new RandomWalk(canvas);
    myAnimation.start();
  }


  //============================================================================================
  // Called by JavaFX when the user clicks a button.
  //============================================================================================
  @Override
  public void handle(ActionEvent event)
  {
    Object source = event.getSource();

    if (source == buttonDraw1) draw1();
    else if (source == buttonDraw2) draw2();
  }

  private boolean updateTextField(String errStr, TextField field)
  {
    if (errStr.length() == 0)
    {
      field.setStyle("-fx-text-inner-color: black;");
      return true;
    }
    else
    {
      field.setStyle("-fx-text-inner-color: red;");
      return false;
    }
  }



  private void update()
  {
    String msgResult = checkTextMsg();
    labelErrorMsg.setText(msgResult);
    msgGood = updateTextField(msgResult, textMsg);



    if (msgGood)
    {
      buttonDraw1.setDisable(false);
      buttonDraw2.setDisable(false);
    }
    else
    {
      buttonDraw1.setDisable(true);
      buttonDraw2.setDisable(true);
    }
  }

  private String checkTextMsg()
  {
    String originalMessage = textMsg.getText();
    boolean inWord = false;
    int wordCount = 0;
    if (originalMessage.length() != 0) //sries of if checks to make sure the message meets proper criteria
    {
      if (originalMessage.length() <= MAX_MSG_LENGTH)
      {
        char end = originalMessage.charAt(originalMessage.length() - 1);
        if (end == '.' || end == '?' || end == '!')
        {
          for (int i = 0; i < originalMessage.length(); i++)
          {
            char c = originalMessage.charAt(i);
            if (!inWord && Character.isLetter(c))
            {
              inWord = true;
              wordCount++;
            }
            if (inWord && Character.isSpaceChar(c))
            {
              inWord = false;
            }
            if (!Character.isLetter(c))
            {
              if (c == '!' || c == ',' || c == '.' || c == '?' || c == '-' || c == ':' || c == ';'
                || c == '"' || c == 39 || c == 32) { ; }
              else
              {
                return "invalid character";
              }
            }
          } //end of for loop through originalMessage
          if (wordCount < MIN_WORD_COUNT) return "not enough words";
          else return "";
        }
        return "invalid ending punctuation";
      }
      return "to many characters " + originalMessage.length();
    }
    return "empty string";
  }




  public static void main(String[] args)
  {
    launch(args);
  }
}
