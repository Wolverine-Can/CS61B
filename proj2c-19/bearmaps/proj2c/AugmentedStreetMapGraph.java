package bearmaps.proj2c;

import bearmaps.hw4.streetmap.Node;
import bearmaps.hw4.streetmap.StreetMapGraph;
import bearmaps.proj2ab.KDTree;
import bearmaps.proj2ab.Point;
import bearmaps.trie.MyTrieSet;

import java.util.*;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 *
 * @author Alan Yao, Josh Hug, ________
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {
    private KDTree kdMap;
    private Map<Point, Node> pointToNode;
    private MyTrieSet placeNames;
    private Map<String, List<Node>> nameToNodeList;

    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        List<Node> nodes = this.getNodes();
        List<Node> nodesWithNeighbors = new ArrayList<>();

        for (Node n : nodes) {
            if (!neighbors(n.id()).isEmpty()) {
                nodesWithNeighbors.add(n);
            }
        }
        pointToNode = new HashMap<>();
        for (Node n : nodesWithNeighbors) {
            pointToNode.put(new Point(n.lon(), n.lat()), n);
        }

        List<Point> points = new ArrayList<>(pointToNode.keySet());
        kdMap = new KDTree(points);

        // code below is for search autocomplete
        placeNames = new MyTrieSet();
        nameToNodeList = new HashMap<>();
        for (Node n : nodes) {
            if (n.name() != null) {
                placeNames.add(cleanString(n.name()));
                if (!nameToNodeList.containsKey(cleanString(n.name()))) {
                    List<Node> nodeList = new LinkedList<>();
                    nodeList.add(n);
                    nameToNodeList.put(cleanString(n.name()), nodeList);
                } else {
                    nameToNodeList.get(cleanString(n.name())).add(n);
                }
            }
        }
    }

    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        return pointToNode.get(kdMap.nearest(lon, lat)).id();
    }


    /**
     * For Project Part III (gold points)
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) {
        List<String> names = new LinkedList<>();
        List<String> cleanNames = placeNames.keysWithPrefix(cleanString(prefix));
        for (String s : cleanNames) {
            names.add(nameToNodeList.get(s).get(0).name());
        }
        return names;
    }

    /**
     * For Project Part III (gold points)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {
        List<Node> nodeList = nameToNodeList.get(cleanString(locationName));
        LinkedList<Map<String, Object>> locations = new LinkedList<>();
        for (Node n : nodeList) {
            Map<String, Object> locationInfo = new HashMap<>();
            locationInfo.put("lat", n.lat());
            locationInfo.put("lon", n.lon());
            locationInfo.put("name", n.name());
            locationInfo.put("id", n.id());
            locations.add(locationInfo);
        }
        return locations;
    }


    /**
     * Useful for Part III. Do not modify.
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

}
