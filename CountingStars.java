//package comp321;
import java.util.Scanner;

/**
 * Problem Statement: https://open.kattis.com/problems/countingstars
 * @author Azulia yue yang
 */


public class CountingStars {
    Scanner input = new Scanner (System.in);
    
    int m;
    int n;
    int[][] array = new int [100][100];
    String message;
    int count;
    int no = 1;
    
    public static void main(String[] args) {
        CountingStars test = new CountingStars();
        
        test.solve();
    }
    
    /**
     * Initiates array based on given input 
     */
    public void solve(){
        while(input.hasNext()){
            m = input.nextInt();
            n = input.nextInt();
            input.nextLine();
            
            for(int i = 0; i < m; i++){
                message= input.nextLine();
                
                for(int j = 0; j < n; j++){
                    if(message.charAt(j) == '#'){
                        array[i][j] = 0;
                    }
                    else if (message.charAt(j) == '-'){
                        array[i][j] = 1; 
                    }
                }
            }
            
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    if(array[i][j] == 1){ // a star is found
                        count++;
                        populate(i,j);
                    }
                }
            }
            System.out.println("Case " + no + ": " + count);
            no++;
            count = 0;
        }
    }
    
    /**
     * Mark off all surrounding areas given a coordinate (x,y) that is a star so that this star is not counted again
     * @param x
     * @param y 
     */
    public void populate(int x, int y){
        array[x][y] =2;
        
        if(x > 0){
            if(array[x-1][y] == 1){
                array[x-1][y] = 2;
                populate(x-1,y);
            }
            
        }
        if(y > 0){
                if(array[x][y-1] == 1){
                    array[x][y-1] = 2;
                    populate(x, y-1);
                }
            }
        if(y < n-1){
                if(array[x][y+1] == 1){
                    array[x][y+1] = 2;
                    populate(x,y+1);
                }
            }
        if(x < m-1){
            if(array[x+1][y] == 1){
                array[x+1][y] = 2;
                populate(x+1, y);
            }
        }
    }
    
}