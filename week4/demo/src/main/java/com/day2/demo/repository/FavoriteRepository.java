package com.day2.demo.repository;

import com.day2.demo.model.Favorite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    Optional<Favorite> findByUserIdAndProductId(Long userId, Long productId);

    @Query("SELECT f FROM Favorite f WHERE f.user.id = :userId")
    Page<Favorite> findFavoritesByUserId(@Param("userId") Long userId, Pageable pageable);

    void deleteFavoriteById(Long id);

    boolean existsByUserIdAndProductId(Long userId, Long productId);
}
