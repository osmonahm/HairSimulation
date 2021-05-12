import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

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
        double gravity = 9.8;   // the gravitational force
        double mass = 30.0;     // the mass of the spring
        double timeStep = 0.02; // pixels to skip
        double damping = 15;    // the damping coefficient
        int k = 5;              // the spring constant - stiffness
        
        Hair[] hairs;
        
        // head settings
        int headX = 200;
        int headY = 10;
        int headWidth = 400;
        int headHeight = 400;
        
        public HairPanel()
        {
            setPreferredSize( new Dimension( HairController.WIDTH, HairController.HEIGHT ) );
            setBackground( Color.white );
            
            Thread thread = new Thread( this );
            thread.start();
            
            // initializing hair objects with random anchors
            hairs = new Hair[100];
            for( int i = 0; i < hairs.length; i++ )
            {
                double[] randomCoordinates = randomCoordinates();
                hairs[i] = new Hair( gravity, mass, timeStep, damping, k, randomCoordinates[0], randomCoordinates[1], 7 );
            }
        }
        
        private double[] randomCoordinates()
        {
            double theta = getRandomNumber( 0, Math.PI * 2 );
            double r = getRandomNumber( 0d, 1d );
            
            double x = headWidth * Math.sqrt( r ) * Math.cos( theta ) / 2;
            double y = ( headHeight * Math.sqrt( r ) * Math.sin( theta ) / 2 ) - 10;
            
            return new double[]{ headX + ( headWidth / 2.0 ) * 0.95 + x, headY + ( headHeight / 2.0 ) * 0.95 + y };
        }
        
        public void paintComponent( Graphics g )
        {
            Graphics2D g2D = ( Graphics2D ) g;
            super.paintComponent( g2D );
            RenderingHints rh = new RenderingHints( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
            rh.put( RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY );
            g2D.setRenderingHints( rh );
            g.setColor( Color.BLACK );
            
            Ellipse2D head = new Ellipse2D.Double( headX, headY, headWidth, headHeight );
            g2D.draw( head );
    
            for( Hair hair : hairs ) hair.drawHair( g2D );
        }
        
        public void run()
        {
            while( true )
            {
                repaint();
                //try { Thread.sleep( 1 ); } catch( InterruptedException ex ) {}
            }
        }
        
        public double getRandomNumber( double min, double max )
        {
            return min + ( max - min ) * new Random().nextDouble();
        }
    }
}