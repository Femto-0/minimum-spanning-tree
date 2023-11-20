import java.util.ArrayList;
import java.util.List;

public class MSTPathFinder {
    private List<Integer> path;
    private boolean[] visited;

    public MSTPathFinder() {
        this.path = new ArrayList<>();
    }

    public List<Integer> findPathInMST(List<EdgeList> mst, int source, int destination) {
        visited = new boolean[mst.size()];
        path.clear();
        dfs(mst, source, destination);
        return path;
    }

    private void dfs(List<EdgeList> mst, int current, int destination) {
        visited[current] = true;
        path.add(current);

        if (current == destination) {
            return;
        }

        for (EdgeList edge : mst) {
            int neighbor;
            if (edge.getSource() == current && !visited[(neighbor = edge.getDestination())]) {
                dfs(mst, neighbor, destination);
            } else if (edge.getDestination() == current && !visited[(neighbor = edge.getSource())]) {
                dfs(mst, neighbor, edge.source);
            }
        }
    }
}