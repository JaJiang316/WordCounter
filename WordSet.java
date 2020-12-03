import java.util.*;

public class WordSet<K extends Comparable<K>, V> implements Counter<K, V> {
      public String get(K word) {
         //Enter code here
         StringBuilder words = new StringBuilder();
         words.append(count.get(word).toString() + ",");
         for(V w: map.get(word)){
            words.append(w + ","); 
         }
         words.replace(words.length()-1,words.length(),"");
         return words.toString();
      }

     public int getCount(K word) {
         //Enter code here
         //fine
         return count.get(word);
     }

      public Set<K> keySet() {
         //Enter code here
    	   return map.keySet();
      }

      public void put(K keyWord, V word) {
         //Enter code here
         if(!map.containsKey(keyWord)){
            set = new HashSet<>();
            set.add(word);
            map.put(keyWord, set);
            count.put(keyWord,1);
         }
         else if(map.containsKey(keyWord)){
            map.get(keyWord).add(word);
            count.put(keyWord, count.get(keyWord) + 1);
         }
         //System.out.println(map.get(keyWord));
         //System.out.println(count.get(keyWord));
      }
      // declare any required instance variables or helpful auxiliary types and methods here
      Map<K, Set<V>> map = new HashMap<>();
      Set<V> set = new HashSet<>();
      Map<K, Integer> count = new HashMap<>();
}