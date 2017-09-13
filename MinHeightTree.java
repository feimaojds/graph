package leecode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinHeightTree {

	 public List<Integer> findMinHeightTrees(int n, int[][] edges) {
		    if(n<1) {
		    	List<Integer> list = new ArrayList<Integer>();
		        list.add(0);
		        return list;
		    }
	        List<Set<Integer>> adj = new ArrayList<Set<Integer>>();
	        for(int i=0;i<n;i++){
	        	adj.add(new HashSet<Integer>());
	        }
	        for(int[] s : edges){
	        	adj.get(s[0]).add(s[1]);
	        	adj.get(s[1]).add(s[0]);
	        }
	        List<Integer> leaves = new ArrayList<Integer>();
	        for(int i=0;i<n;i++){
	        	if(adj.get(i).size()==1){
	        		leaves.add(i);
	        	}
	        }
	        while(n>2){
	        	n = n-leaves.size();
	        	List<Integer> newLeaves = new ArrayList<Integer>();
	        	for(int leave:leaves){
	        		int j = adj.get(leave).iterator().next();
	        		adj.get(j).remove(leave);
	        		if(adj.get(j).size()==1) newLeaves.add(j);
	        	}
	        	leaves = newLeaves;
	        }
	        return leaves;
	    }
}
