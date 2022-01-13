package huffman;

import huffman.tree.Node;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
/**
 * The class implementing the Huffman coding algorithm.
 */
public class Huffman {

    /**
     * Build the frequency table containing the unique characters from the String `input' and the number of times
     * that character occurs.
     *
     * @param   input   The string.
     * @return          The frequency table.
     */
    static final boolean readFromFile = false;
    static final boolean newTextBasedOnOldOne = false;
    static PriorityQueue<Node> nodes= new  PriorityQueue<>((o1, o2)->(o1.value<o2.value)? -1:1);
    static TreeMap<Charatcer, String> codes=new TreeMap();
    static String text="";
    static String encoded="";
    static String decoded="";
    static int ASCII[]= new int[128];
    
    public static void main (String[] args) throws FileNotFoundException {
        Scanner scanner = (readFromFile) ? new Scanner(newFile("input.txt")): new Scanner(System.in);
        int decision=1;
        while(decison != -1){
            if(handlingDecision(scanner, decision)) continue;
            decision = consoleMenu(scanner);
            
        }
    }
    
    private static int consoleMenu(Scanner scanner){
        int decision;
        System.out.println(" [1] to enter new text, [2] to encode text, [3] to decode text, [-1] to exit");
        decision=Interger.parseInt(scanner.nextLine());
        if(readFromFile)
            System.out.println("Decision:"+decision+"\n...End of menu'''\n");
        return decision;
    }
    private static boolean handlingDecisiom(Scanner scanner, int decision){
        if(decision==1){
            if(handleNewText(scanner)) return true;
        }else if(decision==2){
            if(handleEncodingNewText(scanner)) return true;
        }else if(decision==3){
            handleDecodingNewText(scanner);
        }
        return false;
    }
    private static void handleDecodingNewText(Scanner scanner){
        System.out.println("enter the text to decode");
        encoded=scanner.nextLine();
        System.out.println("Text to Decode" + encoded);
        decodeText();
    }
    private static void handleEncodingNewText(Scanner scanner){
        System.out.println("enter the text to encode");
        text=scanner.nextLine();
        System.out.println("Text to encode" +text);
        
        if(!IsSameCharacterSet()){
            System.out.println("Not valid input");
            text="";
            return true;
        }
        encodeText();
        return false;
    }
    private static boolean handelNewText(Scanner scanner){
        int oldTextLength= text.length();
        System.out.println("Enter the text:");
        text=scanner.nextLine();
        if(newTextBasedOnOldOne &&(oldTextLength!=0 && !IsSameCharacterSet())){
            System.out.println("Not Vaid Input");
            text="";
            return true;
        }
            ASCII= new int[128];
            nodes.clear();
            codes.clear();
            encoded="";
            decoded="";
            System.out.println("Text:" +text);
            calculateCharIntervals(nodes, true);
            buildTree(nodes);
            generateCodes(nodes.peek(),"");
            printCodes();
            System.out.println("Encoding and Decoding");
            encodeText();
            decodeText();
            return false;
        }
    
    private static boolean IsSameCharacterSet(){
        boolean flag=true;
        for(int i=0;i<text.length();i++)
            if(ASCII(text.charAt(i))==0){
                flag=false;
                break;
            }
        return flag;
    }
   
   

    /**
     * Given a frequency table, construct a Huffman tree.
     *
     * First, create an empty priority queue.
     *
     * Then make every entry in the frequency table into a leaf node and add it to the queue.
     *
     * Then, take the first two nodes from the queue and combine them in a branch node that is
     * labelled by the combined frequency of the nodes and put it back in the queue. The right hand
     * child of the new branch node should be the node with the larger frequency of the two.
     *
     * Do this repeatedly until there is a single node in the queue, which is the Huffman tree.
     *
     * @param freqTable The frequency table.
     * @return          A Huffman tree.
     */
    private static void buildTree(PriorityQueue<Node> vector){
        while(vector.size()>1)
            Vector.add(new Node(Vector.poll(), vector.poll()));
  
    }
    
    
       
