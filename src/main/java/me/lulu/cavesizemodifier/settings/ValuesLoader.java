package me.lulu.cavesizemodifier.settings;

import me.lulu.cavesizemodifier.utils.ClassScanner;
import org.apache.commons.lang.WordUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class ValuesLoader {
    private final ConfigFile configFile;
    private final Class configClass;

    private List<Class> classes;

    public ValuesLoader(ConfigFile configFile, Class configClass) {
        this.configFile = configFile;
        this.configClass = configClass;

        this.classes = ClassScanner.scanInnerClasses(configClass);
        this.classes.add(configClass);

        for (Class c : this.classes)
            loadFields(c);
    }


    private void loadFields(Class cla) {
        Field[] allField = cla.getDeclaredFields();

        for (Field field : allField) {
            setFieldValuesFromYaml(field);
        }

    }

    private void setFieldValuesFromYaml(Field field) {
        try {
            field.setAccessible(true);

            String pathInYaml = getPathInYaml(field);

            if (configFile.isSet(pathInYaml))
                field.set(null, getFieldValueInYaml(field, pathInYaml));
            else
                configFile.set(pathInYaml, field.get(null));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Object getFieldValueInYaml(Field field, String pathInYaml) throws Exception {
        String methodName = getGetterName(field);

        Method method = ConfigFile.class.getDeclaredMethod(methodName, String.class);
        method.setAccessible(true);

        return method.invoke(configFile, pathInYaml);
    }

    private String getPathInYaml(Field field) {
        String fieldName = WordUtils.capitalizeFully(field.getName(), new char[]{'_'});
        StringBuilder builder = new StringBuilder(fieldName);

        Class fieldClass = field.getDeclaringClass();

        while (fieldClass != configClass) {
            builder.insert(0, fieldClass.getSimpleName() + ".");
            fieldClass = fieldClass.getDeclaringClass();
        }

        return builder.toString();
    }

    private String getGetterName(Field field) {
        Class fieldType = field.getType();

        if (fieldType.isAssignableFrom(List.class)) {
            ParameterizedType type = ( ParameterizedType ) field.getGenericType();
            Class<?> typeClass = ( Class<?> ) type.getActualTypeArguments()[0];
            return "get" + typeClass.getSimpleName() + "List";
        }

        return "get" + fieldType.getSimpleName();
    }
}
