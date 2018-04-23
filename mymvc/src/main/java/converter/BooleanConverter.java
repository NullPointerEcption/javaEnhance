package converter;

import converter.interfaces.ITypeConverter;

public class BooleanConverter implements ITypeConverter {
    @Override
    public Object convertType(String paramValue) {
        if(paramValue==null||paramValue.equalsIgnoreCase("false")){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
