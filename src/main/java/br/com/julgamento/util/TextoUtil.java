package br.com.julgamento.util;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;
import lombok.extern.slf4j.Slf4j;

import static java.util.Objects.nonNull;

@Slf4j
public class TextoUtil {

  public static String convertStringToByte(final byte[] value) {
    try {
      if (nonNull(value)) {
        CharsetDetector charsetDetector = new CharsetDetector();
        charsetDetector.setText(value);
        CharsetMatch detect = charsetDetector.detect();
        return new String(value, detect.getName());
      }
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
    return null;
  }
}
