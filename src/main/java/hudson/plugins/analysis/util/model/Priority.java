package hudson.plugins.analysis.util.model;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

import hudson.plugins.analysis.Messages;

/**
 * Defines the priority of an annotation.
 *
 * @author Ulli Hafner
 */
public enum Priority implements PriorityInt{
    /** High priority. */
    HIGH("#EF2929"),
    /** Normal priority. */
    NORMAL("#FCE94F"),
    /** Low priority. */
    LOW("#729FCF");

    private final String cssColor;

    Priority(final String cssColor){
        this.cssColor = cssColor;
    }

    /** {@inheritDoc} */
    @Override
    public String getCssColor() {
        return cssColor;
    }

    /**
     * Converts a String priority to an actual enumeration value.
     *
     * @param priority
     *            priority as a String
     * @return enumeration value.
     */
    public static Priority fromString(final String priority) {
        return Priority.valueOf(StringUtils.upperCase(priority));
    }

    /**
     * Converts priorities for {@link XStream} deserialization.
     */
    public static final class PriorityConverter extends AbstractSingleValueConverter {
            @SuppressWarnings("rawtypes")
        @Override
        public boolean canConvert(final Class type) {
            return type.equals(Priority.class);
        }

            @Override
        public Object fromString(final String str) {
            return Priority.valueOf(str);
        }
    }

    /**
     * Returns the name of the Priority
     *
     * @return name of the Priority
     */
    @Override
    public String getPriorityName(){
        return name();
    }

    /**
     * Returns a localized description of this priority.
     *
     * @return localized description of this priority
     */
    @Override
    public String getLocalizedString() {
        if (this == HIGH) {
            return Messages.Priority_High();
        }
        if (this == LOW) {
            return Messages.Priority_Low();
        }
        return Messages.Priority_Normal();
    }

    /**
     * Returns a long localized description of this priority.
     *
     * @return long localized description of this priority
     */
    @Override
    public String getLongLocalizedString() {
        if (this == Priority.HIGH) {
            return Messages.HighPriority();
        }
        if (this == Priority.LOW) {
            return Messages.LowPriority();
        }
        return Messages.NormalPriority();
    }

    /**
     * Gets the priorities starting from the specified priority to
     * {@link Priority#HIGH}.
     *
     * @param minimumPriority
     *            the minimum priority
     * @return the priorities starting from the specified priority
     */
    public static Collection<Priority> collectPrioritiesFrom(final Priority minimumPriority) {
        ArrayList<Priority> priorities = new ArrayList<Priority>();
        priorities.add(Priority.HIGH);
        if (minimumPriority == Priority.NORMAL) {
            priorities.add(Priority.NORMAL);
        }
        if (minimumPriority == Priority.LOW) {
            priorities.add(Priority.NORMAL);
            priorities.add(Priority.LOW);
        }
        return priorities;
    }

}