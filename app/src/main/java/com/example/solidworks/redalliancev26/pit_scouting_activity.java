package com.example.solidworks.redalliancev26;

import android.content.Intent;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;


public class pit_scouting_activity extends ActionBarActivity {

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

    Intent mainIntent;
   static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pit_scouting_activity);

        mainIntent = new Intent(this , MainActivity.class);

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
        final Button cameraLaunchButton = (Button) findViewById(R.id.launchCameraButton);

        View.OnClickListener cameraLaunch = new View.OnClickListener()
        {
            public void OnClick(View v)
            {
                dispatchTakePictureIntent();
            }
        };
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


        cameraLaunchButton.setOnClickListener(cameraLaunch);
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pit_scouting_activity, menu);
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
            pit_scouting_activity.this.startActivity(mainIntent);
        }

        return super.onOptionsItemSelected(item);
    }
    public void switchIfStatement(Switch s, Boolean b)
    {
        if(s.isChecked())
        {
            b = true;
        }else b = false;
    }
    public void MessageBox(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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

}
