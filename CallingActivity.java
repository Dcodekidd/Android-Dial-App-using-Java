package ng.org.crownstech.www.dialerappdemo2017;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CallingActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling);
        initializeView();

    }


    private void initializeView() {
        screen = (EditText) findViewById(R.id.screen);
        int idList[] = {R.id.btn0, R.id.btn1, R.id.btn2,
                R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6,
                R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnStar,
                R.id.btnHash, R.id.btnDel, R.id.btnDial};

        for (int d : idList) {
            View v = (View) findViewById(d);
            v.setOnClickListener(this);

        }
    }

    public void display(String val) {
        screen.append(val);
    }

    private boolean checkCallPermission() {
        String permission = "android.permission.CALL_PHONE";
        int res = getApplicationContext().checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn0:
                display("0");
                break;
            case R.id.btn1:
                display("1");
                break;
            case R.id.btn2:
                display("2");
                break;
            case R.id.btn3:
                display("3");
                break;
            case R.id.btn4:
                display("4");
                break;
            case R.id.btn5:
                display("5");
                break;
            case R.id.btn6:
                display("6");
                break;
            case R.id.btn7:
                display("7");
                break;
            case R.id.btn8:
                display("8");
                break;
            case R.id.btn9:
                display("9");
                break;
            case R.id.btnStar:
                display("*");
                break;
            case R.id.btnHash:
                display("#");
                break;
            case R.id.btnDel:
                if (screen.getText().toString().length() >= 1) {
                    String newScreen = screen.getText().toString().substring(0, screen.getText().toString().length() - 1);
                    screen.setText(newScreen);
                }
                break;
            case R.id.btnDial:
                if (screen.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "Enter digit numbers", Toast.LENGTH_SHORT).show();
                else {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                       ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},10);
                        return;
                    }
                    else {
                        try {
                            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + screen.getText())));
                            Toast.makeText(getApplicationContext(), "Calling Successful", Toast.LENGTH_SHORT).show();

                        }catch(android.content.ActivityNotFoundException ex){
                            Toast.makeText(getApplicationContext(),"not successful,not found",Toast.LENGTH_SHORT).show();

                        }

                    }

                }
//                Intent call= new Intent(Intent.ACTION_CALL)
                break;
            default:

                break;



        }

    }
}
