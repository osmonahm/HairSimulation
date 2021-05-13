import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Random;

/**
 * The type Hair.
 */
public class Hair {
    private World world;        // the world
    private double anchorX;     // the anchor position x
    private double anchorY;     // the anchor position y
    private Color hairColor;    // the color of the hair
    private Segment[] segments; // the segments array used to create hair
    private int numOfSegments;  // the number of hair segments
    
    /**
     * Instantiates a new Hair.
     *
     * @param world         the world
     * @param anchorX       the anchor x coordinate
     * @param anchorY       the anchor y coordinate
     * @param numOfSegments the number of segments
     */
    public Hair(World world, double anchorX, double anchorY, int numOfSegments) {
        this.world = world;
        this.anchorX = anchorX;
        this.anchorY = anchorY;
        hairColor = new Color((int) getRandomNumber(180, 200),
                (int) getRandomNumber(80, 120),
                (int) getRandomNumber(0, 20),
                250);

        this.numOfSegments = numOfSegments;
        segments = new Segment[numOfSegments];
        setSegments();
    }
    
    /**
     * Sets segments.
     */
    public void setSegments() {
        for(int i = 0; i < segments.length; i++) {
            segments[i] = new Segment(world,
                    (i == 0) ? anchorX : segments[i-1].getPositionX(),
                    (i == 0) ? anchorY : segments[i-1].getPositionY());
        }
    }
    
    /**
     * Update segments.
     */
    public void updateSegments() {
        for (int i = 0; i < segments.length; i++) {
            segments[i].update((i == 0) ? null : segments[i-1],
                    (i == 0) ? segments[1] : null);
        }
    }
    
    /**
     * Draw hair.
     *
     * @param g2D the drawing graphics component
     */
    public void drawHair( Graphics2D g2D ) {
        for (int i = 0; i < segments.length; i++) {
            updateSegments();
            Line2D line;
            g2D.setColor( hairColor );

            if( i == 0 )
                line = new Line2D.Double( segments[i].getPositionX(),
                        segments[i].getPositionY(),
                        anchorX + 7,
                        anchorY + 15 );
            else
                line = new Line2D.Double( segments[i].getPositionX(),
                        segments[i].getPositionY(),
                        segments[i - 1].getPositionX(),
                        segments[i - 1].getPositionY() );


            g2D.draw( line );
        }
    }
    
    /**
     * Gets a random number.
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
     * Gets the world.
     *
     * @return the world
     */
    public World getWorld() {
        return world;
    }
    
    /**
     * Sets the world.
     *
     * @param world the world
     */
    public void setWorld(World world) {
        this.world = world;
    }
    
    /**
     * Gets anchor x.
     *
     * @return the anchor x
     */
    public double getAnchorX() {
        return anchorX;
    }
    
    /**
     * Sets anchor x.
     *
     * @param anchorX the anchor x
     */
    public void setAnchorX(double anchorX) {
        this.anchorX = anchorX;
    }
    
    /**
     * Gets anchor y.
     *
     * @return the anchor y
     */
    public double getAnchorY() {
        return anchorY;
    }
    
    /**
     * Sets anchor y.
     *
     * @param anchorY the anchor y
     */
    public void setAnchorY(double anchorY) {
        this.anchorY = anchorY;
    }
    
    /**
     * Gets hair color.
     *
     * @return the hair color
     */
    public Color getHairColor() {
        return hairColor;
    }
    
    /**
     * Sets hair color.
     *
     * @param hairColor the hair color
     */
    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }
    
    /**
     * Get segments.
     *
     * @return the segment
     */
    public Segment[] getSegments() {
        return segments;
    }
    
    /**
     * Sets segments.
     *
     * @param segments the segments
     */
    public void setSegments(Segment[] segments) {
        this.segments = segments;
    }
    
    /**
     * Gets the number of segments.
     *
     * @return the number of segments
     */
    public int getNumOfSegments() {
        return numOfSegments;
    }
    
    /**
     * Sets the number of segments.
     *
     * @param numOfSegments the number of segments
     */
    public void setNumOfSegments(int numOfSegments) {
        this.numOfSegments = numOfSegments;
    }
}