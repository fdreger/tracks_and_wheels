package net.bajobongo.tracks;

import java.util.Collection;
import java.util.List;

/**
 * Section of a track between two points.
 *
 * Section:
 *  - knows its ends (points a and b),
 *  - knows its length,
 *  - knows its shape - i.e. coords of a point at the given distance from either end,
 *  - in the future it should know the tangent for any of the points
 *  - does not know anything about the outside world such as other sections, points etc.
 *
 *  The class is in theory meant to be extended to provide different shapes.
 *  In practice it will probably be better to stick with the basic implementation (a linear section).
 *
 */
public class Section {

    public final static Section NULL = new Section(Point.NULL, Point.NULL);

    private final Point a;
    private final Point b;
    private final float length;

    /**
     * Creates a new linear section. Parameters must not be null.
     *
     * @param a
     * @param b
     */
    public Section(Point a, Point b) {
        assert a != null && b != null;
        this.a = a;
        this.b = b;
        length = distance(a, b);
    }

    /**
     * Returns point a or b, depending on which one was passed as the parameter.
     *
    * @param end MUST be one of the section's ends
     * @return
     */
    public Point getOtherEnd(Point end) {
        assert a.equals(end) || b.equals(end);
        return end.equals(a) ? b : a;
    }

    /**
     * Given an end and a distance from the end, the method calculates coordinates of the point in section and returns X.
     *
     * @param fromPoint MUST be one of the end points of the Section
     * @param positionInSection must be between 0 and the section's length, inclusive
     * @return
     */
    public float getXForPosition(Point fromPoint, float positionInSection) {
        assert positionInSection >= 0 && positionInSection <= length;
        return tweenBetween(positionInSection, fromPoint.x, getOtherEnd(fromPoint).x);
    }

    /**
     * Given an end and a distance from the end, the method calculates coordinates of the point in section and returns Y.
     *
     * @param fromPoint MUST be one of the end points of the Section
     * @param positionInSection must be between 0 and the section's length, inclusive
     * @return
     */
    public float getYForPosition(Point fromPoint, float positionInSection) {
        assert positionInSection >= 0 && positionInSection <= length;
        return tweenBetween(positionInSection, fromPoint.y, getOtherEnd(fromPoint).y);
    }

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

    public float getLength() {
        return length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Section section = (Section) o;

        if (!a.equals(section.a)) return false;
        return b.equals(section.b);
    }

    @Override
    public int hashCode() {
        int result = a.hashCode();
        result = 31 * result + b.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Section{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }

    private float distance(Point a, Point b) {
        assert a != null && b != null;
        final float dx = a.x - b.x;
        final float dy = a.y - b.y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    private float tweenBetween(float positionInSection, float a, float b) {
        float normalized = positionInSection / length;
        return a + (b - a) * normalized;
    }


    /**
     * A utility for finding the first section that ends or begins with the given point.
     * If no section found, returns a null section.
     *
     * @param point
     * @param sections
     * @return
     */
    public static Section firstThatEndsWith(Point point, Collection<? extends Section> sections) {
        for (Section section : sections) {
            if (section.a.equals(point) || section.b.equals(point)) {
                return section;
            }
        }
        return Section.NULL;
    }

}
