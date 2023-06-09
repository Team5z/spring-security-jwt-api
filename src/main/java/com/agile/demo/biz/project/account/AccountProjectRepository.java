package com.agile.demo.biz.project.account;

import com.agile.demo.biz.account.AccountEntity;
import com.agile.demo.biz.project.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountProjectRepository extends JpaRepository<AccountProjectEntity, Long> {


    Optional<AccountProjectEntity> findByAccounts_UserId(String userId);

    void deleteByAccounts_UserId(String userId);

    Optional<AccountProjectEntity> findByProjects_Seq(Long np_seq);

    @Query("SELECT COUNT(ap) FROM AccountProjectEntity ap WHERE ap.accounts.userId = :userId AND ap.projects.seq = :projectSeq")
    Long findUser(@Param("userId") String userId, @Param("projectSeq") Long projectSeq);


    @Query("SELECT ap FROM AccountProjectEntity ap WHERE ap.accounts.userId = :userId AND ap.projects.seq = :projectSeq")
    Optional<AccountProjectEntity> findUsers(@Param("userId") String userId, @Param("projectSeq") Long projectSeq);
}
