package manipulacionMidi;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import android.content.Context;

import com.leff.midi.MidiFile;
import com.leff.midi.MidiTrack;
import com.leff.midi.event.MidiEvent;
import com.leff.midi.event.meta.Tempo;

public class MidiManipulatorImpl implements MidiManipulator {
	
	private File cache;
	
	private InputStream input;
	
	private Context ctx;
	
	public MidiManipulatorImpl(InputStream input, Context ctx){
		this.cache = new File(ctx.getFilesDir(), "cache.mid");
		this.input = input;
		this.ctx = ctx;
	}
	
	/* (non-Javadoc)
	 * @see manipulacionMidi.MidiManipulator#changeBPM(java.lang.Integer)
	 */
	@Override
	public File changeBPM(Integer value){
		MidiFile mf = null;
		
		try {
            mf = new MidiFile(input);
        } catch(IOException e) {
                System.err.println("Error parsing MIDI file:");
                e.printStackTrace();
        }
    
    
    
        // 2c. Reduce the tempo by half
        MidiTrack T = mf.getTracks().get(0);
        
        Iterator<MidiEvent> it = T.getEvents().iterator();
        while(it.hasNext()) {
                MidiEvent E = it.next();
                
                if(E.getClass().equals(Tempo.class)) {
                        
                        Tempo tempo = (Tempo)E;
                        tempo.setBpm(value);
                }
        }
        
        // 3. Save the file back to disk
        try {
//                mf.writeToFile(cache);
        		MidiFile_utils.writeToFile(mf, cache, ctx);
        } catch(IOException e) {
                System.err.println("Error writing MIDI file:");
                e.printStackTrace();
        }
        
        return cache;
	}
}
