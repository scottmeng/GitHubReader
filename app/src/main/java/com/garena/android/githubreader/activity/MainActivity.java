package com.garena.android.githubreader.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.garena.android.githubreader.R;
import com.garena.android.githubreader.api.HttpClient;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.et_username) EditText etUsername;

  @BindView(R.id.et_password) EditText etPassword;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);
  }

  @OnClick({ R.id.btn_start_normal, R.id.btn_start_graphql }) void login(View view) {
    String inputUsername = etUsername.getText().toString().trim();
    String inputPassword = etPassword.getText().toString().trim();

    if (TextUtils.isEmpty(inputUsername) || TextUtils.isEmpty(inputPassword)) {
      Toast.makeText(MainActivity.this, "Username and password cannot be empty", Toast.LENGTH_SHORT)
          .show();
      return;
    }

    startLogin(inputUsername, inputPassword, view.getId() == R.id.btn_start_graphql);
  }

  private void startLogin(String username, String password, final boolean proceedWithGraphQL) {
    Request authRequest = new Request.Builder().url("https://api.github.com/authorizations")
        .addHeader("Content-Type", "application/json; charset=utf-8")
        .addHeader("Authorization", getAuthString(username, password))
        .post(RequestBody.create(MediaType.parse("application/json"),
            "{\"client_id\": \"e0b1671ff764de482212\", \"client_secret\": \"8f77dcfd6a807cff38ac558400c859f240806071\", \"scopes\": [\"user\", \"repo\"]}"))
        .build();

    HttpClient.getClient().newCall(authRequest).enqueue(new Callback() {
      @Override public void onFailure(Call call, IOException e) {
        Log.e("mengk", "onFailure", e);
      }

      @Override public void onResponse(Call call, Response response) throws IOException {
        Log.i("mengk", "onResponse");
        String reply = response.body().string();
        Log.i("mengk", reply);

        try {
          JSONObject replyObj = new JSONObject(reply);
          HttpClient.setToken(replyObj.getString("token"));
          if (proceedWithGraphQL) {
            ReposWithGraphQLActivity.start(MainActivity.this);
          } else {
            ReposActivity.start(MainActivity.this);
          }
        } catch (JSONException e) {
          e.printStackTrace();
        }
      }
    });
  }

  private String getAuthString(String username, String password) {
    return "Basic " + Base64.encodeToString((username + ":" + password).getBytes(), Base64.DEFAULT)
        .trim();
  }
}
