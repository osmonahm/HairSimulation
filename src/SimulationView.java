import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class SimulationView extends JApplet
{
    public static final int WIDTH = 800;
    public static final int HEIGHT = 650;

    public static void main( String[] args )
    {
        JFrame frame = new JFrame();
        frame.setTitle( "Hair Simulation" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        JApplet applet = new SimulationView();
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
        private HairController hairController = new HairController(
                new World(9.8, 30.0, 0.02, 15, 5),
                ( SimulationView.WIDTH / 2 ) - 112, 10, 225, 250, 5000);

        public HairPanel()
        {
            setPreferredSize( new Dimension( SimulationView.WIDTH, SimulationView.HEIGHT ) );
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

            g2D.setColor( Color.BLACK );
            Ellipse2D head = new Ellipse2D.Double( hairController.getHeadX(), hairController.getHeadY(),
                    hairController.getHeadWidth(), hairController.getHeadHeight() );
            g2D.draw( head );

            for( Hair hair : hairController.getHair() ) {
                hair.drawHair( g2D );
            }
        }

        public void run()
        {
            while( true )
            {
                repaint();
            }
        }
    }
}
