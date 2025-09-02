package net.minidev.json.writer;

import java.util.Date;
import net.minidev.asm.ConvertDate;

public abstract class BeansMapper extends JsonReaderI {
    public static JsonReaderI MAPPER_DATE = new ArraysMapper((JsonReader) null) {
        public Date convert(Object obj) {
            return ConvertDate.convertToDate(obj);
        }
    };
}
