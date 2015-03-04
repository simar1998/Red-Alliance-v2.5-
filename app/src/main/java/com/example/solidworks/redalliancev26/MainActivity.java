package com.example.solidworks.redalliancev26;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;


public class MainActivity extends ActionBarActivity {

    String teamNumberDouble = "";//Stores the value for the user input of the team Number :INPUT METHOD = EditText
    String matchNumberDouble = "";//Stores the value for the user input of the matchNumber :INPUT METHOD = EditText
    String totesFromLandfillDouble = "";//Stores the value for the user input of the totes from the landfill :INPUT METHOD = EditText
    String upsideDownTotesDouble = "";//Stores the value for the user input of the upside down totes :INPUT METHOD = EditText
    String totesHPDouble = "";//Stores the value for the user input of the totes from the Human Player :INPUT METHOD = EditText
    String numberOverPlatformDouble = "";//Stores the value for the user input of the number of times over platform :INPUT METHOD = EditText
    int cylindersMovedDouble = 0;//Stores the values for the user input of the cylinders moved :INPUT METHOD = NumberPicker
    int totesMovedDouble = 0;//Stores the values for the user input of the totes moved :INPUT METHOD = NumberPicker
    int offStepCylindersDouble = 0;//Stores the values of the user input of the off step cylinders :INPUT METHOD = NumberPicker
    int offStepTotesDouble = 0;//Stores the values of the user input of the off step totes :INPUT METHOD = NumberPicker
    int noodlesCylinderDouble = 0;//Stores the values of the user input of the noodles in  the cylinder :INPUT METHOD = NumberPicker
    int noodlesOpponentDouble = 0;//Stores the values of the user input of the noodles in opponent zone;:INPUT METHOD = NumberPicker
    //booleans
    Boolean wideBool;//Unknown context :INPUT METHOD = CheckBox
    Boolean longBool;//Unknown context :INPUT METHOD = CheckBox
    Boolean botZoneBool;//Stores the value of the bot in zone :INPUT METHOD = ToggleButton
    Boolean toteSetBool;//Stores the value of the totes set :INPUT METHOD = ToggleButton
    Boolean herdLitterBool;//Stores the value of the herd litter option :INPUT METHOD = Switch
    //Constants
    String scoutNameString = "";//Stores the value of the user input of the scout number
    String myFileName = teamNumberDouble + " " + matchNumberDouble;

    Intent pitIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pitIntent = new Intent(MainActivity.this, pit_scouting_activity.class);




        //Button objects
        final Button submitButton = (Button) findViewById(R.id.submitButton);        final int MIN_VALUE = 0;//This variable stores the min value of 0
        final int CYLINDERS_MOVED_MAX = 3;//This variable stores the max of the corresponding value
        final int TOTES_MOVED_MAX = 3;//This variable stores the max of the corresponding value
        final int CYLINDERS_OFF_STEP_MAX = 4;//This variable stores the max of the corresponding value
        final int TOTES_OFF_STEP_MAX = 12;//This variable stores the max of the corresponding value
        final int NOODLES_CYLINDER_MAX = 10;//This variable stores the max of the corresponding value
        final int NOODLES_OPPONENT_ZONE_MAX = 10;//This variable stores the max of the corresponding value
        //Strings

        final EditText teamNumberText = (EditText) findViewById(R.id.teamNumberEditText);
        final EditText matchNumberText = (EditText) findViewById(R.id.matchNumberEditText);
        final EditText scoutNameText = (EditText) findViewById(R.id.scoutNameEditText);
        final EditText landfillTotesText = (EditText) findViewById(R.id.totesFromLandfillEditText);
        final EditText upsideDownTotesText = (EditText) findViewById(R.id.upsideDownTotesEditText);
        final EditText totesFromHP = (EditText) findViewById(R.id.totesHPEditText);
        final EditText platformText = (EditText) findViewById(R.id.numberOverPlatformEditText);

        //Number Picker Objects
        final NumberPicker cylinderMoveNP = (NumberPicker) findViewById(R.id.cylindersMovedNumberPicker);
        final NumberPicker totesMovedNP = (NumberPicker) findViewById(R.id.numberOfTotesMovedNumberPicker);
        final NumberPicker cylinderOffStep = (NumberPicker) findViewById(R.id.numberOffStepNumberPicker);
        final NumberPicker totesOffStep = (NumberPicker) findViewById(R.id.totesOffStepNP);
        final NumberPicker noodlesCylinderNp = (NumberPicker) findViewById(R.id.noodlesCylinderNP);
        final NumberPicker noodlesOpponentNp = (NumberPicker) findViewById(R.id.noodlesInOpponentNP);

        //Toggle button code
        final ToggleButton toteSetToggle = (ToggleButton) findViewById(R.id.toteSetToggleButton);
        final ToggleButton botZoneToggle = (ToggleButton) findViewById(R.id.botZoneToggleButton);

        //CheckBox Objects
        final CheckBox longCheckBox = (CheckBox) findViewById(R.id.longCheckBox);
        final CheckBox wideCheckBox = (CheckBox) findViewById(R.id.wideCheckBox);

        //Switch object
        final Switch herdLitterSwitch = (Switch) findViewById(R.id.herdLitterOptionSwitch);


