package com.day2.demo.repository;

import com.day2.demo.dto.search.UserSearchInputDto;
import com.day2.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u " +
            "FROM User u " +
            "WHERE (:#{#search.username} IS NULL OR u.username = :#{#search.username}) " +
            "AND LOWER(CONCAT(u.name,' ',u.surname)) LIKE LOWER(:#{#search.fullname}) ")
    Page<User> findAllBySearch(
            @Param("search") UserSearchInputDto search, Pageable pageable
    );

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Optional<User> findUserById(Long id);

    /*
    Optional<User> findByNameLikeOrSurnameLikeAndLogins_IpAddress(
            String name, String surname, String ipAddress);

    List<User> findAllByIsActiveTrue();



    // Projection Usage
    @Query("SELECT u.username AS username, " +
            "CONCAT(u.name, ' ', u.surname) AS fullName, " +
            "r.name AS role " +
            "FROM User u " +
            "LEFT JOIN u.roles r " +
            "WHERE u.username = ?1")
    List<UsernameRoleProjection> findUsernameAndRolesByUsername(String username);


    // JPQL - Positioned Parameters
    @Query("SELECT u " +
            "FROM User u " +
            "LEFT JOIN u.logins loginData " +
            "WHERE CONCAT(u.name, ' ',u.surname) LIKE ?1 " +
            "AND loginData.ipAddress = ?2")
    Optional<User> findByFullnameLikeAndLoginIpAddress(
            String fullname, String ipAddress
    );

    // JPQL - Named Parameters
    @Query("SELECT u " +
            "FROM User u " +
            "LEFT JOIN u.logins loginData " +
            "WHERE CONCAT(u.name, ' ',u.surname) LIKE :fullname " +
            "AND loginData.ipAddress = :ipAddress")
    Optional<User> findByFullnameLikeAndLoginIpAddressByNamedParameter(
            @Param("fullname") String fullname,
            @Param("ipAddress") String ipAddress
    );

    // Native SQL - Positioned Parameters
    @Query(nativeQuery = true,
    value = "SELECT u.* " +
            "FROM tbl_user u " +
            "INNER JOIN tbl_user_role ur ON u.id = ur.user_id " +
            "INNER JOIN tbl_role r ON ur.role_id = r.id " +
            "WHERE r.role_name = ?1")
    List<User> findAllUserThatHaveAdminRole(String roleName);

    // Native SQL - Named Parameters
    @Query(nativeQuery = true,
            value = "SELECT u.* " +
                    "FROM tbl_user u " +
                    "INNER JOIN tbl_user_role ur ON u.id = ur.user_id " +
                    "INNER JOIN tbl_role r ON ur.role_id = r.id " +
                    "WHERE r.role_name = :roleName")
    List<User> findAllUserThatHaveAdminRoleByNamedParameters(
            @Param("roleName") String roleName);
     */
}
