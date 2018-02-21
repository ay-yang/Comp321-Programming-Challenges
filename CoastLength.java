/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
//package comp321;

/**
 * Problem Statement: https://open.kattis.com/problems/coast
 * @author Azulia Yue Yang
 */
public class CoastLength {
    Scanner input = new Scanner(System.in);
    
    private int n;
    private int m;
    String map;
    int[][] finalizedMap;
    private int count;
    
    public static void main(String[] args) {
        
        CoastLength test = new CoastLength();
        test.compute();
 
    }
    
    public CoastLength(){
        n = input.nextInt();
        m = input.nextInt();
        finalizedMap = new int[n][m];       
        count=0;     
        
        input.nextLine();
            
    }
    
    public void oceanize(int row, int col){
        //floods the map in all 4 directions given an ocean square
        //System.out.println("OK");
        finalizedMap[row][col] = 2;
        if(row >0){
            if(finalizedMap[row-1][col]==0)
                //finalizedMap[row-1][col] = 2;
                oceanize(row-1,col);
        }
        if(col >0){
            if(finalizedMap[row][col-1] == 0){
                //finalizedMap[row][col-1] = 2;
                oceanize(row, col-1);
            }
        }
        if(row < n-1){
            if(finalizedMap[row+1][col] == 0){
                //finalizedMap[row+1][col] = 2;
                oceanize(row+1, col);
            }
        }
        if(col < m-1){
            if(finalizedMap[row][col+1] == 0)
                oceanize(row, col+1);
        }
    }
    
    /**
     * Counts the surrounding ocean square (2 represents an ocean square)
     * and number of edges if current block is next to the edge of the map
     * @param row
     * @param col 
     */
    public void count(int row, int col){
            if(row >0){
                if(finalizedMap[row-1][col] == 2)
                   count++;
            }
            else if(row == 0)
                count++;
                    
            if(col > 0){
                if(finalizedMap[row][col-1] == 2)
                    count++;
            }
            else if(col == 0 )
                count++;
                    
            if(row < n-1){
                if(finalizedMap[row+1][col] == 2)
                    count++;
             }
            else if(row == n-1)
                count++;
            if(col < m-1){
                if(finalizedMap[row][col+1] == 2)
                    count++;
            }
            else if(col == m-1)
                 count++;

    }
    
    public void compute(){
        
        //this iteration fills the array with the initial default values of the map as given.
        for(int i = 0; i <n; i++){
            map = input.nextLine();
            for(int j = 0; j <m; j++){
                if(map.charAt(j) == '0'){
                    finalizedMap[i][j] = 0;
                }
                else if(map.charAt(j) == '1'){
                    finalizedMap[i][j] = 1;
                }
            }
        } 
        
        //this iteration is to separate sea water from island water
        for(int row=0; row<n; row++){
            for(int col =0; col <m; col++){
                //System.out.print(finalizedMap[row][col]);
                if(finalizedMap[row][col] == 0){
                    //System.out.println("yes");
                    //check if current block is next to an edge of the map, else leave it alone
                    if(row == 0 || col == 0 || row == n-1 || col == m-1){
                        //System.out.println("REached");
                        oceanize(row,col);         
                    }
                }       
            }          
        }
        
        //this iteration takes care of counting the edges surrounding a land block
        for(int row=0; row<n; row++){
            for(int col =0; col <m; col++){
                if(finalizedMap[row][col] == 1){
                    //we check how many 'sea blocks' surround this current piece of land
                    count(row, col); 
                }
                
            }
        }
        
        System.out.println(count);
    }
}
