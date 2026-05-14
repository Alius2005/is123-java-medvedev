package org.example.cinemahome.adapter.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.cinemahome.dto.SeriesDto;
import org.example.cinemahome.port.SeriesPort;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component("jsonSeriesPort")
public class JsonSeriesAdapter implements SeriesPort {

    private static final String SERIES_JSON_PATH = "/home/student/cinema-json/series.json";

    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<SeriesDto> readAllInternal() {
        try {
            File f = new File(SERIES_JSON_PATH);
            if (!f.exists()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(f, new TypeReference<List<SeriesDto>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void writeAllInternal(List<SeriesDto> list) {
        try {
            File f = new File(SERIES_JSON_PATH);
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
        return readAllInternal();
    }

    @Override
    public SeriesDto findById(Long id) {
        return readAllInternal().stream()
                .filter(s -> id.equals(s.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void saveSeriesWithStructure(SeriesDto dto) {
        List<SeriesDto> all = readAllInternal();

        if (dto.getId() == null) {
            long newId = all.stream()
                    .mapToLong(s -> s.getId() == null ? 0L : s.getId())
                    .max()
                    .orElse(0L) + 1;
            dto.setId(newId);
        } else {
            all.removeIf(s -> dto.getId().equals(s.getId()));
        }

        all.add(dto);
        writeAllInternal(all);
    }
}