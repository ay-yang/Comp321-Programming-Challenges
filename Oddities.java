
import java.util.Scanner;
//package comp321;

/**
 * Problem Statement: https://open.kattis.com/problems/oddities
 * @author Azulia Yue Yang
 * 
 */

public class Oddities {
    Scanner input = new Scanner(System.in);
    int no_inputs = 0;
    int[] inputs= new int[20];
    int index = 0;
    int temp = 0;
    
    /**
     * A simple main class that executes the rest of the code by creating an object
     * @param args 
     */
    public static void main(String[] args) {
        
        Oddities test = new Oddities();     
        test.isOddOrEven();
    }
    
    /**
     * This constructor populates the input array with the inputs entered by the user
     */
    public Oddities(){
        no_inputs = input.nextInt();
        temp = no_inputs;
        
        input.nextLine();
        
        while(temp > 0){
            temp--;
            inputs[index] = Integer.parseInt(input.nextLine());
            index++;
        }     
    }
    
    /**This method solves the content of the input array, 
     * determining if each input is even or odd
     */
    public void isOddOrEven(){
        
        int temp = -1;
        index = 0;
        
        while(no_inputs > 0){
            temp = inputs[index];
            no_inputs--;
            index++;
            
            if(temp%2 == 0){
                System.out.println(temp + " is even");
            }
        
            else System.out.println(temp + " is odd");
        }       
    }
}
