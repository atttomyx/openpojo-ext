package com.openpojo.reflection.filters;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.PojoClassFilter;
import org.apache.commons.lang3.StringUtils;

/**
 * This filter will exclude any classes whose names begin or end with "Test".
 */
public class ExcludeTestsFilter implements PojoClassFilter {

    public boolean include(final PojoClass pojoClass) {
        final String name = pojoClass.getClazz().getName();
        final int indexPeriod = StringUtils.lastIndexOf(name, ".");
        final String withoutPeriod = indexPeriod > -1 ? StringUtils.substring(name, indexPeriod + 1, name.length()) : name;
        final int indexDollar = StringUtils.indexOf(withoutPeriod, "$");
        final String withoutDollar = indexDollar > -1 ? StringUtils.substring(withoutPeriod, 0, indexDollar) : withoutPeriod;

        return !(StringUtils.startsWith(withoutDollar, "Test") || StringUtils.endsWith(withoutDollar, "Test"));
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
