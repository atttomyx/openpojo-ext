package com.openpojo.reflection.filters;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.PojoClassFilter;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This filter will exclude any of the specified classes
 */
public class ExcludeClassesFilter implements PojoClassFilter {

    private final Set<String> exclude;

    public ExcludeClassesFilter(Class... exclude) {
        this(Arrays.asList(exclude));
    }

    public ExcludeClassesFilter(Collection<Class> exclude) {
        checkNotNull(exclude, "exclude must not be null");

        this.exclude = exclude.stream()
                .map(Class::getName)
                .collect(Collectors.toSet());
    }

    public boolean include(final PojoClass pojoClass) {
        final String name = pojoClass.getName();

        return !exclude.contains(name);
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
