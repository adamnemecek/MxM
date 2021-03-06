package experiments;

import form.Part;
import form.Passage;
import networks.ModifiedTextLSTM;
import rhythmTree.RhythmTree;
import tools.MidiTools;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jpatsenker on 12/1/16.
 */
public class SimpleRhythmGeneration {

    public static void main(String[] args) throws IOException, InvalidMidiDataException, MidiUnavailableException, InterruptedException {
        //Sequence sequence = tools.MidiTools.download("http://www.classicalmidi.co.uk/music2/Pergynt4.mid");
        Sequence sequence = MidiTools.load("/Users/jpatsenker/MusicExMachina/MxM_2/mxm-main/src/main/resources/midi_schubertImpromptu.mid");
        Passage pass = MidiTools.parse(sequence);

        //tools.MidiTools.play(sequence);
        //System.out.println(sequence.toString());

        System.out.print("HERE WE GO-------");
        ArrayList<List<Integer>> listOfTrees = new ArrayList<>();
        for (Part line : pass) {
            for (RhythmTree rt : line.getRhythm()){
                ArrayList<Integer> rhythmList = new ArrayList<>();
                for(Integer i : rt.toSubdivisionList()) {
                    if (i == 0) {
                        rhythmList.add(1);
                    } else {
                        rhythmList.add(i);
                    }
                }
                if(rhythmList.size() >5){
                    listOfTrees.add(rhythmList);
                    System.out.println(rhythmList);
                }
                //listOfTrees.add(rhythmList);
            }
        }

        int[][] treeArr = new int[listOfTrees.size()][];
        for(int i = 0; i<treeArr.length; i++){
            treeArr[i] = new int[listOfTrees.get(i).size()];
            for(int j = 0; j<treeArr[i].length; j++){
                treeArr[i][j] = listOfTrees.get(i).get(j);
            }
        }

        ModifiedTextLSTM network = new ModifiedTextLSTM(treeArr,50,2);
        network.run();
    }
}
