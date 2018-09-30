package appstore.com.enigmaapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Activity_Login extends AppCompatActivity {
    Button submit;
    EditText name,mail,pass;
    String Email,Password,Name;

    FirebaseAuth rAuth;
    FirebaseDatabase rDBase;
    DatabaseReference rDataBase;
    ProgressDialog rDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        name=(EditText)findViewById(R.id.name);
        mail=(EditText)findViewById(R.id.mail);
        pass=(EditText)findViewById(R.id.pass);
        submit=(Button)findViewById(R.id.regd);

        rAuth=FirebaseAuth.getInstance();
        rDBase=FirebaseDatabase.getInstance();
        rDataBase = rDBase.getReference("EnigmaQuiz");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewUser();
            }
        });
        rDialog=new ProgressDialog(this);

    }

    private void NewUser() {
        //removing extra spaces from front and back
        Name=name.getText().toString().trim();
        Email=mail.getText().toString().trim();
        Password=pass.getText().toString().trim();
        //checking for empty fields

        if(TextUtils.isEmpty(Name))
        {
            Toast.makeText(Activity_Login.this,"Email Field Cannot Be Blank",Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(Email))
        {
            Toast.makeText(Activity_Login.this,"Email Field Cannot Be Blank",Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(Password))
        {
            Toast.makeText(Activity_Login.this,"Password Field Cannot Be Blank",Toast.LENGTH_SHORT).show();
            return;
        }
        else if(Password.length()<6)
        {
            Toast.makeText(Activity_Login.this,"Password Must be Minimum 6 Characters",Toast.LENGTH_SHORT).show();
            return;
        }
        rDialog.setMessage(" Creating User Please Wait ......");
        rDialog.setCanceledOnTouchOutside(false);
        rDialog.show();
        rAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    rDialog.dismiss();
                    Toast.makeText(Activity_Login.this,"Successful....",Toast.LENGTH_SHORT).show();
                    OnAuth(task.getResult().getUser());
                    sendVerificationEmail();
                    FirebaseAuth.getInstance().signOut();
                    Intent intent=new Intent(Activity_Login.this,Activity_home.class);
                    startActivity(intent);
                    finish();

                }
            }
        });
    }
    private void OnAuth(FirebaseUser user) {
        createNew(user.getUid());
    }

    private void createNew(String uid) {
        rDataBase.child(uid).setValue(Name);// writing name to RDBMS
    }
    private void sendVerificationEmail()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // email sent

                            Toast.makeText(getApplicationContext()," Verification mail Sent ",Toast.LENGTH_SHORT).show();

                            // after email is sent just logout the user and finish this activity

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext()," Couldn't Send Verification Email !! Try  Logging In ",Toast.LENGTH_SHORT).show();
                            //restart this activity


                        }
                    }
                });


}
}
