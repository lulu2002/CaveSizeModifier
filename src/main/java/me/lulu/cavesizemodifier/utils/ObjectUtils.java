package me.lulu.cavesizemodifier.utils;

import com.google.common.base.Preconditions;

import javax.annotation.Nullable;

public class ObjectUtils {

    public static <T> T firstNonNull(@Nullable T first, @Nullable T second) {
        return first != null ? first : Preconditions.checkNotNull(second);
    }

}
