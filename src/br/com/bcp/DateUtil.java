/**
 *
 */
package br.com.bcp;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class DateUtil {


	protected static SimpleDateFormat SDF_PT_COMPLETE = new SimpleDateFormat("ddMMyyyy");
	protected static SimpleDateFormat SDF_EN_COMPLETE = new SimpleDateFormat("yyyyMMdd");
	protected static SimpleDateFormat SDF_PT_COMPLETE_BARRA = new SimpleDateFormat("dd/MM/yyyy");
	protected static SimpleDateFormat SDF_EN_COMPLETE_BARRA = new SimpleDateFormat("yyyy/MM/dd");
	protected static SimpleDateFormat SDF_PT_COMPLETE_TRACO = new SimpleDateFormat("dd-MM-yyyy");
	protected static SimpleDateFormat SDF_EN_COMPLETE_TRACO = new SimpleDateFormat("yyyy-MM-dd");


	protected static DateTimeFormatter DTF_PT_COMPLETE = DateTimeFormatter.ofPattern("ddMMyyyy");
	protected static DateTimeFormatter DTF_EN_COMPLETE = DateTimeFormatter.ofPattern("yyyyMMdd");
	protected static DateTimeFormatter DTF_PT_COMPLETE_BARRA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	protected static DateTimeFormatter DTF_EN_COMPLETE_BARRA = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	protected static DateTimeFormatter SYNEL_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZ");
	protected static DateTimeFormatter SYNEL_FORMAT_1 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");



	public static ZonedDateTime parse(String pDate) {
		ZonedDateTime result;
		if (pDate.length() == 8) {
			try {
				result = ZonedDateTime.parse(pDate, DTF_PT_COMPLETE);
			} catch (DateTimeParseException pErro0) {
				try {
					result = ZonedDateTime.parse(pDate, DTF_EN_COMPLETE);
				} catch (DateTimeParseException pErro1) {
					throw new RuntimeException("Não foi possível  converter [" + pDate + "] para o tipo data.");
				}
			}
		} else if (pDate.length() == 10) {
			String value = pDate;
			if (pDate.contains("-")) {
				value = value.replace("-", "/");
			}
			value = value.trim().substring(0, 10);

			try {
				result = ZonedDateTime.of(LocalDate.parse(value, DTF_PT_COMPLETE_BARRA), LocalTime.MIDNIGHT, ZoneId.systemDefault());
			} catch (DateTimeParseException ex) {
				try {
					result = ZonedDateTime.of(LocalDate.parse(value, DTF_EN_COMPLETE_BARRA), LocalTime.MIDNIGHT, ZoneId.systemDefault());
				} catch (Exception e) {
					throw new RuntimeException("Não foi possível  converter [" + pDate + "] para o tipo data.");
				}
			}
		} else {
			try {
				result = ZonedDateTime.parse(pDate, DateTimeFormatter.ISO_DATE_TIME);
			}catch (Exception e) {
				try {
					result = ZonedDateTime.parse(pDate, SYNEL_FORMAT);
				}catch (Exception e1) {
					try {
						result = ZonedDateTime.parse(pDate, SYNEL_FORMAT_1);
					}catch (Exception e2) {
						throw new RuntimeException("Não foi possível  converter [" + pDate + "] para o tipo data.");
					}
				}
			}
		}
		return result;
	}

}