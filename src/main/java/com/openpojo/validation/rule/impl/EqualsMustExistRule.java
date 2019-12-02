package com.openpojo.validation.rule.impl;

import com.atttomyx.utils.StreamUtils;
import com.openpojo.reflection.PojoClass;
import com.openpojo.validation.affirm.Affirm;
import com.openpojo.validation.rule.Rule;

/**
 * This rule ensures that the pojo has implemented an equals method
 */
public class EqualsMustExistRule implements Rule {

    public void evaluate(final PojoClass pojoClass) {
        if (StreamUtils.stream(pojoClass.getPojoMethods())
                .noneMatch(pojoMethod -> "equals".equals(pojoMethod.getName()))) {

            Affirm.fail(String.format("[%s] is missing equals method", pojoClass.getClazz()));
        }
    }
}
