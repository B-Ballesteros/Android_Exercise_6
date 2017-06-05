package itesm.mx.m1_jbbm_labo_jugadores;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by benji on 18/02/17.
 */

public class JugadorAdapter extends ArrayAdapter<Jugador>{
    ArrayList<Jugador> jugadores;
    Context context;

    public JugadorAdapter(Context context, int resource, ArrayList<Jugador> objects){
        super(context, resource, objects);
        this.jugadores = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;

        if(v == null){
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.row, null);
        }

        Jugador jugador = jugadores.get(position);
        if(jugador != null){
            TextView tvNombre = (TextView) v.findViewById(R.id.text_nombre);
            ImageView ivFoto = (ImageView) v.findViewById(R.id.image_foto);

            tvNombre.setText(jugador.getNombre());
            byte[] byteArray = jugador.getByteArrayFoto();
            Bitmap imageBitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            ivFoto.setImageBitmap(imageBitmap);
        }
        return  v;
    }
}
