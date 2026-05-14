package org.example.cinemahome.adapter.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.cinemahome.dto.GenreDto;
import org.example.cinemahome.port.GenrePort;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component("jsonGenrePort")
public class JsonGenreAdapter implements GenrePort {

    private static final String GENRES_JSON_PATH = "/home/student/cinema-json/genres.json";

    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<GenreDto> readAllInternal() {
        try {
            File f = new File(GENRES_JSON_PATH);
            if (!f.exists()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(f, new TypeReference<List<GenreDto>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void writeAllInternal(List<GenreDto> list) {
        try {
            File f = new File(GENRES_JSON_PATH);
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
    public List<GenreDto> findAll() {
        return readAllInternal();
    }

    @Override
    public void save(GenreDto dto) {
        List<GenreDto> all = readAllInternal();

        if (dto.getId() == null) {
            long newId = all.stream()
                    .mapToLong(g -> g.getId() == null ? 0L : g.getId())
                    .max()
                    .orElse(0L) + 1;
            dto.setId(newId);
        } else {
            all.removeIf(g -> dto.getId().equals(g.getId()));
        }

        all.add(dto);
        writeAllInternal(all);
    }
}