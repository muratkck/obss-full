package com.day2.demo.repository;

import com.day2.demo.model.Blacklist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlacklistRepository extends JpaRepository<Blacklist, Long> {

    boolean existsByUserIdAndSellerId( Long userId, Long sellerId);

    @Query("SELECT b FROM Blacklist b WHERE b.user.id = :userId")
    Page<Blacklist> findBlacklistsByUserId(@Param("userId") Long userId, Pageable pageable);

    void deleteBlacklistById(Long id);

    Optional<Blacklist> findBlacklistById(Long id);

}
