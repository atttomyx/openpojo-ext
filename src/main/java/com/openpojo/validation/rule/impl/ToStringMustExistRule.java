package com.openpojo.validation.rule.impl;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.PojoMethod;
import com.openpojo.validation.affirm.Affirm;
import com.openpojo.validation.rule.Rule;

import java.util.List;

/**
 * This rule ensures that the pojo has implemented a toString method
 */
public class ToStringMustExistRule implements Rule {

    public void evaluate(final PojoClass pojoClass) {
        final List<PojoMethod> methods = pojoClass.getPojoMethods();

        if (methods != null && methods.stream()
                .noneMatch(pojoMethod -> "toString".equals(pojoMethod.getName()))) {

            Affirm.fail(String.format("[%s] is missing toString method", pojoClass.getClazz()));
        }
    }
}
