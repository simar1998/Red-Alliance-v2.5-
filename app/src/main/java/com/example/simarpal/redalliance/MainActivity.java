package com.example.simarpal.redalliance;

import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
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

    //Pit scouting variables

    //Strings
    String pitTeamName = "";
    String pitScoutName = "";
    String pitSpeed = "";
    String pitDriveType = "";
    String pitOrientation = "";
    String pitAutonomous = "";
    String pitStackHeight = "";
    String pitContainerHeight = "";
    String pitLitterInContainerHowString = "";
    String pitFileName = "PIT SCOUT TEAM " + pitTeamName;

    //Booleans
    Boolean pitStrafe = false;
    Boolean pitOverPlatform = false;
    Boolean pitTotesPick = false;
    Boolean pitPushTotes = false;
    Boolean pitRightTotes = false;
    Boolean pitLandfillTotes = false;
    Boolean pitHPfeed = false;
    Boolean pitLitterInContainer = false;
    Boolean pitRightContainers = false;
    Boolean pitHerdLitter = false;
    Boolean pitThrowLitter = false;
    Boolean pitLitterLandfill = false;
    Boolean coopOneThree = false;
    Boolean coopThreeOne = false;
    Boolean pitSet = false;
    Boolean pitStack = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.pit_scouting);

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


        ArrayAdapter<CharSequence> containerAdapter = ArrayAdapter.createFromResource(this, R.array.container_take,
                android.R.layout.simple_spinner_item);
        containerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        // pitContainerSpinner.setAdapter(containerAdapter);


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




    public void switchIfStatement(Switch s, Boolean b)
    {
        if(s.isChecked())
        {
            b = true;
        }else b = false;
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
        if (id == R.id.action_scouting) {
            setContentView(R.layout.activity_main);
        }
        if(id == R.id.action_pit_scouting)
        {
            //setContentView(R.layout.pit_scouting);
            //Pit Scouting objects

            //EDIT TEXT OBJECTS
            final EditText pitTeamNumberText = (EditText) findViewById(R.id.teamNumber);
            final EditText pitScoutNameText = (EditText) findViewById(R.id.scoutName);
            final  EditText pitSpeedText = (EditText) findViewById(R.id.speedTextPIT);
            final EditText pitDriveTypeText = (EditText) findViewById(R.id.driveType);
            final EditText pitOrientationText = (EditText) findViewById(R.id.orientationPIT);
            final EditText pitAutonText = (EditText) findViewById(R.id.autonPIT);
            final EditText pitStackHeightText = (EditText) findViewById(R.id.tallStackPIT);
            final EditText pitContainerHeightText = (EditText) findViewById(R.id.containerHeightPIT);
            final EditText pitLitterContainerHowText = (EditText) findViewById(R.id.litterHowPIT);

            //Switch objects
            final Switch pitTotesPickBool= (Switch) findViewById(R.id.totesPickPIT);
            final Switch pitPushTotesBool = (Switch) findViewById(R.id.pushTotesPIT);
            final Switch pitRightToteBool = (Switch)findViewById(R.id.rightTotesPIT);
            final Switch pitLandfillTotesBool = (Switch) findViewById(R.id.landfillTotesPIT);
            final Switch pitHPFeedBool = (Switch) findViewById(R.id.hpFeedPIT);
            final Switch pitLitterBool= (Switch) findViewById(R.id.litterPIT);
            final Switch pitRightContainerBool = (Switch) findViewById(R.id.rightContainersPIT);
            final Switch pitHerdLitterBool =(Switch) findViewById(R.id.herdLitterPIT);
            final Switch pitThrowLitterBool = (Switch) findViewById(R.id.throwLitterPIT);
            final Switch pitLitterLandfillBool = (Switch) findViewById(R.id.litterLandfillPIT);
            final Switch pitOneOnThreeBool = (Switch) findViewById(R.id.oneToteOnThreePIT);
            final Switch pitThreeOnOneBool = (Switch) findViewById(R.id.threeOnOnePit);

            //Toggle button
            final ToggleButton pitStrafeToggleBool = (ToggleButton) findViewById(R.id.strafeTogglePIT);
            final ToggleButton pitOverPlatformBool = (ToggleButton) findViewById(R.id.overPlatformPIT);

            //Checkbox objects
            final CheckBox pitSetBool = (CheckBox) findViewById(R.id.setCheckBoxPIT);
            final CheckBox pitStackBool = (CheckBox) findViewById(R.id.stackCheckBoxPIT);

            //Spinner objects
            final Spinner pitContainerSpinner = (Spinner) findViewById(R.id.containerSpinnerPIT);

            //pit Button object
            final Button pitSubmitButton = (Button) findViewById(R.id.pitSubmit);

            View.OnClickListener listenerPitScout = new View.OnClickListener() {
                public void onClick(View v){

                    pitTeamName = pitTeamNumberText.getText().toString();
                    pitScoutName = pitScoutNameText.getText().toString();
                    pitSpeed = pitSpeedText.getText().toString();
                    pitDriveType = pitDriveTypeText.getText().toString();
                    pitOrientation = pitOrientationText.getText().toString();
                    pitAutonomous = pitAutonText.getText().toString();
                    pitStackHeight = pitStackHeightText.getText().toString();
                    pitContainerHeight = pitContainerHeightText.getText().toString();
                    pitLitterInContainerHowString = pitLitterContainerHowText.getText().toString();

                    if(pitStrafeToggleBool.isChecked()){
                        pitStrafe = true;
                    }
                    else pitStrafe = false;

                    if(pitOverPlatformBool.isChecked())
                    {
                        pitOverPlatform = true;
                    }
                    else pitOverPlatform = false;

                    switchIfStatement(pitTotesPickBool , pitTotesPick);
                    switchIfStatement(pitPushTotesBool , pitPushTotes);
                    switchIfStatement(pitRightToteBool, pitRightTotes);
                    switchIfStatement(pitLandfillTotesBool,pitLandfillTotes);
                    switchIfStatement(pitHPFeedBool,pitHPfeed);
                    switchIfStatement(pitLitterBool , pitLitterInContainer);
                    switchIfStatement(pitRightContainerBool , pitRightContainers);
                    switchIfStatement(pitHerdLitterBool , pitHerdLitter);
                    switchIfStatement(pitThrowLitterBool,pitThrowLitter);
                    switchIfStatement(pitLitterLandfillBool,pitLitterLandfill);
                    switchIfStatement(pitOneOnThreeBool,coopOneThree);
                    switchIfStatement(pitThreeOnOneBool,coopThreeOne);
                    if(pitSetBool.isChecked())
                    {
                        pitSet = true;
                    }else pitSet = false;
                    if(pitStackBool.isChecked())
                    {
                        pitStack = true;
                    }else pitStack = false;
                    try {
                        writerToFilePit();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            pitSubmitButton.setOnClickListener(listenerPitScout);
        }


        return super.onOptionsItemSelected(item);
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
    private void writerToFilePit() throws IOException
    {
        File root = Environment.getExternalStorageDirectory();
        File outDir = new File(root.getAbsolutePath() + File.separator + "Red Alliance");
        Writer writer;
        File outPutFile = new File(outDir, pitFileName);
        writer = new PrintWriter(new FileWriter(outPutFile));
        if (!outDir.isDirectory()) {
            outDir.mkdir();
        }

        if (!outDir.isDirectory()) {
            throw new IOException("unable to create directory ");
        }
        if(!pitTeamName.isEmpty() && !pitScoutName.isEmpty())
        {
            writer.write("Team Number = " + pitTeamName + "\n");
            writer.write("Scout Name = " + pitScoutName + "\n");
            writer.write("Preferred Speed = " + pitSpeed+ "\n");
            writer.write("Drive Train = " + pitDriveType+ "\n");
            writer.write("Orientation = " + pitOrientation+ "\n");
            writer.write("Autonomous = " + pitAutonomous+ "\n");
            writer.write("Stack Height = " + pitStackHeight+ "\n");
            writer.write("Container Height = " + pitContainerHeight+ "\n");
            writer.write("Litter in container how = " + pitLitterInContainerHowString+ "\n");
            writer.write("Strafe = " + pitStrafe+ "\n");
            writer.write("Over Platform = " + pitOverPlatform + "\n");
            writer.write("Can pick totes up = " + pitTotesPick+ "\n");
            writer.write("can push totes = " + pitPushTotes+ "\n");
            writer.write("Can right totes = " + pitRightTotes+ "\n");
            writer.write("Landfill totes = " + pitLandfillTotes+ "\n");
            writer.write("HP FEED = " + pitHPfeed + "\n");
            writer.write("Can put litter in container = " + pitLitterInContainerHowString+ "\n");
            writer.write("Can right container = " + pitRightContainers+ "\n");
            writer.write("Herd litter = " + pitHerdLitter+ "\n");
            writer.write("Can throw litter = " + pitThrowLitter+ "\n");
            writer.write("Can litter landfill = " + pitLitterLandfill+ "\n");
            writer.write("Can put one tote on three = " + coopOneThree+ "\n");
            writer.write("Can put three totes on one = " + coopThreeOne+ "\n");
            writer.write("Set = " + pitSet+ "\n");
            writer.write("Stack = " + pitStack+ "\n");
            MessageBox("File Saved");
        }else MessageBox("Please complete form");


    }




    // Toast never used
    public void MessageBox(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }



}

