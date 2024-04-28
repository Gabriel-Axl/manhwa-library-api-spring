package com.alex.sololibrary.repository;

import com.alex.sololibrary.models.MangaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MangaRepository extends JpaRepository<MangaModel, UUID> {
    Optional<MangaModel> findByTitleContaining(String title);
    @Query("SELECT COUNT(m) FROM MangaModel m")
    int countTotalRecords();
}
