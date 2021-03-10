package business;
import java.sql.Time;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalTimeAttributeConverter implements AttributeConverter<Date, Time> {

	@Override
	public Time convertToDatabaseColumn(Date date) {
		String aux = date.toLocaleString().split(" ")[1];
		return date == null ? null : Time.valueOf(aux);
	}

	@Override
	public Date convertToEntityAttribute(Time sqlTime) {
		return sqlTime == null ? null : new Date(sqlTime.getTime());
	}
}
