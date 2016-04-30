package sr.business.stfs.activities;

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

public class VerificationActivity extends AppCompatActivity implements Validator.ValidationListener{

    @NotEmpty
    private EditText verificationCode;
    private Validator validator;
    private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        validator = new Validator(this);
        validator.setValidationListener(this);

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        email = sharedPref.getString("email","");

        init();
    }

    private void init() {
        verificationCode = (EditText) findViewById(R.id.edit_text_verify);
        Button button = (Button) findViewById(R.id.button_verify);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    validator.validate();
                }
            });
        }
    }

    @Override
    public void onValidationSucceeded() {
        Stfsuser user = new Stfsuser();
        user.setU_email(email);
        user.setU_code(verificationCode.getText().toString());
        Gson gson = Dataholder.getInstance().getGson();
        String json = gson.toJson(user);

        new PostUser(json).execute();
    }

    private class PostUser extends AsyncTask<Void,Void,Void> {

        String json;
        Response response;
        NetworkClient client = Dataholder.getInstance().getNetworkClient();

        public PostUser(String json){
            this.json = json;
        }


        @Override
        protected Void doInBackground(Void... params) {
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
            Toast.makeText(VerificationActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
            switch (response.code()){
                case 302:
                    //
                    break;
                case 202:
                    startActivity(new Intent(VerificationActivity.this,MainActivity.class));
                    break;
                default:
                    //
            }
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
