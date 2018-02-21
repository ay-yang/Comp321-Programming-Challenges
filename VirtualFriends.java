
//package comp321;

import java.util.*;

/**
 * Problem Statement: https://open.kattis.com/problems/virtualfriends
 * @author Azulia Yue Yang
 */
public class VirtualFriends {
    
    Scanner input = new Scanner(System.in);
    private int testCases;
    private int instances;
    String tempName1;
    String tempName2;
    
    Hashtable<String, String> parents = new Hashtable<String, String> (); //keeps track of the parent of the current person-> person: parent
    Hashtable<String, Integer> link = new Hashtable<>(); //keeps track of how many people are in a network-> person: no. of people in their network
                                                        //note: by default, when a person is created in this network, their value here is initialized to 1
                                                        // but only a parent/representative's value will ever be updated. 
    
    
    public static void main(String[] args) {
        
        VirtualFriends test = new VirtualFriends();
               
    }
    
    public VirtualFriends(){
        testCases = input.nextInt();
        input.nextLine();
        
        while(testCases > 0 ){ //the number of test cases
            testCases--;
            instances = input.nextInt();
            
            input.nextLine();
            
            while(instances >0){ //the numbers of friendships formed
                instances--;
                tempName1 = input.next();
                tempName2 = input.next();
                
                processFriends();
                
                input.nextLine();   
            }
            
            //re-initialize the hashtables after every test case
            parents.clear();
            link.clear();
        }
    }
    
    /**
     * Calls the corresponding methods to process the problem
     * First creates 2 sets, under the 2 names, if they do not already exist
     * Then, unite them into the same set, if they are not already part of the same set
     */
    public void processFriends(){
        makeSet(tempName1);
        makeSet(tempName2);
        
        union(tempName1, tempName2);

        System.out.println(link.get(find(tempName1)));
    }
    
    /**
     * Initializes a set with only 1 person in it, if this person does not already exist 
     * If the person already exists, does nothing. 
     * @param name: the name of the person in the set
     */
    public void makeSet(String name){
        if(!parents.containsKey(name)){ //checks if this person already exists
            parents.put(name, name); //by default, a node is its own parent
            link.put(name, 1); //by default, a network has size 1 (contains itself)
        }
    }
    
    /**
     * 
     * @param name: person whom we are interested in looking up the parent
     * @return the parent of associated to the current name
     */
    public String find(String name){
        String temp = name;
        while(!parents.get(temp).equals(temp)){ //goes up the tree until parent is found
            temp = parents.get(temp);
        }      
        return temp;
    }
    
    /**
     * Links 2 sets together by changing the corresponding parent in one of the sets
     * @param set1: the name of the first person we want to link
     * @param set2: name of the 2nd person we want to link 
     */
    public void union(String set1, String set2){
        
        String root1 = find(set1);
        parents.put(set1, root1); //this is to update the root value to make the program run faster
        String root2 = find(set2); 
        parents.put(set2, root2); 
        
        int linkRoot1 = link.get(root1);
        int linkRoot2 = link.get(root2);
        
        //The rank is used to evaluate the approximate size of the network, so we don't get a very deep tree by appending 2 things randomly
        if(!root1.equals(root2)){
            if(linkRoot1 < linkRoot2){ //root1 is appended to root2 (root2 becomes root1's parent
                parents.put(root1, root2);
                link.put(root2, linkRoot1 + linkRoot2); //updates the number of people in this network
            }
            
            else if(linkRoot1 >= linkRoot2){ //root2 is appended to root1
                parents.put(root2, root1);
                link.put(root1, linkRoot1 + linkRoot2);
            }
        }
    }
}

