package ro.pub.cs.systems.eim.practicaltest01var04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var04MainActivity extends AppCompatActivity {
    public static int clicks = 0;

    private Button button;
    private Button secondaryButton;
    final public static int buttonIds[] = {
            R.id.top_left,
            R.id.top_right,
            R.id.center,
            R.id.bottom_left,
            R.id.bottom_right
    };

    private EditText myText;

    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String newString = myText.getText().toString();
            String delim = new String(",");
            newString += ((Button)view).getText().toString();
            newString += delim;
            myText.setText(newString);
            clicks++;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var04_main);

        myText = (EditText)findViewById(R.id.clicks_text);
        secondaryButton = (Button)findViewById(R.id.navigate);

        for (int i = 0; i < buttonIds.length; i++) {
            button = (Button)findViewById(buttonIds[i]);
            button.setOnClickListener(new ButtonListener());
        }

        secondaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var04SecondaryActivity.class);
                intent.putExtra("myString", myText.getText().toString());
                startActivityForResult(intent, 1);
            }
        });

        if (savedInstanceState != null && savedInstanceState.containsKey("totalClicks")) {
            clicks = savedInstanceState.getInt("totalClicks");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("totalClicks", clicks);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey("totalClicks"))
            clicks = savedInstanceState.getInt("totalClicks");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Number of clicks " + clicks, Toast.LENGTH_LONG).show();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 1) {
            if (resultCode == 2)
                Toast.makeText(this, "Verify", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
        }
    }
}
