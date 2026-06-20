package io.milesoft.testing.utils;

import static java.util.Objects.requireNonNull;

import org.apache.commons.lang3.StringUtils;

public class TestingUtils {

    public static <I> void testEqualsAndHashCode(I item, I same, I diff) {
        requireNonNull(item, "item must not be null");
        requireNonNull(same, "same must not be null");
        requireNonNull(diff, "diff must not be null");

        testEquals(item, same, diff);
        testHashCode(item, same, diff);
    }

    private static <I> void testEquals(I item, I same, I diff) {
        if (!item.equals(item)) {
            throw new AssertionError("item should equals self");
        }
        if (!item.equals(same)) {
            throw new AssertionError("item should equals same");
        }
        if (item.equals(diff)) {
            throw new AssertionError("item should not equals diff");
        }
        if (item.equals(null)) {
            throw new AssertionError("item should not equals null");
        }
        if (item.equals(new Other())) {
            throw new AssertionError("item should not equals other");
        }
    }

    private static <I> void testHashCode(I item, I same, I diff) {
        if (item.hashCode() != item.hashCode()) {
            throw new AssertionError("item's hashCode should be deterministic");
        }
        if (same.hashCode() != item.hashCode()) {
            throw new AssertionError("item's hashCode should equals same's");
        }
        if (diff.hashCode() == item.hashCode()) {
            throw new AssertionError("item's hashCode should not equals diff's");
        }
    }

    public static <I> void testToString(I item) {
        requireNonNull(item, "item must not be null");

        final String str = item.toString();
        final String def = item.getClass().getName() + "@" + Integer.toHexString(item.hashCode());

        if (StringUtils.isBlank(str)) {
            throw new AssertionError("toString must not be blank");
        }
        if (def.equals(str)) {
            throw new AssertionError("toString must not equals default Object.toString()");
        }
    }

    private static class Other {}
}
