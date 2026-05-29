package org.example.cinemahome.adapter.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.cinemahome.dto.DirectorDto;
import org.example.cinemahome.port.DirectorPort;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component("jsonDirectorPort")
public class JsonDirectorAdapter implements DirectorPort {
    private static final String DIRECTORS_JSON_PATH = "/home/student/cinema-json/directors.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<DirectorDto> readAllInternal() {
        try {
            File f = new File(DIRECTORS_JSON_PATH);
            if (!f.exists()) return new ArrayList<>();
            return objectMapper.readValue(f, new TypeReference<List<DirectorDto>>() {});
        } catch (Exception e) { e.printStackTrace(); return new ArrayList<>(); }
    }

    private void writeAllInternal(List<DirectorDto> list) {
        try {
            File f = new File(DIRECTORS_JSON_PATH);
            File parent = f.getParentFile();
            if (parent != null && !parent.exists()) parent.mkdirs();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(f, list);
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public List<DirectorDto> findAll() { return readAllInternal(); }

    @Override
    public void save(DirectorDto dto) {
        List<DirectorDto> all = readAllInternal();
        if (dto.getId() == null) {
            long newId = all.stream().mapToLong(d -> d.getId() == null ? 0L : d.getId()).max().orElse(0L) + 1;
            dto.setId(newId);
        } else {
            all.removeIf(d -> dto.getId().equals(d.getId()));
        }
        all.add(dto);
        writeAllInternal(all);
    }
}