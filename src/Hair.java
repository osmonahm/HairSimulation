import java.awt.*;
import java.awt.geom.Line2D;

public class Hair
{
    private double gravity;     // the gravitational force
    private double mass;        // the mass of the spring
    private double timeStep;    // pixels to skip
    private double damping;     // the damping coefficient
    private int k;              // the spring constant - stiffness
    private double anchorX;     // the anchor/initial position x
    private double anchorY;     // the anchor/initial position y
    Segment[] segments;
    int numOfSegments;
    
    public Hair( double G, double M, double ts, double d, int K, double aX, double aY, int segNum )
    {
        gravity = G;
        mass = M;
        timeStep = ts;
        damping = d;
        k = K;
        anchorX = aX;
        anchorY = aY;
        
        numOfSegments = segNum;
        segments = new Segment[numOfSegments];
        setSegments();
    }
    
    public void setSegments()
    {
        for( int i = 0; i < segments.length; i++ )
        {
            if( i == 0 )
                segments[i] = new Segment( gravity, mass, timeStep, damping, k, anchorX, anchorY );
            else
                segments[i] = new Segment( gravity, mass, timeStep, damping, k, segments[i-1].getPositionX(), segments[i-1].getPositionY() );
        }
    }
    
    public void updateSegments()
    {
        for( int i = 0; i < segments.length; i++ )
        {
            if( i == 0 )
                segments[i].update( null, segments[i+1] );
            else
                segments[i].update( segments[i-1], null );
        }
    }
    
    public void drawHair( Graphics2D g2D )
    {
        for( int i = 0; i < segments.length; i++ )
        {
            updateSegments();
            Line2D line;
    
            if( i == 0 )
                line = new Line2D.Double( segments[i].getPositionX() + 10, segments[i].getPositionY(), anchorX + 10, anchorY + 25 );
            else
                line = new Line2D.Double( segments[i].getPositionX() + 10, segments[i].getPositionY(), segments[i-1].getPositionX() + 10, segments[i-1].getPositionY() );
            
            g2D.draw( line );
        }
    }
}