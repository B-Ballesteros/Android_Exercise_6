package itesm.mx.m1_jbbm_labo_jugadores;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;

public class JugadorActivity extends AppCompatActivity implements View.OnClickListener{

    final int REQUEST_IMAGE_CAPTURE = 1;

    EditText etNombreJugador;
    ImageView ivFoto;
    Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugador);

        etNombreJugador = (EditText)findViewById(R.id.edit_jugador);
        Button btnTomarFoto = (Button)findViewById(R.id.button_tomar_foto);
        Button btnGuardar = (Button)findViewById(R.id.button_guardar_jugador);
        ivFoto = (ImageView)findViewById(R.id.image_foto);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null ){
            etNombreJugador.setText(bundle.getString("nombre"));
            imageBitmap = BitmapFactory.decodeByteArray(bundle.getByteArray("foto"), 0,
                    bundle.getByteArray("foto").length);
            ivFoto.setImageBitmap(imageBitmap);
        }

        btnTomarFoto.setOnClickListener(this);
        btnGuardar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button_tomar_foto:
                Intent fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(fotoIntent.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(fotoIntent, REQUEST_IMAGE_CAPTURE);
                }
                break;

            case  R.id.button_guardar_jugador:
                if(etNombreJugador.getText() != null){
                    Toast.makeText(this.getApplicationContext(),
                            "El jugador se ha registrado exitosamente", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent();
                    intent.putExtra("nombre", etNombreJugador.getText().toString());

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    intent.putExtra("foto", byteArray);

                    setResult(RESULT_OK, intent);
                    finish();
                }else{
                    Toast.makeText(this.getApplicationContext(), "Datos incorrectos",
                            Toast.LENGTH_LONG).show();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap)extras.get("data");
            ivFoto.setImageBitmap(imageBitmap);
        }
    }
}
