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

//TODO decompose

import static java.lang.Math.*;

@Setter
public class IntensityGraph {
    private int N = 4;
    private double d = 2500;
    private double lamb = 500;
    private double a = 500;
    private double Io = 1;

    private double maxI = 0;

    private List<Double> array = new ArrayList<>();

    private boolean extraGraph = false;
    private boolean myGraph = false;
    private boolean interPicture = false;

    JFrame frame;
    JFrame frame1;
    JFrame settingWindow = new JFrame("Values Panel");
    JFrame picture = new JFrame("Interference Picture");

    public IntensityGraph() {
        initSettings();
        draw();
    }

    private void draw(){

        maxI = 0;

        frame = new JFrame("Intensity Graph");
        frame1 = new JFrame("Custom Graph Builder");

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

        if (interPicture) initPicture();
        if (myGraph) initMyGraphBuilder();

    }

    private void initMyGraphBuilder(){
        CustomGraphBuilder customGraphBuilder = new CustomGraphBuilder(this);
        frame1.getContentPane().add(customGraphBuilder);
        frame1.setVisible(true);
        frame1.setSize(700,605);
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }

    private void initSettings(){
        settingWindow.setLayout(new FlowLayout());

        settingWindow.add(new Settings(this));
        settingWindow.setSize(436, 260);
        settingWindow.setVisible(true);
        settingWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        settingWindow.setLocation(610, 0);
//
//        JLabel img = new JLabel(new ImageIcon("src/sprites/scheme.png"));
//        JPanel imgFrame = new JPanel();
//        imgFrame.add(img);
//        settingWindow.add(imgFrame);
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
        frame1.dispose();
        frame.dispose();
        picture.dispose();
        array.clear();
        draw();
    }


    public double calculateIntensity(double theta, int holes){
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

    public int getN() {
        return N;
    }
}
