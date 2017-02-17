package android.csulb.edu.tacopronto;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, RadioGroup.OnCheckedChangeListener {
    int total=0;
    private static final String PHONE_NO = "+15628507048";

    private boolean isLarge=true;
    private boolean isMedium=true;
    private boolean isCorn=true;
    private boolean isFlour=true;

    private boolean isBeef=false;
    private boolean isChicken=false;
    private boolean isWhiteFish=false;
    private boolean isCheese=false;
    private boolean isSeaFood=false;
    private boolean isRice=false;
    private boolean isBeans=false;
    private boolean isPico=false;
    private boolean isGuacamole=false;
    private boolean isLbt=false;

    private boolean isSoda=false;
    private boolean isCerveza=false;
    private boolean isMargarita=false;
    private boolean isTequila=false;

    RadioGroup radioGroupSize,radioGroupDough;
    RadioButton radioButtonLarge,radioButtonMedium,radioButtonCorn,radioButtonFlour;
    CheckBox checkBoxBeef,checkBoxChicken,checkBoxWhiteFish,checkBoxCheese,checkBoxSeaFood,checkBoxRice;
    CheckBox checkBoxBeans,checkBoxPico,checkBoxGuacamole,checkBoxLbt,checkBoxSoda,checkBoxMargarita;
    CheckBox checkBoxCerveza,checkBoxTequila;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        radioGroupSize= (RadioGroup) findViewById(R.id.radioGroupSize);
        radioGroupSize.setOnCheckedChangeListener(this);
        radioGroupDough= (RadioGroup) findViewById(R.id.radioGroupDough);
        radioGroupDough.setOnCheckedChangeListener(this);

        radioButtonLarge =(RadioButton) radioGroupSize.findViewById(R.id.large);
        radioButtonMedium =(RadioButton) radioGroupSize.findViewById(R.id.medium);
        radioButtonCorn =(RadioButton) radioGroupDough.findViewById(R.id.corn);
        radioButtonFlour =(RadioButton) radioGroupDough.findViewById(R.id.flour);

        checkBoxBeef = (CheckBox) findViewById(R.id.beef);
        checkBoxBeef.setOnCheckedChangeListener(this);
        checkBoxChicken = (CheckBox) findViewById(R.id.chicken);
        checkBoxChicken.setOnCheckedChangeListener(this);
        checkBoxWhiteFish = (CheckBox) findViewById(R.id.whitefish);
        checkBoxWhiteFish.setOnCheckedChangeListener(this);
        checkBoxCheese = (CheckBox) findViewById(R.id.cheese);
        checkBoxCheese.setOnCheckedChangeListener(this);
        checkBoxSeaFood = (CheckBox) findViewById(R.id.seafood);
        checkBoxSeaFood.setOnCheckedChangeListener(this);
        checkBoxRice = (CheckBox) findViewById(R.id.rice);
        checkBoxRice.setOnCheckedChangeListener(this);
        checkBoxBeans = (CheckBox) findViewById(R.id.beans);
        checkBoxBeans.setOnCheckedChangeListener(this);
        checkBoxPico = (CheckBox) findViewById(R.id.pico);
        checkBoxPico.setOnCheckedChangeListener(this);
        checkBoxGuacamole = (CheckBox) findViewById(R.id.guacamole);
        checkBoxGuacamole.setOnCheckedChangeListener(this);
        checkBoxLbt = (CheckBox) findViewById(R.id.lbt);
        checkBoxLbt.setOnCheckedChangeListener(this);

        checkBoxSoda = (CheckBox) findViewById(R.id.soda);
        checkBoxSoda.setOnCheckedChangeListener(this);
        checkBoxMargarita = (CheckBox) findViewById(R.id.margarita);
        checkBoxMargarita.setOnCheckedChangeListener(this);
        checkBoxCerveza = (CheckBox) findViewById(R.id.cerveza);
        checkBoxCerveza.setOnCheckedChangeListener(this);
        checkBoxTequila = (CheckBox) findViewById(R.id.tequila);
        checkBoxTequila.setOnCheckedChangeListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    sendMessage(total);
                } else {
                    Toast.makeText(this, "Permission denied for SMS", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        }
    }

    public void sendMessage(int total) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(PHONE_NO, null,
                   "Thank you for placing the order!!!. Your total bill is $"+ Integer.toString(total), null, null);
            Toast.makeText(this, "Thank you. Your order is accepted", Toast.LENGTH_SHORT).show();
    }

    public void acceptOrder(int total) {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    10);

        } else sendMessage(total);
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        // this is for check box
        switch(buttonView.getId()){
            case R.id.beef: if(checkBoxBeef.isChecked()){
                                isBeef=true;}
                            else
                                isBeef=false;
                            break;
            case R.id.chicken:  if(checkBoxChicken.isChecked())
                                    isChicken=true;
                                else
                                    isChicken=false;
                                break;
            case R.id.whitefish:if(checkBoxWhiteFish.isChecked())
                                     isWhiteFish=true;
                                else
                                     isWhiteFish=false;
                                break;
            case R.id.cheese:   if(checkBoxCheese.isChecked())
                                    isCheese=true;
                                else
                                    isCheese=false;
                                break;
            case R.id.seafood:  if(checkBoxSeaFood.isChecked())
                                    isSeaFood=true;
                                else
                                    isSeaFood=false;
                                break;
            case R.id.rice:     if(checkBoxRice.isChecked())
                                     isRice=true;
                                else
                                    isRice=false;
                                break;
            case R.id.beans:    if(checkBoxBeans.isChecked())
                                    isBeans=true;
                                else
                                    isBeans=false;
                                break;
            case R.id.pico:     if(checkBoxPico.isChecked())
                                    isPico=true;
                                else
                                    isPico=false;
                                break;
            case R.id.guacamole:    if(checkBoxGuacamole.isChecked())
                                        isGuacamole=true;
                                    else
                                        isGuacamole=false;
                                    break;
            case R.id.lbt:      if(checkBoxLbt.isChecked())
                                    isLbt=true;
                                else
                                    isLbt=false;
                                break;
            case R.id.soda:     if(checkBoxSoda.isChecked())
                                    isSoda=true;
                                else
                                    isSoda=false;
                                break;
            case R.id.cerveza:  if(checkBoxCerveza.isChecked())
                                    isCerveza=true;
                                else
                                    isCerveza=false;
                                break;
            case R.id.margarita:    if(checkBoxMargarita.isChecked())
                                        isMargarita=true;
                                    else
                                        isMargarita=false;
                                    break;
            case R.id.tequila:      if(checkBoxTequila.isChecked())
                                        isTequila=true;
                                    else
                                        isTequila=false;
                                    break;
        }

    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //this is for radio buttons
        RadioButton radioButton=(RadioButton) group.findViewById(checkedId);
        switch(checkedId){
            case R.id.large:    if(radioButton.isChecked()){
                                    isLarge=true;
                                    isMedium=false;
                                }
                                break;
            case R.id.medium:   if(radioButton.isChecked()){
                                    isMedium=true;
                                    isLarge=false;
                                }
                                break;
            case R.id.corn:     if(radioButton.isChecked()){
                                    isCorn=true;
                                    isFlour=false;
                                }
                                break;
            case R.id.flour:    if(radioButton.isChecked()){
                                    isFlour=true;
                                    isCorn=false;
                                }
                                break;
        }
    }

    // "OnClick property" of the button
    public void onClick(View view){
        Boolean sizeFlag=true;
        Boolean doughFlag=true;
        StringBuilder sb = new StringBuilder();
        sb.append("Please ");
        if(!radioButtonLarge.isChecked()&&!radioButtonMedium.isChecked()){
                sb.append("select either Large or Medium Size.");
                sizeFlag=false;
        }

        if(!radioButtonFlour.isChecked()&&!radioButtonCorn.isChecked()){

                doughFlag=false;
                if(sizeFlag==false)
                    sb.append("Also select dough type");
                else
                    sb.append("select dough type");
        }

        if((!sizeFlag)||(!doughFlag)){

            Toast.makeText(this,sb,Toast.LENGTH_SHORT).show();
        }

        else {
            if(!isBeef&&!isChicken&&!isWhiteFish&&!isSeaFood&&!isCheese&&!isRice&&!isBeans&&!isPico&&!isGuacamole&&!isLbt){
                Toast.makeText(this, "Please select atleast one filling", Toast.LENGTH_SHORT).show();
            }
            else {
                total=0;
                if (isBeef) {
                    total += 10;
                }
                if (isChicken) {
                    total += 10;
                }
                if (isWhiteFish) {
                    total += 10;
                }
                if (isSeaFood) {
                    total += 10;
                }
                if (isCheese) {
                    total += 10;
                }
                if (isRice) {
                    total += 10;
                }
                if (isBeans) {
                    total += 10;
                }
                if (isPico) {
                    total += 10;
                }
                if (isGuacamole) {
                    total += 10;
                }
                if (isLbt) {
                    total += 10;
                }
                if (isSoda) {
                    total += 10;
                }
                if (isCerveza) {
                    total += 10;
                }
                if (isMargarita) {
                    total += 10;
                }
                if (isTequila) {
                    total += 10;
                }

                if(isLarge)
                    total+=20;
                else
                    total+=10;

                if(isCorn)
                    total+=20;
                else
                    total+=10;

                acceptOrder(total);
                reset();
            }

        }

    }

    public void reset(){
        isBeef=false;
        checkBoxBeef.setChecked(false);
        isChicken=false;
        checkBoxChicken.setChecked(false);
        isWhiteFish=false;
        checkBoxWhiteFish.setChecked(false);
        isCheese=false;
        checkBoxCheese.setChecked(false);
        isSeaFood=false;
        checkBoxSeaFood.setChecked(false);
        isRice=false;
        checkBoxRice.setChecked(false);
        isBeans=false;
        checkBoxBeans.setChecked(false);
        isPico=false;
        checkBoxPico.setChecked(false);
        isGuacamole=false;
        checkBoxGuacamole.setChecked(false);
        isLbt=false;
        checkBoxLbt.setChecked(false);
        isSoda=false;
        checkBoxSoda.setChecked(false);
        isCerveza=false;
        checkBoxCerveza.setChecked(false);
        isMargarita=false;
        checkBoxMargarita.setChecked(false);
        isTequila=false;
        checkBoxTequila.setChecked(false);
        isMedium=true;
        radioButtonMedium.setChecked(false);
        isLarge=true;
        radioButtonLarge.setChecked(false);
        isCorn=true;
        radioButtonCorn.setChecked(false);
        isFlour=true;
        radioButtonFlour.setChecked(false);
    }
}

