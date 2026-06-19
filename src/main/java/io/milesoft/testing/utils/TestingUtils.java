package io.milesoft.testing.utils;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;

public class TestingUtils {

    public static <I> void testEqualsAndHashCode(I item, I same, I diff) {
        checkNotNull(item, "item must not be null");
        checkNotNull(same, "same must not be null");
        checkNotNull(diff, "diff must not be null");

        testEquals(item, same, diff);
        testHashCode(item, same, diff);
    }

    private static <I> void testEquals(I item, I same, I diff) {
        assertTrue("item should equals self", item.equals(item));
        assertTrue("item should equals same", item.equals(same));
        assertFalse("item should not equals diff", item.equals(diff));
        assertFalse("item should not equals null", item.equals(null));
        assertFalse("item should not equals other", item.equals(new Other()));
    }

    private static <I> void testHashCode(I item, I same, I diff) {
        assertEquals("item's hashCode should be deterministic", item.hashCode(), item.hashCode());
        assertEquals("item's hashCode should equals same's", same.hashCode(), item.hashCode());
        assertNotEquals("item's hashCode should not equals diff's", diff.hashCode(), item.hashCode());
    }

    public static <I> void testToString(I item) {
        checkNotNull(item, "item must not be null");

        final String str = item.toString();
        final String def = item.getClass().getName() + "@" + Integer.toHexString(item.hashCode());

        assertTrue("toString must not be blank", StringUtils.isNotBlank(str));
        assertNotEquals("toString must not equals default Object.toString()", def, str);
    }

    private static class Other {}
}
