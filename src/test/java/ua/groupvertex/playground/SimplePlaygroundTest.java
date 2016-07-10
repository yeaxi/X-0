package ua.groupvertex.playground;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimplePlaygroundTest {
    private static Playground playground;

    @BeforeClass
    public static void initGame() {
        playground = new SimplePlayground();
    }

    @Before
    public void startGame() {
        playground.start();
    }

    @Test
    public void doStepWonCross() throws Exception {

        assertEquals(playground.doStep(0, 0), State.PLAY);
        assertEquals(playground.doStep(1, 0), State.PLAY);
        assertEquals(playground.doStep(0, 1), State.PLAY);
        assertEquals(playground.doStep(1, 1), State.PLAY);
        assertEquals(playground.doStep(0, 2), State.CROSS_WON);
        assertEquals(playground.doStep(123, 123), State.CROSS_WON);
    }

    @Test
    public void doStepWonNought() throws Exception {

        assertEquals(playground.doStep(0, 0), State.PLAY);
        assertEquals(playground.doStep(1, 0), State.PLAY);

        assertEquals(playground.doStep(0, 1), State.PLAY);
        assertEquals(playground.doStep(1, 1), State.PLAY);

        assertEquals(playground.doStep(2, 0), State.PLAY);
        assertEquals(playground.doStep(1, 2), State.NOUGHT_WON);
    }

    @Test
    public void doStepDraw() throws Exception {

        assertEquals(playground.doStep(0, 1), State.PLAY);
        assertEquals(playground.doStep(0, 0), State.PLAY);
        assertEquals(playground.doStep(0, 2), State.PLAY);


        assertEquals(playground.doStep(1, 2), State.PLAY);
        assertEquals(playground.doStep(1, 0), State.PLAY);
        assertEquals(playground.doStep(2, 0), State.PLAY);

        assertEquals(playground.doStep(1, 1), State.PLAY);
        assertEquals(playground.doStep(2, 1), State.PLAY);
        assertEquals(playground.doStep(2, 2), State.DRAW);
        System.out.println(playground.print());
    }

    @Test
    public void isMoveAllowed() throws Exception {
        assertEquals(playground.doStep(0, 0), State.PLAY);
        assertEquals(playground.doStep(0, 0), State.INVALID_INPUT_DATA);
        assertEquals(playground.doStep(-1, 3), State.INVALID_INPUT_DATA);
    }


}