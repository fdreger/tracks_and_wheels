package net.bajobongo.tracks.basic;

import net.bajobongo.tracks.Point;
import net.bajobongo.tracks.Section;
import net.bajobongo.tracks.SwitchBoard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fdreger on 8/2/2016.
 */
public class BasicSwitchBoard implements SwitchBoard {
    private final Map<Point, BasicSwitch> switches = new HashMap<Point, BasicSwitch>();

    public Section switchTo(Point end, Section currentSection, List<Section> availableSections) {
        BasicSwitch basicSwitch = switches.get(end);
        if (basicSwitch == null) {
            basicSwitch = new BasicSwitch(availableSections.get(0), availableSections.get(1));
            switches.put(end, basicSwitch);
        }
        return basicSwitch.switchTo(end, currentSection);
    }
}
