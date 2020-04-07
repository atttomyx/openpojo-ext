package com.atttomyx.utils;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import static com.google.common.base.Preconditions.checkNotNull;

public class TestingUtils {

    public static <I> void testEqualsAndHashCode(I item, I same, I diff) {
        checkNotNull(item, "item must not be null");
        checkNotNull(same, "same must not be null");
        checkNotNull(diff, "diff must not be null");

        testEquals(item, same, diff);
        testHashCode(item, same, diff);
    }

    private static <I> void testEquals(I item, I same, I diff) {
        Assert.assertTrue("item should equals self", item.equals(item));
        Assert.assertTrue("item should equals same", item.equals(same));
        Assert.assertFalse("item should not equals diff", item.equals(diff));
        Assert.assertFalse("item should not equals null", item.equals(null));
        Assert.assertFalse("item should not equals other", item.equals(new Other()));
    }

    private static <I> void testHashCode(I item, I same, I diff) {
        Assert.assertEquals("item's hashCode should be deterministic", item.hashCode(), item.hashCode());
        Assert.assertEquals("item's hashCode should equals same's", same.hashCode(), item.hashCode());
        Assert.assertNotEquals("item's hashCode should not equals diff's", diff.hashCode(), item.hashCode());
    }

    public static <I> void testToString(I item) {
        checkNotNull(item, "item must not be null");

        final String str = item.toString();
        final String def = item.getClass().getName() + "@" + Integer.toHexString(item.hashCode());

        Assert.assertTrue("toString must not be blank", StringUtils.isNotBlank(str));
        Assert.assertNotEquals("toString must not equals default Object.toString()", def, str);
    }

    private static class Other {}
}
