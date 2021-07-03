package com.example.Voting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NewElection extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    ListView listView;
    Button btnVote,btnBack;
    int count1,count2,count3,count4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_election);

        listView=findViewById(R.id.listView);
        btnVote=findViewById(R.id.btnVote);

        btnBack=findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NewElection.this,MainActivity2.class);
                startActivity(intent);
            }
        });
          final ArrayList<String> list=new ArrayList<>();
          final ArrayAdapter adapter=new ArrayAdapter<String>(this, R.layout.list_item,list);
          listView.setAdapter(adapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Candidates");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Candidates can=snapshot.getValue(Candidates.class);
                    String txt= can.getCan_name() + " : " +can.getCan_address();
                    list.add(txt);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
/*
        btnVote1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count1++;
                Toast.makeText(NewElection.this,"Your vote has been registered",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(NewElection.this,MainActivity2.class);
                startActivity(intent);
            }
        });
        btnVote2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count2++;
                Toast.makeText(NewElection.this,"Your vote has been registered",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(NewElection.this,MainActivity2.class);
                startActivity(intent);
            }
        });
        btnVote3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count3++;
                Toast.makeText(NewElection.this,"Your vote has been registered",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(NewElection.this,MainActivity2.class);
                startActivity(intent);
            }
        });
        btnVote4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count4++;
                Toast.makeText(NewElection.this,"Your vote has been registered",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(NewElection.this,MainActivity2.class);
                startActivity(intent);
            }
        });

 */
    }
    public void show_popup(View view){
        PopupMenu popup=new PopupMenu(this,view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.show_popup);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.vote1:
                Toast.makeText(this,"You voted for Candidate 1",Toast.LENGTH_LONG).show();
                count1++;
                return true;

            case R.id.vote2:
                Toast.makeText(this,"You voted for Candidate 2",Toast.LENGTH_LONG).show();
                count2++;
                return true;

            case R.id.vote3:
                Toast.makeText(this,"You voted for Candidate 3",Toast.LENGTH_LONG).show();
                count3++;
                return true;

            case R.id.vote4:
                Toast.makeText(this,"You voted for Candidate 4",Toast.LENGTH_LONG).show();
                count4++;
                return true;

            default:
                return false;
        }
    }
}