package com.openpojo.reflection.filters;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.PojoClassFilter;
import org.apache.commons.lang3.StringUtils;

/**
 * This filter will exclude any classes whose names begin or end with "Test".
 */
public class ExcludeTestsFilter implements PojoClassFilter {

    public boolean include(final PojoClass pojoClass) {
        final String name = pojoClass.getClazz().getSimpleName();

        return StringUtils.startsWith(name, "Test") || StringUtils.endsWith(name, "Test");
    }

    @Override
    public boolean equals(Object o) {
        return this == o || !(o == null || getClass() != o.getClass());
    }

    @Override
    public int hashCode() {
        return this.getClass().hashCode();
    }

}
