package net.bajobongo.tracks.basic;

import net.bajobongo.tracks.Point;
import net.bajobongo.tracks.Section;
import net.bajobongo.tracks.SwitchBoard;

import java.util.List;

/**
 * Created by fdreger on 8/2/2016.
 */
public class BasicSwitch {

    private final Section fixedSection;
    private Section selectedSection;

    public BasicSwitch(Section fixedSection, Section selectedSection) {
        this.fixedSection = fixedSection;
        this.selectedSection = selectedSection;
    }

    public Section switchTo(Point end, Section currentSection) {
        if (currentSection.equals(fixedSection)) {
            return selectedSection;
        } else if (currentSection.equals(selectedSection)) {
            return fixedSection;
        } else {
            return Section.NULL;
        }
    }


}
