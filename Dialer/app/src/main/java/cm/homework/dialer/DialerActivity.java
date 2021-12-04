package cm.homework.dialer;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class DialerActivity extends AppCompatActivity{
    EditText edtPhoneNo;
    Button quickBtn1, quickBtn2, quickBtn3;
    String quickBtn1Text, quickBtn3Text, quickBtn2Text;
    String quickBtn1Number, quickBtn3Number, quickBtn2Number;
    ActivityResultLauncher<Intent> launchActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        //get result
                        Intent data = result.getData();
                        String temp = data.getStringExtra("btnNumber");
                        String tempName = data.getStringExtra("name");
                        String tempNumber = data.getStringExtra("number");
                        //save data received and update the corresponding button
                        switch(temp){
                            case "1":
                                quickBtn1Text = tempName;
                                quickBtn1Number = tempNumber;
                                quickBtn1.setText(quickBtn1Text);
                                break;
                            case "2":
                                quickBtn2Text = tempName;
                                quickBtn2Number = tempNumber;
                                quickBtn2.setText(quickBtn2Text);
                                break;
                            case "3":
                                quickBtn3Text = tempName;
                                quickBtn3Number = tempNumber;
                                quickBtn3.setText(quickBtn3Text);
                                break;
                        }
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialer);

        edtPhoneNo = (EditText) findViewById(R.id.edtPhoneNumber);
        quickBtn1 = (Button) findViewById(R.id.btnQuick1);
        quickBtn2 = (Button) findViewById(R.id.btnQuick2);
        quickBtn3 = (Button) findViewById(R.id.btnQuick3);
        //initial values
        quickBtn1Text = "JJ";
        quickBtn2Text = "SC";
        quickBtn3Text = "RA";
        quickBtn1.setText(quickBtn1Text);
        quickBtn2.setText(quickBtn2Text);
        quickBtn3.setText(quickBtn3Text);
        quickBtn1Number = "123456789";
        quickBtn2Number = "987654321";
        quickBtn3Number = "567894321";

        //set listeners for each buttons long presses
        quickBtn1.setOnLongClickListener(new View.OnLongClickListener() {
                                             @Override
                                             public boolean onLongClick(View v) {
                                                 switchActivities("1",quickBtn1Text,quickBtn1Number);
                                                 return true;
                                             }
                                         }
        );
        quickBtn2.setOnLongClickListener(new View.OnLongClickListener() {
                                             @Override
                                             public boolean onLongClick(View v) {
                                                 switchActivities("2",quickBtn2Text,quickBtn2Number);
                                                 return true;
                                             }
                                         }
        );
        quickBtn3.setOnLongClickListener(new View.OnLongClickListener() {
                                             @Override
                                             public boolean onLongClick(View v) {
                                                 switchActivities("3",quickBtn3Text,quickBtn3Number);
                                                 return true;
                                             }
                                         }
        );


    }
    private void switchActivities(String btnNumber, String text, String number){
        Intent switchActivity = new Intent(this, QuickCallActivity.class);
        //launch activity with info to be changed
        switchActivity.putExtra("btnNumber", btnNumber).putExtra("name",text).putExtra("number",number);
        launchActivity.launch(switchActivity);
    }

    public void buttonClickEvent(View v) {
        String phoneNo = edtPhoneNo.getText().toString();

        try {
            switch (v.getId()) {

                case R.id.btnQuick1:
                    edtPhoneNo.setText(quickBtn1Number);
                    break;
                case R.id.btnQuick2:
                    edtPhoneNo.setText(quickBtn2Number);
                    break;
                case R.id.btnQuick3:
                    edtPhoneNo.setText(quickBtn3Number);
                    break;
                case R.id.btnAsterisk:
                    phoneNo += "*";
                    edtPhoneNo.setText(phoneNo);
                    Log.e("TEST",edtPhoneNo.toString());
                    break;

                case R.id.btnHash:
                    phoneNo += "#";
                    edtPhoneNo.setText(phoneNo);
                    break;

                case R.id.btn0:
                    phoneNo += "0";
                    edtPhoneNo.setText(phoneNo);
                    break;

                case R.id.btn1:
                    phoneNo += "1";
                    edtPhoneNo.setText(phoneNo);
                    break;

                case R.id.btn2:
                    phoneNo += "2";
                    edtPhoneNo.setText(phoneNo);
                    break;

                case R.id.btn3:
                    phoneNo += "3";
                    edtPhoneNo.setText(phoneNo);
                    break;

                case R.id.btn4:
                    phoneNo += "4";
                    edtPhoneNo.setText(phoneNo);
                    break;

                case R.id.btn5:
                    phoneNo += "5";
                    edtPhoneNo.setText(phoneNo);
                    break;

                case R.id.btn6:
                    phoneNo += "6";
                    edtPhoneNo.setText(phoneNo);
                    break;

                case R.id.btn7:
                    phoneNo += "7";
                    edtPhoneNo.setText(phoneNo);
                    break;

                case R.id.btn8:
                    phoneNo += "8";
                    edtPhoneNo.setText(phoneNo);
                    break;

                case R.id.btn9:
                    phoneNo += "9";
                    edtPhoneNo.setText(phoneNo);
                    break;

                case R.id.btnDel:
                    if (phoneNo != null && phoneNo.length() > 0) {
                        phoneNo = phoneNo.substring(0, phoneNo.length() - 1);
                    }

                    edtPhoneNo.setText(phoneNo);
                    break;

                case R.id.btnCall:
                    if (phoneNo.trim().equals("")) {
                        Log.d("Warning","Not a valid number");
                    } else {
                        Boolean isHash = false;
                        //necessary to pass hash to native dialer
                        if (phoneNo.subSequence(phoneNo.length() - 1, phoneNo.length()).equals("#")) {
                            phoneNo = phoneNo.substring(0, phoneNo.length() - 1);
                            String callInfo = "tel:" + phoneNo + Uri.encode("#");
                            Intent callIntent = new Intent(Intent.ACTION_DIAL);
                            callIntent.setData(Uri.parse(callInfo));
                            startActivity(callIntent);
                        } else {
                            String callInfo = "tel:" + phoneNo;
                            Intent callIntent = new Intent(Intent.ACTION_DIAL);
                            callIntent.setData(Uri.parse(callInfo));
                            startActivity(callIntent);
                        }
                    }
                    break;
            }

        } catch (Exception ex) {

        }
    }
}
