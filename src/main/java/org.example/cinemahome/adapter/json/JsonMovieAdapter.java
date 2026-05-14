package org.example.cinemahome.adapter.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.cinemahome.dto.MovieDto;
import org.example.cinemahome.port.MoviePort;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component("jsonMoviePort")
public class JsonMovieAdapter implements MoviePort {

    private static final String MOVIES_JSON_PATH = "/home/student/cinema-json/movies.json";

    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<MovieDto> readAllInternal() {
        try {
            File f = new File(MOVIES_JSON_PATH);
            if (!f.exists()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(f, new TypeReference<List<MovieDto>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void writeAllInternal(List<MovieDto> list) {
        try {
            File f = new File(MOVIES_JSON_PATH);
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
    public List<MovieDto> findAll() {
        return readAllInternal();
    }

    @Override
    public MovieDto findById(Long id) {
        return readAllInternal().stream()
                .filter(m -> id.equals(m.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(MovieDto movie) {
        List<MovieDto> all = readAllInternal();

        if (movie.getId() == null) {
            long newId = all.stream()
                    .mapToLong(m -> m.getId() == null ? 0L : m.getId())
                    .max()
                    .orElse(0L) + 1;
            movie.setId(newId);
        } else {
            all.removeIf(m -> movie.getId().equals(m.getId()));
        }

        all.add(movie);
        writeAllInternal(all);
    }
}