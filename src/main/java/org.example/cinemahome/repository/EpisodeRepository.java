package org.example.cinemahome.repository;

import org.example.cinemahome.pojo.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Long> {

    Optional<Episode> findBySeason_IdAndEpisodeNumber(Long seasonId, Integer episodeNumber);

    Optional<Episode> findFirstBySeason_Series_IdOrderByEpisodeNumberAsc(Long seriesId);
}