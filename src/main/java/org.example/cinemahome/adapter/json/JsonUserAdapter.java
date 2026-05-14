package org.example.cinemahome.adapter.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.cinemahome.dto.UserDto;
import org.example.cinemahome.port.UserPort;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component("jsonUserPort")
public class JsonUserAdapter implements UserPort {

    private static final String USERS_JSON_PATH = "/home/student/cinema-json/users.json";

    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<UserDto> readAllInternal() {
        try {
            File f = new File(USERS_JSON_PATH);
            if (!f.exists()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(f, new TypeReference<List<UserDto>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void writeAllInternal(List<UserDto> list) {
        try {
            File f = new File(USERS_JSON_PATH);
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
    public List<UserDto> findAll() {
        return readAllInternal();
    }

    @Override
    public UserDto findByUsername(String username) {
        return readAllInternal().stream()
                .filter(u -> username.equals(u.getUsername()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(UserDto userDto) {
        List<UserDto> all = readAllInternal();

        if (userDto.getId() == null) {
            long newId = all.stream()
                    .mapToLong(u -> u.getId() == null ? 0L : u.getId())
                    .max()
                    .orElse(0L) + 1;
            userDto.setId(newId);
        } else {
            all.removeIf(u -> userDto.getId().equals(u.getId()));
        }

        all.add(userDto);
        writeAllInternal(all);
    }
}
