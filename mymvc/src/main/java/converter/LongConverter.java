package converter;

import converter.interfaces.ITypeConverter;

public class LongConverter implements ITypeConverter {
    @Override
    public Object convertType(String paramValue) {
        return Long.parseLong(paramValue);
    }
}
