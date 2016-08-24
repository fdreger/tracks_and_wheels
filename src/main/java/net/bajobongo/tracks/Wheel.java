package net.bajobongo.tracks;

final public class Wheel {
    Section section;
    float positionInSection;
    Point fromPoint;
    private float x;
    private float y;

    public Wheel(Section section, float positionInSection, Point fromPoint) {
        assert section != null;
        assert positionInSection >= 0;
        assert positionInSection < section.getLength();
        assert fromPoint != null;
        assert fromPoint == section.getA() || fromPoint == section.getB();
        this.section = section;
        this.positionInSection = positionInSection;
        this.fromPoint = fromPoint;
        recalculateWorldCoords();
    }

    void recalculateWorldCoords() {
        x = section.getXForPosition(fromPoint, positionInSection);
        y = section.getYForPosition(fromPoint, positionInSection);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Section getSection() {
        return section;
    }

    public void reverse() {
        fromPoint = section.getOtherEnd(fromPoint);
        positionInSection = section.getLength() - positionInSection;
    }

    public Wheel copy() {
        return new Wheel(section, positionInSection, fromPoint);
    }
    
}
