
package comp321;

import java.util.Hashtable;
import java.util.Scanner;

/**
 * Problem Statement: https://open.kattis.com/problems/t9spelling
 * @author Azulia Yue Yang
 */
public class T9spelling {
    
    Scanner input = new Scanner (System.in);
    
    Hashtable<Character, String> parser = new Hashtable<>();  
    String message;

    public static void main(String[] args) {
        T9spelling test = new T9spelling();
        
        test.populate();
        test.solve();
    }
    
    /**
     * Populates our dictionary with all character and their corresponding encoding 
     */
    public void populate(){
        parser.put('a', "2");
        parser.put('b', "22");
        parser.put('c', "222");
        parser.put('d', "3");
        parser.put('e', "33");
        parser.put('f', "333");
        parser.put('g', "4");
        parser.put('h', "44");
        parser.put('i', "444");
        parser.put('j', "5");
        parser.put('k', "55");
        parser.put('l', "555");
        parser.put('m', "6");
        parser.put('n', "66");
        parser.put('o', "666");
        parser.put('p', "7");
        parser.put('q', "77");
        parser.put('r', "777");
        parser.put('s', "7777");
        parser.put('t', "8");
        parser.put('u', "88");
        parser.put('v', "888");
        parser.put('w', "9");
        parser.put('x', "99");
        parser.put('y', "999");
        parser.put('z', "9999");
        parser.put(' ', "0");
        
    }
    
    /**
     * Reads string line by line and converts it into its number encoding 
     */
    public void solve(){
        int testCases = input.nextInt();
        input.nextLine();
        String prev_msg;
        int count = 1;
        
         for (int i = 0; i < testCases; i++){
             message = input.nextLine();
             char prev;
             if(message.length() > 0)
                prev = message.charAt(0);
             else prev = '\0';
             
             System.out.print("Case #" + count +": ");
             count++;
             for(int l = 0; l < message.length();l++){
                     prev_msg = parser.get(prev);
                     if(l > 0){ //checks for space 
                        if(prev_msg.contains(parser.get(message.charAt(l))) || ( parser.get(message.charAt(l))).contains(prev_msg))
                            System.out.print(" ");
                     }
                     System.out.print(parser.get(message.charAt(l)));
                     prev = message.charAt(l);
             }
             System.out.println();          
            
        }
        
    }
    
}