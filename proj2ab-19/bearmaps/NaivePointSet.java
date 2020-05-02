package bearmaps;
import java.util.HashSet;
import java.util.List;

public class NaivePointSet implements PointSet {
    private HashSet<Point> PointSet;
    Point nearestPoint;
    double bestDis;

    public NaivePointSet(List<Point> points) {
        PointSet = new HashSet<>();
        PointSet.addAll(points);
        bestDis = Double.POSITIVE_INFINITY;;
    }

    @Override
    public Point nearest(double x, double y) {
        double dist;
        for (Point point : PointSet) {
            dist = Math.sqrt((point.getX() - x) * (point.getX() - x) + (point.getY() - y) * (point.getY() - y));
            if (dist < bestDis) {
                bestDis = dist;
                nearestPoint = point;
            }
        }
        return nearestPoint;
    }
}
