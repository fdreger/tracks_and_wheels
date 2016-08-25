package net.bajobongo.tracks.basic;

import net.bajobongo.tracks.Point;
import net.bajobongo.tracks.Section;
import net.bajobongo.tracks.SwitchBoard;

import java.util.*;

/**
 * Created by fdreger on 8/2/2016.
 */
public class BasicSwitchBoard implements SwitchBoard {
    private final Map<Point, BasicSwitch> switches = new HashMap<Point, BasicSwitch>();

    public Section switchTo(Point end, Section currentSection, Collection<? extends Section> availableSections) {
        BasicSwitch basicSwitch = switches.get(end);
        if (basicSwitch == null) {
            Iterator<? extends Section> iter = availableSections.iterator();
            basicSwitch = new BasicSwitch(iter.next(), iter.next());
            switches.put(end, basicSwitch);
        }
        return basicSwitch.switchTo(end, currentSection);
    }
}
