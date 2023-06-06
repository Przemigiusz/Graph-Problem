import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static long maxCost = 500000000000L;

    public static int howManyTypesOfMetals(){
        System.out.print("How many types of metals do You want to enter: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        return n;
    }

    public static void enteringTypesOfMetal(ArrayList<String> typesOfMetals, int n){
        for(int i = 0; i < n; ++i){
            System.out.println("Enter type of metal: ");
            typesOfMetals.add(scanner.nextLine());
        }
    }

    public static void enteringMetalPrices(ArrayList<Integer> metalPrices, ArrayList<String> typesOfMetal, int n){
        for(int i = 0; i < n; ++i){
            System.out.println("Enter price of the " + typesOfMetal.get(i) + " : ");
            metalPrices.add(scanner.nextInt());
            scanner.nextLine();
        }
    }

    public static int howManyProcesses(){
        System.out.print("how many transformation processes are known: ");
        int n = scanner.nextInt();
        return n;
    }

    public static long[][] fillingTransformationCostMatrix(int n, int numberOfTransformationsProcesses){
        long[][] matrix = new long[n][n];
        int i;
        int j;
        for(i = 0; i < n; ++i){
            for(j = 0; j < n; ++j){
                if(i != j){
                    matrix[i][j] = maxCost;
                }else{
                    matrix[i][j] = 0;
                }

            }
        }
        int x = 0;
        int y = 0;
        for(i = 0; i < numberOfTransformationsProcesses; ++i){
            x =scanner.nextInt();
            scanner.nextLine();
            y = scanner.nextInt();
            scanner.nextLine();
            matrix[x-1][y-1] = scanner.nextInt();
            scanner.nextLine();
            System.out.println("-----------");
        }
        return matrix;
    }

    public static void creatingListOfVertices(ArrayList<Vertex> graphVertices, ArrayList<Integer> metalPrices, ArrayList<String> typesOfMetal, int n){
        Vertex tempVertex;

        for(int i = 0; i < n; ++i){
            tempVertex = new Vertex(typesOfMetal.get(i));
            tempVertex.setPriceOfTheMetal(metalPrices.get(i));
            tempVertex.setSmallestTax((int)(0.5*tempVertex.getPriceOfTheMetal()));
            tempVertex.updateTotalCost();
            graphVertices.add(tempVertex);
            if(i == 0){
                tempVertex.setCurrentCost(0);
                tempVertex.updateTotalCost();
            }
        }
    }

    public static void fillingInformationAboutTransformations(ArrayList<Vertex> graphVertices, long[][] transformationCostMatrix, int n){
        int i;
        int j;

        for(i = 0; i < n; ++i){
            if(transformationCostMatrix[i][0] != maxCost){
                graphVertices.get(i).setToGoldTransformation(true);
            }else{
                graphVertices.get(i).setToGoldTransformation(false);
            }
            for(j = 0; j < n; ++j){
                if(transformationCostMatrix[i][j] != maxCost && j != i){
                    graphVertices.get(i).addPossibleTransformations(graphVertices.get(j));
                    graphVertices.get(i).addCostOfTransformation(transformationCostMatrix[i][j]);
                }
            }
        }
    }

    public static void theSequence(ArrayList<Vertex> graphVertices, long[][] transformationCostMatrix, int n){
        long max = maxCost;
        boolean flag = false;
        long temp;
        for(int i = 0; i < n; ++i){
            if(graphVertices.get(i).getToGoldTransformation()){
                if(i != 0){
                    temp = graphVertices.get(i).getTotalCost() + transformationCostMatrix[i][0];
                }else{
                    temp = graphVertices.get(0).getSmallestTax();
                }
                if(temp < max){
                    max = temp;
                }
            }
        }
        if(!flag){
            System.out.println("Minimum cost: " + max);
        }
    }

    public static void main(String[] args) {
        int n = howManyTypesOfMetals();

        ArrayList<String> typesOfMetal = new ArrayList<>(); //Jakie metale mamy
        enteringTypesOfMetal(typesOfMetal, n);

        ArrayList<Integer> metalPrices = new ArrayList<>(); //Jakie sa ceny tych metali
        enteringMetalPrices(metalPrices, typesOfMetal, n);

        int numberOfTransformationsProcesses = howManyProcesses(); //Ile jest mozliwych przemian
        long[][] transformationCostMatrix = fillingTransformationCostMatrix(n, numberOfTransformationsProcesses);

        ArrayList<Vertex> graphVertices = new ArrayList<>();
        creatingListOfVertices(graphVertices, metalPrices, typesOfMetal, n);
        fillingInformationAboutTransformations(graphVertices, transformationCostMatrix, n);

        PriorityQueue queue = new PriorityQueue();
        queue.Dijkstra(graphVertices);

        theSequence(graphVertices, transformationCostMatrix, n);
        scanner.close();
    }
}
