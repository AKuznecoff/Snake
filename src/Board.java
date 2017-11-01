import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

class Board {
    private int height;
    private int width;
    private int foodLimit;
    private int foodCount;
    private Directions direction;
    private SnakeSegment head;
    private LinkedList<SnakeSegment> snake;
    private int snakeLength;
    private HashMap<Point, GameObject> gameObjects;
    private boolean finished = false;
    private boolean paused = true;
    private int scores;

    public Board(int height, int width, int foodLimit) {
        if (height < 10) throw new IllegalArgumentException("Height must more than 10");
        if (width < 10) throw new IllegalArgumentException("Width must be more than 10");
        this.height = height;
        this.width = width;
        this.foodLimit = foodLimit;
        foodCount = 0;
        gameObjects = new HashMap<>();
        snakeLength = 0;
        direction = Directions.RIGHT;
        scores = 0;
        initWalls();
        initSnake();
        placeFood();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getScores() {
        return scores;
    }

    public LinkedList<SnakeSegment> getSnake() {
        return snake;
    }

    public HashMap<Point, GameObject> getGameObjects() {
        return gameObjects;
    }

    public void pause() {
        paused = !paused;
    }

    public boolean checkPause() {
        return paused;
    }

    public void setDirection(Directions dir) {
        if (!direction.getOpposite().equals(dir))
            direction = dir;
    }

    public Directions getDirection() {
        return direction;
    }

    public void addObject(Point position, GameObject object) {
        gameObjects.put(position, object);
    }

    private void initWalls(){
        for(int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
            {
                if (x == 0 || x == width - 1 || y == 0 || y == height - 1) {
                    Point position = new Point(x, y);
                    addObject(position, new Wall(position));
                }
            }
    }

    private void initSnake() {
        snake = new LinkedList<>();
        int y = height / 2;
        for (int x = width / 2 - 2; x < width / 2 + 2; x++) {
            snakeLength++;
            SnakeSegment snakeSegment = new SnakeSegment(new Point(x, y));
            if (snakeLength == 4)
                head = snakeSegment;
            snake.add(snakeSegment);
        }
    }

    private boolean checkCollision(Point point) {
        return snake.stream().anyMatch(s -> s.getPosition().equals(point));
    }

    public boolean checkFinished() {
        return finished;
    }

    private void placeFood() {
        while (foodCount < foodLimit) {
            Random rnd = new Random();
            Point position;
            do {
                position = new Point(rnd.nextInt(width), rnd.nextInt(height));
            } while (checkCollision(position) || gameObjects.containsKey(position));
            addObject(position, new Food(position));
            foodCount += 1;
        }
    }

    public void nextStep() {
        if (!finished && !paused)
            makeMove(direction.getShift());
    }

    private void makeMove(Shift shift) {
        Point headPosition = head.getPosition();
        Point newPosition = headPosition.shift(shift);
        finished = checkCollision(newPosition) ||
                gameObjects.values().stream().anyMatch(o -> o.getPosition().equals(newPosition) && !o.checkEdible());
        if (!finished) {
            head = new SnakeSegment(newPosition);
            snake.add(head);
            if (gameObjects.containsKey(newPosition) && gameObjects.get(newPosition).checkEdible()) {
                scores += ((Food)gameObjects.remove(newPosition)).getScores();
                foodCount --;
                placeFood();
                return;
            }
            snake.poll();
        }
    }
}
