import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class CirclePane extends JPanel {
    private HairController hairController = new HairController(
            new World(9.8, 30.0, 0.1, 15, 5),
            200, 200, 200, 200, 500);

    private Point p = new Point(100, 100);

    public CirclePane() {
        MouseAdapter mouseHandler = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Point previousPoint = new Point(p.x, p.y);
                p = e.getPoint();
                for( Hair hair : hairController.getHair() ) {
//                    hair.setAnchorX(p.x - (previousPoint.x - hair.getAnchorX()));
//                    hair.setAnchorY(p.y - (previousPoint.y - hair.getAnchorY()));
                    Segment firstSegment = hair.getSegments()[0];
                    firstSegment.setPositionX(p.x - (previousPoint.x - firstSegment.getPositionX()));
                    firstSegment.setPositionY(p.y - (previousPoint.y - firstSegment.getPositionY()));
//                    hair.getSegments()[1].update(hair.getSegments()[0], hair.getSegments()[2]);
                }
                repaint();
            }
        };
        addMouseMotionListener(mouseHandler);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 800);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        RenderingHints rh = new RenderingHints( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        rh.put( RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY );
        g2d.setRenderingHints( rh );

        g2d.setColor( Color.BLACK );
        Ellipse2D head = new Ellipse2D.Double( p.x - hairController.getHeadWidth() / 2.0,
                p.y - hairController.getHeadHeight() / 2.0,
                hairController.getHeadWidth(),
                hairController.getHeadHeight() );

        g2d.draw( head );

        for( Hair hair : hairController.getHair() ) {
            hair.drawHair( g2d );
        }

        repaint();
    }
}