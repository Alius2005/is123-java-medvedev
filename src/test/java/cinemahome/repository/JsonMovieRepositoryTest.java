package cinemahome.repository;

import org.example.cinemahome.adapter.json.JsonMovieAdapter;
import org.example.cinemahome.dto.MovieDto;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class JsonMovieRepositoryTest {

    @Test
    void saveAndFindById_workWithTempFile() throws Exception {
        // временный файл вместо /home/student/cinema-json/movies.json
        File tmp = Files.createTempFile("movies-test", ".json").toFile();
        tmp.deleteOnExit();

        JsonMovieAdapter adapter = new JsonMovieAdapter();

        // подменяем приватную константу MOVIES_JSON_PATH
        ReflectionTestUtils.setField(adapter, "MOVIES_JSON_PATH", tmp.getAbsolutePath());

        MovieDto dto = new MovieDto();
        dto.setTitle("Test movie");
        dto.setDescription("Desc");
        dto.setMoodTag("Calm");

        adapter.save(dto);

        List<MovieDto> all = adapter.findAll();
        assertThat(all).hasSize(1);

        MovieDto saved = all.get(0);
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getTitle()).isEqualTo("Test movie");

        MovieDto byId = adapter.findById(saved.getId());
        assertThat(byId).isNotNull();
        assertThat(byId.getTitle()).isEqualTo("Test movie");
    }
}