package com.garena.android.githubreader.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.garena.android.githubreader.R;
import com.garena.android.githubreader.api.HttpClient;
import com.garena.android.githubreader.data.Repo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kaizhimeng on 18/9/16
 *
 * @author kaizhimeng
 *         since version tw1.0
 */
public class ReposActivity extends AppCompatActivity {

  public static void start(Context context) {
    Intent starter = new Intent(context, ReposActivity.class);
    context.startActivity(starter);
  }

  private ReposAdapter mAdapter;

  @BindView(R.id.rv_repos) RecyclerView mRvRepos;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_repos);
    ButterKnife.bind(this);

    mAdapter = new ReposAdapter();
    mRvRepos.setAdapter(mAdapter);
    mRvRepos.setLayoutManager(new LinearLayoutManager(this));

    loadRepos();
  }

  ///////////////////////////////////////////////////////////////////////////
  // network logic
  ///////////////////////////////////////////////////////////////////////////

  private void loadRepos() {
    Request repoRequest = new Request.Builder().url("https://api.github.com/user/repos").build();

    HttpClient.getClient().newCall(repoRequest).enqueue(new Callback() {
      @Override public void onFailure(Call call, IOException e) {
        Toast.makeText(ReposActivity.this, "failed", Toast.LENGTH_SHORT).show();
      }

      @Override public void onResponse(Call call, Response response) throws IOException {
        Log.i("mengk", "onResponse");
        String reply = response.body().string();
        Log.i("mengk", reply);

        try {
          JSONArray repoArrays = new JSONArray(reply);
          final List<Repo> repos = new ArrayList<>(repoArrays.length());
          for (int i = 0; i < repoArrays.length(); i++) {
            JSONObject repoObj = repoArrays.getJSONObject(i);
            repos.add(new Repo(repoObj.getString("name"), repoObj.getJSONObject("owner").getString("login")));
          }
          runOnUiThread(new Runnable() {
            @Override public void run() {
              mAdapter.addRepos(repos);
            }
          });
        } catch (JSONException e) {
          e.printStackTrace();
        }
      }
    });
  }
}
