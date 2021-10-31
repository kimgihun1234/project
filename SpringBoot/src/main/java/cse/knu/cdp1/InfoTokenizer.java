package cse.knu.cdp1;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class InfoTokenizer {
    public static List<String> getInfo(String input) {
        ArrayList<String> tokenized = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(input, "/");
        while(tokenizer.hasMoreTokens()) {
            tokenized.add(tokenizer.nextToken());
        }

        return tokenized;
    }
}
