package org.example.cinemahome.factory;

import org.example.cinemahome.repository.MovieRepository;
import org.example.cinemahome.repository.GenreRepository;
import org.example.cinemahome.repository.ActorRepository;
import org.example.cinemahome.repository.UserRepository;

public interface RepositoryFactory {
    MovieRepository createJsonMovieRepository();
    GenreRepository createJsonGenreRepository();
    ActorRepository createJsonActorRepository();
    UserRepository createJsonUserRepository();
}
