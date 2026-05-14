package org.example.cinemahome.adapter.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.cinemahome.dto.SeriesDto;
import org.example.cinemahome.dto.json.SeasonJsonDto;
import org.example.cinemahome.dto.json.EpisodeJsonDto;
import org.example.cinemahome.port.SeriesPort;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component("jsonSeriesPort")
public class JsonSeriesAdapter implements SeriesPort {

    private static final String SERIES_JSON_PATH   = "/home/student/cinema-json/series.json";
    private static final String SEASONS_JSON_PATH  = "/home/student/cinema-json/seasons.json";
    private static final String EPISODES_JSON_PATH = "/home/student/cinema-json/episodes.json";

    private final ObjectMapper objectMapper = new ObjectMapper();

    private <T> List<T> readList(String path, TypeReference<List<T>> typeRef) {
        try {
            File f = new File(path);
            if (!f.exists()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(f, typeRef);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void writeList(String path, List<?> list) {
        try {
            File f = new File(path);
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
    public List<SeriesDto> findAll() {
        return readList(SERIES_JSON_PATH, new TypeReference<List<SeriesDto>>() {});
    }

    @Override
    public SeriesDto findById(Long id) {
        return findAll().stream()
                .filter(s -> id.equals(s.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void saveSeriesWithStructure(SeriesDto dto) {
        // 1. series.json
        List<SeriesDto> seriesList =
                readList(SERIES_JSON_PATH, new TypeReference<List<SeriesDto>>() {});

        if (dto.getId() == null) {
            long newId = seriesList.stream()
                    .mapToLong(s -> s.getId() == null ? 0L : s.getId())
                    .max()
                    .orElse(0L) + 1;
            dto.setId(newId);
        } else {
            seriesList.removeIf(s -> dto.getId().equals(s.getId()));
        }

        seriesList.add(dto);
        writeList(SERIES_JSON_PATH, seriesList);

        Long seriesId = dto.getId();
        Integer seasonNumber = (dto.getSeasonNumber() != null) ? dto.getSeasonNumber() : 1;
        Integer episodesCount = (dto.getEpisodesCount() != null && dto.getEpisodesCount() > 0)
                ? dto.getEpisodesCount()
                : 1;

        // 2. seasons.json
        List<SeasonJsonDto> seasons =
                readList(SEASONS_JSON_PATH, new TypeReference<List<SeasonJsonDto>>() {});

        // новый id сезона
        long newSeasonId = seasons.stream()
                .mapToLong(s -> s.getId() == null ? 0L : s.getId())
                .max()
                .orElse(0L) + 1;

        SeasonJsonDto seasonDto = new SeasonJsonDto(
                newSeasonId,
                seriesId,
                seasonNumber,
                episodesCount,
                seasonNumber + " сезон"
        );
        seasons.add(seasonDto);
        writeList(SEASONS_JSON_PATH, seasons);

        // 3. episodes.json
        List<EpisodeJsonDto> episodes =
                readList(EPISODES_JSON_PATH, new TypeReference<List<EpisodeJsonDto>>() {});

        String folder = dto.getFolder(); // как в БД‑режиме
        for (int epNum = 1; epNum <= episodesCount; epNum++) {
            long newEpisodeId = episodes.stream()
                    .mapToLong(e -> e.getId() == null ? 0L : e.getId())
                    .max()
                    .orElse(0L) + 1;

            String filePath = folder + "/" + seasonNumber + " сезон/" + epNum + " серия.mp4";

            EpisodeJsonDto ep = new EpisodeJsonDto(
                    newEpisodeId,
                    newSeasonId,
                    seriesId,
                    epNum,
                    epNum + " серия",
                    filePath
            );
            episodes.add(ep);
        }
        writeList(EPISODES_JSON_PATH, episodes);
    }
}