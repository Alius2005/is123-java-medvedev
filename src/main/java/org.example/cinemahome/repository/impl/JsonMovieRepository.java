package org.example.cinemahome.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.example.cinemahome.config.ApplicationProperties;
import org.example.cinemahome.dto.MovieDto;
import org.example.cinemahome.repository.MovieRepository;
import org.example.cinemahome.util.JsonDataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.List;
@Repository
@Primary
public class JsonMovieRepository implements MovieRepository {

    @Autowired
    private ApplicationProperties properties;

    @Autowired
    private ObjectMapper mapper; // Spring уже предоставляет ObjectMapper

    @Override
    public List<MovieDto> findAll() {
        return JsonDataLoader.loadMovies(properties.getDataPath());
    }

    @Override
    public MovieDto findById(String id) {
        return findAll().stream()
                        .filter(m -> m.getId().equals(id))
                        .findFirst()
                        .orElse(null);
    }

    @Override
    public void save(MovieDto movie) {
        try {
            File file = new File(properties.getDataPath());
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                // Создаём пустой JSON-объект
                ObjectNode root = mapper.createObjectNode();
                root.putArray("movies");
                mapper.writeValue(file, root);
            }

            // Загружаем существующие фильмы
            List<MovieDto> movies = findAll();
            movies.add(movie);

            // Перезаписываем файл
            ObjectNode root = mapper.createObjectNode();
            ArrayNode moviesArray = mapper.valueToTree(movies);
            root.set("movies", moviesArray);
            root.putArray("genres");
            root.putArray("actors");
            root.putArray("users");

            mapper.writeValue(file, root);

        } catch (IOException e) {
            throw new RuntimeException("Failed to save movie to JSON", e);
        }
    }
}
