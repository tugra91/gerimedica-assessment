package com.gerimedica.codelist.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/***
 * @author M.Tugra Er
 *     18.09.2022
 */
@Entity
@Table(name = "T_CODE_LIST")
@Data
public class CodeEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1634247299871444839L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigDecimal codeListId;
    private String source;
    private String codeListCode;
    @Column(unique = true)
    private String code;
    private String displayValue;
    private String longDescription;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Long sortingPriority;
}
