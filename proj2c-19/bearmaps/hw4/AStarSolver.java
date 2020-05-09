package bearmaps.hw4;
import bearmaps.proj2ab.ArrayHeapMinPQ;
import bearmaps.proj2ab.ExtrinsicMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private AStarGraph<Vertex> graph;
    private Vertex source;
    private Vertex target;
    private boolean targetFound = false;
    private boolean timeOut = false;
    private HashMap<Vertex, Double> distTo = new HashMap<>();
    private HashMap<Vertex, Vertex> edgeTo = new HashMap<>();
    private List<Vertex> solution = new ArrayList<>();
    private double solutionWeight = 0;
    private int numStatesExplored = 0;
    double explorationTime;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();
        graph = input;
        source = start;
        target = end;
        ExtrinsicMinPQ<Vertex> pq = new ArrayHeapMinPQ<>();

        distTo.put(source, 0.0);
        pq.add(source, priority(source));
        if (source.equals(target)) {
            targetFound = true;
        }
        while (!targetFound && !timeOut && pq.size() > 0) {
            Vertex v = pq.removeSmallest();
            numStatesExplored += 1;
            if (v.equals(target)) {
                targetFound = true;
            }
            if (sw.elapsedTime() > timeout) {
                timeOut = true;
            }
            for (WeightedEdge<Vertex> e : graph.neighbors(v)) {
                relax(e, pq);
            }
        }
        if (targetFound) {
            Vertex v = target;
            while (!v.equals(source)) {
                solution.add(0, v);
                v = edgeTo.get(v);
            }
            solution.add(0, source);
            solutionWeight = distTo.get(target);
        }
        explorationTime = sw.elapsedTime();
    }

    private void relax(WeightedEdge<Vertex> e, ExtrinsicMinPQ<Vertex> pq) {
        Vertex p = e.from();
        Vertex q = e.to();
        double w = e.weight();
        if (!distTo.containsKey(q)) {
            distTo.put(q, Double.POSITIVE_INFINITY);
        }
        if (distTo.get(p) + w < distTo.get(q)) {
            distTo.put(q, distTo.get(p) + w);
            edgeTo.put(q, p);
            if (pq.contains(q)) {
            pq.changePriority(q, priority(q));
            } else {
            pq.add(q, priority(q));
            }
        }
    }

    private double priority(Vertex v) {
        return distTo.get(v) + graph.estimatedDistanceToGoal(v, target);
    }

    public SolverOutcome outcome() {
        if (targetFound) {
            return SolverOutcome.SOLVED;
        } else if (timeOut) {
            return SolverOutcome.TIMEOUT;
        } else {
            return SolverOutcome.UNSOLVABLE;
        }
    }
    public List<Vertex> solution() {
            return solution;
    }
    public double solutionWeight() {
        return solutionWeight;
    }
    public int numStatesExplored() {
        return numStatesExplored;
    }
    public double explorationTime() {
        return explorationTime;
    }
}