        cylinderMoveNP.setMaxValue(CYLINDERS_MOVED_MAX);
        totesMovedNP.setMaxValue(TOTES_MOVED_MAX);
        cylinderOffStep.setMaxValue(CYLINDERS_OFF_STEP_MAX);
        totesOffStep.setMaxValue(TOTES_OFF_STEP_MAX);
        noodlesCylinderNp.setMaxValue(NOODLES_CYLINDER_MAX);
        noodlesOpponentNp.setMaxValue(NOODLES_OPPONENT_ZONE_MAX);
        //Number Picker min values assigned
        cylinderMoveNP.setMinValue(MIN_VALUE);
        totesMovedNP.setMinValue(MIN_VALUE);
        cylinderMoveNP.setMinValue(MIN_VALUE);
        totesOffStep.setMinValue(MIN_VALUE);
        noodlesOpponentNp.setMinValue(MIN_VALUE);
        noodlesCylinderNp.setMinValue(MIN_VALUE);


        View.OnClickListener listenerScout = new View.OnClickListener() {
            public void onClick(View v) {
                //This method updates the information from the scouting_layout.xml during runtime
                // START OF EDIT TEXT
                scoutNameString = scoutNameText.getText().toString();//Updates the scout name
                teamNumberDouble = teamNumberText.getText().toString();//Updates team number
                matchNumberDouble = matchNumberText.getText().toString();//Updates match number
                totesFromLandfillDouble = landfillTotesText.getText().toString();//Updates landfill
                upsideDownTotesDouble = upsideDownTotesText.getText().toString();//Updates upside Down totes
                totesHPDouble = totesFromHP.getText().toString();//Updates totes from HP
                numberOverPlatformDouble = platformText.getText().toString();//Updates times over platform
                //END OF EDIT TEXT

                //START OF NUMBER PICKER
                cylindersMovedDouble = cylinderMoveNP.getValue();//Updates the cylinder
                totesMovedDouble = totesMovedNP.getValue();//Updates the totes
                offStepCylindersDouble = cylinderOffStep.getValue();//Updates the off step cylinder
                offStepTotesDouble = totesOffStep.getValue();//Update the off step tote
                noodlesCylinderDouble = noodlesCylinderNp.getValue();//Update the noodles in cylinder
                noodlesOpponentDouble = noodlesOpponentNp.getValue();//Updates the noodles in opponent
                //END OF NUMBER PICKER

                //START OF TOGGLE BUTTONS

                //Checks if tote toggle is pressed if so changes bool
                if (toteSetToggle.isChecked()) {
                    toteSetBool = true;
                } else toteSetBool = false;

                //Checks if bot zone toggle is pressed if so changes bool
                if (botZoneToggle.isChecked()) {
                    botZoneBool = true;
                } else botZoneBool = false;

                //END OF TOGGLE BUTTONS

                //START OF CHECKBOX
                //Checks if long checkbox is pressed if so changes bool
                if (longCheckBox.isChecked()) {
                    longBool = true;
                }
                //Checks if wide checkbox is pressed if so changes bool
                else longBool = false;
                if (wideCheckBox.isChecked()) {
                    wideBool = true;
                } else wideBool = false;
                //END OF CHECKBOX

                //START OF SWITCH
                //Checks if herd litter switch is pressed if so changes bool
                if (herdLitterSwitch.isChecked()) {
                    herdLitterBool = true;
                } else herdLitterBool = false;

                try {
                    writerToFileScouting();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //END OF SWITCH

            }
        };
        submitButton.setOnClickListener(listenerScout);
    }
    private void writerToFileScouting() throws IOException {


        File root = Environment.getExternalStorageDirectory();
        File outDir = new File(root.getAbsolutePath() + File.separator + "Red Alliance");
        Writer writer;
        File outPutFile = new File(outDir, myFileName);
        writer = new PrintWriter(new FileWriter(outPutFile));
        if (!outDir.isDirectory()) {
            outDir.mkdir();
        }

        if (!outDir.isDirectory()) {
            throw new IOException("unable to create directory ");

        }
        if (!teamNumberDouble.isEmpty() && !matchNumberDouble.isEmpty()) {
            myFileName = "Team " + teamNumberDouble.toString() + " Match " + matchNumberDouble;
            writer.write("Team Number = " + teamNumberDouble + "\n");
            writer.write("Match Number = " + matchNumberDouble + "\n");
            writer.write("Scout Name = " + scoutNameString + "\n");
            writer.write("Totes Number = " + totesMovedDouble + "\n");
            writer.write("Cylinders Moved = " + cylindersMovedDouble + "\n");
            writer.write("Cylinders off step = " + offStepCylindersDouble + "\n");
            writer.write("Tote set = " + toteSetBool + "\n");
            writer.write("Bot in zone = " + botZoneBool + "\n");
            writer.write("Long = " + longBool + "\n");
            writer.write("Wide = " + wideBool + "\n");
            writer.write("Totes from landfill = " + totesFromLandfillDouble + "\n");
            writer.write("Upside down totes = " + upsideDownTotesDouble + "\n");
            writer.write("Totes off step = " + offStepTotesDouble + "\n");
            writer.write("Noodles in cylinder = " + noodlesCylinderDouble + "\n");
            writer.write("Noodles in opponent zone = " + noodlesOpponentDouble + "\n");
            writer.write("Herd Litter = " + herdLitterBool + "\n");
            writer.write("Totes from HP = " + totesHPDouble + "\n");
            writer.write("Number over platform = " + numberOverPlatformDouble + "\n");
            writer.close();
            MessageBox("File saved");
        }
        else
        {
            MessageBox("Please enter team Number and match number");
        }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_pit_scouting) {
            MainActivity.this.startActivity(pitIntent);
        }
        return super.onOptionsItemSelected(item);
    }
    public void MessageBox(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
