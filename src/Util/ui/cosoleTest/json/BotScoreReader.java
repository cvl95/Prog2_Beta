package Util.ui.cosoleTest.json;

import GameEngine.BotScore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class BotScoreReader {
    public static Map<String, BotScore> read(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, BotScore> botScoreMap = mapper.readValue(new File(path),
                new TypeReference<Map<String, BotScore>>() {});
        return botScoreMap;
    }
}
