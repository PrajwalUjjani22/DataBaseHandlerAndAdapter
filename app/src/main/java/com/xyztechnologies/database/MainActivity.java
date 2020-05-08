package com.xyztechnologies.database;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ListView contactView;

    List<Contact> list;
    MyAdapter adapter;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactView = findViewById(R.id.contactView);

        db = new DatabaseHandler(this);
        Log.d("Insert: ", "Inserting ..");
        db.addContact(new Contact("Ravi", "9100000000"));
        db.addContact(new Contact("Srinivas", "9199999999"));
        db.addContact(new Contact("Tommy", "9522222222"));
        db.addContact(new Contact("Karthik", "9533333333"));




        // Reading all contacts
        final List<Contact> contactsList = db.getAllContacts();

        adapter = new MyAdapter(contactsList,MainActivity.this);
        contactView.setAdapter(adapter);

        contactView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, ""+contactsList.get(position).getID(), Toast.LENGTH_SHORT).show();
                Contact contact = contactsList.get(position);
                openDialog(contact);
            }

        });


        for (Contact cn : contactsList) {
            String log = "Id: " + cn.getID() + " ,Name: " + cn.getName() + " ,Phone: " +
                    cn.getPhoneNumber();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }

    }

    public void openDialog(Contact det){
            final Contact cntcts = det;
            final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.mycustom_dialog, null);


            final EditText name = dialogView.findViewById(R.id.edtContactName);
            final EditText phone = dialogView.findViewById(R.id.edtContactNumber);

            //TextView remarks = dialogView.findViewById(R.id.remar);

            name.setText(cntcts.getName());
            phone.setText(cntcts.getPhoneNumber());

            final Button button1 = dialogView.findViewById(R.id.btnUpdate);
            Button button2 = dialogView.findViewById(R.id.buttonCancel);
            Button button3 = dialogView.findViewById(R.id.buttonDelete);



            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    db.getContactsCount();
                    dialogBuilder.dismiss();
                }
            });
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db.deleteContact(cntcts);
                    dialogBuilder.dismiss();
                }
            });
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // DO SOMETHINGS
                    //det.setRemarks(contactView.getText().toString());

                    cntcts.setName(name.getText().toString());
                    cntcts.setPhoneNumber(phone.getText().toString());

                    db.updateContact(cntcts);
                    //refreshList();
                    dialogBuilder.dismiss();
                }
            });

            dialogBuilder.setView(dialogView);
            dialogBuilder.show();
    }


}

