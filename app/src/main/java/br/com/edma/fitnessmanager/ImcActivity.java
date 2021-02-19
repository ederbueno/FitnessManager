package br.com.edma.fitnessmanager;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ImcActivity extends AppCompatActivity {

    private EditText editHeigth;
    private EditText editWeigth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);

        editHeigth = findViewById(R.id.edit_imc_heigth);
        editWeigth = findViewById(R.id.edit_imc_weigth);

        Button btnSend = findViewById(R.id.btn_imc_send);

        btnSend.setOnClickListener(v -> {
          if(!validate()){
              Toast.makeText(ImcActivity.this, R.string.fields_messages, Toast.LENGTH_LONG).show();
              return;
          }

            int sHeigth = Integer.parseInt(editHeigth.getText().toString());
            int sWeigth = Integer.parseInt(editWeigth.getText().toString());

            double result = calculateImc(sHeigth, sWeigth);

            int imcResponceId = imcResponse(result);

            AlertDialog dialog = new AlertDialog.Builder(ImcActivity.this)
                                 .setTitle(getString(R.string.imc_responde, result))
                                 .setMessage(imcResponceId)
                                 .setPositiveButton(android.R.string.ok, (dialog1, which) -> {
                                 })
                                 .create();
            dialog.show();

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editHeigth.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(editWeigth.getWindowToken(), 0);

        });

    }

    @StringRes
    private int imcResponse(double imc){
          if(imc < 15)
              return R.string.imc_severely_low_weigth;
          else if(imc < 16)
              return R.string.imc_very_low_weigth;
          else if(imc < 18.5)
              return R.string.imc_low_weigth;
          else if(imc < 25)
              return R.string.normal;
          else if(imc < 30)
              return R.string.imc_high_weigth;
          else if(imc < 35)
              return R.string.imc_so_high_weigth;
          else if(imc < 40)
              return R.string.imc_severely_high_weigth;
          else
              return R.string.imc_extreme_weigth;
    }

    private double calculateImc(int heigth, int weigth){

        return weigth / ( ((double)heigth / 100) * ((double) heigth / 100));
    }

    private boolean validate(){
        return(!editHeigth.getText().toString().startsWith("0")
               && !editWeigth.getText().toString().startsWith("0")
               && !editHeigth.getText().toString().isEmpty()
               && !editWeigth.getText().toString().isEmpty() );
    }
}