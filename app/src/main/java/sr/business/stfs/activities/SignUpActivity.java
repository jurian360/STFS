package sr.business.stfs.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.io.IOException;
import java.util.List;

import sr.business.stfs.R;
import sr.business.stfs.models.Stfsuser;
import sr.business.stfs.utils.Dataholder;
import sr.business.stfs.utils.Global;
import sr.business.stfs.utils.NetworkClient;

public class SignUpActivity extends AppCompatActivity implements Validator.ValidationListener{

    @NotEmpty
    private EditText email, name, surName, phone1;
    private EditText phone2, phone3, address;
    @NotEmpty
    @Password(scheme = Password.Scheme.ALPHA_NUMERIC, min = 8)
    private EditText password;
    @NotEmpty
    @ConfirmPassword
    private EditText passwordConfirm;
    private Validator validator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        validator = new Validator(this);
        validator.setValidationListener(this);

        init();
    }

    private void init() {
        final Button addMoreFields = (Button) findViewById(R.id.button_add_more);
        final Button signUp = (Button) findViewById(R.id.button_sign_up);
        final TextView view = (TextView) findViewById(R.id.text_view_placeholder);
        final LinearLayout extraFields = (LinearLayout) findViewById(R.id.linear_extra_field);
        if (addMoreFields != null) {
            addMoreFields.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addMoreFields.setVisibility(View.GONE);
                    if (extraFields != null) {
                        extraFields.setVisibility(View.VISIBLE);
                    }
                    if (view != null) {
                        view.setVisibility(View.GONE);
                    }

                    RelativeLayout.LayoutParams params = null;
                    if (signUp != null) {
                        params = (RelativeLayout.LayoutParams) signUp.getLayoutParams();
                    }
                    if (params != null) {
                        params.addRule(RelativeLayout.BELOW, R.id.linear_extra_field);
                    }
                }
            });
        }

        email = (EditText) findViewById(R.id.edit_text_email);
        password = (EditText) findViewById(R.id.edit_text_password);
        passwordConfirm = (EditText) findViewById(R.id.edit_text_password_confirm);
        name = (EditText) findViewById(R.id.edit_text_name);
        surName = (EditText) findViewById(R.id.edit_text_surname);
        phone1 = (EditText) findViewById(R.id.edit_text_phone);
        phone2 = (EditText) findViewById(R.id.edit_text_phone2);
        phone3 = (EditText) findViewById(R.id.edit_text_phone3);
        address = (EditText) findViewById(R.id.edit_text_address);

        if (signUp != null) {
            signUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    validator.validate();
                }
            });
        }
    }

    @Override
    public void onValidationSucceeded() {
        Gson gson = new Gson();
        Stfsuser user = new Stfsuser();
        user.setU_email(email.getText().toString());
        user.setU_name(name.getText().toString());
        user.setU_phone1(phone1.getText().toString());
        user.setU_password(password.getText().toString());
        user.setU_surname(surName.getText().toString());

        //Not Required Fields
        user.setU_phone2(phone2.getText().toString());
        user.setU_phone3(phone3.getText().toString());
        user.setU_address(address.getText().toString());
        String json = gson.toJson(user);

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("email", email.getText().toString());
        editor.commit();

        new PostUser(json).execute();
    }

    private class PostUser extends AsyncTask<Void,Void,Void>{

        String json,response;
        NetworkClient client = Dataholder.getInstance().getNetworkClient();
        ProgressDialog progress;

        public PostUser(String json){
            this.json = json;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = ProgressDialog.show(SignUpActivity.this, "Creating",
                    "creating user", true);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                response = client.post(Global.domain + Global.app +Global.model,json).body().toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progress.dismiss();
            Toast.makeText(SignUpActivity.this, response, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SignUpActivity.this,VerificationActivity.class));
        }
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
