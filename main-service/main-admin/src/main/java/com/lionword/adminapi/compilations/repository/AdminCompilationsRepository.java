package com.lionword.adminapi.compilations.repository;

import com.lionword.entity.compilation.Compilation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminCompilationsRepository extends JpaRepository<Compilation, Long> {

}
