public class Segment {
    private World world;

    private double positionX;   // spring position x
    private double positionY;   // spring position y
    private double velocityX;   // spring velocity x
    private double velocityY;   // spring velocity y

    private double anchorX;     // the anchor/initial position x
    private double anchorY;     // the anchor/initial position y
    
    private double displacementX, displacementY;    // Displacement Force
    private double springForceX, springForceY;      // Spring Force
    private double dampingForceX, dampingForceY;    // Damping Force
    private double forceX, forceY;                  // The net Force
    private double accelerationX, accelerationY;    // Acceleration
    
    public Segment(World world, double anchorX, double anchorY) {
        positionX = 150;
        positionY = 200;
        velocityX = 0.0;
        velocityY = 0.0;
        
        this.world = world;
        this.anchorX = anchorX;
        this.anchorY = anchorY;
    }
    
    public void setDisplacement(Segment segment) {
        if(segment == null) {
            displacementX = positionX - anchorX;
            displacementY = positionY - anchorY;
        } else {
            displacementX = positionX - segment.positionX;
            displacementY = positionY - segment.positionY;
        }
    }
    
    public void setSpringForce() {
        springForceX = -world.getK() * displacementX;
        springForceY = -world.getK() * displacementY;
    }
    
    public void setDampingForce() {
        dampingForceX = world.getDamping() * velocityX;
        dampingForceY = world.getDamping() * velocityY;
    }
    
    public void setForce(Segment segment) {
        if(segment == null) {
            forceX = springForceX - dampingForceX;
            forceY = springForceY + world.getMass() * world.getGravity() - dampingForceY;
        } else {
            forceX = springForceX - dampingForceX - segment.springForceX + segment.dampingForceX;
            forceY = springForceY + world.getMass() * world.getGravity() - dampingForceY - segment.springForceX + segment.dampingForceX;
        }
    }
    
    public void setAcceleration() {
        accelerationX = forceX / world.getMass();
        accelerationY = forceY / world.getMass();
    }
    
    public void setVelocity() {
        velocityX += accelerationX * world.getTimeStep();
        velocityY += accelerationY * world.getTimeStep();
    }
    
    public void setPosition() {
        positionX += velocityX * world.getTimeStep();
        positionY += velocityY * world.getTimeStep();
    }
    
    public double getPositionX()
    {
        return positionX;
    }
    
    public double getPositionY()
    {
        return positionY;
    }
    
    public void update(Segment prevSegment, Segment nextSegment) {
        setDisplacement(prevSegment);
        setSpringForce();
        setDampingForce();
        setForce(nextSegment);
        setAcceleration();
        setVelocity();
        setPosition();
    }
}