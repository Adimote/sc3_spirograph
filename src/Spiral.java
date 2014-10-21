import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Andy on 17/10/2014.
 */
public class Spiral {
    double R,r,O;

    public Spiral(double r_fixed, double r_moving, double offset_moving) {
        this.R = r_fixed;
        this.r = r_moving;
        this.O = offset_moving;
    }

    public Point get_pos(double time, double multiplier, Dimension image_dim) {
        double pos_x = (R+r)*Math.cos(time) - (r+O)*Math.cos(((R+r)/r)*time);
        double pos_y = (R+r)*Math.sin(time) - (r+O)*Math.sin(((R+r)/r)*time);
        return new Point(
                (int)Math.round(pos_x*multiplier+ image_dim.width/2),
                (int)Math.round(pos_y*multiplier + image_dim.height/2)
        );
    }

    // Try and find the lowest common multiple,
    // with a limit however, as there's a chance there isn't one.
    private static int LCM_LIMIT = 999999;
    private double lcm (double a,double b) {
       if (a == b) {
           return a;
       }
       if (a == 1) {
            return b;
       }
       if (b == 1) {
            return a;
       }
       double a_mul= a, b_mul = b;
       while (a_mul != b_mul) {
           if ((a_mul > LCM_LIMIT)||(b_mul > LCM_LIMIT)) {
               break;
           }
           while (a_mul < b_mul) { a_mul += a;}
           while (b_mul < a_mul) { b_mul += b;}
       }
       return a_mul;
    }

    // This function outputs incorrect values
    // TODO fix this function
    private double calculate_spirograph_scale(Dimension dim) {
        double max_radius = Math.abs(this.R) + Math.abs(2*this.r) + Math.abs(this.O);
        double border = 50;
        double smallest_dimension = Math.min(dim.width, dim.height);
        return (smallest_dimension - (border * 2)) / (max_radius);
    }

    private static double RESOLUTION = 0.01;
    public void draw_to_image(BufferedImage image) {
        Graphics2D g2d = (Graphics2D)image.getGraphics();
        Dimension image_dimensions = new Dimension(image.getWidth(),image.getHeight());
        g2d.setColor(Color.black);

        // Calculate the multiplier so the spirograph fits on screen:
        double multiplier = calculate_spirograph_scale(image_dimensions);
        Point prev = get_pos(0,multiplier,image_dimensions);
        Point current;
        for (double t = RESOLUTION; t < lcm(this.R,this.r); t += RESOLUTION) {
            current = get_pos(t,multiplier,image_dimensions);
            g2d.drawLine(prev.x,prev.y,current.x,current.y);
            prev = current;
        }
    }
}
