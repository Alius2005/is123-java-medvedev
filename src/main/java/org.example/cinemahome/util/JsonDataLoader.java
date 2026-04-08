package org.example.cinemahome.util;

import org.example.cinemahome.dto.MovieDto;
import org.example.cinemahome.dto.GenreDto;
import org.example.cinemahome.dto.ActorDto;
import org.example.cinemahome.dto.UserDto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

public class JsonDataLoader {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static List<MovieDto> loadMovies(String path) {
        try (InputStream is = new ClassPathResource(path).getInputStream()) {
            return mapper.readValue(is, mapper.getTypeFactory().constructCollectionType(List.class, MovieDto.class));
        } catch (Exception e) {
            return List.of();
        }
    }

    public static List<GenreDto> loadGenres(String path) {
        try (InputStream is = new ClassPathResource(path).getInputStream()) {
            return mapper.readValue(is, mapper.getTypeFactory().constructCollectionType(List.class, GenreDto.class));
        } catch (Exception e) {
            return List.of();
        }
    }

    public static List<ActorDto> loadActors(String path) {
        try (InputStream is = new ClassPathResource(path).getInputStream()) {
            return mapper.readValue(is, mapper.getTypeFactory().constructCollectionType(List.class, ActorDto.class));
        } catch (Exception e) {
            return List.of();
        }
    }

    public static List<UserDto> loadUsers(String path) {
        try (InputStream is = new ClassPathResource(path).getInputStream()) {
            return mapper.readValue(is, mapper.getTypeFactory().constructCollectionType(List.class, UserDto.class));
        } catch (Exception e) {
            return List.of();
        }
    }
}
