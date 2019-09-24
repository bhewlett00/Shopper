package com.example.shopper;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    //declare an intent
    Intent intent;

    //declare database handler
    DBHandler dbHandler;

    //decalre a Shopping Lists Cursor Adapter
    ShoppingLists shoppingListsAdapter;

    //declare a ListView
    ListView shopperListView;

    /**
     * This method initializes the Action Bar and view of the Main Activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // this code generated by AndroidStudio initializes the view and action bar of the Main Activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initialize the database handler
        dbHandler = new DBHandler(this, null);

        //initialize the ListView
        shopperListView = (ListView) findViewById(R.id.shopperListView);

        //initialize the Shopping Lists Cursor Adapter
        shoppingListsAdapter = new ShoppingLists(this, dbHandler.getShoppingList(), 0);

        //set ShoppingLists Cursor Adapter on ListView
        shopperListView.setAdapter(shoppingListsAdapter);

        //set OnItemClickListener to shopper ListView
        shopperListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //launching the ViewList Activity and sending it the id of the shopping list
                intent = new Intent(MainActivity.this, ViewList.class);
                intent.putExtra("_id", id);
                startActivity(intent);
            }
        });
    }

    /**
     * This method further initializes the Action Bar of hte Main Acrivity. It gets the code we
     * wrote in the menu main and incorporates it into the Action Bar.
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * This method gets called when an item in an overflow menu is selected and it controls
     * what happens when the item is selected.
     * @param item item selected in the overflow menu. It contains information about the item;
     *             for example, its id.
     * @return true if an item was selected; else it returns false.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // get id of item selected
        switch (item.getItemId()){
            case R.id.action_home:
                //initializing an intent for the Main Activity, starting it, and returning true
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_CreateList:
                // intializing an Intent for the CreateList Activty, starting it and returning true
                intent = new Intent(this, CreateList.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openCreateList(View view) {
    // intializing an Intent for the CreateList Activty and starting it
        intent = new Intent(this, CreateList.class);
        startActivity(intent);
    }
}
