package manipulacionMidi;

import com.metronomo.R;

public class MidiSelector {

	public static int select(TipoSonido tipo, Integer beat, TipoFigura figura){
		int res = 0;	
		switch(tipo){
			case A:
				switch (beat){
					case 1:
						switch(figura){
							case negra:
								res = R.raw.a_1_negra;
								break;
							case corchea:
								res = R.raw.a_1_corchea;
								break;
							case triplete:
								res = R.raw.a_1_triplete;
								break;
						}
						break;
					case 2:
						switch(figura){
						case negra:
							res = R.raw.a_2_negra;
							break;
						case corchea:
							res = R.raw.a_2_corchea;
							break;
						case triplete:
							res = R.raw.a_2_triplete;
							break;
					}
					break;
					case 3:
						switch(figura){
						case negra:
							res = R.raw.a_3_negra;
							break;
						case corchea:
							res = R.raw.a_3_corchea;
							break;
						case triplete:
							res = R.raw.a_3_triplete;
							break;
					}
					break;
					case 4:
						switch(figura){
						case negra:
							res = R.raw.a_4_negra;
							break;
						case corchea:
							res = R.raw.a_4_corchea;
							break;
						case triplete:
							res = R.raw.a_4_triplete;
							break;
					}
					break;
					case 5:
						switch(figura){
						case negra:
							res = R.raw.a_5_negra;
							break;
						case corchea:
							res = R.raw.a_5_corchea;
							break;
						case triplete:
							res = R.raw.a_5_triplete;
							break;
					}
					break;
					case 6:
						switch(figura){
						case negra:
							res = R.raw.a_6_negra;
							break;
						case corchea:
							res = R.raw.a_6_corchea;
							break;
						case triplete:
							res = R.raw.a_6_triplete;
							break;
					}
					break;
					case 7:
						switch(figura){
						case negra:
							res = R.raw.a_7_negra;
							break;
						case corchea:
							res = R.raw.a_7_corchea;
							break;
						case triplete:
							res = R.raw.a_7_triplete;
							break;
					}
					break;
				}
				break;
				
				case B:
					switch (beat){
						case 1:
							switch(figura){
								case negra:
									res = R.raw.b_1_negra;
									break;
								case corchea:
									res = R.raw.b_1_corchea;
									break;
								case triplete:
									res = R.raw.b_1_triplete;
									break;
							}
							break;
						case 2:
							switch(figura){
							case negra:
								res = R.raw.b_2_negra;
								break;
							case corchea:
								res = R.raw.b_2_corchea;
								break;
							case triplete:
								res = R.raw.b_2_triplete;
								break;
						}
						break;
						case 3:
							switch(figura){
							case negra:
								res = R.raw.b_3_negra;
								break;
							case corchea:
								res = R.raw.b_3_corchea;
								break;
							case triplete:
								res = R.raw.b_3_triplete;
								break;
						}
						break;
						case 4:
							switch(figura){
							case negra:
								res = R.raw.b_4_negra;
								break;
							case corchea:
								res = R.raw.b_4_corchea;
								break;
							case triplete:
								res = R.raw.b_4_triplete;
								break;
						}
						break;
						case 5:
							switch(figura){
							case negra:
								res = R.raw.b_5_negra;
								break;
							case corchea:
								res = R.raw.b_5_corchea;
								break;
							case triplete:
								res = R.raw.b_5_triplete;
								break;
						}
						break;
						case 6:
							switch(figura){
							case negra:
								res = R.raw.b_6_negra;
								break;
							case corchea:
								res = R.raw.b_6_corchea;
								break;
							case triplete:
								res = R.raw.b_6_triplete;
								break;
						}
						break;
						case 7:
							switch(figura){
							case negra:
								res = R.raw.b_7_negra;
								break;
							case corchea:
								res = R.raw.b_7_corchea;
								break;
							case triplete:
								res = R.raw.b_7_triplete;
								break;
						}
						break;
					}
					break;
				case C:
					switch(figura){
						case negra:
							res = R.raw.c_negra;
							break;
						case corchea:
							res = R.raw.c_corchea;
							break;
						case triplete:
							res = R.raw.c_triplete;
							break;
					}
					break;
			default:
				res= R.raw.c_negra;
				break;
			
		}
		return res;		
	}
}
