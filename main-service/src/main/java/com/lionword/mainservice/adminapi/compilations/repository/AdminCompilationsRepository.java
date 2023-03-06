package com.lionword.mainservice.adminapi.compilations.repository;

import com.lionword.mainservice.entity.compilation.Compilation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminCompilationsRepository extends JpaRepository<Compilation, Long> {

}
