import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The type Circle pane.
 */
public class CirclePane extends JPanel {
    private final int FRAME_WIDTH = 1000;
    private final int FRAME_HEIGHT = 800;

    private HairController hairController = new HairController(
            new World(9.8, 5.0, 0.1, 15, 5),
            FRAME_WIDTH / 2 - 100, 50, 200, 200, 500);

    private Point p = new Point(100, 100);
    
    /**
     * Instantiates a new Circle pane.
     */
    public CirclePane() {
        MouseAdapter mouseHandler = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Point previousPoint = new Point(p.x, p.y);
                p = e.getPoint();
                for( Hair hair : hairController.getHair() ) {
                    Segment firstSegment = hair.getSegments()[0];
                    hair.setAnchorX(p.x - (previousPoint.x - firstSegment.getPositionX()));
                    hair.setAnchorY(p.y - (previousPoint.y - firstSegment.getPositionY()));
                    firstSegment.setPositionX(p.x - (previousPoint.x - firstSegment.getPositionX()));
                    firstSegment.setPositionY(p.y - (previousPoint.y - firstSegment.getPositionY()));
                }
                repaint();
            }
        };
        addMouseMotionListener(mouseHandler);
    }

    @Override
    /**
     * Creates a new Dimension object.
     *
     * @return the Dimension
     */
    public Dimension getPreferredSize() {
        return new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
    }
    
    @Override
    /**
     * Draw hair.
     *
     * @param g the drawing graphics component
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        RenderingHints rh = new RenderingHints( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        rh.put( RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY );
        g2d.setRenderingHints( rh );

        for( Hair hair : hairController.getHair() ) {
            hair.drawHair( g2d );
        }

        repaint();
    }
}