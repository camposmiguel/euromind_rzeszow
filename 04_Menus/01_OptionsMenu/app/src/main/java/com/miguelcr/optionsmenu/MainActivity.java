package com.miguelcr.optionsmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText message;
    TextView messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message = (EditText) findViewById(R.id.editTextMessage);
        messageList = (TextView) findViewById(R.id.textViewMessages);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_share:
                // Do anything for the share option
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, messageList.getText().toString());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

                return true;
            case R.id.action_clear:
                // Do anything for the clear option
                messageList.setText("");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void sendMessage(View view) {
        String newMessage = message.getText().toString();

        if(!newMessage.equals("")) {
            messageList.setText(messageList.getText() + "\n" + newMessage);
            message.setText("");
        } else {
            Toast.makeText(this,"Write anything please!",Toast.LENGTH_LONG).show();
        }

    }
}
