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
        double damping = 15;    // the damping coefficient
        int k = 5;              // the spring constant - stiffness
        
        Hair[] hairs;
        
        public HairPanel()
        {
            setPreferredSize( new Dimension( HairController.WIDTH, HairController.HEIGHT ) );
            setBackground( Color.white );
            
            Thread thread = new Thread( this );
            thread.start();
    
            // initializing hair objects with random anchors
            hairs = new Hair[200];
            for( int i = 0; i < hairs.length; i++ )
                hairs[i] = new Hair( gravity, mass, timeStep, damping, k, getRandomNumber( 200, 550 ), getRandomNumber( 100, 400 ), 7 );
        }
        
        public void paintComponent( Graphics g )
        {
            Graphics2D g2D = ( Graphics2D ) g;
            super.paintComponent( g2D );
            RenderingHints rh = new RenderingHints( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
            rh.put( RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY );
            g2D.setRenderingHints( rh );
            g.setColor( Color.BLACK );
            
            Ellipse2D head = new Ellipse2D.Double( 200, 100, 400, 400 );
            g2D.draw( head );
    
            for( int i = 0; i < hairs.length; i++ )
                hairs[i].drawHair( g2D );
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