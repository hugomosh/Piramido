package com.hugoosh.android;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class PiramidoActivity extends Activity {
	/** Called when the activity is first created. */

	private int tipoJuego = 0;
	//private Random rgen = new Random(777);
	private Random rgen = new Random();
	private int[] posiciones = new int[28];
	private boolean[] destapados = new boolean[28];
	private boolean jugando = false;
	private int[] dominos = new int[] { R.drawable.do1, R.drawable.do2,
			R.drawable.do3, R.drawable.do4, R.drawable.do5, R.drawable.do6,
			R.drawable.do7, R.drawable.do8, R.drawable.do9, R.drawable.do10,

			R.drawable.do11, R.drawable.do12, R.drawable.do13, R.drawable.do14,
			R.drawable.do15, R.drawable.do16, R.drawable.do17, R.drawable.do18,
			R.drawable.do19, R.drawable.do20,

			R.drawable.do21, R.drawable.do22, R.drawable.do23, R.drawable.do24,
			R.drawable.do25, R.drawable.do26, R.drawable.do27, R.drawable.do28,
			R.drawable.do29, R.drawable.do30,

	};
	private String tag = "hm";
	private ImageButton[] arrFichas;
	// indice de la ficha actual en la tabla de posiciones
	// tambien indica enl numero de destapadas de una manera
	private int indFichaActual;

	static final int DIALOGO_POSICIONES = 0;
	static final int DIALOGO_COMO = 1;
	static final int DIALOGO_DEDICATORIA = 2;
	static final int DIALOGO_ACERCA = 3;

	/*
	 * 
	 * Menu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.piramido_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.nuevo_juego:
			nuevoJuego();
			return true;
		case R.id.cambiar_tipo:
			// cambiaTipo();
			Log.d(tag, "menu cambiar tipo");
			Toast toast = Toast.makeText(getApplicationContext(),
					"Cambiar el tipo de juego empieza un nuevo juego",
					Toast.LENGTH_LONG);
			toast.show();
			return true;
		case R.id.clasico:
			// cambiaTipo();
			Log.d(tag, "menu cambiar tipo clasico");
			tipoJuego = 0;
			item.setChecked(true);
			nuevoJuego();
			return true;
		case R.id.siempre_gana:
			// cambiaTipo();
			Log.d(tag, "menu cambiar tipo  siempre gana");
			item.setChecked(true);
			tipoJuego = 1;
			nuevoJuego();
			return true;
		case R.id.randomita:
			// cambiaTipo();
			Log.d(tag, "menu cambiar tipo randomita");
			/*item.setChecked(true);
			tipoJuego = 3;*/
			Toast toast2 = Toast.makeText(getApplicationContext(),
					"Todavia no programado",
					Toast.LENGTH_LONG);
			toast2.show();
			return true;

		case R.id.ayuda:
			// a();
			Log.d(tag, "menu ayuda");
			return true;
		case R.id.posiciones:
			Log.d(tag, "menu posciciones");
			muestraPosiciones();
			showDialog(DIALOGO_POSICIONES);
			return true;
		case R.id.Acerca:
			Log.d(tag, "menu acerca");
			showDialog(DIALOGO_ACERCA);
			return true;
		case R.id.como:
			Log.d(tag, "menu como");
			showDialog(DIALOGO_COMO);
			return true;
		case R.id.historia:
			Log.d(tag, "menu historia");
			showDialog(DIALOGO_DEDICATORIA);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void muestraPosiciones() {
		// TODO Auto-generated method stub

	}

	/*
	 * 
	 * 
	 * 
	 */

	public void botonazo(View v) {
		if (jugando) {

			int idBoton = botonConIndice(v.getId());
			if (idBoton == posiciones[indFichaActual]) {
				Log.d(tag, String.format("Botno indicado %d",idBoton));
				arrFichas[idBoton].setImageDrawable(getResources().getDrawable(
						dominos[idBoton]));
				destapados[idBoton] = true;
				sigFicha();
			}
		}
	}

	private void sigFicha() {
		// TODO Auto-generated method stub
		indFichaActual++;
		arrFichas[28].setImageDrawable(getResources().getDrawable(
				dominos[posiciones[indFichaActual]]));
		if (indFichaActual == 27) {
			Toast toast = Toast.makeText(getApplicationContext(),
					"¡Woooww, Ganaste! Que suertudo eres : P",
					Toast.LENGTH_LONG);
			toast.show();
			arrFichas[0].setImageDrawable(getResources()
					.getDrawable(dominos[0]));

		} else
			switch (tipoJuego) {

			case 0:
				if (posiciones[indFichaActual] == 0) {
					Toast toast = Toast.makeText(getApplicationContext(),
							"¡Ouch! Haz perdido : (", Toast.LENGTH_LONG);
					toast.show();
					jugando = false;
					arrFichas[0].setImageDrawable(getResources().getDrawable(
							dominos[0]));
				}

				break;
			case 1:/*
				if (posiciones[indFichaActual] == 0) {
					int i = 1;
					while (destapados[i]) {
						i++;
					}
					posiciones[indFichaActual] = i;

					if (destapados[posiciones[i]]) {
						int j = 1;
						while (destapados[j]) {
							j++;
						}
						posiciones[i] = j;
					}
					arrFichas[28].setImageDrawable(getResources().getDrawable(
							dominos[i]));
				}*/
				if (posiciones[indFichaActual] == 0||destapados[posiciones[indFichaActual]]) {
					int i = 1;
					while (destapados[i]) {
						i++;
					}
					posiciones[indFichaActual] = i;

					arrFichas[28].setImageDrawable(getResources().getDrawable(
							dominos[i]));
				}


				break;
			case 2:

				break;

			default:
				break;
			}
	}

	private int botonConIndice(int id) {
		int i = 0;
		// TODO Auto-generated method stub
		while (arrFichas[i].getId() != id) {
			i++;
		}

		return i;
	}

	private void nuevoJuego() {
		// TODO Auto-generated method stub
		Log.d(tag, "Nuevo juego selecciondo");
		for (int i = 0; i < arrFichas.length; i++) {
			arrFichas[i].setImageDrawable(getResources().getDrawable(
					dominos[28]));
		}
		jugando = true;
		destapados = new boolean[28];

		switch (tipoJuego) {
		// clasico
		case 0:
			iniciaClasico();
			break;
		case 1:
			// iniciaSiempreGana();
			iniciaClasico();
			break;

		case 2:
			// iniciaRandomita();
			
			break;

		default:
			break;
		}

	}

	private void iniciaClasico() {
		// TODO Auto-generated method stub
		for (int i = 0; i < posiciones.length; i++) {
			posiciones[i] = i;
		}
		// CAMBIASR!!!!!!!!!!!!!!!11
		// posiciones[posiciones.length-1]=0;

		for (int i = 0; i < posiciones.length; i++) {
			int randomPosition = rgen.nextInt(posiciones.length);
			int temp = posiciones[i];
			posiciones[i] = posiciones[randomPosition];
			posiciones[randomPosition] = temp;
		}

		// Collections.shuffle(Arrays.asList(posiciones));

		for (int i = 0; i < posiciones.length; i++) {
			String g = String.format("%d", posiciones[i]);
			Log.d(tag, g);
		}

		arrFichas[0].setVisibility(1);
		arrFichas[0].setImageDrawable(getResources().getDrawable(dominos[29]));

		indFichaActual = -1;
		/*
		 * arrFichas[28].setImageDrawable(getResources().getDrawable(
		 * dominos[posiciones[indFichaActual]]));
		 */
		sigFicha();

	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		indFichaActual = savedInstanceState.getInt("fichaActual");
		jugando = savedInstanceState.getBoolean("jugando");
		posiciones = savedInstanceState.getIntArray("posiciones");
		destapados = savedInstanceState.getBooleanArray("destapados");

		tipoJuego = savedInstanceState.getInt("tipoJuego");
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		outState.putInt("fichaActual", indFichaActual);
		outState.putBoolean("jugando", jugando);
		outState.putIntArray("posiciones", posiciones);
		outState.putBooleanArray("destapados", destapados);

		outState.putInt("tipoJuego", tipoJuego);

		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		creaArrFichas();
		switch (tipoJuego) {

		case 1:
			// MenuItem R.id.siempre_gana cambiar opcion de menu
			break;
		case 2:

			break;

		default:
			break;
		}

		if (jugando) {
			arrFichas[0].setVisibility(1);
			arrFichas[0].setImageDrawable(getResources().getDrawable(
					dominos[29]));
			arrFichas[28].setImageDrawable(getResources().getDrawable(
					dominos[posiciones[indFichaActual]]));
			for (int i = 0; i < destapados.length; i++) {
				if (destapados[i]) {
					arrFichas[i].setImageDrawable(getResources().getDrawable(
							dominos[i]));
				}
			}
		}

		super.onResume();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.piramido);

		Drawable[] drDominos;

		creaArrFichas();

		// button.setBackgroundColor(RESULT_OK);
		drDominos = new Drawable[dominos.length];
		for (int i = 0; i < drDominos.length - 1; i++) {
			drDominos[i] = getResources().getDrawable(dominos[i]);
		}
		// pinta fichas
		for (int i = 0; i < arrFichas.length; i++) {
			arrFichas[i].setImageDrawable(getResources().getDrawable(
					dominos[28]));
		}

	}

	private void creaArrFichas() {
		// TODO Auto-generated method stub
		arrFichas = new ImageButton[] {

		(ImageButton) findViewById(R.id.bo0),
				(ImageButton) findViewById(R.id.bo1),
				(ImageButton) findViewById(R.id.bo2),
				(ImageButton) findViewById(R.id.bo3),
				(ImageButton) findViewById(R.id.bo4),
				(ImageButton) findViewById(R.id.bo5),
				(ImageButton) findViewById(R.id.bo6),
				(ImageButton) findViewById(R.id.bo7),
				(ImageButton) findViewById(R.id.bo8),
				(ImageButton) findViewById(R.id.bo9),
				(ImageButton) findViewById(R.id.bo10),

				(ImageButton) findViewById(R.id.bo11),
				(ImageButton) findViewById(R.id.bo12),
				(ImageButton) findViewById(R.id.bo13),
				(ImageButton) findViewById(R.id.bo14),
				(ImageButton) findViewById(R.id.bo15),
				(ImageButton) findViewById(R.id.bo16),
				(ImageButton) findViewById(R.id.bo17),
				(ImageButton) findViewById(R.id.bo18),
				(ImageButton) findViewById(R.id.bo19),
				(ImageButton) findViewById(R.id.bo20),

				(ImageButton) findViewById(R.id.bo21),
				(ImageButton) findViewById(R.id.bo22),
				(ImageButton) findViewById(R.id.bo23),
				(ImageButton) findViewById(R.id.bo24),
				(ImageButton) findViewById(R.id.bo25),
				(ImageButton) findViewById(R.id.bo26),
				(ImageButton) findViewById(R.id.bo27),

				(ImageButton) findViewById(R.id.bo28),

		};
	}

	protected Dialog onCreateDialog(int id) {
		Dialog dialogo = null;

		switch (id) {
		case DIALOGO_POSICIONES:
			dialogo = null;
			// do the work to define the pause Dialog
			Dialog pos =  new Dialog(this);
			pos.setContentView(R.layout.posiciones);
			pos.setTitle("Posiciones");			
			
			/*
			Context mContext = getApplicationContext();
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.custom_dialog,
			                               (ViewGroup) findViewById(R.id.layout_root));
			
			// do the work to define the game over Dialog
			AlertDialog.Builder posBuilder = new AlertDialog.Builder(this);
			posBuilder
			.setTitle("Acerca")
			.setMessage("Esta aplicación es la digitalización del solitario de domino, piramido." +
					"\n \n Creada por:\n \tHugo Mosh \n \t hugomosh.com ")	
			.setNeutralButton("Vale, a jugar", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							})
					.setCancelable(false);
			AlertDialog pos = posBuilder.create();
			// do the work to define the game over Dialog
			dialogo = pos;*/
			
			
			
			// do the work to define the game over Dialog
			dialogo = pos;
			break;
		case DIALOGO_COMO:
			AlertDialog.Builder comoBuilder = new AlertDialog.Builder(this);
			comoBuilder
			.setTitle("Cómo Jugar")
			.setMessage("La ficha de domino visible al iniciar un juego debe ser 'colocada' " +
					"en su posición adecuada, esto hara que la ficha este en su lugar y" +
					" destapara una nueva ficha por ser colocada en su posción. " +
					"\n Se gana el juego cuando todas las fichas estan destapadas. " +
					"Se pierde cuando sale la ficha del cero (clasico)  o una ficha repetida (Randomita)" +
					"\nEste es un juego de azar completamente asi que ¡Mucha Suerte!")	
			.setNeutralButton("Vale, a jugar", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							})
					.setCancelable(false).setPositiveButton("Ver Poscisiones",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									showDialog(DIALOGO_POSICIONES);
									dialog.cancel();
									
									
									
								}
							});
			AlertDialog como = comoBuilder.create();
			// do the work to define the game over Dialog
			dialogo = como;

			break;
		case DIALOGO_DEDICATORIA:
			// do the work to define the game over Dialog
			AlertDialog.Builder dedicatoriaBuilder = new AlertDialog.Builder(this);
			dedicatoriaBuilder
			.setTitle("Dedicatoria")
			.setMessage("Esta pequeña aplicación se la dedico a mi Abuelo, Horacio Hrndz, " +
					"por haber ensañado este pequeño solitario de domino " +
					"y por ser fuente de inspiración \n" +
					"Gracias")	
			.setNeutralButton("Vale, a jugar", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							})
					.setCancelable(false);
			AlertDialog dedicatoria = dedicatoriaBuilder.create();
			// do the work to define the game over Dialog
			dialogo = dedicatoria;
			
			break;
		case DIALOGO_ACERCA:
			// do the work to define the game over Dialog
			AlertDialog.Builder acercaBuilder = new AlertDialog.Builder(this);
			acercaBuilder
			.setTitle("Acerca")
			.setMessage("Esta aplicación es la digitalización del solitario de domino, piramido." +
					"\n \n Creada por:\n \tHugo Mosh \n \t hugomosh.com ")	
			.setNeutralButton("Vale, a jugar", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							})
					.setCancelable(false);
			AlertDialog acerca = acercaBuilder.create();
			// do the work to define the game over Dialog
			dialogo = acerca;
			
			break;
		default:
			dialogo = null;
		}
		return dialogo;
	}
	public void cierra(View v) {
		Dialog a;
		a=new Dialog(v.getContext());
		a.cancel();
	}

}