import java.util.ArrayList;

public class Vertex {


    private String metalName;
    private long currentCost; //Transformation cost so far
    private int priceOfTheMetal;
    private int smallestTax;
    private long totalCost;
    private ArrayList<Vertex> possibleTransformations;
    private ArrayList<Long> costsOfTransformations;
    private Vertex pred;
    private boolean toGoldTransformation;
    private boolean visited;
    private int posInTheHeap;

    public Vertex(String metalName){
        this.metalName = metalName;

        this.currentCost = Main.maxCost;
        this.priceOfTheMetal = 0;
        this.smallestTax = 0;
        this.totalCost = this.currentCost + this.smallestTax; //it should work

        this.possibleTransformations = new ArrayList<>();
        this.costsOfTransformations = new ArrayList<>();
        this.pred = null;
        this.toGoldTransformation = false;
        this.visited = false;
        this.posInTheHeap = -1;
    }

//    public String getMetalName(){
//        return this.metalName;
//    }

    public boolean getToGoldTransformation(){
        return this.toGoldTransformation;
    }

    public void setToGoldTransformation(boolean toGoldTransformation){
        this.toGoldTransformation = toGoldTransformation;
    }

    public long getCurrentCost(){
        return this.currentCost;
    }

    public void setCurrentCost(long currentCost){
        this.currentCost = currentCost;
    }

    public Vertex getTransformation(int i){
        return this.possibleTransformations.get(i);
    }

    public long getCostOfTransformation(int i){
        return this.costsOfTransformations.get(i);
    }


    public void addPossibleTransformations(Vertex vertex){
        this.possibleTransformations.add(vertex);
    }

    public void addCostOfTransformation(long cost){
        this.costsOfTransformations.add(cost);
    }

    public int howManyTransitions(){
        return this.costsOfTransformations.size();
    }

    public boolean isVisited(){
        return this.visited;
    }

    public void setVisited(){
        this.visited = true;
    }

    public Vertex getPred() {
        return pred;
    }

    public void setPred(Vertex pred) {
        this.pred = pred;
    }

    public int getPosInTheHeap() {
        return posInTheHeap;
    }

    public void setPosInTheHeap(int posInTheHeap) {
        this.posInTheHeap = posInTheHeap;
    }

    public int getPriceOfTheMetal() {
        return priceOfTheMetal;
    }

    public void setPriceOfTheMetal(int priceOfTheMetal) {
        this.priceOfTheMetal = priceOfTheMetal;
    }

    public int getSmallestTax() {
        return smallestTax;
    }

    public void setSmallestTax(int smallestTax) {
        this.smallestTax = smallestTax;
    }

    public long getTotalCost() {
        return totalCost;
    }

    public void updateTotalCost(){
        this.totalCost = this.currentCost + this.smallestTax;
    }
}
