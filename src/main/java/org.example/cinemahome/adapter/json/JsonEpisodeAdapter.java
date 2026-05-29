package org.example.cinemahome.adapter.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.cinemahome.dto.EpisodeDto;
import org.example.cinemahome.dto.json.EpisodeJsonDto;
import org.example.cinemahome.port.EpisodePort;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component("jsonEpisodePort")
public class JsonEpisodeAdapter implements EpisodePort {

    private static final String EPISODES_JSON_PATH = "/home/student/cinema-json/episodes.json";

    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<EpisodeJsonDto> readAllInternal() {
        try {
            File f = new File(EPISODES_JSON_PATH);
            if (!f.exists()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(f, new TypeReference<List<EpisodeJsonDto>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private EpisodeDto toDto(EpisodeJsonDto e) {
        return new EpisodeDto(
                e.getId(),
                e.getTitle(),
                e.getFilePath(),
                e.getEpisodeNumber(),
                e.getSeasonId()
        );
    }

    @Override
    public EpisodeDto findById(Long id) {
        return readAllInternal().stream()
                .filter(e -> id.equals(e.getId()))
                .findFirst()
                .map(this::toDto)
                .orElse(null);
    }

    @Override
    public EpisodeDto findNext(EpisodeDto current) {
        if (current == null || current.getSeasonId() == null || current.getEpisodeNumber() == null) {
            return null;
        }
        return readAllInternal().stream()
                .filter(e -> current.getSeasonId().equals(e.getSeasonId())
                        && (current.getEpisodeNumber() + 1) == e.getEpisodeNumber())
                .findFirst()
                .map(this::toDto)
                .orElse(null);
    }

    @Override
    public EpisodeDto findFirstOfSeries(Long seriesId) {
        return readAllInternal().stream()
                .filter(e -> seriesId.equals(e.getSeriesId()))
                .sorted(Comparator.comparingInt(EpisodeJsonDto::getEpisodeNumber))
                .findFirst()
                .map(this::toDto)
                .orElse(null);
    }

    @Override
    public List<EpisodeDto> findBySeasonId(Long seasonId) {
        List<EpisodeDto> result = new ArrayList<>();
        for (EpisodeJsonDto e : readAllInternal()) {
            if (seasonId.equals(e.getSeasonId())) {
                result.add(toDto(e));
            }
        }
        result.sort(Comparator.comparingInt(EpisodeDto::getEpisodeNumber));
        return result;
    }

    @Override
    public EpisodeDto findPrevious(EpisodeDto current) {
        if (current == null || current.getSeasonId() == null || current.getEpisodeNumber() == null || current.getEpisodeNumber() <= 1) {
            return null;
        }
        return readAllInternal().stream()
                .filter(e -> current.getSeasonId().equals(e.getSeasonId())
                        && (current.getEpisodeNumber() - 1) == e.getEpisodeNumber())
                .findFirst()
                .map(this::toDto)
                .orElse(null);
    }
}