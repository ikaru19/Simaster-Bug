package com.ikaru19.simaster_bug;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ikaru19.simaster_bug.adapters.ContactAdapter;
import com.ikaru19.simaster_bug.models.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ContactAdapter adapter;
    List<Contact> contacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        recyclerView = findViewById(R.id.rv_contacts);
        addContact();
        adapter = new ContactAdapter(contacts, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void addContact(){
        contacts.add(new Contact("Heri Prabowo, MSc","Peneliti Hama","+6281328273472","https://www.simasterbugs.com/image/Heri_Prabowo.jpg"));
        contacts.add( new Contact("Nur Asbani, PhD","Peneliti Hama","+15619833703","https://www.simasterbugs.com/image/asbani.jpg"));
        contacts.add( new Contact("Sri Adi Kadarsih , MSc","Peneliti Pemulia Tanaman","+628156863311","https://www.simasterbugs.com/image/123.jpg"));
    }
}
