package com.openpojo.validation.test.impl;

import com.openpojo.reflection.PojoClass;
import com.openpojo.validation.affirm.Affirm;
import com.openpojo.validation.test.Tester;
import com.openpojo.validation.utils.ValidationHelper;

/**
 * Test the hashCode method
 */
public class HashCodeTester implements Tester {

    public void run(final PojoClass pojoClass) {
        final Object classInstance = ValidationHelper.getBasicInstance(pojoClass);

        if (classInstance.hashCode() != classInstance.hashCode()) {
            Affirm.fail(String.format("[%s]'s hashCode is not valid", pojoClass.getClazz()));
        }
    }
}
