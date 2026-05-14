package org.example.cinemahome.config;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

@Component
public class DataModeService {

    private static final String MODE_ATTR = "dataMode";

    private final HttpSession session;

    public DataModeService(HttpSession session) {
        this.session = session;
    }

    public DataMode getMode() {
        Object val = session.getAttribute(MODE_ATTR);
        if (val instanceof DataMode dm) {
            return dm;
        }
        // по умолчанию БД
        return DataMode.DB;
    }

    public void setMode(DataMode mode) {
        session.setAttribute(MODE_ATTR, mode);
    }
}