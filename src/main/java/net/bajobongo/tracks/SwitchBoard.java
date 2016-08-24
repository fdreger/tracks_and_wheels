package net.bajobongo.tracks;

import java.util.List;

/**
 * Created by fdreger on 8/2/2016.
 */
public interface SwitchBoard {
    Section switchTo(Point end, Section currentSection, List<Section> availableSections);
}
