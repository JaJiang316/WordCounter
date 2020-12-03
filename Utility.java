import java.util.*;

class Utility {
    public static void main(String args[]){
        Counter<String, String> x = new WordSet<String, String>();
        Utility.run(x);
     }
   public static void run(Counter<String, String> words){
      //Variables
      Counter<String, String> sortedWords = new WordSet<String, String>();
      Scanner scnr = new Scanner(System.in);
      String[] testInput = "A user interface is like a joke. If you have to explain it, it is not that good. What is a UX designer's favourite book? A macbook.".split("[\\s\t\n]");
      
      //Read in input
      if(scnr.hasNext()){
    	  System.out.println("User Input\n***************************");
    	  while (scnr.hasNext()) {
    		String word = scnr.next().replaceAll("[^a-zA-Z\\d ]+$", "");
    	  	String keyWord = Utility.dropS(word);
    	  	words.put(keyWord, word);
    	  }
      }
      //Default test case
      else {
    	  System.out.println("Default Input\n***************************");
    	  for(String word : testInput) {
			  String keyWord = Utility.dropS(word);
			  words.put(keyWord, word.replaceAll("[^a-zA-Z\\d ]+$", ""));
    	  }
      }
      //Get keys and sort list
      ArrayList<String> sortedKeys = new ArrayList<String>(words.keySet());     
      Collections.sort(sortedKeys);  
      
      //Instance JSON Object to store keys
      JSONObject wordList = new JSONObject();
      
      // Display the TreeMap which is naturally sorted 
      for (String w : sortedKeys){
         if (words.getCount(w) >= 3){
            String[] strVals = words.get(w).split(",");
            ArrayList<String> vals = new ArrayList<String>();
            //Split instances of word and create array
            for(int i = 1; i < strVals.length; i++)
               vals.add(strVals[i]);
            //Sort vals ArrayList
            Collections.sort(vals);
            //Instance JSON Object
            JSONObject wordItemsObj = new JSONObject();
            //Add entry for unique forms
            wordItemsObj.put("forms", vals);
            //Add entry for count
            wordItemsObj.put("count", words.get(w).split(",")[0]);
            wordList.put(w,wordItemsObj);
         }
      }
      //Output JSON
      System.out.println(wordList);
   }
   
   static String dropS(String word) {
      String ans = word.replaceAll("[^a-zA-Z ]+$", "").toLowerCase();
      if (ans.endsWith("'s"))
         return ans.substring(0, ans.length() - 2);
      else if (ans.endsWith("s"))
         return ans.substring(0, ans.length() - 1);
      else if (ans.endsWith("ed"))
         return ans.substring(0, ans.length() - 2);
      else if (ans.endsWith("ing"))
         return ans.substring(0, ans.length() - 3);
      return ans;
   }
}