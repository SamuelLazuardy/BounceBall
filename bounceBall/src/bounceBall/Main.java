package bounceBall;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends Application {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final double BALL_RADIUS = 20;
    private static final int NUM_BALLS = 12;

    public int red = 4;
    public int blue = 4;
    public int green = 4;
    List<Ball> balls;
    Label label1;
    Label label2;
    Label label3;
    @Override
    public void start(Stage stage) {
        stage.setTitle("Bouncing Balls");

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        List<Ball> balls = createBalls(NUM_BALLS, WIDTH, HEIGHT, BALL_RADIUS);

        label1 = new Label("Red = " + red);
        label2 = new Label("Blue = " + blue);
        label3 = new Label("Green = " + green);
        
        StackPane pane = new StackPane();
        pane.getChildren().add(canvas);
        pane.getChildren().addAll(label1,label2,label3);
        
        StackPane.setAlignment(label1, Pos.TOP_LEFT);
        StackPane.setAlignment(label2, Pos.TOP_CENTER);
        StackPane.setAlignment(label3, Pos.TOP_RIGHT);
        
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gc.clearRect(0, 0, WIDTH, HEIGHT);
                for (Ball ball : balls) {
                    ball.update();
                    ball.draw(gc);
                }
                checkCollisions(balls);
                countBallColors(balls);
                updateLabels();
                winner();
//                System.out.println(red);
//                System.out.println(blue);
//                System.out.println(green);
            }
        };
        animationTimer.start();
        
        stage.setScene(new Scene(pane, WIDTH, HEIGHT));
        stage.show();
    }

    private List<Ball> createBalls(int numBalls, double width, double height, double radius) {
        List<Ball> balls = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numBalls; i++) {
            double x = random.nextDouble() * (width - 2 * radius) + radius;
            double y = random.nextDouble() * (height - 2 * radius) + radius;
            double dx = random.nextDouble() * 6 - 3; 
            double dy = random.nextDouble() * 6 - 3;
            if(i % 3 == 0) {
                balls.add(new Ball(width, height, radius, x, y, dx, dy, Color.RED));
            }else if(i % 3 == 1){
            	balls.add(new Ball(width, height, radius, x, y, dx, dy, Color.BLUE));
            }else if(i % 3 == 2) {
            	balls.add(new Ball(width, height, radius, x, y, dx, dy, Color.GREEN));
            }
        }
        return balls;
    }
    
    private void countBallColors(List<Ball> balls) {
        red = 0;
        blue = 0;
        green = 0;

        for (Ball ball : balls) {
            Color color = ball.getColor();
            if (color.equals(Color.RED)) {
                red++;
            } else if (color.equals(Color.BLUE)) {
                blue++;
            } else if (color.equals(Color.GREEN)) {
                green++;
            }
        }
    }
    
    private void checkCollisions(List<Ball> balls) {
        for (int i = 0; i < balls.size(); i++) {
            for (int j = i + 1; j < balls.size(); j++) {
                Ball ball1 = balls.get(i);
                Ball ball2 = balls.get(j);
                if (ball1.intersects(ball2)) {
                    ball1.handleCollision(ball2);
                }
            }
        }
    }
    Label win = new Label();
    private void winner() {
    	if(red == 12) {
    		win.setText("RED WIN!!!");
    		showWinner();
    	}else if(blue == 12){
    		win.setText("BLUE WIN!!!");
    		showWinner();
    	}else if(green == 12) {
    		win.setText("GREEN WIN!!!");
    		showWinner();
    	}
    }
    int k = 0;
    private void showWinner() {
    	if(k == 0) {
        	Stage stage2 = new Stage();
        	StackPane sp = new StackPane(win);
        	
        	Scene scene = new Scene(sp,400,400);
        	stage2.setScene(scene);
        	stage2.show();
        	k++;
    	}

    }
    

    
    private void updateLabels() {
        Platform.runLater(() -> {
            label1.setText("Red = " + red);
            label2.setText("Blue = " + blue);
            label3.setText("Green = " + green);
        });
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}






