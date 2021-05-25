package com.openpojo.validation.rule.impl;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.PojoMethod;
import com.openpojo.validation.affirm.Affirm;
import com.openpojo.validation.rule.Rule;

import java.util.List;

/**
 * This rule ensures that the pojo has implemented an equals method
 */
public class EqualsMustExistRule implements Rule {

    public void evaluate(final PojoClass pojoClass) {
        final List<PojoMethod> methods = pojoClass.getPojoMethods();

        if (methods != null && methods.stream()
                .noneMatch(pojoMethod -> "equals".equals(pojoMethod.getName()))) {

            Affirm.fail(String.format("[%s] is missing equals method", pojoClass.getClazz()));
        }
    }
}
