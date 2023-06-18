import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Picture extends JPanel {

    private final List<Double> array;
    private final Context context;


    public Picture(Context context){
        this.setVisible(true);
        this.setSize(400, 50);

        array = context.getArray();
        this.context = context;
    }

    void drawLines(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 50;

        BasicStroke stroke = new BasicStroke(3f);

        int indx = 0;
        double max = context.getMaxI();

        for(double i = -1.5708; i < 1.5708; i+=0.01){
            g2d.drawLine(x1, y1, x2, y2);
            g2d.setStroke(stroke);

            int br = (int)(array.get(indx)/max*255);

            Color color = new Color(br, br, br);
            g2d.setColor(color);

            x1+=2;
            x2+=2;
            indx++;
        }
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        drawLines(g);
    }

}
