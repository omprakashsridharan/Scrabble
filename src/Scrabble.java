import java.util.*;

public class Scrabble {

    char[] rack;
    HashMap<String,Integer> scores = new HashMap<>();
    HashMap<Integer,ArrayList<String>> hashMap;
    TreeMap<Integer,ArrayList<String>> scoreMap;
    TreeMap<Integer,ArrayList<String>> scenarioTwoMap;

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

    public char[] getRack() {
        return rack;
    }

    public void setRack(char[] rack) {
        this.rack = rack;
    }

    public void computeScenarioTwo(){
        scenarioTwoMap = new TreeMap<>();
        ArrayList<Integer> keys = new ArrayList<Integer>(scoreMap.keySet());
        for(int i=keys.size()-1; i>=0;i--){
            ArrayList<String> arrayList = scoreMap.get(keys.get(i));
            for(String s: arrayList){
                if(isAdjacent(s)){
                    int score = computeScore(s);
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

    public void computeScenarioOne(){
        scoreMap = new TreeMap<>();
        Iterator iterator = hashMap.entrySet().iterator();
        for(int i=1;i<8;i++){
            ArrayList<String> wordList = hashMap.get(i);
            if(wordList != null){
                for(String s: wordList){
                    if(isValidWord(s)){
                        int score = computeScore(s);
                        if(scoreMap.get(score) != null){
                            ArrayList<String> arrayList = scoreMap.get(score);
                            arrayList.add(s);
                            scoreMap.put(score,arrayList);
                        }else {
                            ArrayList<String> arrayList = new ArrayList<>();
                            arrayList.add(s);
                            scoreMap.put(score,arrayList);
                        }
                    }
                }
            }
        }


    }

    public TreeMap<Integer, ArrayList<String>> getScoreMap() {
        return scoreMap;
    }

    public  TreeMap<Integer,ArrayList<String>> constructTreeMap()
    {
        Integer[] scores=new Integer[]{1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
        TreeMap<Integer,ArrayList<String>> scorePlate = new TreeMap<Integer, ArrayList<String>>();

        for (Integer key : hashMap.keySet())
        {
            for(int i=0;i<hashMap.get(key).size();i++)
            {
                int score=0;
                String word= hashMap.get(key).get(i);
                for(int j=0;j<word.length();j++)
                {
                    score=scores[word.charAt(j)-'A']+score;
                }
                if(!scorePlate.containsKey(score))
                {
                    scorePlate.put(score,new ArrayList<String>());
                }
                scorePlate.get(score).add(word);
            }
        }
        System.out.print(scorePlate);
        return scorePlate;
    }

    private int computeScore(String s) {
        int sum = 0;
        for(char c: s.toCharArray()){
           sum += scores.get(c+"");
        }
        return sum;
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

}
