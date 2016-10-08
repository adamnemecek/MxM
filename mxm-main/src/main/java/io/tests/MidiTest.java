package io.tests;

import io.Midi;
import org.junit.Test;
import javax.sound.midi.Sequence;

/**
 *
 */
public class MidiTest {

    /**
     * Tests that you can run
     */
    @Test
    public void basicTest() {
        System.out.print("A");
        try {
            System.out.print("B");
            Sequence sequence1 = Midi.download("http://www.midiworld.com/midis/other/n1/EspanjaPrelude.mid");
            Sequence sequence2 = Midi.download("http://www.midiworld.com/download/4573");
            System.out.print("C");
            Midi.play(sequence1);
            Midi.play(sequence2);
            System.out.print("D");
            Thread.sleep(100000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}