package com.example.joser.trident;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnPlay;
    private Button btnRestart;
    private ImageView piezaView;
    private TextView txtFaltantes;
    private TextView txtJugada;
    private TextView txtMarcador;
    private MediaPlayer piezaSound;
    private MediaPlayer restartSound;
    private MediaPlayer endSound;
    private ArrayList listaUsados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = (Button) findViewById(R.id.play);
        btnRestart = (Button) findViewById(R.id.restart);
        piezaView = (ImageView) findViewById(R.id.piezaView);
        txtFaltantes = (TextView) findViewById(R.id.faltantesView);
        txtJugada = (TextView) findViewById(R.id.tv_jugada);
        txtMarcador = (TextView) findViewById(R.id.txtMarcador);
        listaUsados = new ArrayList();
        piezaSound = MediaPlayer.create(this, R.raw.standby);
        restartSound = MediaPlayer.create(this, R.raw.news);
        endSound = MediaPlayer.create(this, R.raw.dada0);
        txtJugada.setText(R.string.txt_presionaverde);
        txtMarcador.setVisibility(View.INVISIBLE);

        btnPlay.setOnClickListener(this);
        btnRestart.setOnClickListener(this);

        changeFont();
        putImg("welcome2");
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragment = getSupportFragmentManager();
        DialogoSalir salir = new DialogoSalir();
        salir.show(fragment, "TagSalir");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_settings:
                Intent i = new Intent(this, Reglas.class);
                startActivity(i);
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == btnPlay.getId()) {
            animacionBnt(btnPlay);
            txtMarcador.setVisibility(View.VISIBLE);
            piezaView.setOnClickListener(this);
        }
        else if (v.getId() == btnRestart.getId()) {
            restart();
            return;
        }

        showPlay();
        animacion();
        txtFaltantes.setText(contPiezas());
    }

    private String contPiezas() {
        int contador = listaUsados.size();
        return String.valueOf(28 - contador);
    }

    private void putImg(String nombreImg) {
        int id = getResources().getIdentifier(nombreImg, "drawable", getPackageName());
        piezaView.setImageResource(id);
    }

    public void showPlay() {
        //Asignando imagen de pieza
        String imgName = "img" + generateNumber();
        if (imgName.equals("img19")) {
            soundPieza();
            putImg(imgName);
            Toast.makeText(this, "BEBE EL TRIKI!!!", Toast.LENGTH_SHORT).show();
            txtJugada.setText(showInstruction(imgName));
        } else {
            soundPieza();
            putImg(imgName);
            txtJugada.setText(showInstruction(imgName));
        }
    }

    @SuppressWarnings("unchecked")
    public int generateNumber() {
        Random random = new Random();
        if (listaUsados.size() < 28) {
            int agarrar = random.nextInt(28) + 1;
            if (listaUsados.isEmpty()) {
                listaUsados.add(agarrar);
                return agarrar;
            } else {
                if (listaUsados.contains(agarrar)) {
                    return generateNumber();
                } else {
                    listaUsados.add(agarrar);
                    return agarrar;
                }
            }
        } else {
            //Llamando al Dialogo creado.
            endGame();
            return -1;
        }
    }

    public void endGame() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DialogoFin fin = new DialogoFin();
        fin.show(fragmentManager, "TagFin");
        soundEnd();
    }

    public void animacion() {
        // Animations with DaimajiaAnimations Library
        Random aleatorio = new Random();
        int azar = aleatorio.nextInt(5);

        switch (azar) {
            case 0:
                YoYo.with(Techniques.ZoomInUp).duration(700).playOn(piezaView);
                break;
            case 1:
                YoYo.with(Techniques.RubberBand).duration(700).playOn(piezaView);
                break;
            case 2:
                YoYo.with(Techniques.Pulse).repeat(2).duration(500).playOn(piezaView);
                break;
            case 3:
                YoYo.with(Techniques.FlipInX).duration(700).playOn(piezaView);
                break;
            case 4:
                YoYo.with(Techniques.FlipInY).duration(700).playOn(piezaView);
                break;
            default:
                YoYo.with(Techniques.Tada).duration(700).playOn(piezaView);
        }
    }

    private void animacionBnt(View v) {
        if (v.getId() == btnRestart.getId())
            YoYo.with(Techniques.ZoomInLeft).duration(1000).playOn(btnPlay);
        else
            YoYo.with(Techniques.ZoomOutLeft).duration(1000).playOn(btnPlay);
    }

    public String showInstruction(String leer) {
        String tomarString;

        switch (leer) {
            case "img1":
                tomarString = getString(R.string.jugada1);
                return tomarString;
            case "img2":
                tomarString = getString(R.string.jugada2);
                return tomarString;
            case "img3":
                tomarString = getString(R.string.jugada3);
                return tomarString;
            case "img4":
                tomarString = getString(R.string.jugada4);
                return tomarString;
            case "img5":
                tomarString = getString(R.string.jugada5);
                return tomarString;
            case "img6":
                tomarString = getString(R.string.jugada6);
                return tomarString;
            case "img7":
                tomarString = getString(R.string.jugada7);
                return tomarString;
            case "img8":
                tomarString = getString(R.string.jugada8);
                return tomarString;
            case "img9":
                tomarString = getString(R.string.jugada9);
                return tomarString;
            case "img10":
                tomarString = getString(R.string.jugada10);
                return tomarString;
            case "img11":
                tomarString = getString(R.string.jugada11);
                return tomarString;
            case "img12":
                tomarString = getString(R.string.jugada12);
                return tomarString;
            case "img13":
                tomarString = getString(R.string.jugada13);
                return tomarString;
            case "img14":
                tomarString = getString(R.string.jugada14);
                return tomarString;
            case "img15":
                tomarString = getString(R.string.jugada15);
                return tomarString;
            case "img16":
                tomarString = getString(R.string.jugada16);
                return tomarString;
            case "img17":
                tomarString = getString(R.string.jugada17);
                return tomarString;
            case "img18":
                tomarString = getString(R.string.jugada18);
                return tomarString;
            case "img19":
                tomarString = getString(R.string.jugada19);
                return tomarString;
            case "img20":
                tomarString = getString(R.string.jugada20);
                return tomarString;
            case "img21":
                tomarString = getString(R.string.jugada21);
                return tomarString;
            case "img22":
                tomarString = getString(R.string.jugada22);
                return tomarString;
            case "img23":
                tomarString = getString(R.string.jugada23);
                return tomarString;
            case "img24":
                tomarString = getString(R.string.jugada24);
                return tomarString;
            case "img25":
                tomarString = getString(R.string.jugada25);
                return tomarString;
            case "img26":
                tomarString = getString(R.string.jugada26);
                return tomarString;
            case "img27":
                tomarString = getString(R.string.jugada27);
                return tomarString;
            case "img28":
                tomarString = getString(R.string.jugada28);
                return tomarString;
            default:
                return "";
        }
    }

    private void soundPieza() {
        piezaSound.start();
    }

    private void soundRestar() {
        restartSound.start();
    }

    private void soundEnd() {
        endSound.start();
    }

    private void restart() {
        listaUsados.clear();
        String colocar = String.valueOf(28);
        txtFaltantes.setText(colocar);
        animacionBnt(btnRestart);
        txtJugada.setText(R.string.txt_presionaverde);
        putImg("welcome2");
        soundRestar();
    }

    private void changeFont() {
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/Rubik-Black.ttf");
        txtJugada.setTypeface(customFont);
        txtFaltantes.setTypeface(customFont);
        txtMarcador.setTypeface(customFont);
    }
}
