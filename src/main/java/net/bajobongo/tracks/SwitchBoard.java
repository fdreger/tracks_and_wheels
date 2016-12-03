package net.bajobongo.tracks;

import java.util.Collection;
import java.util.Iterator;

/**
 * SwitchBoard interface represents a function deciding which section
 * should be selected after the first one ends.
 *
 * This interface is also the main API for the users of the library.
 *
 */
public interface SwitchBoard {
    Section switchTo(Point end, Section currentSection, Collection<? extends Section> availableSections);

    final SwitchBoard SINGLE_TRACK = new SwitchBoard() {
        public Section switchTo(Point end, Section currentSection, Collection<? extends Section> availableSections) {
            final int choices = availableSections.size();
            switch (choices) {
                case 1:
                    return Section.NULL;
                case 2:
                    Iterator<? extends Section> iter = availableSections.iterator();
                    Section a = iter.next();
                    Section b = iter.next();
                    return currentSection == a ? b : a;
                default:
                    throw new RuntimeException("SINGLE_TRACK switchboard overwhelmed by " + choices + " choices for node " + end);
            }
        }
    };

}
