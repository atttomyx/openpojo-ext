package com.openpojo.validation.test.impl;

import com.openpojo.reflection.PojoClass;
import com.openpojo.validation.affirm.Affirm;
import com.openpojo.validation.test.Tester;
import com.openpojo.validation.utils.ValidationHelper;
import org.apache.commons.lang3.StringUtils;

/**
 * Test the toString method
 */
public class ToStringTester implements Tester {

    public void run(final PojoClass pojoClass) {
        final Object classInstance = ValidationHelper.getBasicInstance(pojoClass);

        if (StringUtils.isBlank(classInstance.toString())) {
            Affirm.fail(String.format("[%s]'s toString is not valid", pojoClass.getClazz()));
        }
    }
}
