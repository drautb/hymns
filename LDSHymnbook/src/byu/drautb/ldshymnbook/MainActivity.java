package byu.drautb.ldshymnbook;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Load the hymns into the list
		ListView listView = (ListView)findViewById(R.id.lv_hymn_list);

		ArrayList<Hymn> hymns = loadHymns();
		
		// First paramenter - Context
		// Second parameter - Layout for the row
		// Third parameter - ID of the TextView to which the data is written
		// Forth - the Array of data
		ArrayAdapter<Hymn> adapter = new ArrayAdapter<Hymn>(this, android.R.layout.simple_list_item_1, android.R.id.text1, hymns);

		// Assign adapter to ListView
		listView.setAdapter(adapter); 
		
		// Set up an event for item clicks
		listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                
                String item = ((TextView)view).getText().toString();
                item = item.replace(" - ", " ");
                item = item.concat(".pdf");
                
                Uri path = Uri.parse("android.resource://" + getPackageName() + "/assets/pdfs/" + item);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(path, "application/pdf");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public ArrayList<Hymn> loadHymns() {
		// Container for hymn objects
		ArrayList<Hymn> hymns = new ArrayList<Hymn>();
			
		// All the filenames in the pdf directory
		String[] pdfs = null;
		try {
			pdfs = getAssets().list("pdfs");
		} catch (Exception e) {}
		
		if (pdfs == null)
			return hymns;
		
		for (String pdf : pdfs) {
			int number = Integer.parseInt(pdf.substring(0, pdf.indexOf(' ')));
			String name = pdf.substring(pdf.indexOf(' '), pdf.indexOf(".pdf"));
			
			hymns.add(new Hymn(number, name, pdf));				   
		}
		
		return hymns;
	}
	

}
