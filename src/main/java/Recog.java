import org.ansj.app.keyword.KeyWordComputer;
import org.ansj.app.keyword.Keyword;
import org.ansj.recognition.impl.StopRecognition;
import org.ansj.splitWord.analysis.NlpAnalysis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Recog {
    public static void main(String[] args) {
//        System.out.println(System.getProperty("java.class.path"));
        List<String> names = readNames();
        recognition(names);
//        recogTest();
//        keyword();
    }

    public static List<String> readNames() {
        List<String> nameList = new ArrayList<>();
        String file = "name.txt";
        BufferedReader bf = null;
        String line;
        try {
            bf = new BufferedReader(new FileReader(file));
            while ((line = bf.readLine()) != null){
                nameList.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return nameList;
    }

    public static void recognition(List<String> names) {
        Map<String, Long> wordCount = new HashMap<>(1000);
        StopRecognition filter = new StopRecognition();
        for (String name : names) {
            String[] words = NlpAnalysis.parse(name).recognition(filter).toStringWithOutNature().split(",");
            for(String word : words) {
                if (word.length() <= 1) {
                    continue;
                }
                if (wordCount.containsKey(word)) {
                    wordCount.put(word, wordCount.get(word) + 1);
                } else {
                    wordCount.put(word, 1L);
                }
            }
        }
        for(String w : wordCount.keySet()) {
            System.out.printf("%s:%s\n", w, wordCount.get(w));
        }
    }

    public static void recogTest() {
        String in = "通兴順老北京炙子烤肉";
        System.out.println(NlpAnalysis.parse(in));
    }

    public static void keyword() {
        KeyWordComputer kwc = new KeyWordComputer(2);
        String title = "牛肉拉面安徽牛肉板面";
//        String content = "牛肉拉面安徽牛肉板面";
        Collection<Keyword> result = kwc.computeArticleTfidf(title, "");
        System.out.println(result);
    }
}
