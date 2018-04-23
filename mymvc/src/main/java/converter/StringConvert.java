package converter;

import converter.interfaces.ITypeConverter;

public class StringConvert implements ITypeConverter {
    @Override
    public Object convertType(String paramValue) {
        return String.valueOf(paramValue);
    }
}
