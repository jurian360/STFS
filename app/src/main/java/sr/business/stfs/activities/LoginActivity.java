package sr.business.stfs.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.io.IOException;
import java.util.List;

import okhttp3.Response;
import sr.business.stfs.R;
import sr.business.stfs.models.Stfsuser;
import sr.business.stfs.utils.Dataholder;
import sr.business.stfs.utils.Global;
import sr.business.stfs.utils.NetworkClient;

public class LoginActivity extends AppCompatActivity implements Validator.ValidationListener {

    @NotEmpty
    private EditText email,password;
    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        validator = new Validator(this);
        validator.setValidationListener(this);
        init();
    }

    private void init() {
        Button login = (Button) findViewById(R.id.button_login);
        Button signup = (Button) findViewById(R.id.button_sign_up);

        if (signup != null) {
            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent signUpIntent = new Intent(LoginActivity.this,SignUpActivity.class);
                    startActivity(signUpIntent);
                }
            });
        }

        if (login != null) {
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //validator.validate();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }
            });
        }

        email = (EditText) findViewById(R.id.edit_text_email);
        password = (EditText) findViewById(R.id.edit_text_password);

        TextView text = (TextView) findViewById(R.id.textClick);
        if (text != null) {
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(LoginActivity.this,VerificationActivity.class));
                }
            });
        }
    }

    private void loginUser(String email,String password){
        Stfsuser user = new Stfsuser();
        user.setU_email(email);
        user.setU_password(password);

        new SignInNetworkCall(user).execute();
    }

    private class SignInNetworkCall extends AsyncTask<Void,Void,Void>{
        Stfsuser user;
        Response response;
        Gson gson = Dataholder.getInstance().getGson();
        NetworkClient client = Dataholder.getInstance().getNetworkClient();

        public SignInNetworkCall(Stfsuser user){
            this.user = user;
        }

        @Override
        protected Void doInBackground(Void... params) {
            String json = gson.toJson(user);
            try {
                response = client.post(Global.loginWebservice,json);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            switch (response.code()){
                case 302:
                    Intent view = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(view);
                    break;
                case 404:
                    Toast.makeText(LoginActivity.this, "User/Password Mismatch", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    //empty
            }
        }
    }

    @Override
    public void onValidationSucceeded() {
        loginUser(email.getText().toString(),password.getText().toString());
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
