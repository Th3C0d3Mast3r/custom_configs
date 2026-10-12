/*
 * This is a Custom Boiler Plate for Java
 * Use this with Java21 for CP sites
 * @Th3C0d3Mast3r
 *
 * To make changes, using IntelliJ, do following:-
 * ctrl+alt+s => Live Templates => 'user'
 * Once there, make the changes and all for the same
 * */

// link:- 

import java.util.*;
import java.math.*;

public class {
    static Scanner obj=new Scanner(System.in);
    public static void main(String args[]){
        run();
    }

    private static void run(){
        int t=obj.nextInt();
        while(t-->0){
            mainFunction();
        }
    }

    private static void mainFunction(){
        
    }
    // the below present are some of the handy functions-for quick use

    // maps.put(a[i], maps.getOrDefault(a[i], 0)+1);

    // FOR ITERATING HASHMAP:-
    // for (Integer key : mapA.keySet()) {
    //    int freqA = mapA.getOrDefault(key, 0);
    //    int freqB = mapB.getOrDefault(key, 0); // Get from mapB
    // }

    // GCD using the Euclidean Algorithm (iterative version)
    // GCD mein add only 2 numbers-2 ka hi nikalega
    private static int computeGCD(int num1, int num2) {
        while(num2!=0) {
            int temp=num2;
            num2=num1%num2;
            num1=temp;
        }
        return num1;
    }

    // LCM using GCD (handling overflow cases)
    private static int computeLCM(int num1, int num2) {
        if(num1==0||num2==0) return 0; // LCM of 0 with anything is 0
        return Math.abs(num1/computeGCD(num1,num2)*num2); // Avoid overflow
    }

    // Sieve of Eratosthenes with debug prints
    private static boolean[] generatePrimes(int limit) {
        boolean[] isPrime=new boolean[limit+1];
        Arrays.fill(isPrime, true);
        isPrime[0]=isPrime[1]=false;

        for(int i=2; i*i<=limit; i++) {
            if(isPrime[i]) {
                for(int j=i*i; j<=limit; j+=i) {
                    isPrime[j]=false;
                }
                System.out.println("Marked multiples of "+i+" as non-prime.");
            }
        }
        return isPrime;
    }

    // Binary search with debugging statements
    // would return location if found-parameter goes (array, target)
    private static int performBinarySearch(int[] sortedArray, int target) {
        int left=0, right=sortedArray.length-1;

        while(left<=right) {
            int mid=left+(right-left)/2;
            System.out.println("Checking mid index: "+mid);

            if(sortedArray[mid]==target) {
                System.out.println("Found "+target+" at index "+mid);
                return mid;
            } else if(sortedArray[mid]<target) {
                left=mid+1;
            } else {
                right=mid-1;
            }
        }
        return -1;
    }

    private static boolean probablePrime(String s){
        BigInteger big=new BigInteger(s);
        return big.isProbablePrime(30);
    }

    // QuickSort with slight modifications
    private static void quickSort(int[] arr, int low, int high) {
        if(low<high) {
            int pivotIndex=partition(arr, low, high);
            quickSort(arr, low, pivotIndex-1);
            quickSort(arr, pivotIndex+1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot=arr[high], i=low-1;

        for(int j=low; j<high; j++) {
            if(arr[j]<pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i+1, high);
        return i+1;
    }

    private static void swap(int[] arr, int i, int j) {
        if(i!=j) { // Avoid unnecessary swaps
            int temp=arr[i];
            arr[i]=arr[j];
            arr[j]=temp;
        }
    }

    // DFS (Depth First Search)
    private static void depthFirstSearch(List<List<Integer>> graph, int node, boolean[] visited) {
        visited[node]=true;
        System.out.println("Visiting node: "+node);

        for(int neighbor:graph.get(node)) {
            if(!visited[neighbor]) {
                depthFirstSearch(graph, neighbor, visited);
            }
        }
    }

    // BFS (Breadth First Search)
    private static void breadthFirstSearch(List<List<Integer>> graph, int startNode) {
        boolean[] visited=new boolean[graph.size()];
        Queue<Integer> queue=new LinkedList<>();

        queue.add(startNode);
        visited[startNode]=true;

        while(!queue.isEmpty()) {
            int currentNode=queue.poll();
            System.out.println("Processing node: "+currentNode);

            for(int neighbor:graph.get(currentNode)) {
                if(!visited[neighbor]) {
                    visited[neighbor]=true;
                    queue.add(neighbor);
                }
            }
        }
    }

    // KMP Algorithm (Knuth-Morris-Pratt) with additional comments
    private static int[] computeLPSArray(String pattern) {
        int length=0;
        int[] lps=new int[pattern.length()];
        int i=1;

        while(i<pattern.length()) {
            if(pattern.charAt(i)==pattern.charAt(length)) {
                lps[i++]=++length;
            } else {
                if(length!=0) {
                    length=lps[length-1];
                } else {
                    lps[i++]=0;
                }
            }
        }
        return lps;
    }

    private static boolean isPatternFound(String text, String pattern) {
        int textLength=text.length(), patternLength=pattern.length();
        int[] lps=computeLPSArray(pattern);

        int i=0, j=0;
        while(i<textLength) {
            if(pattern.charAt(j)==text.charAt(i)) {
                i++; j++;
            }

            if(j==patternLength) {
                System.out.println("Pattern found in text.");
                return true;
            } else if(i<textLength && pattern.charAt(j)!=text.charAt(i)) {
                j=(j!=0) ? lps[j-1] : 0;
            }
        }

        System.out.println("Pattern not found.");
        return false;
    }

    // implementing tries in boiler-plate
    static class Trie {
        private TrieNode root;

        private static class TrieNode {
            TrieNode[] children = new TrieNode[26];
            boolean wordEnds;
        }

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                int index = ch - 'a';
                if (node.children[index] == null)
                    node.children[index] = new TrieNode();
                node = node.children[index];
            }
            node.wordEnds = true;
        }

        public boolean search(String word) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                int index = ch - 'a';
                if (node.children[index] == null)
                    return false;
                node = node.children[index];
            }
            return node.wordEnds;
        }

        public boolean startsWith(String prefix) {
            TrieNode node = root;
            for (char ch : prefix.toCharArray()) {
                int index = ch - 'a';
                if (node.children[index] == null)
                    return false;
                node = node.children[index];
            }
            return true;
        }
    }
}
