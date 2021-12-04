package cm.homework.dialer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class QuickCallActivity extends AppCompatActivity {

    String btnNumber;
    String name,number;
    EditText edtPhoneNo,edtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_quick_btn_layout);

        edtPhoneNo = (EditText) findViewById(R.id.edtPhoneNumber);
        edtName = (EditText) findViewById(R.id.edtName);

        //get action bar and change its title
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Edit quick button");

        //display back/home button
        actionBar.setDisplayHomeAsUpEnabled(true);

        //get info from previous activity
        btnNumber = getIntent().getStringExtra("btnNumber");
        name = getIntent().getStringExtra("name");
        number = getIntent().getStringExtra("number");

        //set the text fields with values passed
        edtPhoneNo.setText(number);
        edtName.setText(name);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            //when back button is pressed
            case android.R.id.home:
                //return the strings in the text widgets
                setResult(Activity.RESULT_OK,new Intent().putExtra("btnNumber",btnNumber).putExtra("name",edtName.getText().toString()).putExtra("number",edtPhoneNo.getText().toString()));
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
