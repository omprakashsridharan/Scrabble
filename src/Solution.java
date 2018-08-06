import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Solution {

    public static HashMap<Integer, ArrayList<String>> hashMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        readFromFile();
        Scrabble scrabble = new Scrabble("ABCDEG",hashMap);
      //  System.out.println(scrabble.constructTreeMap().toString());
        scrabble.computeScenarioOne();
        TreeMap treeMap = scrabble.getScoreMap();
        System.out.println(treeMap.get(treeMap.size()).toString());
        scrabble.computeScenarioTwo();
    }

    private static void readFromFile() throws IOException {
        String fileName = "src/sowpods.txt";
        String line;
        FileReader fileReader =
                new FileReader(fileName);
        BufferedReader bufferedReader =
                new BufferedReader(fileReader);
        while((line = bufferedReader.readLine()) != null) {
            if(hashMap.get(line.length()) == null){
                ArrayList arrayList = new ArrayList();
                arrayList.add(line);
                hashMap.put(line.length(),arrayList);
            }else {
                ArrayList arrayList = hashMap.get(line.length());
                arrayList.add(line);
                hashMap.put(line.length(),arrayList);
            }
        }
        bufferedReader.close();
    }

}
