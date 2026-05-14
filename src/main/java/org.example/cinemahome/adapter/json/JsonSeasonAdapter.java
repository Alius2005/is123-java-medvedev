package org.example.cinemahome.adapter.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.cinemahome.dto.json.SeasonJsonDto;
import org.example.cinemahome.pojo.Season;
import org.example.cinemahome.pojo.Series;
import org.example.cinemahome.port.SeasonPort;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component("jsonSeasonPort")
public class JsonSeasonAdapter implements SeasonPort {

    private static final String SEASONS_JSON_PATH = "/home/student/cinema-json/seasons.json";

    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<SeasonJsonDto> readAllInternal() {
        try {
            File f = new File(SEASONS_JSON_PATH);
            if (!f.exists()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(f, new TypeReference<List<SeasonJsonDto>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Season> findBySeriesId(Long seriesId) {
        List<SeasonJsonDto> all = readAllInternal();
        List<Season> result = new ArrayList<>();

        for (SeasonJsonDto sj : all) {
            if (!seriesId.equals(sj.getSeriesId())) continue;

            Season s = new Season();
            s.setId(sj.getId());
            s.setSeasonNumber(sj.getSeasonNumber());
            s.setEpisodesCount(sj.getEpisodesCount());
            s.setTitle(sj.getTitle());

            Series series = new Series();
            series.setId(seriesId);
            s.setSeries(series);

            result.add(s);
        }
        return result;
    }
}