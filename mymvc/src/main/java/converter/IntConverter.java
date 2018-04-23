package converter;

import converter.interfaces.ITypeConverter;

public class IntConverter implements ITypeConverter {

    @Override
    public Object convertType(String paramValue) {
        return Integer.parseInt(paramValue);
    }
}
