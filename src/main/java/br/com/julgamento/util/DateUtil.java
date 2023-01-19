package br.com.julgamento.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

  public static Date getZeroTimeDate(Date fecha) {
    Date res = fecha;
    Calendar calendar = Calendar.getInstance();

    calendar.setTime(fecha);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    res = calendar.getTime();
    return res;
  }

  public static Date addOneMinutoToDate(Date fecha) {
    Date res = fecha;
    Calendar calendar = Calendar.getInstance();

    calendar.setTime(fecha);
    calendar.add(Calendar.MINUTE, 1);
    res = calendar.getTime();

    return res;
  }
}
