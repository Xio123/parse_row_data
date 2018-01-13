package com.ai.parse_row_data.dao.timeconver;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 17:44 2018/1/13
 * @Modified By:
 */
@Converter(autoApply = true)
public class JPALocalDataConverter implements AttributeConverter<LocalDate, Date> {
    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
        return (localDate == null ? null : Date.valueOf(localDate));
    }

    @Override
    public LocalDate convertToEntityAttribute(Date date) {
        return (date == null ? null : date.toLocalDate());
    }
}
