package ie.gmit.gmit;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

/*
 * This class holds a list of societies/clubs that are populated from a listAdapter
 * it uses the ClubInformation class to get this data, it then passes its data to the ClubInfo page for population when an item is selected
 */

public class ClubActivity extends ListActivity {
	private ClubInformation ci = null;
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		Intent i = new Intent(getBaseContext(), ClubInfo.class);
		i.putExtra("intent", position);
		i.putExtra("name", ci.getName(position));
		i.putExtra("body", ci.getBody(position));
		i.putExtra("link", ci.getLink(position));
		i.putExtra("image", ci.getImage(position));
		startActivity(i);
		
		//Toast.makeText(getBaseContext(), "tap: " + id , Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_club);
		ci = new ClubInformation();
		
		setTitle("Clubs & Societies");
		getActionBar().setDisplayHomeAsUpEnabled(true);		
		init();
	}

	private void init() {
		// gets and edits the ActionBar
		//ActionBar actionBar = getActionBar();
		//actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE);
		Log.d("GMIT", "1");
		// values to be added to list, The ListView itself

		//ListView listView = (ListView) findViewById(R.id.clubCon);
		
		// create a list and populate it
		ArrayList<String> list = new ArrayList<String>();
	    for (int i = 0; i < ci.getLen(); ++i) {
	      list.add(ci.getName(i));
	    }
	    Log.d("GMIT", "2");
		// adapter which interacts with list; adapter linked to ListView
	    @SuppressWarnings("unchecked")
		ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list){

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View view = super.getView(position, convertView, parent);
			    TextView text = (TextView) view.findViewById(android.R.id.text1);
			    
			    text.setTextColor(Color.WHITE);
			    // the below rgb values are the exact same as the official GMIT primary colour
			    view.setBackgroundColor(Color.rgb(125, 155, 193));
			    
				return view;
			}

	    };
	    Log.d("GMIT", "3");
	    //listView.setAdapter(adapter);
	    setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.club, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		
		switch (item.getItemId()){
			case android.R.id.home:
				// this is called when the Home (up) button is pressed in the action bar
				Intent parentActivityIntent = new Intent(this, MainActivity.class);
				parentActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(parentActivityIntent);
				finish();
				return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
}
