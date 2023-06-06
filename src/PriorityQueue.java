import java.util.ArrayList;

public class PriorityQueue {
    private ArrayList<Vertex> myHeap;
    private int size = -1;

    public PriorityQueue(){
        this.myHeap = new ArrayList<>();
    }

    private void swap(int i, int j){
        Vertex temp = this.myHeap.get(i);
        this.myHeap.set(i, myHeap.get(j));
        this.myHeap.set(j, temp);
    }

    private void swapPos(int i, int j){
        int temp = this.myHeap.get(i).getPosInTheHeap();
        this.myHeap.get(i).setPosInTheHeap(this.myHeap.get(j).getPosInTheHeap());
        this.myHeap.get(j).setPosInTheHeap(temp);
    }

    private int getParent(int i){
        return (i-1)/2;
    }

    private int getLeftChild(int i){
        return ((2*i)+1);
    }

    private  int getRightChild(int i){
        return ((2*i)+2);
    }

    private void shiftUp(int i){
        while(i > 0 && this.myHeap.get(getParent(i)).getTotalCost() > this.myHeap.get(i).getTotalCost()){
            swap(i, getParent(i));
            swapPos(i, getParent(i));
            i = getParent(i);
        }
    }

    private void shiftDown(int i){
        int leftChildID;
        int rightChildID;
        int maxID;

        while(i <= this.size){
            maxID = i;
            leftChildID = getLeftChild(i);
            rightChildID = getRightChild(i);

            if(leftChildID <= this.size && this.myHeap.get(i).getTotalCost() > this.myHeap.get(leftChildID).getTotalCost()){
                maxID = leftChildID;
            }

            if(rightChildID <= this.size && this.myHeap.get(i).getTotalCost() > this.myHeap.get(rightChildID).getTotalCost()){
                maxID = rightChildID;
            }

            if(i != maxID){
                swap(i, maxID);
                swapPos(i, maxID);
                i = maxID;
            }else{
                break;
            }
        }
    }

    public void insert(Vertex vertex){
        this.myHeap.add(vertex);
        ++this.size;
        vertex.setPosInTheHeap(this.size);
        shiftUp(this.size);
    }

    public void extractMin(){
        this.myHeap.set(0, this.myHeap.get(size));
        this.myHeap.get(0).setPosInTheHeap(0);
        this.myHeap.remove(size);
        --this.size;
        shiftDown(0);
    }

    public Vertex getMin(){
        return this.myHeap.get(0);
    }

    private void changePriority(int i, long priority, int lowestTax){
        this.myHeap.get(i).setSmallestTax(lowestTax);
        this.myHeap.get(i).setCurrentCost(priority);
        this.myHeap.get(i).updateTotalCost();
        shiftUp(i);
    }

    public void Dijkstra(ArrayList<Vertex> graph){
        int i;
        long newCost;
        int betterOption;
        Vertex min;
        Vertex temp;

        for(i = 0; i < graph.size(); ++i){
            insert(graph.get(i));
        }
        graph.get(0).setSmallestTax((int)(0.5*graph.get(0).getPriceOfTheMetal()));
        while(this.size != -1){
            min = getMin();
            extractMin();
            min.setVisited();
            for(i = 0; i < min.howManyTransitions(); ++i){
                temp = min.getTransformation(i);
                if(!temp.isVisited()){
                    newCost = min.getCurrentCost() + min.getCostOfTransformation(i);
                    if(min.getSmallestTax() > temp.getSmallestTax()){
                        betterOption = temp.getSmallestTax();
                    }else{
                        betterOption = min.getSmallestTax();
                    }
                    if(temp.getTotalCost() > newCost + betterOption){
                        changePriority(temp.getPosInTheHeap(), newCost, betterOption);
                        temp.setPred(min);
                    }
                }
            }
        }

    }




}
