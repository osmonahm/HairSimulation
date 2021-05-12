import java.util.Random;

public class HairController
{
    private World world;
    private int headX;
    private int headY;
    private int headWidth;
    private int headHeight;
    private Hair[] hair;

    public HairController(World world, int headX, int headY, int headWidth, int headHeight, int numberOfHair) {
        this.world = world;
        this.headX = headX;
        this.headY = headY;
        this.headWidth = headWidth;
        this.headHeight = headHeight;

        makeHair(numberOfHair);
    }

    public HairController(World world, int headX, int headY, int headWidth, int headHeight, Hair[] hair) {
        this.world = world;
        this.headX = headX;
        this.headY = headY;
        this.headWidth = headWidth;
        this.headHeight = headHeight;
        this.hair = hair;
    }

    public Hair[] makeHair(int numberOfHair) {
        // initializing hair objects with random anchors
        this.hair = new Hair[numberOfHair];
        for( int i = 0; i < hair.length; i++ )
        {
            double[] randomCoordinates = randomCoordinates();
            this.hair[i] = new Hair(world, randomCoordinates[0], randomCoordinates[1], 7 );
        }

        return hair;
    }

    private double[] randomCoordinates()
    {
        double theta = getRandomNumber( 0, Math.PI * 2 );
        double r = getRandomNumber( 0d, 1d );

        double x = headWidth * Math.sqrt( r ) * Math.cos( theta ) / 2;
        double y = ( headHeight * Math.sqrt( r ) * Math.sin( theta ) / 2 ) - 10;

        return new double[]{ headX + ( headWidth / 2.0 ) * 0.95 + x, headY + ( headHeight / 2.0 ) * 0.95 + y };
    }
        
    public double getRandomNumber( double min, double max )
    {
        return min + ( max - min ) * new Random().nextDouble();
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public int getHeadX() {
        return headX;
    }

    public void setHeadX(int headX) {
        this.headX = headX;
    }

    public int getHeadY() {
        return headY;
    }

    public void setHeadY(int headY) {
        this.headY = headY;
    }

    public int getHeadWidth() {
        return headWidth;
    }

    public void setHeadWidth(int headWidth) {
        this.headWidth = headWidth;
    }

    public int getHeadHeight() {
        return headHeight;
    }

    public void setHeadHeight(int headHeight) {
        this.headHeight = headHeight;
    }

    public Hair[] getHair() {
        return hair;
    }

    public void setHair(Hair[] hair) {
        this.hair = hair;
    }
}