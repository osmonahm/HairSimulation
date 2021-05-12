public class World {
    private double gravity;     // the gravitational force
    private double mass;        // the mass of the spring
    private double timeStep;    // pixels to skip
    private double damping;     // the damping coefficient
    private int k;              // the spring constant - stiffness

    public World(double gravity, double mass, double timeStep, double damping, int k) {
        this.gravity = gravity;
        this.mass = mass;
        this.timeStep = timeStep;
        this.damping = damping;
        this.k = k;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getTimeStep() {
        return timeStep;
    }

    public void setTimeStep(double timeStep) {
        this.timeStep = timeStep;
    }

    public double getDamping() {
        return damping;
    }

    public void setDamping(double damping) {
        this.damping = damping;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }
}