    /**
     * Construct the map of characters and codes from a tree. Just call the traverse
     * method of the tree passing in an empty list, then return the populated code map.
     *
     * @param tree  The Huffman tree.
     * @return      The populated map, where each key is a character, c, that maps to a list of booleans
     *              representing the path through the tree from the root to the leaf node labelled c.
     */
    private static void printCodes() {
        System.out.println("....to print Codes...");
        codes.forEach((k,v)->System.out.println("'" +k+ "':"+v));
    } 
    private static void calculateCharIntervals(PriorityQueue<Node> vector, boolean printIntervals){
        if (printIntervals) System.out.println("---intervals...");
        for(int i=0;i<text.length();i++)
            ASCII[text.charAt(i)]++;
        for(int i=0;i<ASCII.length();i++)
            if(ASCII[i]>0){
                vector.add(new Node(ASCII[i])/(text.length()*1.0),((char)i+""));
                if(printIntervals)
                           System.out.println("'" + ((char)i) + ":" + ASCII[i]/text.length()*1.0);
            }
    }
    private static void generateCodes(Node node, String s){
        if(node!=null){
            if(node.right!=null)
                generateCodes(node.right, s+ "1");
            if(node.left!=null)
                generateCodes(node.left,s+"0");
            if(node.left==null && node.right==null)
                codes.put(node.character.charAt(0), s);
        }
    }

    /**
     * Create the huffman coding for an input string by calling the various methods written above. I.e.
     *
     * + create the frequency table,
     * + use that to create the Huffman tree,
     * + extract the code map of characters and their codes from the tree.
     *
     * Then to encode the input data, loop through the input looking each character in the map and add
     * the code for that character to a list representing the data.
     *
     * @param input The data to encode.
     * @return      The Huffman coding.
     */
        private static void encodeText() {
            encoded ="";
            for(int i=0; i<text.length(); i++)
                encoded += codes.get(text.charAt(i));
            System.out.println("Encoded Text:" +encoded);
        }
    /**
     * Reconstruct a Huffman tree from the map of characters and their codes. Only the structure of this tree
     * is required and frequency labels of all nodes can be set to zero.
     *
     * Your tree will start as a single Branch node with null children.
     *
     * Then for each character key in the code, c, take the list of booleans, bs, corresponding to c. Make
     * a local variable referring to the root of the tree. For every boolean, b, in bs, if b is false you want to "go
     * left" in the tree, otherwise "go right".
     *
     * Presume b is false, so you want to go left. So long as you are not at the end of the code so you should set the
     * current node to be the left-hand child of the node you are currently on. If that child does not
     * yet exist (i.e. it is null) you need to add a new branch node there first. Then carry on with the next entry in
     * bs. Reverse the logic of this if b is true.
     *
     * When you have reached the end of this code (i.e. b is the final element in bs), add a leaf node
     * labelled by c as the left-hand child of the current node (right-hand if b is true). Then take the next char from
     * the code and repeat the process, starting again at the root of the tree.
     *
     * @param code  The code.
     * @return      The reconstructed tree.
     */
 

    /**
     * Decode some data using a map of characters and their codes. To do this you need to reconstruct the tree from the
     * code using the method you wrote to do this. Then take one boolean at a time from the data and use it to traverse
     * the tree by going left for false, right for true. Every time you reach a leaf you have decoded a single
     * character (the label of the leaf). Add it to the result and return to the root of the tree. Keep going in this
     * way until you reach the end of the data.
     *
     * @param code  The code.
     * @param data  The encoded data.
     * @return      The decoded string.
     */
        private static void decodeText(){
            decoded="";
            Node node=nodes.peek();
            for(int i=0;i<encoded.length(); ){
                Node tmpNode=node;
                while(tmpNode.left!=null && tmpNode.right!=null && i<encoded.length()){
                    if(encoded.charAt(i)=='1')
                        tmpNode = tmpNode.right;
                    else tmpNode=tmpNode.left;
                    i++;
                }
            if(tmpNode!=null)
                if(tmpNode.character.length()==1)
                    decoded +=tmpNode.character;
                else
                    System.out.println("Input not valid");
            }        
            System.out.println("Decoded Text:"+decoded);
        }  
}
    
class Node{
    Node left,right;
    double value;
    String character;
     
    public Node(double value, String character){
        this.value=value;
        this.character=character;
        left=null;
        right=null;
            
        }
    public Node(Node left, Node right){
        this.value=left.value + right.value;
        character=left.character + right.character;
        if(left.value<right.value)
        {
            this.right=right;
            this.left=left;
        }
        else
        {
            this.right=left;
            this.left=right;
        }
}
