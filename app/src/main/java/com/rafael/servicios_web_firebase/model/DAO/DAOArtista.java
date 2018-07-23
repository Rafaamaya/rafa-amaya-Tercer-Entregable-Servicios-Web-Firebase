package com.rafael.servicios_web_firebase.model.DAO;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rafael.servicios_web_firebase.model.POJO.Artista;
import com.rafael.servicios_web_firebase.ResultadoListener;

public class DAOArtista {
    private static final String CONTACTOS = "artists";
    private FirebaseDatabase firebaseDatabase;

    public void obtenerArtista (String id, final ResultadoListener <Artista> artistaResultadoListenerController){
        String artistaId=ajustarId(id);
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference().child(CONTACTOS).child(artistaId);
        Log.i("VALORID",id);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                if (!dataSnapshot.exists()) {
                    return;
                }
                Artista artista = dataSnapshot.getValue(Artista.class);
                artistaResultadoListenerController.resultado(artista);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Toast.makeText(FirebaseDB.this, "Fallo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String ajustarId (String id){
        Integer num = Integer.valueOf(id)-1;
        String resul = num.toString();
        return resul;
    }

}
