import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Created by hug.
 */
public class Experiments {
    public static void experiment1() {
        BST<Integer> tree = new BST<>();
        Random r = new Random();
        List<Double> yValues = new ArrayList<>();
        List<Double> y2Values = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        for (int x = 1; x <= 5000; ) {
            int randomN = r.nextInt(50000);
            if (!tree.contains(randomN)) {
                tree.add(randomN);
                double thisY = tree.averageDepth();
                xValues.add(x);
                yValues.add(thisY);
                double thisY2 = ExperimentHelper.optimalAverageDepth(x);
                y2Values.add(thisY2);
                x++;
            }
        }
        System.out.println(tree.size());
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("averageDepth", xValues, yValues);
        chart.addSeries("optimalAverageDepth", xValues, y2Values);
        new SwingWrapper(chart).displayChart();
    }

    public static void experiment2() {
        int N = 5000;
        int M = 10000;
        List<Double> yValues = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        BST<Double> tree = new BST<>();
        Random r = new Random();
        for (int x = 1; x <= N;) {
            double randomN = r.nextDouble();
            if (!tree.contains(randomN)) {
                tree.add(randomN);
                x++;
            }
        }
        for (int x = 1; x <= M;) {
            double randomN = r.nextDouble();
            if (!tree.contains(randomN)) {
                tree.deleteTakingSuccessor(tree.getRandomKey());
                tree.add(randomN);
                double thisY = tree.averageDepth();
                xValues.add(x);
                yValues.add(thisY);
                x++;
            }
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("averageDepth", xValues, yValues);
        new SwingWrapper(chart).displayChart();
    }

    public static void experiment3() {
        int N = 5000;
        int M = 100000;
        List<Double> yValues = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        BST<Double> tree = new BST<>();
        Random r = new Random();
        for (int x = 1; x <= N;) {
            double randomN = r.nextDouble();
            if (!tree.contains(randomN)) {
                tree.add(randomN);
                x++;
            }
        }
        for (int x = 1; x <= M;) {
            double randomN = r.nextDouble();
            if (!tree.contains(randomN)) {
                tree.deleteTakingRandom(tree.getRandomKey());
                tree.add(randomN);
                double thisY = tree.averageDepth();
                xValues.add(x);
                yValues.add(thisY);
                x++;
            }
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("averageDepth", xValues, yValues);
        new SwingWrapper(chart).displayChart();
    }


    public static void main(String[] args) {
        experiment3();
    }
}
