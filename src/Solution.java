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
        scoreComputing();

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
    public static TreeMap<Integer,ArrayList<String>> scoreComputing()
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
}
