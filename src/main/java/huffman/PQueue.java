package huffman;

import huffman.tree.Node;

import java.util.ArrayList;
import java.util.List;
/**
 * A priority queue of @Node@ objects. Each node has an int as its label representing its frequency.
 * The queue should order objects in ascending order of frequency, i.e. lowest first.
 */
public class PQueue {
    final int DEFAULTMAX = 256;
    HuffmanNode [] nodes;
    int capacity,total

    private List<Node> queue;

    public PQueue() {
       capacity = DEFAULTMAX;
		total = 0;
		nodes = new Node[capacity];
    }
     public PQueue(int max) {
       capacity = max;
		total = 0;
		nodes = new Node[capacity];
    }

    /**
     * Add a node to the queue. The new node should be inserted at the point where the frequency of next node is
     * greater than or equal to that of the new one.
     * @param n The node to enqueue.
     */
    public void enqueue(Node n) {
        if(isFull()) return false;
		
		if(isEmpty()){ //first element?
			nodes[total++] = n;
			return true;
		}
		int i = total-1,pos;
		while(i >= 0){
			if(nodes[i].freq < n.freq){
				break;
				}
			i--;
			}
		pos = total-1;
		while(pos >= i+1){
			nodes[pos+1] = nodes[pos];
			pos--;
			}
		nodes[i+1] = n;
		total++;
		return true;
	}
        throw new UnsupportedOperationException("Method not implemented");
    }

    /**
     * Remove a node from the queue.
     * @return  The first node in the queue.
     */
    public Node dequeue() {
        if(isEmpty()) return null;
		Node ret = nodes[0];
		total--;
		for(int i = 0;i<total;i++)
		nodes[i] = nodes[i+1];
		return ret;
		}
	
	public boolean isEmpty(){
		return (total == 0);
		}
	
	public boolean isFull(){
		return (total == capacity);
		}
		
	public int totalNodes(){
		return total;
		}
		

        throw new UnsupportedOperationException("Method not implemented");
    }

    /**
     * Return the size of the queue.
     * @return  Size of the queue.
     */
    public int size() {
        throw new UnsupportedOperationException("Method not implemented");
    }
}
