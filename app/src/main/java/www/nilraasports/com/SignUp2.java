package www.nilraasports.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Collections;

public class SignUp2 extends AppCompatActivity {
    String game,level,discription,name,email,pass,age,gender,imglink;
    EditText sport,disc;
    Bundle bundleData;
    private FirebaseAuth firebaseAuth;

    private ArrayList<String> getGameContent() {
        ArrayList<String> tempList = new ArrayList<>();
        tempList.add("Cricket");
        tempList.add("Basketball");
        tempList.add("Badminton");
        tempList.add("Football");
        tempList.add("Rugby");
        tempList.add("American Football");
        tempList.add("Chess");
        tempList.add("Swimming");
        tempList.add("Baseballa");
        tempList.add("Softball");
        tempList.add("Golf");
        tempList.add("Table Tennis");
        tempList.add("Volleyball");
        tempList.add("Handball");
        tempList.add("tennis");
        tempList.add("Hockey");
        tempList.add("Ice Hockey");
        tempList.add("Archery");
        tempList.add("Bowling");
        tempList.add("Captain's ball");
        tempList.add("Floorball");
        tempList.add("Frisbee");
        tempList.add("Kinball");
        tempList.add("Lacrosse");
        tempList.add("Netball");
        tempList.add("Polo");
        tempList.add("Skating");
        tempList.add("Snooker");
        tempList.add("8 Ball pool");
        tempList.add("Water polo");
        Collections.sort(tempList);
        return tempList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);
        final AutoCompleteTextView acType=findViewById(R.id.acType);
        ArrayAdapter arrayAdapter= new ArrayAdapter<>(SignUp2.this, android.R.layout.simple_list_item_1,getGameContent());
        acType.setThreshold(1);
        acType.setAdapter(arrayAdapter);
        firebaseAuth=FirebaseAuth.getInstance();

        findViewById(R.id.signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                game=acType.getText().toString();
//                discription=disc.getText().toString().trim();
                bundleData=SignUp2.this.getIntent().getExtras();
                name=bundleData.getString("name");
                email=bundleData.getString("email");
                pass=bundleData.getString("pass");
                age=bundleData.getString("dob");
                gender=bundleData.getString("gender");
                imglink=bundleData.getString("imagelink");


                firebaseAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(SignUp2.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //checking if success
                                if(task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(),"Registration",Toast.LENGTH_LONG).show();


                                }else{
                                    //display some message here
                                    Toast.makeText(getApplicationContext(),"Registration Error",Toast.LENGTH_LONG).show();
                                }

                            }
                        });

                Intent intent=new Intent(SignUp2.this,Map.class);
                startActivity(intent);


            }
        });



        findViewById(R.id.inter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level="Inter";
                ImageView inter=findViewById(R.id.inter);
                ImageView beg=findViewById(R.id.beg);
                ImageView ad=findViewById(R.id.advanced);
                ImageView pro=findViewById(R.id.pro);
                inter.setImageResource(R.drawable.interm);
                beg.setImageResource(R.drawable.am);
                ad.setImageResource(R.drawable.advanced);
                pro.setImageResource(R.drawable.pro);

                

            }
        });
        findViewById(R.id.beg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level="Begineer";
                ImageView inter=findViewById(R.id.inter);
                ImageView beg=findViewById(R.id.beg);
                ImageView ad=findViewById(R.id.advanced);
                ImageView pro=findViewById(R.id.pro);
                inter.setImageResource(R.drawable.inter);
                beg.setImageResource(R.drawable.beg);
                ad.setImageResource(R.drawable.advanced);
                pro.setImageResource(R.drawable.pro);

            }
        });
        findViewById(R.id.advanced).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level="advanced";
                ImageView inter=findViewById(R.id.inter);
                ImageView beg=findViewById(R.id.beg);
                ImageView ad=findViewById(R.id.advanced);
                ImageView pro=findViewById(R.id.pro);
                inter.setImageResource(R.drawable.inter);
                beg.setImageResource(R.drawable.am);
                ad.setImageResource(R.drawable.ad);
                pro.setImageResource(R.drawable.pro);

            }
        });
        findViewById(R.id.pro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level="Pro";
                ImageView inter=findViewById(R.id.inter);
                ImageView beg=findViewById(R.id.beg);
                ImageView ad=findViewById(R.id.advanced);
                ImageView pro=findViewById(R.id.pro);
                inter.setImageResource(R.drawable.inter);
                beg.setImageResource(R.drawable.am);
                ad.setImageResource(R.drawable.advanced);
                pro.setImageResource(R.drawable.prof);

            }
        });
    }

}
