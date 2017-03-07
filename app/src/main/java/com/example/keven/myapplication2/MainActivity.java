package com.example.keven.myapplication2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameField = (EditText)findViewById(R.id.namefield);
        String value = nameField.getText().toString();
        Log.v("MainActivity" , "name " + value);


        CheckBox whippedcream =  (CheckBox)  findViewById(R.id.checkbox);
        boolean haswhippedcream = whippedcream.isChecked();
        Log.v("MainActivity", "HAs WHIPPED CREAM" + haswhippedcream);

        CheckBox chocolate = (CheckBox) findViewById(R.id.checkboxc);
        boolean haschocolate = chocolate.isChecked();
        Log.v("MainActivity" , "HAs CHocolate" + haschocolate);

        int price = calculatePrice(haswhippedcream , haschocolate);
        String priceMessage = createOrderSummary(value , price , haswhippedcream , haschocolate );
        displayMessage(priceMessage);

//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("mailto:"));
//        intent.putExtra(Intent.EXTRA_SUBJECT,"just java order for " + value);
//        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);
//        if(intent.resolveActivity(getPackageManager()) !=null)
//        {
//            startActivity(intent);
//        }

    }

    private String createOrderSummary( String value ,int price ,  boolean addwhipedCream , boolean addChocolate)
    {

        String priceMessage = "Name :" + value + "\n";
        priceMessage = priceMessage + "add whipe ?" + addwhipedCream + "\n";
        priceMessage = priceMessage + "add chocolate ?" + addChocolate + "\n";
        priceMessage = priceMessage + "Quantitiy : " + quantity + "\n";
        priceMessage = priceMessage + "Total $ " + price + "\n" + "Thank you";
        return priceMessage;

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantitiy(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantitiy_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView= (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    private int calculatePrice(boolean addwhipedCream , boolean addChocolate)
    {
         int basePrice = 5;
        if(addwhipedCream == true)
        {
            basePrice = basePrice + 1;
        }
        if(addChocolate == true)
        {
            basePrice = basePrice + 2;
        }



        int price = basePrice * quantity;
        return price;
    }




    int quantity = 0;

    public void increment(View view)
    {
        if(quantity == 100)
        {
            Toast.makeText(this , "can't more than 100 !" , Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity+1;
        displayQuantitiy(quantity);
    }

    public void decrement(View view)
    {
        if(quantity == 0)
        {
            Toast.makeText(this , "can't less than 0 !" , Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity-1;
        displayQuantitiy(quantity);
    }

}