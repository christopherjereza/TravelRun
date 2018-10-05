package com.nowrunning.travelrun;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;

public class NewUserActivity extends AbsActivity {

    private EditText emailIn;
    private EditText passIn;
    private EditText dateOfBirth;
    private EditText bioIn;
    private EditText whatUpIn;
    private EditText nameIn;

    private Button contButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        emailIn =(EditText) findViewById(R.id.editText2);

        passIn =(EditText) findViewById(R.id.editText4);

        dateOfBirth =(EditText) findViewById(R.id.editText10);

        bioIn =(EditText) findViewById(R.id.inputbio);

        whatUpIn =(EditText) findViewById(R.id.inputwhat);

        nameIn = (EditText) findViewById(R.id.nameIn);

        contButton = (Button) findViewById(R.id.button);

        contButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OwnerProfile.seteMail(emailIn.getText().toString());
                OwnerProfile.setName(nameIn.getText().toString());
                OwnerProfile.setMyPassword(passIn.getText().toString());
                //OwnerProfile.setAge();
                OwnerProfile.setBio(bioIn.getText().toString());
                OwnerProfile.setWhatsUp(whatUpIn.getText().toString());

                databaseRef.child("userFindByEmail").child(OwnerProfile.getName()).child("FullEmail").setValue(OwnerProfile.geteMail());

                DatabaseReference pushedRef = databaseRef.child("user").push();
                OwnerProfile.setUniqueID(pushedRef.getKey());

                databaseRef.child("userFindByEmail").child(OwnerProfile.getName()).child("id").setValue(OwnerProfile.getUniqueID());

                

            }
        });


    }
}
