package com.example.thachchibao.homework02;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.telephony.SmsManager;

/**
 * Created by ThachChiBao on 2/22/2017.
 */

public class Choose extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose);
    }

    public void onOrderClick(View a){
        //size
        RadioButton large = (RadioButton)findViewById(R.id.Large);
        //Tortilla
        RadioButton corn = (RadioButton)findViewById(R.id.Corn);
        //Fillongs
        CheckBox beff = (CheckBox)findViewById(R.id.Beff);
        CheckBox chicken = (CheckBox)findViewById(R.id.Chicken);
        CheckBox whitefish = (CheckBox)findViewById(R.id.WhiteFish);
        CheckBox chesse = (CheckBox)findViewById(R.id.Chesse);
        CheckBox seefood = (CheckBox)findViewById(R.id.SeeFood);

        CheckBox rice = (CheckBox)findViewById(R.id.Rice);
        CheckBox beans = (CheckBox)findViewById(R.id.Beans);
        CheckBox picodegallo = (CheckBox)findViewById(R.id.PicoDeGallo);
        CheckBox guacamole = (CheckBox)findViewById(R.id.Guacamole);
        CheckBox lbt = (CheckBox)findViewById(R.id.LBT);
        //Beverage
        CheckBox soda = (CheckBox)findViewById(R.id.Soda);
        CheckBox cerveza = (CheckBox)findViewById(R.id.Cerveza);
        CheckBox margarita = (CheckBox)findViewById(R.id.Margarita);
        CheckBox tequila = (CheckBox)findViewById(R.id.Tequila);
        //

        StringBuilder s = new StringBuilder();
        s.append("Size: ");
        if(large.isChecked()){
            s.append("Large").append('\n');
        }else{
            s.append("Medium").append('\n');
        }
        s.append("Tortilla: ");
        if(corn.isChecked()){
            s.append("Corn").append('\n');
        }else {
            s.append("Flour").append('\n');
        }
        //
        s.append("Filings: ");
        if(beff.isChecked()){
            s.append("Beff, ");
        }
        if(chicken.isChecked()){
            s.append("Chicken, ");
        }
        if(whitefish.isChecked()){
            s.append("White Fish, ");
        }
        if(chesse.isChecked()){
            s.append("Chesse, ");
        }
        if(seefood.isChecked()){
            s.append("See Food, ");
        }
        //
        if(rice.isChecked()){
            s.append("Rice, ");
        }
        if(beans.isChecked()){
            s.append("Beans, ");
        }
        if(picodegallo.isChecked()){
            s.append("Pico de Gallo, ");
        }
        if(guacamole.isChecked()){
            s.append("Guacamole, ");
        }
        if(lbt.isChecked()){
            s.append("LBT, ");
        }
        //
        s.append('\n');
        s.append("Beverage: ");
        if(soda.isChecked()){
            s.append("Soda, ");
        }
        if(cerveza.isChecked()){
            s.append("Cerveza, ");
        }
        if(margarita.isChecked()){
            s.append("Margarita, ");
        }
        if(tequila.isChecked()){
            s.append("Tequila");
        }




        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("0972671570", null, s.toString(), null, null);

        /*
        String phoneNumber = "0972671571";
        String message = "HUNGRY AGAIN, more tacos ";
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:" + phoneNumber));
        intent.putExtra("sms_body", message);
        startActivity(intent);
        */
    }
}
