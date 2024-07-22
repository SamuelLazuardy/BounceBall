package bounceBall;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class Ball {
    private double x;
    private double y;
    private double dx;
    private double dy;
    private double radius;
    private double width;
    private double height;
    private Color color;

    public Ball(double width, double height, double radius, double x, double y, double dx, double dy, Color color) {
        this.width = width;
        this.height = height;
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.color = color;
    }
    
    
    
    public double getX() {
		return x;
	}



	public void setX(double x) {
		this.x = x;
	}



	public double getY() {
		return y;
	}



	public void setY(double y) {
		this.y = y;
	}



	public double getDx() {
		return dx;
	}



	public void setDx(double dx) {
		this.dx = dx;
	}



	public double getDy() {
		return dy;
	}



	public void setDy(double dy) {
		this.dy = dy;
	}



	public double getRadius() {
		return radius;
	}



	public void setRadius(double radius) {
		this.radius = radius;
	}



	public double getWidth() {
		return width;
	}



	public void setWidth(double width) {
		this.width = width;
	}



	public double getHeight() {
		return height;
	}



	public void setHeight(double height) {
		this.height = height;
	}



	public Color getColor() {
		return color;
	}



	public void setColor(Color color) {
		this.color = color;
	}



	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public void update() {
        if (x + dx < radius || x + dx > width - radius) {
            dx = -dx;
        }
        if (y + dy < radius || y + dy > height - radius) {
            dy = -dy;
        }
        x += dx;
        y += dy;
    }
 
    public boolean intersects(Ball other) {
        double distance = Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
        return distance < this.radius + other.radius; 
    }
    Main main = new Main();
    public void handleCollision(Ball other) {
       if(this.color.equals(Color.RED) && other.color.equals(Color.GREEN)) {
    	   this.color = Color.GREEN;
    	   main.red--;
    	   main.green++;
       }else if(this.color.equals(Color.GREEN) && other.color.equals(Color.BLUE)) {
    	   this.color = Color.BLUE;
    	   main.blue++;
    	   main.green--;
       }else if(this.color.equals(Color.BLUE) && other.color.equals(Color.RED)) {
    	   this.color = Color.RED;
    	   main.red++;
    	   main.blue--; 	
       }else if(other.color.equals(Color.RED) && this.color.equals(Color.GREEN)) {
    	   other.color = Color.GREEN;
    	   main.red--;
    	   main.green++;

       }else if(other.color.equals(Color.GREEN) && this.color.equals(Color.BLUE)) {
    	   other.color = Color.BLUE;
    	   main.blue++;
    	   main.green--;

       }else if(other.color.equals(Color.BLUE) && this.color.equals(Color.RED)) {
    	   other.color = Color.RED;
    	   main.red++;
    	   main.blue--;

       }
    }
    
	public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
    }
}