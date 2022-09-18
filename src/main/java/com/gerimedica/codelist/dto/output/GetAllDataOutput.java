package com.gerimedica.codelist.dto.output;

import com.gerimedica.codelist.dto.model.Code;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/***
 * @author M.Tugra Er
 *     18.09.2022
 */
public record GetAllDataOutput(List<Code> codeList) implements Serializable {
    @Serial
    private static final long serialVersionUID = -1951891668942354276L;
}
