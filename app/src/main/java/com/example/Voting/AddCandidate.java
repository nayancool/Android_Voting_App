package com.example.Voting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class AddCandidate extends AppCompatActivity {

    TextView txtCan_name,txtCan_Addr,txtCan_phone,txtCan_des,txtCan_nick;
    ImageView img_can;
    Button Save,AddMore;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    Candidates candidates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_candidate);

        txtCan_name=findViewById(R.id.txtCan_name);
        txtCan_Addr=findViewById(R.id.txtCan_Addr);
        txtCan_phone=findViewById(R.id.txtCan_phone);
        txtCan_des=findViewById(R.id.txtCan_des);
        txtCan_nick=findViewById(R.id.txtCan_nick);

        img_can=findViewById(R.id.img_can);

        Save=findViewById(R.id.Save);
        AddMore=findViewById(R.id.AddMore);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = fAuth.getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Candidates");
        candidates=new Candidates();

        Intent data =getIntent();
        String can_name= data.getStringExtra("Can_Name");
        String can_add= data.getStringExtra("Can_Address");
        String can_phone= data.getStringExtra("Can_Phone");
        String can_des= data.getStringExtra("Can_description");
        String can_nick= data.getStringExtra("Can_Nickname");


        StorageReference candidateRef= storageReference.child("Candidates/"+fAuth.getCurrentUser().getUid()+"/candidate.jpg");
        candidateRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(img_can);
            }
        });

        img_can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openGalleryIntent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent,1000);
            }
        });

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtCan_name.getText().toString().isEmpty() || txtCan_Addr.getText().toString().isEmpty() || txtCan_phone.getText().toString().isEmpty() ||
                txtCan_des.getText().toString().isEmpty() || txtCan_nick.getText().toString().isEmpty())
                {
                    Toast.makeText(AddCandidate.this,"One or many field are empty",Toast.LENGTH_SHORT).show();
                    return;
                }

              // int phone=Integer.parseInt(txtCan_phone.getText().toString());
                candidates.setCan_name(txtCan_name.getText().toString().trim());
                candidates.setCan_address(txtCan_Addr.getText().toString().trim());
                candidates.setCan_description(txtCan_des.getText().toString().trim());
                candidates.setCan_nickname(txtCan_nick.getText().toString().trim());
              //  candidates.setCan_phone(phone);
                databaseReference.push().setValue(candidates);
                Toast.makeText(AddCandidate.this,"Data inserted Succesfully",Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(AddCandidate.this,MainActivity2.class);
                startActivity(intent);

            }
        });

        AddMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddCandidate.this,MainActivity2.class);
                startActivity(intent);
            }
        });



    }
}