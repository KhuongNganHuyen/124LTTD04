package com.example.baitap7sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import androidx.appcompat.app.AppCompatActivity;

public class CountryListActivity extends AppCompatActivity {

    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_SUBJECT, DatabaseHelper.COLUMN_DESC };
    final int[] to = new int[] { R.id.id, R.id.subject, R.id.description };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.item_country, cursor, from, to, 0);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent modify_intent = new Intent(getApplicationContext(), ModifyCountryActivity.class);
            modify_intent.putExtra("id", id);
            startActivity(modify_intent);
        });
    }
}
