import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;

import static java.lang.Math.*;

public class Intensity {
    private Context context;
    private ChartPanel chartPanel;

    public Intensity(Context context){
        this.context = context;
        drawPanel();
    }

    void drawPanel(){
        context.setMaxI(0);

        XYSeries series = new XYSeries(0);
        XYSeries series1 = new XYSeries(1);

        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(series);
        collection.addSeries(series1);

        double tmp;

        for(double i = -1.5708; i < 1.5708; i+=0.01){

            //tmp = calculateIntensity(i, N);
            tmp = Functions.calculateIntensity(i, context.getN(), context);

//            array.add(tmp);
//            maxI = max(tmp, maxI);
            context.getArray().add(tmp);
            context.setMaxI(max(tmp, context.getMaxI()));

//            if (extraGraph) series1.add(i, calculateIntensity(i, 1) * N*N);
//            series.add(i, tmp);
            int n = context.getN();
            if (context.isExtraGraph()) series1.add(i, Functions.calculateIntensity(i, 1, context) * n*n);
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

        chartPanel = new ChartPanel(chart);
    }

    public ChartPanel getChartPanel() {
        return chartPanel;
    }
}
