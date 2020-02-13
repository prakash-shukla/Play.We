package www.nilraasports.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {
    TextInputEditText email,pass;
    Button getstarted;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        email=findViewById(R.id.signemail);
        pass=findViewById(R.id.signpass);
        getstarted=findViewById(R.id.btn_signup);
        mAuth = FirebaseAuth.getInstance();
        getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e=email.getText().toString();
                String p=pass.getText().toString();
                if(!TextUtils.isEmpty(e) && !TextUtils.isEmpty(p)) {
                    mAuth.signInWithEmailAndPassword(e, p).addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(SignIn.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignIn.this, "fill the  proper details", Toast.LENGTH_SHORT).show();
                            }
                        }


                    });
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();


    }
}
