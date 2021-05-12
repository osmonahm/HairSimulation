/**
 * The type Segment.
 */
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
    
    /**
     * Instantiates a new Segment.
     *
     * @param world   the world
     * @param anchorX the anchor x coordinate
     * @param anchorY the anchor y coordinate
     */
    public Segment(World world, double anchorX, double anchorY) {
        positionX = 150;
        positionY = 200;
        velocityX = 0.0;
        velocityY = 0.0;
        
        this.world = world;
        this.anchorX = anchorX;
        this.anchorY = anchorY;
    }
    
    /**
     * Sets displacement.
     *
     * @param segment the segment
     */
    public void setDisplacement(Segment segment) {
        if(segment == null) {
            displacementX = positionX - anchorX;
            displacementY = positionY - anchorY;
        } else {
            displacementX = positionX - segment.positionX;
            displacementY = positionY - segment.positionY;
        }
    }
    
    /**
     * Sets spring force.
     */
    public void setSpringForce() {
        springForceX = -world.getK() * displacementX;
        springForceY = -world.getK() * displacementY;
    }
    
    /**
     * Sets damping force.
     */
    public void setDampingForce() {
        dampingForceX = world.getDamping() * velocityX;
        dampingForceY = world.getDamping() * velocityY;
    }
    
    /**
     * Sets net force.
     *
     * @param segment the segment
     */
    public void setForce(Segment segment) {
        if(segment == null) {
            forceX = springForceX - dampingForceX;
            forceY = springForceY + world.getMass() * world.getGravity() - dampingForceY;
        } else {
            forceX = springForceX - dampingForceX - segment.springForceX + segment.dampingForceX;
            forceY = springForceY + world.getMass() * world.getGravity() - dampingForceY - segment.springForceX + segment.dampingForceX;
        }
    }
    
    /**
     * Sets acceleration.
     */
    public void setAcceleration() {
        accelerationX = forceX / world.getMass();
        accelerationY = forceY / world.getMass();
    }
    
    /**
     * Sets velocity.
     */
    public void setVelocity() {
        velocityX += accelerationX * world.getTimeStep();
        velocityY += accelerationY * world.getTimeStep();
    }
    
    /**
     * Sets position.
     */
    public void setPosition() {
        positionX += velocityX * world.getTimeStep();
        positionY += velocityY * world.getTimeStep();
    }
    
    /**
     * Gets position x.
     *
     * @return the position x
     */
    public double getPositionX()
    {
        return positionX;
    }
    
    /**
     * Gets position y.
     *
     * @return the position y
     */
    public double getPositionY()
    {
        return positionY;
    }
    
    /**
     * Update.
     *
     * @param prevSegment the previous segment
     * @param nextSegment the next segment
     */
    public void update(Segment prevSegment, Segment nextSegment) {
        setDisplacement(prevSegment);
        setSpringForce();
        setDampingForce();
        setForce(nextSegment);
        setAcceleration();
        setVelocity();
        setPosition();
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
     * Sets position x.
     *
     * @param positionX the position x
     */
    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }
    
    /**
     * Sets position y.
     *
     * @param positionY the position y
     */
    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }
    
    /**
     * Gets velocity x.
     *
     * @return the velocity x
     */
    public double getVelocityX() {
        return velocityX;
    }
    
    /**
     * Sets velocity x.
     *
     * @param velocityX the velocity x
     */
    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }
    
    /**
     * Gets velocity y.
     *
     * @return the velocity y
     */
    public double getVelocityY() {
        return velocityY;
    }
    
    /**
     * Sets velocity y.
     *
     * @param velocityY the velocity y
     */
    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
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
     * Gets displacement x.
     *
     * @return the displacement x
     */
    public double getDisplacementX() {
        return displacementX;
    }
    
    /**
     * Sets displacement x.
     *
     * @param displacementX the displacement x
     */
    public void setDisplacementX(double displacementX) {
        this.displacementX = displacementX;
    }
    
    /**
     * Gets displacement y.
     *
     * @return the displacement y
     */
    public double getDisplacementY() {
        return displacementY;
    }
    
    /**
     * Sets displacement y.
     *
     * @param displacementY the displacement y
     */
    public void setDisplacementY(double displacementY) {
        this.displacementY = displacementY;
    }
    
    /**
     * Gets spring force x.
     *
     * @return the spring force x
     */
    public double getSpringForceX() {
        return springForceX;
    }
    
    /**
     * Sets spring force x.
     *
     * @param springForceX the spring force x
     */
    public void setSpringForceX(double springForceX) {
        this.springForceX = springForceX;
    }
    
    /**
     * Gets spring force y.
     *
     * @return the spring force y
     */
    public double getSpringForceY() {
        return springForceY;
    }
    
    /**
     * Sets spring force y.
     *
     * @param springForceY the spring force y
     */
    public void setSpringForceY(double springForceY) {
        this.springForceY = springForceY;
    }
    
    /**
     * Gets damping force x.
     *
     * @return the damping force x
     */
    public double getDampingForceX() {
        return dampingForceX;
    }
    
    /**
     * Sets damping force x.
     *
     * @param dampingForceX the damping force x
     */
    public void setDampingForceX(double dampingForceX) {
        this.dampingForceX = dampingForceX;
    }
    
    /**
     * Gets damping force y.
     *
     * @return the damping force y
     */
    public double getDampingForceY() {
        return dampingForceY;
    }
    
    /**
     * Sets damping force y.
     *
     * @param dampingForceY the damping force y
     */
    public void setDampingForceY(double dampingForceY) {
        this.dampingForceY = dampingForceY;
    }
    
    /**
     * Gets force x.
     *
     * @return the force x
     */
    public double getForceX() {
        return forceX;
    }
    
    /**
     * Sets force x.
     *
     * @param forceX the force x
     */
    public void setForceX(double forceX) {
        this.forceX = forceX;
    }
    
    /**
     * Gets force y.
     *
     * @return the force y
     */
    public double getForceY() {
        return forceY;
    }
    
    /**
     * Sets force y.
     *
     * @param forceY the force y
     */
    public void setForceY(double forceY) {
        this.forceY = forceY;
    }
    
    /**
     * Gets acceleration x.
     *
     * @return the acceleration x
     */
    public double getAccelerationX() {
        return accelerationX;
    }
    
    /**
     * Sets acceleration x.
     *
     * @param accelerationX the acceleration x
     */
    public void setAccelerationX(double accelerationX) {
        this.accelerationX = accelerationX;
    }
    
    /**
     * Gets acceleration y.
     *
     * @return the acceleration y
     */
    public double getAccelerationY() {
        return accelerationY;
    }
    
    /**
     * Sets acceleration y.
     *
     * @param accelerationY the acceleration y
     */
    public void setAccelerationY(double accelerationY) {
        this.accelerationY = accelerationY;
    }
}