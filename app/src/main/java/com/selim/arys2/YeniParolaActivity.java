package com.selim.arys2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class YeniParolaActivity extends AppCompatActivity {

     EditText e1;
     FirebaseAuth auth;
     ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yeni_parola_layout);

        dialog = new ProgressDialog(this);
        e1 = (EditText)findViewById(R.id.editText);
        auth = FirebaseAuth.getInstance();
    }


    public void change(View view) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){

            dialog.setMessage("Parola değiştiriliyor lütfen bekleyiniz");
            dialog.show();

            user.updatePassword(e1.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(),"parola değişti",Toast.LENGTH_LONG);
                        auth.signOut();
                        finish();
                        Intent i = new Intent(YeniParolaActivity.this,girisActivity.class);
                        startActivity(i);

                    }
                    else
                        {
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(),"parola değişmedi",Toast.LENGTH_LONG);
                        }
                }
            });
        }
    }
}
