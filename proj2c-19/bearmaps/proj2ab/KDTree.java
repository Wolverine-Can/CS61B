package bearmaps.proj2ab;
import java.util.List;

public class KDTree {
    private Node root;
    private Node best;

    public KDTree(List<Point> points) {
        for (Point point : points) {
            insert(new Node(point));
        }
        best = root;
    }

    public Point nearest(double x, double y) {
        return nearestHelper(root, x, y).key;
    }

    private Node nearestHelper(Node n, double x, double y) {
        if (n == null) {
            return best;
        }
        if (n.dist(x, y) < best.dist(x, y)) {
            best = n;
        }
        Node goodSide, badSide;
        if (n.level % 2 == 0) {
            if (x < n.key.getX()) {
                goodSide = n.left;
                badSide = n.right;
            } else {
                goodSide = n.right;
                badSide = n.left;
            }
        } else {
            if (y < n.key.getY()) {
                goodSide = n.left;
                badSide = n.right;
            } else {
                goodSide = n.right;
                badSide = n.left;
            }
        }
        best = nearestHelper(goodSide, x, y);
        if (n.level % 2 == 0 && (Math.abs(x - n.key.getX()) < best.dist(x, y))) {
            best = nearestHelper(badSide, x, y);
        }
        if (n.level % 2 == 1 && (Math.abs(y - n.key.getY()) < best.dist(x, y))) {
            best = nearestHelper(badSide, x, y);
        }
        return best;
    }

    private class Node {
        /* (K, V) pair stored in this Node. */
        private Point key;

        /* Children of this Node. */
        private Node left;
        private Node right;
        private int level;

        private Node(Point k) {
            key = k;
        }
        private double dist(double x, double y) {
            return Math.sqrt((key.getX() - x) * (key.getX() - x) + (key.getY() - y) * (key.getY() - y));
        }
    }

    private void insert(Node n) {
        if (root == null) {
            root = n;
            n.level = 0;
            return;
        }
        insertHelper(root, n);
    }
    private void insertHelper(Node p, Node n) {
        if (p.key.getX() == n.key.getX() && p.key.getY() == n.key.getY()) {
            return;
        }
        if (p.level % 2 == 0) {
            if (n.key.getX() < p.key.getX()) {
                if (p.left == null) {
                    p.left = n;
                    n.level = p.level + 1;
                    return;
                }
                insertHelper(p.left, n);
            } else {
                if (p.right == null) {
                    p.right = n;
                    n.level = p.level + 1;
                    return;
                }
                insertHelper(p.right, n);
            }
        } else {
            if (n.key.getY() < p.key.getY()) {
                if (p.left == null) {
                    p.left = n;
                    n.level = p.level + 1;
                    return;
                }
                insertHelper(p.left, n);
            } else {
                if (p.right == null) {
                    p.right = n;
                    n.level = p.level + 1;
                    return;
                }
                insertHelper(p.right, n);
            }
        }
    }
}
