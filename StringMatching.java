/*
*Implementation of KMP algorithm for string parsing
 */
//package comp321;

import java.util.Scanner;

/**
 * Problem Statement: https://open.kattis.com/problems/stringmatching
 * @author Azulia Yue Yang
 */
public class StringMatching {
    private String key; 
    private String message;

    Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        StringMatching test= new StringMatching();
        test.manageInputs();
        
    }
    
    public void manageInputs(){
        while(input.hasNextLine()){
            key = input.nextLine();
            message = input.nextLine();
            this.search(this.parseKey());
            System.out.println();
        }       
    }
    
    /**
     * Pre-parses the input string to improve program efficiency
     */
    public int[] parseKey(){
        int[] keyArr = new int[key.length()];
        keyArr[0] = 0;
        int current = 0;
        int next = 1;
        
        while(next < key.length()){
            if(key.charAt(next) == key.charAt(current)){ //matching prefix/suffix
                keyArr[next] = current+1;
                current++;
            }
            else{ //current character is not a match                 
                while(current > 0 && key.charAt(next) != key.charAt(current)){
                    current = keyArr[current-1];
                }
                if(current == 0) //if the value found is 0, it is kept as 0
                    keyArr[next] = current;
                else //if the value is more than 0, than we want to increment this value to bring it to the index after the character
                    keyArr[next] = current+1;
            }
            next++;
        }    
        
        return keyArr;       
    }
    
    /**
     * Searches the message for the key until end of message is reached
     * Note: key may appear in message more than once
     * Prints the number of times key occurs in message
     */
    public void search(int[] keyArr){
        int currentK = 0; //indicates current character in string key
        int currentM = 0; //indicates current character in string message
        
        while(currentM < message.length()){
            
            if(currentK < key.length()){
                if(key.charAt(currentK) == message.charAt(currentM)){//character is matched
                    currentK++;
                    currentM++;
                }
                else{//mismatch in characters
                    if(currentK > 0)
                        currentK = keyArr[currentK-1]; //revert the pointer to the corresponding substring that contains a prefix to current pointer
                    
                    else
                        currentM++;
                }
                if(currentK == key.length()){ //found a match for key in message
                    System.out.print(currentM-currentK + " ");
                    currentK = keyArr[currentK-1];
                }
            }
            
        }
    }
}
