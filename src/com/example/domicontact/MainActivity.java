/*
 * DomiContact Ver. 3.0
 * Start 2013.05.15 - End 2013.06.04
 * SQLite Adatbázis kezelés
 * Ami mûködik:
 *  - Add new record
 *  - Max record number
 *  - Read records to Edit window
 *  - Delete record by ID. Manuálisan, nem programban!
 *  - Delet All records
*/

package com.example.domicontact;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

// Nem szükséges a visszaadott értéket használni!
//	    final int cmax = countMaxRecord();
	    countMaxRecord();
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	    
	/** Called when the user clicks the addNewRecord button */
	public void addFourRecord(View view) {
        Log.d("DB insert: ", "addFourRecord...");

        DatabaseHandler db = new DatabaseHandler(this);
        db.addContact(new Contact("Ravi", "9100000000"));       
        db.addContact(new Contact("Srinivas", "9199999999"));
        db.addContact(new Contact("Tommy", "9522222222"));
        db.addContact(new Contact("Karthik", "9533333333"));
        
	    TextView textView1 = (TextView) findViewById(R.id.textView1 );
	    textView1.setText("addNewRecord");
//	    textView1.setText(Integer.toString(max_contact));
	    countMaxRecord();
        readRecord(null);
	}	

	/** Called when the user clicks the readRecord button */
	public void readRecord(View view) {
        Log.d("DB read: ", "readRecord...");

		DatabaseHandler db = new DatabaseHandler(this);
        
	    TextView textView1 = (TextView) findViewById(R.id.textView1 );
        EditText editText1 = (EditText) findViewById(R.id.editText1);

	    textView1.setText("readRecord");
//	    editText1.setText("A recordok..." + "\n");
	    editText1.setText("");

	    List<Contact> contacts = db.getAllContacts();      
        
        for (Contact cn : contacts) {
            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
                // Writing Contacts to log
        Log.d("Name: ", log);
//	    editText1.setText(log);
	    editText1.append(log + "\n");
        }
	}
	
/** Count Max Record */
public int countMaxRecord() {
    		DatabaseHandler db = new DatabaseHandler(this);
        
    	    final int contmax = db.getContactsCount();
    	    TextView textView3 = (TextView) findViewById(R.id.textView3 );
    	    textView3.setText(Integer.toString(contmax));
return contmax;
}	

/** Called when the user clicks the clearRecord button */
public void clearRecord(View view) {
            Log.d("DB clear: ", "Clear All Record...");
    		DatabaseHandler db = new DatabaseHandler(this);
    	    TextView textView1 = (TextView) findViewById(R.id.textView1 );
    	    textView1.setText("Clear All Record...");
// Delete record if ID=1   MÛKÖDIK!!!       
/*
    	    Contact cont = new Contact();
            cont.setID(1);
    	    db.deleteContact(cont);
*/
    	    db.deleteALLContacts();	    
    	    countMaxRecord();
            readRecord(null);
	}	

/** Called when the user clicks the AddNewRecord button */
public void newRecord(View view) {
            Log.d("DB insert: ", "AddNewRecord...");
    		DatabaseHandler db = new DatabaseHandler(this);

    		TextView textView1 = (TextView) findViewById(R.id.textView1 );
    	    textView1.setText("Add New Record");

            EditText editText3 = (EditText) findViewById(R.id.editText3);
            EditText editText4 = (EditText) findViewById(R.id.editText4);

            String sname = editText3.getText().toString();
            String nphone = editText4.getText().toString();
            db.addContact(new Contact(sname, nphone));
    	    countMaxRecord();
            editText3.setText(""); // Törli a tartalmat
            editText4.setText("");
            readRecord(null);
	}	

}
