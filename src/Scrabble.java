import java.util.*;

public class Scrabble {

    char[] rack;
    HashMap<String,Integer> scores = new HashMap<>();
    HashMap<Integer,ArrayList<String>> hashMap;
    TreeMap<Integer,ArrayList<String>> scenarioOneMap;
    TreeMap<Integer,ArrayList<String>> scenarioTwoMap;
    TreeMap<Integer,ArrayList<String>> scenarioThreeMap;

    public Scrabble(String rack,HashMap<Integer,ArrayList<String>> hs) {
        this.rack = rack.toCharArray();
        this.hashMap = hs;
        scores.put("A",1);
        scores.put("E",1);
        scores.put("I",1);
        scores.put("L",1);
        scores.put("N",1);
        scores.put("O",1);
        scores.put("R",1);
        scores.put("S",1);
        scores.put("T",1);
        scores.put("U",1);
        scores.put("D",2);
        scores.put("G",2);
        scores.put("B",3);
        scores.put("C",3);
        scores.put("M",3);
        scores.put("P",3);
        scores.put("F",4);
        scores.put("H",4);
        scores.put("V",4);
        scores.put("W",4);
        scores.put("Y",4);
        scores.put("K",5);
        scores.put("J",8);
        scores.put("X",8);
        scores.put("Q",10);
        scores.put("Z",10);
    }

    public void computeScenarioOne(){
        scenarioOneMap = new TreeMap<>();
        Iterator iterator = hashMap.entrySet().iterator();
        for(int i=1;i<8;i++){
            ArrayList<String> wordList = hashMap.get(i);
            if(wordList != null){
                for(String s: wordList){
                    if(isValidWord(s)){
                        int score = computeScore(s);
                        PopulateMap(s, score, scenarioOneMap);
                    }
                }
            }
        }
        System.out.println(scenarioOneMap.get(scenarioOneMap.size()).toString());

    }

    boolean isValidWord(String s){
        boolean[] isvalid = new boolean[Character.MAX_VALUE + 1];
        for (char c : rack) {
            isvalid[c] = true;
        }
        for (char c: s.toCharArray()){
            if(!isvalid[c]){
                return false;
            }
        }
        return true;
    }

    public void computeScenarioTwo(){
        scenarioTwoMap = new TreeMap<>();
        ArrayList<Integer> keys = new ArrayList<Integer>(scenarioOneMap.keySet());
        for(int i=keys.size()-1; i>=0;i--){
            ArrayList<String> arrayList = scenarioOneMap.get(keys.get(i));
            for(String s: arrayList){
                if(isAdjacent(s)){
                    int score = computeScore(s);
                    PopulateMap(s, score, scenarioTwoMap);
                }
            }
        }
        System.out.println(scenarioTwoMap.get(scenarioTwoMap.size()).toString());

    }

    private boolean isAdjacent(String s) {
        int count = 0;
        for (char c: rack){
            if(s.contains(""+c)){
                count++;
            }
        }
        return count==s.length()-1;
    }

    public void computeScenarioThree(Map<Character,Integer> constraintMap){
        scenarioThreeMap = new TreeMap<>();
        ArrayList<Integer> keys = new ArrayList<Integer>(scenarioOneMap.keySet());
        for(int i=keys.size()-1; i>=0;i--){
            ArrayList<String> arrayList = scenarioOneMap.get(keys.get(i));
            for(String s: arrayList){
                if(hasSatisfiedConstraints(s,constraintMap)){
                    int score = computeScore(s);
                    PopulateMap(s, score, scenarioThreeMap);
                }
            }
        }
        System.out.println(scenarioThreeMap.get(scenarioThreeMap.size()).toString());

    }

    private boolean hasSatisfiedConstraints(String s,Map map) {
        Iterator<Map.Entry<Character, Integer>> itr = map.entrySet().iterator();

        while(itr.hasNext())
        {
            Map.Entry<Character, Integer> entry = itr.next();
            if(s.indexOf(entry.getKey()) != entry.getValue()){
                return false;
            }
        }
        return true;
    }

    private void PopulateMap(String s, int score, TreeMap<Integer, ArrayList<String>> scenarioTwoMap) {
        if(scenarioTwoMap.get(score) != null){
            ArrayList<String> a = scenarioTwoMap.get(score);
            a.add(s);
            scenarioTwoMap.put(score,a);
        }else {
            ArrayList<String> a = new ArrayList<>();
            a.add(s);
            scenarioTwoMap.put(score,a);
        }
    }

    private int computeScore(String s) {
        int sum = 0;
        for(char c: s.toCharArray()){
           sum += scores.get(c+"");
        }
        return sum;
    }

}
