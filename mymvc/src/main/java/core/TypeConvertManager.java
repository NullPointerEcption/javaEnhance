package core;

import converter.interfaces.ITypeConverter;

import java.util.HashMap;
import java.util.Map;

public class TypeConvertManager {

    private static TypeConvertManager instance = new TypeConvertManager();
    private static Map<Class<?>,ITypeConverter> typeConverterMap = new HashMap();

    public TypeConvertManager registerConverter(Class<?> clz,ITypeConverter converter){
        typeConverterMap.put(clz,converter);
        return this;
    }

    private TypeConvertManager() {
    }

    public static TypeConvertManager getInstance(){
        return instance;
    }

    public ITypeConverter getTypeConverter(Class<?> type){
        return typeConverterMap.get(type);
    }

}
