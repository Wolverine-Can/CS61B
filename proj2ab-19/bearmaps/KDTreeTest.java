package bearmaps;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class KDTreeTest {

    @Test
    public void test1() {
        Point p1 = new Point(1, 2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(1, 3);
        Point p3 = new Point(-4, 7);
        Point p4 = new Point(-5, 1);
        Point p5 = new Point(-5, -5);
        Point p6 = new Point(5, -5);
        Point p7 = new Point(0, -5);
        Point p8 = new Point(2, -2);
        Point p9 = new Point(3, -7);
        Point p10 = new Point(6, 6);

        NaivePointSet np = new NaivePointSet(List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));
        KDTree kd = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));
        Point point1 = np.nearest(6, 6);
        Point point2 = kd.nearest(6, 6);
        assertEquals(point1.getX(), point2.getX(), 0.001);
        assertEquals(point1.getY(), point2.getY(), 0.001);
    }
}
