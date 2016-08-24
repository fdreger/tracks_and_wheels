package net.bajobongo.tracks;

import java.util.List;

public class Section {

    public final static Section NULL = new Section(Point.NULL, Point.NULL);

    private final Point a;
    private final Point b;
    private final float length;

    public Section(Point a, Point b) {
        assert a != null && b != null;
        this.a = a;
        this.b = b;
        length = distance(a, b);
    }
    
    public Point getOtherEnd(Point end) {
        assert a == end || b == end;
        return end == a ? b : a;
    }

    public static Section firstThatEndsWith(Point point, List<Section> sections) {
        for (Section section : sections) {
            if (section.a.equals(point) || section.b.equals(point)) {
                return section;
            }
        }
        return null;
    }

    private float distance(Point a, Point b) {
        assert a != null && b != null;
        final float dx = a.x - b.x;
        final float dy = a.y - b.y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    public float getXForPosition(Point fromPoint, float positionInSection) {
        assert positionInSection >= 0 && positionInSection <= length;
        final Point placeFrom = fromPoint;
        final Point placeTo = getOtherEnd(placeFrom);
        float norm = positionInSection / length;
        return placeFrom.x + (placeTo.x - placeFrom.x) * norm;
    }

    public float getYForPosition(Point fromPoint, float positionInSection) {
        assert positionInSection >= 0 && positionInSection <= length;
        final Point placeFrom = fromPoint;
        final Point placeTo = getOtherEnd(placeFrom);
        float norm = positionInSection / length;
        return placeFrom.y + (placeTo.y - placeFrom.y) * norm;
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
}
