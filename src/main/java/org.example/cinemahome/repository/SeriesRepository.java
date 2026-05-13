package org.example.cinemahome.repository;

import org.example.cinemahome.pojo.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {}