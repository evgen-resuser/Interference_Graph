import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.PI;
import static java.lang.Math.sin;

public class CustomGraphBuilder extends JPanel {

    private Frames graph;
    private Map<Integer, Integer> map = new HashMap<>();
    private Context context;

    public CustomGraphBuilder(Context context){
        this.setVisible(true);
        this.setSize(650, 600);

        this.graph = graph;
        this.context = context;
    }

    private void draw(Graphics g, int n){
        Graphics2D g2d = (Graphics2D) g;

        map.clear();

        int x1;
        int y1;
        int x2;
        int y3 = 550;
        int y2;

        double max = context.getMaxI();

        System.out.println("drawing");

        BasicStroke stroke = new BasicStroke(1f);

        drawAxes(g2d);

        g2d.setColor(Color.RED);
        g2d.setStroke(stroke);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        x1 = 0;
        for(double i = -1.5708; i < 1.5708; i+=0.005){
            y1 = (int) (y3-(calculateIntensity(i, n)/max*500));
            map.put(x1, y1);
            x1++;
        }

        int len = map.size();
        System.out.println(len);

        x1 = 0;
        y1 = map.get(x1);
        for (int i = 1; i != len; i++){
            x2 = i;
            y2 = map.get(i);
            g2d.drawLine(x1+10, y1, x2+10, y2);
            x1 = x2;
            y1 = y2;
        }
    }

    private void drawAxes(Graphics2D g2d){
        g2d.setColor(Color.GRAY);
        g2d.setFont(new Font("Courier New", Font.BOLD, 15));

        g2d.drawString("0", 320, 565);

        g2d.drawLine(10, 550, 660, 550); // x axis
        g2d.drawLine(660, 550, 640, 546);
        g2d.drawLine(660, 550, 640, 554);
        g2d.drawString("Theta θ", 620, 565);

        g2d.drawLine(324, 550, 324, 10); // y axis
        g2d.drawLine(324, 10, 328, 30);
        g2d.drawLine(324, 10, 320, 30);
        g2d.drawString("Intensity", 330, 20);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        draw(g, context.getN());
    }

    public double calculateIntensity(double theta, int holes){
        double res;

        double lamb = context.getLamb();
        double d = context.getD();

        double tmp = sinc(PI * context.getA() / lamb * sin(theta));
        double tmp1 = sin(holes * PI * d / lamb * sin(theta)) /
                sin (PI * d / lamb * sin(theta));

//        double tmp = sinc(a / d * theta);
//        double tmp1 = sin(holes * theta) /
//                sin (theta);

        res = context.getIo() * tmp*tmp * tmp1*tmp1;

        return res ;
    }

    private static double sinc(double angle){
        if (angle == 0) return 1;
        return sin(angle)/angle;
    }
}
