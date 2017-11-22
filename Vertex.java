package assignment3;
import java.io.*;
import java.util.*;

public class Vertex<L> {
    private String word;
    private HashMap<Vertex, Integer> edges;

    public Vertex(String s){
        word = s;
        edges = new HashMap();
    }

    public String toString(){
        return word;
    }

    public void addEdge(Vertex v) {
        try {
            if (v == null) {
                throw new NullPointerException();
            }
        if (this.edges.containsKey(v)) {
            /* connection as already in graph */
            Integer weight = this.edges.get(v);
            //this.edges.remove(v);
            weight = weight + 1;
            this.edges.put(v, weight);
        } else {
            /* completely new edge */
            this.edges.put(v, 1);
        }
    }
    catch(NullPointerException ex) {
        System.out.println("E: Cannot add null edge");
    }
        return;
    }



    public String findBridge(Vertex v){
        ArrayList<Vertex> possibleBridges = new ArrayList(this.edges.size());
        possibleBridges.addAll(this.edges.keySet());

        Vertex bridge = null;

        for(int i = 0; i < possibleBridges.size(); i ++){

            if(possibleBridges.get(i).isBridge(v)){
                if(bridge == null){
                    /* a bridge exists */
                    bridge = possibleBridges.get(i);
                    //System.out.println("bridge set to: " + possibleBridges.get(i) + ", weight " + edges.get(possibleBridges.get(i)) );
                }
                else{
                    /* if weight of possible bridge is greater than weight of current bridge */
                    if(this.edges.get(bridge) < this.edges.get(possibleBridges.get(i)) ){
                        bridge = possibleBridges.get(i);
                        //System.out.println("bridge set to: " + possibleBridges.get(i) + ", weight " + edges.get(possibleBridges.get(i)) );
                    }

                }
            }
        } //end for loop
        if(bridge == null){
            return "";
        }
        else{
            return (bridge.toString());
        }
        //return v.toString();
    }

    private boolean isBridge(Vertex destination){
        if(this.edges.containsKey(destination)){
            return true;
        }
        return false;
    }

    /* For Debugging */
    public void printConnections(){
        System.out.print("'" + word + "' has the map: ");
        System.out.println(edges);
    }
}
