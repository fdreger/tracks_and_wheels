package net.bajobongo.tracks;

import java.util.*;

/**
 * Encompasses data for the connections between sections and the logic for moving wheels between those sections.
 *
 * Does not contain any sort of logic for selecting the right track - delegates those choices to a SwitchBoard given as a parameter. If no switchBoard given, falls back to a single track implementation that does handle loops and tracks, but does not handle any forking.
 *
 */
public final class TracksLayout {

    private final Map<Point, List<Section>> index = new HashMap<Point, List<Section>>();

    private Section findOtherSectionFrom(Point otherEnd, Section currentSection, SwitchBoard switchBoard) {
        return switchBoard.switchTo(otherEnd, currentSection, index.get(otherEnd));
    }

    public Section connect(Point a, Point b) {
        Section s = new Section(a, b);
        addToIndex(a, s);
        addToIndex(b, s);
        return s;
    }

    public float move(Wheel wheel, float distance) {
        return move(wheel, distance, SwitchBoard.SINGLE_TRACK);
    }

    public float move(Wheel wheel, float distance, SwitchBoard switchBoard) {
        assert wheel.section != null;
        assert wheel.positionInSection >= 0;

        float possibleMoveUpperBound = wheel.section.getLength() - wheel.positionInSection;
        float possibleMoveLowerBound =  -wheel.positionInSection;

        if (distance >= possibleMoveUpperBound) {
            Point otherEnd = wheel.section.getOtherEnd(wheel.fromPoint);
            Section nextSection = findOtherSectionFrom(otherEnd, wheel.section, switchBoard);
            wheel.fromPoint = otherEnd;
            wheel.section = nextSection;
            wheel.positionInSection = 0;
            return possibleMoveUpperBound + move(wheel, distance - possibleMoveUpperBound, switchBoard);

        } else if (distance < possibleMoveLowerBound) {
            Section nextSection = findOtherSectionFrom(wheel.fromPoint, wheel.section, switchBoard);
            Point otherEnd = nextSection.getOtherEnd(wheel.fromPoint);
            
            wheel.fromPoint = otherEnd;
            wheel.section = nextSection;
            wheel.positionInSection = nextSection.getLength();
            return possibleMoveLowerBound + move(wheel, distance - possibleMoveLowerBound, switchBoard);
            
        } else {
            wheel.positionInSection += distance;
            wheel.recalculateWorldCoords();
            return distance;
        }
    }

    private void addToIndex(Point punkt, Section sekcja) {
        List<Section> sekcje = index.get(punkt);
        if (sekcje == null) {
            sekcje = new ArrayList<Section>(4);
            index.put(punkt, sekcje);
        }
        sekcje.add(sekcja);
    }
    
}
