import java.util.HashMap;
import java.util.Map;

public class DisjointSet {
    private Map<Integer, Integer> parent;


    public DisjointSet(){
        this.parent=new HashMap<>();

    }
    public void makeSet(int vertex){
        parent.put(vertex,vertex);
    }
    public int find(int vertex){
        if(parent.get(vertex)==vertex){
            return vertex;
        }
        return find(parent.get(vertex));
    }
public void union(int source, int destination){
        int from= find(source);
        int to= find(destination);
        parent.put(from, to); // Make the root of one set the parent of the root of the other set
}

}
