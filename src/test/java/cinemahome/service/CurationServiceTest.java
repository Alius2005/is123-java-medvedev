package cinemahome.service;

import org.example.cinemahome.dto.MovieDto;
import org.example.cinemahome.pojo.Movie;
import org.example.cinemahome.repository.MovieRepository;
import org.example.cinemahome.service.CurationService;
import org.example.cinemahome.util.MoodAnalyzer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CurationServiceTest {

    private MovieRepository movieRepository;
    private MoodAnalyzer moodAnalyzer;
    private CurationService curationService;

    @BeforeEach
    void setUp() {
        movieRepository = Mockito.mock(MovieRepository.class);
        moodAnalyzer = Mockito.mock(MoodAnalyzer.class);
        curationService = new CurationService();

        // подменяем приватные поля через рефлексию
        injectField(curationService, "movieRepository", movieRepository);
        injectField(curationService, "moodAnalyzer", moodAnalyzer);
    }

    @Test
    void recommendForToday_returnsFallbackWhenNoMovies() {
        when(movieRepository.findAll()).thenReturn(List.of());

        MovieDto dto = curationService.recommendForToday();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getTitle()).isEqualTo("No movies yet");
    }

    @Test
    void recommendForToday_usesMoodAnalyzer() {
        Movie m = new Movie();
        m.setId(1L);
        m.setTitle("Test movie");
        m.setDescription("Desc");
        m.setActors(List.of());
        m.setGenres(List.of());

        when(movieRepository.findAll()).thenReturn(List.of(m));
        when(moodAnalyzer.analyzeMood()).thenReturn("Calm");

        MovieDto dto = curationService.recommendForToday();

        assertThat(dto.getTitle()).isEqualTo("Test movie");
        assertThat(dto.getMoodTag()).isEqualTo("Calm");
    }

    // утилита для простого внедрения в тесте
    private static void injectField(Object target, String fieldName, Object value) {
        try {
            var f = target.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            f.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}