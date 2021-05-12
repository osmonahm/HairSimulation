public class Segment
{
    private double positionX;   // spring position x
    private double positionY;   // spring position y
    private double velocityX;   // spring velocity x
    private double velocityY;   // spring velocity y
    
    private double gravity;     // the gravitational force
    private double mass;        // the mass of the spring
    private double timeStep;    // pixels to skip
    private double damping;     // the damping coefficient
    private int k;              // the spring constant - stiffness
    private double anchorX;     // the anchor/initial position x
    private double anchorY;     // the anchor/initial position y
    
    private double displacementX, displacementY;    // Displacement Force
    private double springForceX, springForceY;      // Spring Force
    private double dampingForceX, dampingForceY;    // Damping Force
    private double forceX, forceY;                  // The net Force
    private double accelerationX, accelerationY;    // Acceleration
    
    public Segment( double G, double M, double ts, double d, int K, double aX, double aY )
    {
        positionX = 150;
        positionY = 200;
        velocityX = 0.0;
        velocityY = 0.0;
        
        gravity = G;
        mass = M;
        timeStep = ts;
        damping = d;
        k = K;
        anchorX = aX;
        anchorY = aY;
    }
    
    public void setDisplacement( Segment segment )
    {
        if( segment == null )
        {
            displacementX = positionX - anchorX;
            displacementY = positionY - anchorY;
        }
        else
        {
            displacementX = positionX - segment.positionX;
            displacementY = positionY - segment.positionY;
        }
    }
    
    public void setSpringForce()
    {
        springForceX = -k * displacementX;
        springForceY = -k * displacementY;
    }
    
    public void setDampingForce()
    {
        dampingForceX = damping * velocityX;
        dampingForceY = damping * velocityY;
    }
    
    public void setForce( Segment segment )
    {
        if( segment == null )
        {
            forceX = springForceX - dampingForceX;
            forceY = springForceY + mass * gravity - dampingForceY;
        }
        else
        {
            forceX = springForceX - dampingForceX - segment.springForceX + segment.dampingForceX;
            forceY = springForceY + mass * gravity - dampingForceY - segment.springForceX + segment.dampingForceX;
        }
    }
    
    public void setAcceleration()
    {
        accelerationX = forceX / mass;
        accelerationY = forceY / mass;
    }
    
    public void setVelocity()
    {
        velocityX += accelerationX * timeStep;
        velocityY += accelerationY * timeStep;
    }
    
    public void setPosition()
    {
        positionX += velocityX * timeStep;
        positionY += velocityY * timeStep;
    }
    
    public double getPositionX()
    {
        return positionX;
    }
    
    public double getPositionY()
    {
        return positionY;
    }
    
    public void update( Segment prevSegment, Segment nextSegment )
    {
        setDisplacement( prevSegment );
        setSpringForce();
        setDampingForce();
        setForce( nextSegment );
        setAcceleration();
        setVelocity();
        setPosition();
    }
}