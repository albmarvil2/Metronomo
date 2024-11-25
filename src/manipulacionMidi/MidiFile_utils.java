package manipulacionMidi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.util.Log;

import com.leff.midi.MidiFile;
import com.leff.midi.MidiTrack;
import com.leff.midi.util.MidiUtil;

public class MidiFile_utils {
	
	@SuppressWarnings({ "deprecation" })
	public static void writeToFile(MidiFile mf, File outFile, Context ctx) throws FileNotFoundException, IOException {
		Log.d("PRUEBA", "Guardando con permisos");
		FileOutputStream fout = ctx.openFileOutput(outFile.getName(), Context.MODE_WORLD_READABLE);
		fout.write(MidiFile.IDENTIFIER);
		fout.write(MidiUtil.intToBytes(6, 4));
		fout.write(MidiUtil.intToBytes(mf.getType(), 2));
		fout.write(MidiUtil.intToBytes(mf.getTrackCount(), 2));
		fout.write(MidiUtil.intToBytes(mf.getResolution(), 2));
		
		for(MidiTrack T : mf.getTracks()) {
			T.writeToFile(fout);
		}
		
		fout.flush();
		fout.close();
	}
}