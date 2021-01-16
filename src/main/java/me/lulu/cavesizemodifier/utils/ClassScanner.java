package me.lulu.cavesizemodifier.utils;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@UtilityClass
public class ClassScanner {

    public List<Class> scanInnerClasses(Class parent) {
        List<Class> classes = new ArrayList<>();
        addClasses(classes, parent);
        return classes;
    }

    private void addClasses(List<Class> toAdd, Class targetClass) {
        Class[] classes = targetClass.getClasses();
        toAdd.addAll(Arrays.asList(classes));
        for (Class sub : classes)
            addClasses(toAdd, sub);
    }
}
