import java.util.Random;

/**
 * The type Hair controller.
 */
public class HairController
{
    private World world;
    private int headX;
    private int headY;
    private int headWidth;
    private int headHeight;
    private Hair[] hair;
    
    /**
     * Instantiates a new Hair controller.
     *
     * @param world        the world
     * @param headX        the head x coordinate
     * @param headY        the head y coordinate
     * @param headWidth    the head width
     * @param headHeight   the head height
     * @param numberOfHair the number of hair
     */
    public HairController(World world, int headX, int headY, int headWidth, int headHeight, int numberOfHair) {
        this.world = world;
        this.headX = headX;
        this.headY = headY;
        this.headWidth = headWidth;
        this.headHeight = headHeight;

        makeHair(numberOfHair);
    }
    
    /**
     * Instantiates a new Hair controller.
     *
     * @param world      the world
     * @param headX      the head x coordinate
     * @param headY      the head y coordinate
     * @param headWidth  the head width
     * @param headHeight the head height
     * @param hair       the hair
     */
    public HairController(World world, int headX, int headY, int headWidth, int headHeight, Hair[] hair) {
        this.world = world;
        this.headX = headX;
        this.headY = headY;
        this.headWidth = headWidth;
        this.headHeight = headHeight;
        this.hair = hair;
    }
    
    /**
     * Make hair.
     *
     * @param numberOfHair the number of hair
     * @return the hair
     */
    public Hair[] makeHair(int numberOfHair) {
        this.hair = new Hair[numberOfHair];
        for( int i = 0; i < hair.length; i++ )
        {
            double[] randomCoordinates = randomCoordinates();
            this.hair[i] = new Hair(world, randomCoordinates[0], randomCoordinates[1], 40 );
        }

        return hair;
    }
    
    /**
     * Get random coordinates
     *
     * @return the random coordinates
     */
    private double[] randomCoordinates()
    {
        double theta = getRandomNumber( 0, Math.PI * 2 );
        double r = getRandomNumber( 0d, 1d );

        double x = headWidth * Math.sqrt( r ) * Math.cos( theta ) / 2;
        double y = ( headHeight * Math.sqrt( r ) * Math.sin( theta ) / 2 ) - 10;

        return new double[]{ headX + ( headWidth / 2.0 ) * 0.95 + x, headY + ( headHeight / 2.0 ) * 0.95 + y };
    }
    
    /**
     * Gets random number.
     *
     * @param min the minimum
     * @param max the maximum
     * @return the random number
     */
    public double getRandomNumber( double min, double max )
    {
        return min + ( max - min ) * new Random().nextDouble();
    }
    
    /**
     * Gets world.
     *
     * @return the world
     */
    public World getWorld() {
        return world;
    }
    
    /**
     * Sets world.
     *
     * @param world the world
     */
    public void setWorld(World world) {
        this.world = world;
    }
    
    /**
     * Gets head x coordinate.
     *
     * @return the head x coordinate
     */
    public int getHeadX() {
        return headX;
    }
    
    /**
     * Sets head x coordinate.
     *
     * @param headX the head x coordinate
     */
    public void setHeadX(int headX) {
        this.headX = headX;
    }
    
    /**
     * Gets head y coordinate.
     *
     * @return the head y coordinate
     */
    public int getHeadY() {
        return headY;
    }
    
    /**
     * Sets head y coordinate.
     *
     * @param headY the head y coordinate
     */
    public void setHeadY(int headY) {
        this.headY = headY;
    }
    
    /**
     * Gets head width.
     *
     * @return the head width
     */
    public int getHeadWidth() {
        return headWidth;
    }
    
    /**
     * Sets head width.
     *
     * @param headWidth the head width
     */
    public void setHeadWidth(int headWidth) {
        this.headWidth = headWidth;
    }
    
    /**
     * Gets head height.
     *
     * @return the head height
     */
    public int getHeadHeight() {
        return headHeight;
    }
    
    /**
     * Sets head height.
     *
     * @param headHeight the head height
     */
    public void setHeadHeight(int headHeight) {
        this.headHeight = headHeight;
    }
    
    /**
     * Get hair.
     *
     * @return the hair
     */
    public Hair[] getHair() {
        return hair;
    }
    
    /**
     * Sets hair.
     *
     * @param hair the hair
     */
    public void setHair(Hair[] hair) {
        this.hair = hair;
    }
}