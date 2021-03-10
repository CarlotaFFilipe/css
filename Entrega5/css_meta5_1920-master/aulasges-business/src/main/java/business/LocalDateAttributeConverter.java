package business;
import java.util.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true) // if 'true', *all* LocalDates in the program will be automatically converted
public class LocalDateAttributeConverter implements AttributeConverter<java.util.Date, java.sql.Date> {

  @Override
  public java.sql.Date convertToDatabaseColumn(java.util.Date javaDate) {
    return javaDate == null ? null : java.sql.Date.valueOf(javaDate.toLocaleString());
  }

  @Override
  public java.util.Date convertToEntityAttribute(java.sql.Date sqlDate) {
    return sqlDate == null ? null : new Date(sqlDate.getDate());
  }
}
