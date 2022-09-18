package com.gerimedica.codelist.dao;

import com.gerimedica.codelist.entity.CodeEntity;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.Optional;

/***
 * @author M.Tugra Er
 *     18.09.2022
 */
public interface CodeListRepository extends CrudRepository<CodeEntity, BigDecimal> {

    Optional<CodeEntity> findByCode(String code);
}
