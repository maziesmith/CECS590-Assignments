package android.csulb.edu.notedatabase;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ListActivity extends AppCompatActivity {

    private DBManager dbManager;
    private ListView listview;
    private SimpleCursorAdapter adapter;
    private int photoId;
    final String[] from = new String[]{DBHelper.NOTE_CAPTION};
    final int[] to = new int[]{R.id.viewCaption};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListActivity.this,AddPhotoActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_uninstall:
                Uri packageUri = Uri.parse("package:android.csulb.edu.notedatabase");
                Intent uninstallIntent = new Intent(Intent.ACTION_UNINSTALL_PACKAGE,packageUri);
                startActivity(uninstallIntent);
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listview =(ListView)findViewById(R.id.listView);
        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record,cursor,from,to,0);

        adapter.notifyDataSetChanged();

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView captionTextView = (TextView) view.findViewById(R.id.viewCaption);;
                Intent intent = new Intent(ListActivity.this,ViewPhotoActivity.class);
                intent.putExtra("photoId", position+1);
                startActivity(intent);
            }
        });
    }
}
