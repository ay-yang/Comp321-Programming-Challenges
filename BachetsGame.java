
//package comp321;

import java.io.IOException;
import java.util.Scanner;

/**
 * Problem Statement: https://open.kattis.com/problems/bachetsgame
 * @author Yue Yang
 */
public class BachetsGame {
    
    Scanner sc = new Scanner(System.in);
            int numStones;
        int moveSet;
        
        int[] removeSet = new int[10];
        boolean[] win = new boolean[1000001];
        
    
    public static void main(String[] args) throws IOException{
        BachetsGame test = new BachetsGame();
        test.solve();
    
    }
    
    /**
     * Uses a recursive approach to determine who wins given the current number of stones, numStones 
     */
    public void solve(){
        
        while(sc.hasNextInt()){
            numStones = sc.nextInt();
            moveSet = sc.nextInt();
            
            for(int i = 0; i < moveSet; i++){
                removeSet[i] = sc.nextInt();
            }
            
            for (int j = 0; j <= numStones; j++){
                win[j] = false; //initializes everything to false at first 
            }
            
            for(int i = 0; i < moveSet; i++){
                if(removeSet[i] <= numStones){
                    int value = removeSet[i];
                    win[value] = true;
                }
            }
            
            for (int j = 2; j <= numStones; j++){
                if(!win[j]){
                    for(int i = 0; i < moveSet; i++){
                        if(j-removeSet[i] > 0 && j-removeSet[i] <= numStones){
                            if(!win[j - removeSet[i]]){
                                win[j] = true; //Stan wins
                                break;
                            }
                        }
                    }
                }
            }
            
            if(win[numStones]){              
                System.out.println("Stan wins");
            }
            else
                System.out.println("Ollie wins");
            
        
        }
    
    
    }
    
}



