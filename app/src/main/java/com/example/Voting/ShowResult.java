package com.example.Voting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ShowResult extends NewElection {
    Button Back,button2;
    EditText winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);

        Back=findViewById(R.id.Back);
        winner=findViewById(R.id.winner);
        button2=findViewById(R.id.button2);


       if((count1>count2) &&(count1>count3)&&(count1>count4))
        {
            Toast.makeText(ShowResult.this,"Winner is Candidate 1",Toast.LENGTH_LONG).show();
            winner.setText("Candidate 1 is winner");


        }
         if((count2>count3)&&(count2>count4)&&(count2>count1))
            {
                Toast.makeText(ShowResult.this,"Winner is Candidate 2",Toast.LENGTH_LONG).show();
                winner.setText("Candidate 2 is winner");
            }
             if((count3>count4)&&(count3>count2)&&(count3>count1))
                {
                    Toast.makeText(ShowResult.this,"Winner is Candidate 3",Toast.LENGTH_LONG).show();
                    winner.setText("Candidate 3 is winner");
                }
                if((count4>count3)&&(count4>count2)&&(count4>count1))
                {
                    Toast.makeText(ShowResult.this,"Winner is Candidate 4",Toast.LENGTH_LONG).show();
                    winner.setText("Candidate 4 is winner");
                }

      /*
        if((count1>count2) &&(count1>count3)&&(count1>count4))
        {
            Toast.makeText(ShowResult.this,"Winner is Candidate 1",Toast.LENGTH_LONG).show();
            winner.setText("Candidate 1 is winner");


        }
        else if((count2>count3)&&(count2>count4))
        {
            Toast.makeText(ShowResult.this,"Winner is Candidate 2",Toast.LENGTH_LONG).show();
            winner.setText("Candidate 2 is winner");
        }
        else 
        {
            Toast.makeText(ShowResult.this,"Winner is Candidate 3",Toast.LENGTH_LONG).show();
            winner.setText("Candidate 3 is winner");
        }
       /* else
        {
            Toast.makeText(ShowResult.this,"Winner is Candidate 4",Toast.LENGTH_LONG).show();
            winner.setText("Candidate 4 is winner");
        }
*/
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                winner.setText("Canidate 1 is winner");
            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(ShowResult.this,MainActivity2.class);
                        startActivity(intent);
                    }
                });

    }
}