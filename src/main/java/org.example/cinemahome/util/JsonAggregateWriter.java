package org.example.cinemahome.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class JsonAggregateWriter {

    private final ObjectMapper objectMapper;

    public JsonAggregateWriter() {
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public void writeList(String filePath, List<?> list) {
        try {
            File file = new File(filePath);
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            objectMapper.writeValue(file, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}