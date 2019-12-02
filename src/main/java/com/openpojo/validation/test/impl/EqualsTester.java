package com.openpojo.validation.test.impl;

import com.openpojo.reflection.PojoClass;
import com.openpojo.validation.affirm.Affirm;
import com.openpojo.validation.test.Tester;
import com.openpojo.validation.utils.ValidationHelper;

/**
 * Test the equals method
 */
public class EqualsTester implements Tester {

    public void run(final PojoClass pojoClass) {
        final Object classInstance = ValidationHelper.getBasicInstance(pojoClass);

        if (!classInstance.equals(classInstance)) {
            Affirm.fail(String.format("[%s]'s equals is not valid", pojoClass.getClazz()));
        }
    }
}
