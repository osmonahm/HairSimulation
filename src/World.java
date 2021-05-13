/**
 * The type World.
 */
public class World {
    private double gravity;     // the gravitational force
    private double mass;        // the mass of the spring
    private double timeStep;    // pixels to skip
    private double damping;     // the damping coefficient
    private int k;              // the spring constant - stiffness
    
    /**
     * Instantiates a new World.
     *
     * @param gravity  the gravity
     * @param mass     the mass
     * @param timeStep the time step
     * @param damping  the damping
     * @param k        the k constant
     */
    public World(double gravity, double mass, double timeStep, double damping, int k) {
        this.gravity = gravity;
        this.mass = mass;
        this.timeStep = timeStep;
        this.damping = damping;
        this.k = k;
    }
    
    /**
     * Gets gravity.
     *
     * @return the gravity
     */
    public double getGravity() {
        return gravity;
    }
    
    /**
     * Sets gravity.
     *
     * @param gravity the gravity
     */
    public void setGravity(double gravity) {
        this.gravity = gravity;
    }
    
    /**
     * Gets mass.
     *
     * @return the mass
     */
    public double getMass() {
        return mass;
    }
    
    /**
     * Sets mass.
     *
     * @param mass the mass
     */
    public void setMass(double mass) {
        this.mass = mass;
    }
    
    /**
     * Gets time step.
     *
     * @return the time step
     */
    public double getTimeStep() {
        return timeStep;
    }
    
    /**
     * Sets time step.
     *
     * @param timeStep the time step
     */
    public void setTimeStep(double timeStep) {
        this.timeStep = timeStep;
    }
    
    /**
     * Gets damping.
     *
     * @return the damping
     */
    public double getDamping() {
        return damping;
    }
    
    /**
     * Sets damping.
     *
     * @param damping the damping
     */
    public void setDamping(double damping) {
        this.damping = damping;
    }
    
    /**
     * Gets k.
     *
     * @return the k
     */
    public int getK() {
        return k;
    }
    
    /**
     * Sets k.
     *
     * @param k the k
     */
    public void setK(int k) {
        this.k = k;
    }
}