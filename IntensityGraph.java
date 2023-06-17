import lombok.Setter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

@Setter
public class IntensityGraph {
    private int N;
    private double d;
    private double lamb;
    private double a;
    private double Io = 1;

    private double maxI = 0;

    private List<Double> array = new ArrayList<>();

    private boolean extraGraph = false;

    JFrame frame;
    JFrame settingWindow = new JFrame("Values Panel");
    JFrame picture = new JFrame("Interference Picture");


    public IntensityGraph(int N, double d, double l, double hs) {
        this.d = d;
        this.N = N;
        this.lamb = l;
        this.a = hs;
        initSettings();
        draw();
    }

    private void draw(){

        maxI = 0;

        frame = new JFrame("Intensity Graph");

        XYSeries series = new XYSeries(0);
        XYSeries series1 = new XYSeries(1);

        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(series);
        collection.addSeries(series1);

        double tmp;

        for(double i = -1.5708; i < 1.5708; i+=0.01){

            tmp = calculateIntensity(i, N);
            array.add(tmp);
            maxI = max(tmp, maxI);

            if (extraGraph) series1.add(i, calculateIntensity(i, 1) * N*N);
            series.add(i, tmp);

            //if (extraGraph) series1.add(i, countDiffTerm(i));
            //series.add(i, countIntensity(PI * d / lamb * sin(i)));
        }

        JFreeChart chart = ChartFactory
                .createXYLineChart("", "Theta θ", "Intensity",
                        collection,
                        PlotOrientation.VERTICAL,
                        false, true, true);

        XYPlot plot = (XYPlot) chart.getPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
        renderer.setSeriesStroke(1, new BasicStroke(
                1.2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                1.0f, new float[]{0.5f, 10.0f}, 0.0f));

        renderer.setPaint(new Color(255, 90, 0));

        plot.setRenderer(renderer);

        ChartPanel chartPanel = new ChartPanel(chart);

        frame.add(chartPanel);
        frame.setVisible(true);
        frame.setSize(600,500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initPicture();
    }

    private void initSettings(){
        settingWindow.setLayout(new FlowLayout());

        settingWindow.add(new Settings(this));
        settingWindow.setSize(436, 360);
        settingWindow.setVisible(true);
        settingWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        settingWindow.setLocation(610, 0);

        JLabel img = new JLabel(new ImageIcon("src/scheme.png"));
        JPanel imgFrame = new JPanel();
        imgFrame.add(img);
        settingWindow.add(imgFrame);
    }

    private void initPicture(){
        JPanel panel = new Picture(this);
        this.picture.add(panel);
        picture.setVisible(true);
        picture.setSize(644, 80);
        picture.setResizable(false);
        picture.setLocation(0, 505);
        picture.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void reload(){
        frame.dispose();
        picture.dispose();
        array.clear();
        draw();
    }


    private double calculateIntensity(double theta, int holes){
        double res;

        double tmp = sinc(PI * a / lamb * sin(theta));
        double tmp1 = sin(holes * PI * d / lamb * sin(theta)) /
                sin (PI * d / lamb * sin(theta));

//        double tmp = sinc(a / d * theta);
//        double tmp1 = sin(holes * theta) /
//                sin (theta);

        res = Io * tmp*tmp * tmp1*tmp1;

        return res ;
    }

    private static double sinc(double angle){
        if (angle == 0) return 1;
        return sin(angle)/angle;
    }


    public List<Double> getArray() {
        return array;
    }

    public double getMaxI() {
        return maxI;
    }
}