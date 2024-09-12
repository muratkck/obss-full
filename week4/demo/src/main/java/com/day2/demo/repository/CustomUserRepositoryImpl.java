package com.day2.demo.repository;

import com.day2.demo.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jdk.jfr.Registered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository {

    private final EntityManager em;

    @Autowired
    public CustomUserRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<User> findByUsernameUsingCriteria(String username) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        // Create a CriteriaQuery Object
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        // Define the root of the query (i.e., the entity we're querying)
        Root<User> userRoot = criteriaQuery.from(User.class);

        // Build the predicate for the username condition
        Predicate usernamePredicate = criteriaBuilder.equal(userRoot.get("username"), username);

        // Apply the predicate to the query
        criteriaQuery.where(usernamePredicate);

        // Create a TypedQuery from the CriteriaQuery
        TypedQuery<User> query = em.createQuery(criteriaQuery);

        return query.getResultStream().findFirst();
    }



}
