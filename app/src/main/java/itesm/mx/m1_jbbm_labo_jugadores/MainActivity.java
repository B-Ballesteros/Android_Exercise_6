package itesm.mx.m1_jbbm_labo_jugadores;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends ListActivity implements AdapterView.OnItemClickListener {

    final int REQUEST_CODE_AGREGAR = 1;

    ArrayList<Jugador> jugadores;
    JugadorAdapter jugadoresAdapter;
    int indexJugador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jugadores = getDataForListView();
        jugadoresAdapter = new JugadorAdapter(getApplicationContext(), R.layout.row, jugadores);
        setListAdapter(jugadoresAdapter);

        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        Intent intent;

        intent = new Intent(this, JugadorActivity.class);
        Jugador jugador = jugadores.get(position);
        intent.putExtra("nombre", jugador.getNombre());
        intent.putExtra("foto", jugador.getByteArrayFoto());
        startActivityForResult(intent, REQUEST_CODE_AGREGAR);
        indexJugador = position;
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == REQUEST_CODE_AGREGAR){
                Bundle datos = data.getExtras();
                Jugador jugador = new Jugador(datos.getString("nombre"), 0, null, 0,
                        datos.getByteArray("foto"));
                jugadores.set(indexJugador, jugador);
                jugadoresAdapter.notifyDataSetChanged();
            }
        }
    }

    public ArrayList<Jugador>getDataForListView(){
        Jugador jugador;

        ArrayList<Jugador> listaJugadores = new ArrayList<>();

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.person);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        jugador = new Jugador("Messi", 0, null, 0, byteArray);
        listaJugadores.add(jugador);

        jugador = new Jugador("Neymar", 0, null, 0, byteArray);
        listaJugadores.add(jugador);

        jugador = new Jugador("Ronaldo", 0, null, 0, byteArray);
        listaJugadores.add(jugador);

        return listaJugadores;
    }
}
