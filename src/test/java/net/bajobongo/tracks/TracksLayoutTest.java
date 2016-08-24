package net.bajobongo.tracks;

import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

/**
 * Created by fdreger on 8/1/2016.
 */
public class TracksLayoutTest {

    TracksLayout layout;
    Section ab;
    Point a = new Point(0,0);
    Point b = new Point(1,0);
    Point c = new Point(1,1);
    Point d = new Point(0,1);

    Section bc;
    Section cd;
    Section da;

    @Before
    public void pre() {
        layout = new TracksLayout();
        ab = layout.connect(a,b);
        bc = layout.connect(b, c);
        cd = layout.connect(c, d);
        da = layout.connect(d, a);
    }

    @Test
    public void lengthsAreCorrect() {
        assertThat(ab.getLength()).isEqualTo(1);
    }

    @Test
    public void movesAlongASingleTrack() {
        Wheel wheel = new Wheel(ab, 0, ab.getA());
        layout.move(wheel, 0.99f);
        assertThat(wheel.section).isSameAs(ab);
    }

    @Test
    public void movesAlongTwoTracks() {
        Wheel wheel = new Wheel(ab, 0, ab.getA());
        layout.move(wheel, 1.5f);
        assertThat(wheel.section).isEqualTo(bc);
    }

    @Test
    public void movesAlongTwoTracksBackwards() {
        Wheel wheel = new Wheel(ab, 0, ab.getA());
        layout.move(wheel, -0.5f);
        assertThat(wheel.section.getA()).isEqualTo(d);
        assertThat(wheel.section.getB()).isEqualTo(a);
    }

    @Test
    public void moveFullCircle() {
        Wheel wheel = new Wheel(ab, 0.25f, ab.getA());
        Wheel copy = wheel.copy();

        layout.move(wheel, 4f);
        assertThat(wheel.section).isEqualTo(copy.section);
        assertThat(wheel.fromPoint).isEqualTo(copy.fromPoint);
        assertThat(wheel.positionInSection).isEqualTo(copy.positionInSection);
    }

    @Test
    public void moveFullCircleBack() {
        Wheel wheel = new Wheel(ab, 0.25f, ab.getA());
        Wheel copy = wheel.copy();

        layout.move(wheel, -4f);
        assertThat(wheel.section).isEqualTo(copy.section);
        assertThat(wheel.fromPoint).isEqualTo(copy.fromPoint);
        assertThat(wheel.positionInSection).isEqualTo(copy.positionInSection);
    }
}

