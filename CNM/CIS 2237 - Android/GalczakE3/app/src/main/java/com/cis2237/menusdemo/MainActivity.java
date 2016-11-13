package com.cis2237.menusdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, PopupMenu.OnMenuItemClickListener{

    private PopupMenu popupMenu;
    private Button btnPopup;
    private TextView txtContext;

    private ImageView imgCartoon;
    private TextView txtCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Changing title of titleBar
        setTitle("Character Selector");
        setContentView(R.layout.activity_main);

        // Initializing design objects
        btnPopup = (Button)findViewById(R.id.btnAnchor);
        imgCartoon = (ImageView)findViewById(R.id.imgCartoon);
        txtCharacter = (TextView)findViewById(R.id.txtSelection);
        txtContext = (TextView)findViewById(R.id.txtContext);

        // Initializing popup menu options
        popupMenu = new PopupMenu(this, findViewById(R.id.btnAnchor));
        popupMenu.getMenu().add(Menu.NONE, 1, Menu.NONE, "Spider-Man");
        popupMenu.getMenu().add(Menu.NONE, 2, Menu.NONE, "Incredible Hulk");

        // Assigning popupmenu and its button listeners
        popupMenu.setOnMenuItemClickListener(this);
        btnPopup.setOnClickListener(this);

        // Attaching context menu to txtContext
        registerForContextMenu(txtContext);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("DC Characters");
        menu.add(0, v.getId(), 0, "Batman");
        menu.add(0, v.getId(), 0, "Super-Man");
        menu.add(0, v.getId(), 0, "Wonder-Woman");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle().equals("Batman")){
            imgCartoon.setImageResource(R.drawable.batman);
            txtCharacter.setText(item.getTitle());

        }
        else if(item.getTitle().equals("Super-Man")){
            imgCartoon.setImageResource(R.drawable.superman);
            txtCharacter.setText(item.getTitle());
        }
        else if(item.getTitle().equals("Wonder-Woman")){
            imgCartoon.setImageResource(R.drawable.wonderwoman);
            txtCharacter.setText(item.getTitle());
        }
        else{
            return false;
        }
        return true;
    }



    @Override
    public void onClick(View v) {
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                txtCharacter.setText("Spider-Man");
                Toast.makeText(this, "Spider-Man selected", Toast.LENGTH_SHORT).show();
                imgCartoon.setImageResource(R.drawable.spiderman);
                break;
            case 2:
                txtCharacter.setText("Incredible Hulk");
                Toast.makeText(this, "Incredible Hulk selected", Toast.LENGTH_SHORT).show();
                imgCartoon.setImageResource(R.drawable.hulk);
                break;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_action_bar, menu);
        return true;
        // anonymous version,  getMenuInflater().inflate(R.menu.menu_action_bar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_goofy:
                imgCartoon.setImageResource(R.drawable.goofy);
                txtCharacter.setText("Goofy");
                break;
            case R.id.action_mickey:
                imgCartoon.setImageResource(R.drawable.mickey);
                txtCharacter.setText("Mickey");
                break;
            case R.id.action_clear:
                Toast.makeText(this, "Cleared!", Toast.LENGTH_SHORT).show();
                imgCartoon.setImageResource(0);
                txtCharacter.setText("Character name:");
                break;
            default:
                break;
        }

        return true;
    }


}
