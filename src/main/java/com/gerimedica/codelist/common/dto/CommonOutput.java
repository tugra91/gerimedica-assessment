package com.gerimedica.codelist.common.dto;

import java.io.Serial;
import java.io.Serializable;

/***
 * @author M.Tugra Er
 *     18.09.2022
 */
public record CommonOutput(String message) implements Serializable {
    @Serial
    private static final long serialVersionUID = -7025099692300135218L;
}
