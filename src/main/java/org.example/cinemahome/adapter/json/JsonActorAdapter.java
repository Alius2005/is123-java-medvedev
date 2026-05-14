package org.example.cinemahome.adapter.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.cinemahome.dto.ActorDto;
import org.example.cinemahome.port.ActorPort;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component("jsonActorPort")
public class JsonActorAdapter implements ActorPort {

    private static final String ACTORS_JSON_PATH = "/home/student/cinema-json/actors.json";

    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<ActorDto> readAllInternal() {
        try {
            File f = new File(ACTORS_JSON_PATH);
            if (!f.exists()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(f, new TypeReference<List<ActorDto>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void writeAllInternal(List<ActorDto> list) {
        try {
            File f = new File(ACTORS_JSON_PATH);
            File parent = f.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(f, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ActorDto> findAll() {
        return readAllInternal();
    }

    @Override
    public void save(ActorDto dto) {
        List<ActorDto> all = readAllInternal();

        if (dto.getId() == null) {
            long newId = all.stream()
                    .mapToLong(a -> a.getId() == null ? 0L : a.getId())
                    .max()
                    .orElse(0L) + 1;
            dto.setId(newId);
        } else {
            all.removeIf(a -> dto.getId().equals(a.getId()));
        }

        all.add(dto);
        writeAllInternal(all);
    }
}