import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Random;

public class Hair {
    private World world;
    private double anchorX;     // the anchor/initial position x
    private double anchorY;     // the anchor/initial position y
    private Color hairColor;    // the color of the hair
    Segment[] segments;
    int numOfSegments;

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
    
    public void setSegments() {
        for(int i = 0; i < segments.length; i++) {
            segments[i] = new Segment(world,
                    (i == 0) ? anchorX : segments[i-1].getPositionX(),
                    (i == 0) ? anchorY : segments[i-1].getPositionY());
        }
    }
    
    public void updateSegments() {
        for (int i = 0; i < segments.length; i++) {
            segments[i].update((i == 0) ? null : segments[i-1],
                    (i == 0) ? segments[1] : null);
        }
    }
    
    public void drawHair( Graphics2D g2D ) {
        for (int i = 0; i < segments.length; i++) {
            updateSegments();
            Line2D line;
            g2D.setColor( hairColor );

            if( i == 0 )
                line = new Line2D.Double( segments[i].getPositionX() + 10, segments[i].getPositionY(), anchorX + 10, anchorY + 25 );
            else
                line = new Line2D.Double( segments[i].getPositionX() + 10, segments[i].getPositionY(), segments[i - 1].getPositionX() + 10, segments[i - 1].getPositionY() );


            g2D.draw( line );
        }
    }

    public double getRandomNumber( double min, double max )
    {
        return min + ( max - min ) * new Random().nextDouble();
    }
}
