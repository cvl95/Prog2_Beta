package Util.ui.cosoleTest.json;

import GameEngine.BotScore;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class BotScoreReader {
    public static void write(Map<String, BotScore> botScoreMap, String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), botScoreMap);
    }
}
