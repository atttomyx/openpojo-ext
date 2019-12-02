package com.openpojo.reflection.filters;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.PojoClassFilter;

/**
 * This filter will exclude any classes whose names begin with "Test".
 */
public class ExcludeTestsFilter implements PojoClassFilter {

    public boolean include(final PojoClass pojoClass) {
        return !pojoClass.getClazz().getSimpleName().startsWith("Test");
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
