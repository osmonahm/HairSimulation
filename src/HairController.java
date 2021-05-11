import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class HairController extends JApplet
{
    public static final int WIDTH = 800;
    public static final int HEIGHT = 650;
    
    public static void main( String[] args )
    {
        JFrame frame = new JFrame();
        frame.setTitle( "Hair Simulation" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        JApplet applet = new HairController();
        applet.init();
        frame.getContentPane().add( applet );
        frame.pack();
        frame.setVisible( true );
    }
    
    public void init()
    {
        JPanel panel = new HairPanel();
        getContentPane().add( panel );
    }
    
    class HairPanel extends JPanel implements Runnable
    {
        // initial settings
        int anchorX = 209;      // the anchor/initial position x
        int anchorY = 181;      // the anchor/initial position y
        
        double gravity = 9.8;   // the gravitational force
        double mass = 30.0;     // the mass of the spring
        double timeStep = 0.02; // pixels to skip
        double damping = 10;     // the damping coefficient
        int k = 5;              // the spring constant - stiffness
        
        Spring spring = new Spring( gravity, mass, timeStep, damping, k, anchorX, anchorY );
        Spring spring2 = new Spring( gravity, mass, timeStep, damping, k, spring.getPositionX(), spring.getPositionY() );
        Spring spring3 = new Spring( gravity, mass, timeStep, damping, k, spring2.getPositionX(), spring2.getPositionY() );
        
        public HairPanel()
        {
            setPreferredSize( new Dimension( HairController.WIDTH, HairController.HEIGHT ) );
            setBackground( Color.white );
            
            Thread thread = new Thread( this );
            thread.start();
        }
        
        public void paintComponent( Graphics g )
        {
            Graphics2D g2D = ( Graphics2D ) g;
            super.paintComponent( g2D );
            RenderingHints rh = new RenderingHints( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
            rh.put( RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY );
            g2D.setRenderingHints( rh );
            g.setColor( Color.BLACK );
            
            spring.update( null, spring2 );
            spring2.update( spring, null );
            spring3.update( spring2, null );
            
            g2D.fillRect( anchorX + 5, anchorX - 5, 10, 10 );
            
            // Draw Mass 1
            Line2D line1 = new Line2D.Double( spring.getPositionX() + 10, spring.getPositionY(), anchorX + 10, anchorY + 25 );
            Ellipse2D ellipse1 = new Ellipse2D.Double( spring.getPositionX(), spring.getPositionY(), 20, 20 );
            g2D.draw( line1 );
            g2D.draw( ellipse1 );
    
            // Draw Mass 2
            Line2D line2 = new Line2D.Double( spring2.getPositionX() + 10, spring2.getPositionY(), spring.getPositionX() + 10, spring.getPositionY() + 20 );
            Ellipse2D ellipse2 = new Ellipse2D.Double( spring2.getPositionX(), spring2.getPositionY(), 20, 20 );
            g2D.draw( line2 );
            g2D.draw( ellipse2 );
    
            // Draw Mass 3
            Line2D line3 = new Line2D.Double( spring3.getPositionX() + 10, spring3.getPositionY(), spring2.getPositionX() + 10, spring2.getPositionY() + 20 );
            Ellipse2D ellipse3 = new Ellipse2D.Double( spring3.getPositionX(), spring3.getPositionY(), 20, 20 );
            g2D.draw( line3 );
            g2D.draw( ellipse3 );
        }
        
        public void run()
        {
            while( true )
            {
                repaint();
                //try { Thread.sleep( 1 ); } catch( InterruptedException ex ) {}
            }
        }
        
        public int getRandomNumber( int min, int max )
        {
            return ( int ) ( ( Math.random() * ( max - min ) ) + min );
        }
    }
}

//    // Mass 1
//    double mass1PositionX = 150;    // spring position x
//    double mass1PositionY = 200;    // spring position y
//    double mass1VelocityX = 0.0;    // spring velocity x
//    double mass1VelocityY = 0.0;    // spring velocity y
//
//    // Mass 2
//    double mass2PositionX = 150;    // spring position x
//    double mass2PositionY = 270;    // spring position y
//    double mass2VelocityX = 0.0;    // spring velocity x
//    double mass2VelocityY = 0.0;    // spring velocity y
//
//    // Mass 3
//    double mass3PositionX = 150;    // spring position x
//    double mass3PositionY = 320;    // spring position y
//    double mass3VelocityX = 0.0;    // spring velocity x
//    double mass3VelocityY = 0.0;    // spring velocity y
//
//    // Mass 1 Displacement Force
//    double mass1DisplacementX = mass1PositionX - anchorX;
//    double mass1DisplacementY = mass1PositionY - anchorY;
//
//    // Mass 2 Displacement Force
//    double mass2DisplacementX = mass2PositionX - mass1PositionX;
//    double mass2DisplacementY = mass2PositionY - mass1PositionY;
//
//    // Mass 3 Displacement Force
//    double mass3DisplacementX = mass3PositionX - mass2PositionX;
//    double mass3DisplacementY = mass3PositionY - mass2PositionY;
//
//    // Mass 1 Spring Force
//    double mass1SpringForceX = -k * mass1DisplacementX;
//    double mass1SpringForceY = -k * mass1DisplacementY;
//
//    // Mass 2 Spring Force
//    double mass2SpringForceX = -k * mass2DisplacementX;
//    double mass2SpringForceY = -k * mass2DisplacementY;
//
//    // Mass 3 Spring Force
//    double mass3SpringForceX = -k * mass3DisplacementX;
//    double mass3SpringForceY = -k * mass3DisplacementY;
//
//    // Mass 1 Damping force = DampingCoefficient * Velocity of displacement
//    double mass1DampingForceX = damping * mass1VelocityX;
//    double mass1DampingForceY = damping * mass1VelocityY;
//
//    // Mass 2 Damping force = DampingCoefficient * Velocity of displacement
//    double mass2DampingForceX = damping * mass2VelocityX;
//    double mass2DampingForceY = damping * mass2VelocityY;
//
//    // Mass 3 damping
//    double mass3DampingForceY = damping * mass3VelocityY;
//    double mass3DampingForceX = damping * mass3VelocityX;
//
//    // Mass 1 net Force
//    double mass1ForceX = mass1SpringForceX - mass1DampingForceX - mass2SpringForceX + mass2DampingForceX;
//    double mass1ForceY = mass1SpringForceY + mass * gravity - mass1DampingForceY - mass2SpringForceY + mass2DampingForceY;
//
//    // Mass 2 net Force
//    double mass2ForceX = mass2SpringForceX - mass2DampingForceX - mass3SpringForceX + mass3DampingForceX;
//    double mass2ForceY = mass2SpringForceY + mass * gravity - mass2DampingForceY - mass3SpringForceY + mass3DampingForceY;
//
//    // Mass 3 net Force
//    double mass3ForceX = mass3SpringForceX - mass3DampingForceX;
//    double mass3ForceY = mass3SpringForceY + mass * gravity - mass3DampingForceY;
//
//    // Mass 1 Acceleration = Force / Mass
//    double mass1AccelerationX = mass1ForceX / mass;
//    double mass1AccelerationY = mass1ForceY / mass;
//
//    // Mass 2 Acceleration = Force / Mass
//    double mass2AccelerationX = mass2ForceX / mass;
//    double mass2AccelerationY = mass2ForceY / mass;
//
//    // Mass 2 Acceleration = Force / Mass
//    double mass3AccelerationX = mass3ForceX / mass;
//    double mass3AccelerationY = mass3ForceY / mass;
//
//// Mass 1 Velocity = Acceleration * TimeStep
//            mass1VelocityX += mass1AccelerationX * timeStep;
//                    mass1VelocityY += mass1AccelerationY * timeStep;
//
//                    // Mass 2 Velocity = Acceleration * TimeStep
//                    mass2VelocityX += mass2AccelerationX * timeStep;
//                    mass2VelocityY += mass2AccelerationY * timeStep;
//
//                    // Mass 3 Velocity = Acceleration * TimeStep
//                    mass3VelocityX += mass3AccelerationX * timeStep;
//                    mass3VelocityY += mass3AccelerationY * timeStep;
//
//                    // Mass 1 Position = Velocity * TimeStep
//                    mass1PositionX += mass1VelocityX * timeStep;
//                    mass1PositionY += mass1VelocityY * timeStep;
//
//                    // Mass 2 Position = Velocity * TimeStep
//                    mass2PositionX += mass2VelocityX * timeStep;
//                    mass2PositionY += mass2VelocityY * timeStep;
//
//                    // Mass 3 Position = Velocity * TimeStep
//                    mass3PositionX += mass3VelocityX * timeStep;
//                    mass3PositionY += mass3VelocityY * timeStep;
// Draw Mass 1
//Line2D line1 = new Line2D.Double( mass1PositionX + 10, mass1PositionY, anchorX, anchorY + 25 );
//    Ellipse2D ellipse1 = new Ellipse2D.Double( mass1PositionX, mass1PositionY, 20, 20 );
//            g2D.draw( line1 );
//                    g2D.draw( ellipse1 );
//
//                    // Draw Mass 2
//                    Line2D line2 = new Line2D.Double( mass2PositionX + 10, mass2PositionY, mass1PositionX + 10, mass1PositionY + 20 );
//                    Ellipse2D ellipse2 = new Ellipse2D.Double( mass2PositionX, mass2PositionY, 20, 20 );
//                    g2D.draw( line2 );
//                    g2D.draw( ellipse2 );
//
//                    // Draw Mass 1
//                    Line2D line3 = new Line2D.Double( mass3PositionX + 10, mass3PositionY, mass2PositionX + 10, mass2PositionY + 20 );
//                    Ellipse2D ellipse3 = new Ellipse2D.Double( mass3PositionX, mass3PositionY, 20, 20 );
//                    g2D.draw( line3 );
//                    g2D.draw( ellipse3 );