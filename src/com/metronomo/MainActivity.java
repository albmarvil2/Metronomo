package com.metronomo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import manipulacionMidi.MidiManipulator;
import manipulacionMidi.MidiManipulatorImpl;
import manipulacionMidi.MidiSelector;
import manipulacionMidi.TipoFigura;
import manipulacionMidi.TipoSonido;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MainActivity extends Activity {

	
	private MediaPlayer mediaPlayer = new MediaPlayer();
	private TipoSonido tipoSonido = TipoSonido.A;
	private MidiManipulator manipulator;
	private Integer beat;
	private TipoFigura figura;
	private OnCheckedChangeListener toggleListener = new OnCheckedChangeListener(){

		@Override
		public void onCheckedChanged(CompoundButton v, boolean checked) {
			RadioButton negra = (RadioButton) findViewById(R.id.negra);
	    	RadioButton corchea = (RadioButton) findViewById(R.id.triplete);
	    	RadioButton triplete =(RadioButton) findViewById(R.id.corchea);
	    	
	    	if(negra.getId() == v.getId()){
	    		if(checked){
	    			figura = TipoFigura.negra;
	    			negra.setChecked(true);
	    			corchea.setChecked(false);
	    			triplete.setChecked(false);
	    		}
	    	}else if(corchea.getId() == v.getId()){
	    		if(checked){
	    			figura = TipoFigura.triplete;///KIAAAAAAAAAAAAAAAAA
	    			//REVISAR ESTO
	    			corchea.setChecked(true);
	    			negra.setChecked(false);
	    			triplete.setChecked(false);
	    		}
	    	}else if(triplete.getId() == v.getId()){
	    		if(checked){
	    			figura = TipoFigura.corchea;
	    			triplete.setChecked(true);
	    			corchea.setChecked(false);
	    			negra.setChecked(false);
	    		}
	    	}
			
		}
		
	};
	private SeekBar.OnSeekBarChangeListener barListener = new SeekBar.OnSeekBarChangeListener(){
		
		@Override
		public void onProgressChanged(SeekBar bar, int progress,
				boolean fromUser) {
			
			TextView text = (TextView) findViewById(R.id.tempo);
			Integer d = progress +40;
			String tempo = d.toString();

			text.setText(tempo);
			
		}

		@Override
		public void onStartTrackingTouch(SeekBar arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStopTrackingTouch(SeekBar arg0) {
			// TODO Auto-generated method stub
			
		}
	};
	
	private  AdapterView.OnItemSelectedListener spinnerListener = new  AdapterView.OnItemSelectedListener(){

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
				
				switch (pos){
					case 0:
						tipoSonido = TipoSonido.A;
						break;
					case 1:
						tipoSonido = TipoSonido.B;
						break;
					case 2:
						tipoSonido = TipoSonido.C;
						break;
				}			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
//		LinearLayout layout = (LinearLayout) findViewById(R.id.mainLayout);
//		layout.setBackgroundResource(R.drawable.background);
//		
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		
		SeekBar bar = (SeekBar) findViewById(R.id.seekBarTempo);
		bar.setOnSeekBarChangeListener(barListener);
		bar.setMax(220);
		bar.setProgress(80);
		
		Spinner spinnerSonidos  = (Spinner) findViewById(R.id.spinnerSonidos);
		
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.Sonidos, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinnerSonidos.setAdapter(adapter);
		spinnerSonidos.setOnItemSelectedListener(spinnerListener);
		
		NumberPicker np = (NumberPicker) findViewById(R.id.beatPicker);
		np.setMaxValue(7);
		np.setMinValue(1);
		np.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
		
		RadioButton negra = (RadioButton) findViewById(R.id.negra);
    	RadioButton corchea = (RadioButton) findViewById(R.id.triplete);
    	RadioButton triplete =(RadioButton) findViewById(R.id.corchea);
    	corchea.setOnCheckedChangeListener(toggleListener);
    	triplete.setOnCheckedChangeListener(toggleListener);
    	negra.setOnCheckedChangeListener(toggleListener);
    	
		
		beat = 1;
		figura = TipoFigura.negra;
	}
	
	public void playPause (View view) throws IllegalArgumentException, SecurityException, IllegalStateException, IOException{
		ImageButton button = (ImageButton) findViewById(R.id.playButton);
		if(mediaPlayer.isPlaying()){
			button.setImageResource(R.drawable.play);
			mediaPlayer.stop();
//			mediaPlayer.release();
		}else{
			
			button.setImageResource(R.drawable.pause);
			TextView text = (TextView) findViewById(R.id.tempo);
			Integer value = Integer.valueOf(text.getText().toString());
			NumberPicker np = (NumberPicker) findViewById(R.id.beatPicker);
			beat = np.getValue();
            InputStream input  = this.getResources().openRawResource(MidiSelector.select(tipoSonido,beat,figura));
            manipulator = new MidiManipulatorImpl(input, getApplicationContext());
            File cache = manipulator.changeBPM(value);
            
            
            Uri myUri = Uri.fromFile(cache);
            Log.d("PRUEBA", "uri: "+myUri);
            
            mediaPlayer = MediaPlayer.create(getApplicationContext(), myUri);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
		}
	}
	
	protected void onResume() {
        super.onResume();
        if(mediaPlayer.isPlaying()){
			mediaPlayer.stop();
//        	mediaPlayer.release();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if(mediaPlayer.isPlaying()){
			mediaPlayer.stop();
//        	mediaPlayer.release();
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        if(mediaPlayer.isPlaying()){
			mediaPlayer.stop();
//        	mediaPlayer.release();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer.isPlaying()){
			mediaPlayer.stop();
//        	mediaPlayer.release();
        }
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
