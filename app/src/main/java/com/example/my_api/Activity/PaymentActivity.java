package com.example.my_api.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my_api.R;
import com.razorpay.Checkout;
import com.razorpay.ExternalWalletListener;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultListener;
import com.razorpay.PaymentResultWithDataListener;
import com.squareup.picasso.Picasso;


import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class PaymentActivity extends Activity implements PaymentResultWithDataListener, ExternalWalletListener {
    private static final String TAG = PaymentActivity.class.getSimpleName();
    String name,price,image;
    ImageView imageView;
    TextView txtname,txtprice;
    private AlertDialog.Builder alertDialogBuilder;

    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_payment);

        imageView=findViewById(R.id.pay_image);
        txtname=findViewById(R.id.txtname);
        txtprice=findViewById(R.id.txtprice);

        name=getIntent().getStringExtra("name");
        price=getIntent().getStringExtra("price");
        image=getIntent().getStringExtra("image");

        txtname.setText(name);
        txtprice.setText(price);


        String img="https://ishaniecommerce.000webhostapp.com/Mysite/"+image;
        Picasso.get()
                .load(img)
                .placeholder(R.drawable.animation)
                .into(imageView);



        /*
         To ensure faster loading of the Checkout form,
          call this method as early as possible in your checkout flow.
         */
        Checkout.preload(getApplicationContext());

        // Payment button created by you in XML layout
        Button button = (Button) findViewById(R.id.btn_pay);

        alertDialogBuilder = new AlertDialog.Builder(PaymentActivity.this);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setTitle("Payment Result");
        alertDialogBuilder.setPositiveButton("Ok", (dialog, which) -> {
            //do nothing
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });

        TextView privacyPolicy = (TextView) findViewById(R.id.txt_privacy_policy);

        privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent httpIntent = new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://razorpay.com/sample-application/"));
                startActivity(httpIntent);
            }
        });
    }

    public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = this;

        final Checkout co = new Checkout();

        EditText etApiKey = findViewById(R.id.et_api_key);
        etApiKey.setText("rzp_test_X4JrN7DROaWPqi");
        if (!TextUtils.isEmpty(etApiKey.getText().toString())){
            co.setKeyID(etApiKey.getText().toString());
        }
        EditText etCustomOptions = findViewById(R.id.et_custom_options);
        if (!TextUtils.isEmpty(etCustomOptions.getText().toString())){
            try{
                JSONObject options = new JSONObject(etCustomOptions.getText().toString());
                co.open(activity, options);
            }catch (JSONException e){
                Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                        .show();
                e.printStackTrace();
            }
        }else{
            try {
                JSONObject options = new JSONObject();
                options.put("name", name);
                options.put("description", "Demoing Charges");
                options.put("send_sms_hash",true);
                options.put("allow_rotation", true);
                //You can omit the image option to fetch the image from dashboard
                options.put("image","https://ishaniecommerce.000webhostapp.com/Mysite/"+image);
                options.put("currency", "INR");
                options.put("amount",price);

                JSONObject preFill = new JSONObject();
                preFill.put("email", "test@razorpay.com");
                preFill.put("contact", "9327772430");

                options.put("prefill", preFill);

                co.open(activity, options);
            } catch (Exception e) {
                Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                        .show();
                e.printStackTrace();
            }
        }


    }

    /**
     * The name of the function has to be
     * onPaymentSuccess
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */


    @Override
    public void onExternalWalletSelected(String s, PaymentData paymentData) {
        try{
            alertDialogBuilder.setMessage("External Wallet Selected:\nPayment Data: "+paymentData.getData());
            alertDialogBuilder.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {
        try{
            alertDialogBuilder.setMessage("Payment Successful :\nPayment ID: "+s+"\nPayment Data: "+paymentData.getData());
            alertDialogBuilder.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {
        try{
            alertDialogBuilder.setMessage("Payment Failed:\nPayment Data: "+paymentData.getData());
            alertDialogBuilder.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}