package leecode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EvaluateDivision {
	
public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {

	Map<String,Map<String,Double>> map = new HashMap<>();
	int i=0;
	for(String[] s:equations){
		insertMap(s[0], s[1],values[i] , map);
		insertMap(s[1], s[0], 1.0/values[i], map);
		i++;
	}
	double[] res = new double[queries.length];
	i=0;
	for(String[] query:queries){
		Double resObj = dfs(query[0],query[1],map,new HashSet<String>());
	    res[i++] = resObj == null? -1.0:resObj;	
	}
	return res;
}

private Double dfs(String left, String right, Map<String, Map<String, Double>> map, HashSet<String> visitedSet) {
	String current = left+":"+right;
	if(visitedSet.contains(current)) return null;
	if(!map.containsKey(left)||!map.containsKey(right)) return null;
	if(left.equals(right)) return 1.0;
	
	Map<String,Double> temp = map.get(left);
	visitedSet.add(current);
	for(String str : temp.keySet()){
		Double res = dfs(str, right, map, visitedSet);
		if(res !=null ) return res*temp.get(str);
	}
	visitedSet.remove(current);
	return null;
}

public static void insertMap(String a,String b,Double d,Map<String,Map<String,Double>> map){
	if(!map.containsKey(a)){
		map.put(a, new HashMap<String,Double>());
		map.get(a).put(b, d);
	}else{
		map.get(a).put(b, d);
	}
}


}
