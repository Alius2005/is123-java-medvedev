package kinoteka.pattern;

import kinoteka.repository.*;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class RepositoryFactory {

    private final Environment env;
    private final InMemoryUserRepository memUser;
    private final InMemoryMovieRepository memMovie;
    private final JsonUserRepository jsonUser;
    private final JsonMovieRepository jsonMovie;
    private final JpaUserRepository jpaUser;
    private final JpaMovieRepository jpaMovie;

    public RepositoryFactory(Environment env,
                             InMemoryUserRepository memUser,
                             InMemoryMovieRepository memMovie,
                             JsonUserRepository jsonUser,
                             JsonMovieRepository jsonMovie,
                             JpaUserRepository jpaUser,
                             JpaMovieRepository jpaMovie) {
        this.env = env;
        this.memUser = memUser;
        this.memMovie = memMovie;
        this.jsonUser = jsonUser;
        this.jsonMovie = jsonMovie;
        this.jpaUser = jpaUser;
        this.jpaMovie = jpaMovie;
    }

    public UserRepositoryCustom getUserRepository() {
        switch (env.getProperty("app.db-type")) {
            case "json": return jsonUser;
            case "memory": return memUser;
            default: return jpaUser;
        }
    }

    public MovieRepositoryCustom getMovieRepository() {
        switch (env.getProperty("app.db-type")) {
            case "json": return jsonMovie;
            case "memory": return memMovie;
            default: return jpaMovie;
        }
    }
}
