import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
creating an adjacency matrix
 */
public class Graph {
    public  int value;
     int[][] twoDArray;
     ArrayList<String> arrList;
     int distance;
     String source;
     String destination;

    public Graph(int value) {
        this.value = value;
        twoDArray = new int[value][value];
    }
    public void addNode(Node node){
    }

    public  void addElements(int source, int destination, int distance) {
        twoDArray[source][destination] = distance;
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
    public  boolean checkIfNodeExits(int source, int destination, int distance){
        if(twoDArray[source][destination]==distance){
            return true;
        }else{
            return false;
        }
    }

    public  void readFile(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            arrList = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {//read the file and insert all the unique nodes into the arrList so that we can construct a 2d array
                String[] arr = line.split(" ");
                source=arr[0];
                destination=arr[1];
                distance= Integer.parseInt(arr[2]);
                if(!arrList.contains(source)){ //adding new nodes to the array list
                    arrList.add(source);
                }
                if(!arrList.contains(destination)){// adding new nodes ot the array list
                    arrList.add(destination);
                }
                if(!checkIfNodeExits(arrList.indexOf(source),arrList.indexOf(destination),distance)) { //checking if it already exists
                    addElements(arrList.indexOf(source), arrList.indexOf(destination), distance); //adding edges to the graph
                }
            }
            value=arrList.size();
        }
    }

    public static void main(String[] args) throws IOException {

        Graph graph = new Graph(8);
        graph.readFile("src/blackGold.txt");
        graph.print();
    }

}
