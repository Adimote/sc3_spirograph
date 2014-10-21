import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * The Object to insert into a JForm or applet to display the spirographs
 *
 * Created by Andy on 17/10/2014.
 *
 */
public class SpirographCanvas extends Canvas {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 700;

    // on paint
    @Override
    public void paint(Graphics g) {
        // Call the parent function
        super.paint(g);
        BufferedImage buffer = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_ARGB);
        Spiral spiral = new Spiral(2.0,1.4,0.2);
        spiral.draw_to_image(buffer);
        g.drawImage(buffer,0,0,null);
    }

}
