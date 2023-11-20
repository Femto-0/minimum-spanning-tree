import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

/*
creating an adjacency matrix
 */
public class Graph {
    int vertices;

    public int getVertices() {
        return vertices;
    }

    int[][] twoDArray;

    public ArrayList<String> getArrList() {
        return arrList;
    }

    ArrayList<String> arrList;
     ArrayList<EdgeList> edgeList;
     int distance;
     String source;
     String destination;

    public Graph(int value) {
        this.vertices = value;
        twoDArray = new int[value][value];
    }
//    public void addNode(Node node){
//    }

    public  void addElements(int source, int destination, int distance) {
        twoDArray[source][destination] = distance;
        twoDArray[destination][source]=distance; //because our graph is a non-directed graph
    }

    public void print() {
        for (int[] ints : twoDArray) { //just another way of writing for(int i=0; i<twoDArray.length-1;i++)
            for (int j = 0; j < twoDArray.length; j++) {
                System.out.printf("%-5d",ints[j]);
            }
            System.out.println();
        }
    }

    public void delete(int source, int destination) {
        twoDArray[source][destination] = 0;
    }
    public  int checkIfNodeExits(int source, int destination){
        if(twoDArray[source][destination]!=0){
            return  twoDArray[source][destination];

        }else{
            return 0;
        }
    }

    public  int[][] readFile(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            arrList = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {//read the file and insert all the unique nodes into the arrList so that we can construct a 2d array
                String[] arr = line.split(" ");
                source = arr[0];
                destination = arr[1];
                distance = Integer.parseInt(arr[2]);
                if (!arrList.contains(source)) { //adding new nodes to the array list
                    arrList.add(source);
                }
                if (!arrList.contains(destination)) {// adding new nodes ot the array list
                    arrList.add(destination);
                }
                if (checkIfNodeExits(arrList.indexOf(source), arrList.indexOf(destination)) == 0) { //checking if it already exists
                    addElements(arrList.indexOf(source), arrList.indexOf(destination), distance); //adding edges to the graph
                }
            }
            vertices=arrList.size();
        }
        return twoDArray;
    }

    public ArrayList<EdgeList> formAnEdgeList(int[][]adjacencyMatrix){
        EdgeList el;
        edgeList= new ArrayList<>();
        for(int i=0; i<adjacencyMatrix.length;i++){
            for (int j=0; j<adjacencyMatrix.length;j++){
                if(adjacencyMatrix[i][j]!=0){
                    el= new EdgeList(i, j,adjacencyMatrix[i][j]);
                   edgeList.add(el);
                }
            }
        }
        return edgeList;
    }

    public ArrayList<EdgeList> kruskalsAlgo(ArrayList<EdgeList> edgeList, int vertices){
        ArrayList<EdgeList> minimumSpanningTree= new ArrayList<>();
        edgeList.sort(Comparator.comparingInt(EdgeList::getWeight));
      DisjointSet ds= new DisjointSet();
      for(int i=0; i<vertices; i++){
          ds.makeSet(i);
        }
      for(EdgeList edge : edgeList){
          int from= ds.find(edge.getSource());
          int to= ds.find(edge.getDestination());
          if(from!=to){
              minimumSpanningTree.add(edge);
          }
          ds.union(from,to);
      }
      return minimumSpanningTree;
    }

    public static void main(String[] args) throws IOException {

        int vertices=8;
        Graph graph = new Graph(vertices);
        ArrayList<EdgeList> edgeList=graph.formAnEdgeList(graph.readFile("src/blackGold.txt"));

        ArrayList<EdgeList> minimumSpanningTree= graph.kruskalsAlgo(edgeList,vertices);

        for(EdgeList edge: minimumSpanningTree){
            System.out.println(graph.getArrList().get(edge.getSource())+"---"+ graph.getArrList().get(edge.getDestination())+"--"+edge.getWeight());
        }

//
//       for(EdgeList element:  edgeList){
//           System.out.print(element);
//           System.out.println();
//       }
//        edgeList.sort(Comparator.comparingInt(EdgeList::getWeight));
//       System.out.println("Sorted: ");
//        for(EdgeList element:  edgeList){
//            System.out.print(element);
//            System.out.println();
//        }
//       graph.print();
    }

}
