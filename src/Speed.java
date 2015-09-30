import java.util.Random;

/**
 * Created by Роман on 26.09.2015.
 */
public class Speed {
    private int minSpeed;
    private int maxSpeed;
    private int currentSpeed;

    public Speed() {
        this(0, 0);
    }

    public Speed(int minSpeed, int maxSpeed) {
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
    }

    public void setMinSpeed(int minSpeed) {
        this.minSpeed = minSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        if (maxSpeed <= minSpeed)
            maxSpeed = minSpeed + 1;
        this.maxSpeed = maxSpeed;
    }

    public int getMinSpeed() {
        return minSpeed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public int moveAndGetSpeed() {
        Random random = new Random();
        currentSpeed = minSpeed + random.nextInt(maxSpeed - minSpeed);
        return getCurrentSpeed();
    }
}
