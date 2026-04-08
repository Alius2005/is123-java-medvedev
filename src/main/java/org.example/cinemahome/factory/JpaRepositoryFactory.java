package org.example.cinemahome.factory;

import org.example.cinemahome.repository.ActorRepository;
import org.example.cinemahome.repository.GenreRepository;
import org.example.cinemahome.repository.MovieRepository;
import org.example.cinemahome.repository.UserRepository;
import org.example.cinemahome.repository.impl.JsonActorRepository;
import org.example.cinemahome.repository.impl.JsonGenreRepository;
import org.example.cinemahome.repository.impl.JsonMovieRepository;
import org.example.cinemahome.repository.impl.JsonUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("jpaRepositoryFactory")
public class JpaRepositoryFactory implements RepositoryFactory {

    @Autowired
    private JsonMovieRepository jsonMovieRepository;

    @Autowired
    private JsonGenreRepository jsonGenreRepository;

    @Autowired
    private JsonActorRepository jsonActorRepository;

    @Autowired
    private JsonUserRepository jsonUserRepository;

    @Override
    public MovieRepository createJsonMovieRepository() {
        return jsonMovieRepository;
    }

    @Override
    public GenreRepository createJsonGenreRepository() {
        return jsonGenreRepository;
    }

    @Override
    public ActorRepository createJsonActorRepository() {
        return jsonActorRepository;
    }

    @Override
    public UserRepository createJsonUserRepository() {
        return jsonUserRepository;
    }
}
