package com.milan.Assignment.Submission.repository;

import com.milan.Assignment.Submission.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    @Query(value = "SELECT a FROM Authority a WHERE a.authority IN :authorities")
    Set<Authority> findAllByAuthority(Set<String> authorities);
}
