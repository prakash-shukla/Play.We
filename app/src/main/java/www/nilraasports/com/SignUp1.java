package www.nilraasports.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Locale;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUp1 extends AppCompatActivity {
    public static final String TAG = "Sign Up1 Activity";
    private CircleImageView dp;
  EditText name, email, passwoard, dob;
  public String gender,sname,semail,semails,spass,age;

  FirebaseAuth auth=FirebaseAuth.getInstance();
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageReference = storage.getReference();
    DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference();
    Bundle bundle=new Bundle();

    public Uri filePath;
    String imageLink;
    private final int PICK_IMAGE_REQUEST = 71;
    private ArrayList<String> getSpinnerContent() {
        ArrayList<String> tempList = new ArrayList<>();
        tempList.add("Male");
        tempList.add("Female");
        tempList.add("Other");
        return tempList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up1);
        final AutoCompleteTextView acType=findViewById(R.id.gender);
        ArrayAdapter arrayAdapter= new ArrayAdapter<>(SignUp1.this, android.R.layout.simple_list_item_1,getSpinnerContent());
        acType.setThreshold(0);
        acType.setAdapter(arrayAdapter);
        name=findViewById(R.id.signupname);
        email=findViewById(R.id.signupemail);
        passwoard=findViewById(R.id.signuppass);
        dob=findViewById(R.id.signupdob);

        dp = findViewById(R.id.dp);

        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateForm()){
                    final ProgressDialog progress = new ProgressDialog(SignUp1.this);
                    progress.setMessage("Registering ........... ");
                    progress.show();
                    String semail = email.getText().toString().trim();
                    String spass = passwoard.getText().toString().trim();
                    gender=acType.getText().toString();
                    auth.signInWithEmailAndPassword(semail,spass).addOnCompleteListener(SignUp1.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Intent intent = new Intent(SignUp1.this, SignUp2.class);
                            bundle.putString("name",name.getText().toString().trim());
                            bundle.putString("email",email.getText().toString().trim());
                            bundle.putString("pass",passwoard.getText().toString().trim());
                            bundle.putString("dob",dob.getText().toString().trim());
                          //  bundle.putString("gender",gender);.
                            bundle.putString("imagelink",imageLink);String spass = passwoard.getText().toString().trim();
                            intent.putExtras(bundle);
                            progress.dismiss();
                            startActivity(intent);
                        }
                    });

                }
                else {

                }


            }
        });
        dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choseImage();
            }
        });

    }

    private void choseImage() {
        checkFilePermissions();
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);

    }

    private void checkFilePermissions() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            int permissionCheck = SignUp1.this.checkSelfPermission("Manifest.permission.READ_EXTERNAL_STORAGE");
            permissionCheck += SignUp1.this.checkSelfPermission("Manifest.permission.WRITE_EXTERNAL_STORAGE");
            if (permissionCheck != 0) {
                this.requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1001); //Any number
            }
        } else {
            Log.d(TAG, "checkBTPermissions: No need to check permissions. SDK version < LOLLIPOP.");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            Log.d(TAG, "filepath : " + filePath.toString());
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), filePath);
                dp.setImageBitmap(bitmap);
                uploadImage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage() {
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            final ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            final String url = "images/" + UUID.randomUUID().toString();
            // Defining the child of storageReference
               final StorageReference ref = storageReference.child(url);
            Log.d(TAG,"url : "+url);
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Log.d(TAG, "onSuccess: uri= " + uri.toString());
                                    imageLink = uri.toString();
                                    progressDialog.dismiss();
                                    Toast.makeText(getBaseContext(), " image uploaded", Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    });

                    }
                    }


    private boolean validateForm() {
        boolean state =true;
        String semail = email.getText().toString().trim();
        String sname = name.getText().toString().trim();
        String sage =   dob.getText().toString().trim();
        String spass = passwoard.getText().toString().trim();
        if(TextUtils.isEmpty(semail)) { state=false; email.setError("Required");}
        if(TextUtils.isEmpty(sage)) { state=false; dob.setError("Required");}
        else{
            if( Integer.parseInt(sage)<=13){ state = false; dob.setError("Age should be more than 15");}
        }
        if(TextUtils.isEmpty(sname)) { state=false; name.setError("Required");}
        if(TextUtils.isEmpty(spass)) { state=false; passwoard.setError("Required");}

        if(spass.length()<6) {
            Toast.makeText(SignUp1.this, "Password must be atleast 6 characters", Toast.LENGTH_SHORT).show();
            state=false;}

        return state;
    }

}