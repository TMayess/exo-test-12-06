package com.example;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FrameTest {


    @Test
    void shouldIncreaseScoreWhenFirstRollIsMadeInStandardFrame() {
        IGenerateur gen = mock(IGenerateur.class);
        when(gen.randomPin(10)).thenReturn(5);

        Frame frame = new Frame(gen, false);
        frame.makeRoll();

        assertEquals(5, frame.getScore());
    }

    @Test
    void shouldIncreaseScoreWhenSecondRollIsMadeInStandardFrame() {
        IGenerateur gen = mock(IGenerateur.class);
        when(gen.randomPin(10)).thenReturn(3).thenReturn(4);

        Frame frame = new Frame(gen, false);
        frame.makeRoll();
        frame.makeRoll();

        assertEquals(7, frame.getScore());
    }

    @Test
    void shouldRejectSecondRollWhenStandardFrameStartsWithStrike() {
        IGenerateur gen = mock(IGenerateur.class);
        when(gen.randomPin(10)).thenReturn(10);

        Frame frame = new Frame(gen, false);
        frame.makeRoll();

        boolean result = frame.makeRoll();

        assertFalse(result);
    }

    @Test
    void shouldRejectThirdRollWhenStandardFrameAlreadyHasTwoRolls() {
        IGenerateur gen = mock(IGenerateur.class);
        when(gen.randomPin(10)).thenReturn(3).thenReturn(4);

        Frame frame = new Frame(gen, false);
        frame.makeRoll();
        frame.makeRoll();

        boolean result = frame.makeRoll();

        assertFalse(result);
    }


    @Test
    void shouldAcceptSecondRollWhenLastFrameStartsWithStrike() {
        IGenerateur gen = mock(IGenerateur.class);
        when(gen.randomPin(10)).thenReturn(10).thenReturn(7);

        Frame frame = new Frame(gen, true);
        frame.makeRoll(); // strike

        boolean result = frame.makeRoll();

        assertTrue(result);
    }

    @Test
    void shouldIncreaseScoreWhenSecondRollIsMadeAfterStrikeInLastFrame() {
        IGenerateur gen = mock(IGenerateur.class);
        when(gen.randomPin(10)).thenReturn(10).thenReturn(7);

        Frame frame = new Frame(gen, true);
        frame.makeRoll();
        frame.makeRoll();

        assertEquals(17, frame.getScore());
    }

    @Test
    void shouldAcceptThirdRollWhenLastFrameStartsWithStrike() {
        IGenerateur gen = mock(IGenerateur.class);
        when(gen.randomPin(10)).thenReturn(10).thenReturn(7).thenReturn(2);

        Frame frame = new Frame(gen, true);
        frame.makeRoll();
        frame.makeRoll();

        boolean result = frame.makeRoll();

        assertTrue(result);
    }

    @Test
    void shouldIncreaseScoreWhenThirdRollIsMadeAfterStrikeInLastFrame() {
        IGenerateur gen = mock(IGenerateur.class);
        when(gen.randomPin(10)).thenReturn(10).thenReturn(7).thenReturn(2);

        Frame frame = new Frame(gen, true);
        frame.makeRoll();
        frame.makeRoll();
        frame.makeRoll();

        assertEquals(19, frame.getScore());
    }

    @Test
    void shouldAcceptThirdRollWhenLastFrameStartsWithSpare() {
        IGenerateur gen = mock(IGenerateur.class);
        when(gen.randomPin(10)).thenReturn(6).thenReturn(4).thenReturn(5);

        Frame frame = new Frame(gen, true);
        frame.makeRoll();
        frame.makeRoll();

        boolean result = frame.makeRoll();

        assertTrue(result);
    }

    @Test
    void shouldIncreaseScoreWhenThirdRollIsMadeAfterSpareInLastFrame() {
        IGenerateur gen = mock(IGenerateur.class);
        when(gen.randomPin(10)).thenReturn(6).thenReturn(4).thenReturn(5);

        Frame frame = new Frame(gen, true);
        frame.makeRoll();
        frame.makeRoll();
        frame.makeRoll();

        assertEquals(15, frame.getScore());
    }

    @Test
    void shouldRejectThirdRollWhenLastFrameHasNoStrikeOrSpare() {
        IGenerateur gen = mock(IGenerateur.class);
        when(gen.randomPin(10)).thenReturn(3).thenReturn(4);

        Frame frame = new Frame(gen, true);
        frame.makeRoll();
        frame.makeRoll();

        boolean result = frame.makeRoll();

        assertFalse(result);
    }

    @Test
    void shouldRejectFourthRollInLastFrame() {
        IGenerateur gen = mock(IGenerateur.class);
        when(gen.randomPin(10)).thenReturn(10).thenReturn(10).thenReturn(10);

        Frame frame = new Frame(gen, true);
        frame.makeRoll();
        frame.makeRoll();
        frame.makeRoll();

        boolean result = frame.makeRoll();

        assertFalse(result);
    }
}