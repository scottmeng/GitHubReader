package com.garena.android.githubreader.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.garena.android.githubreader.R;

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

  @BindView(R.id.rv_repos) RecyclerView mRvRepos;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_repos);
    ButterKnife.bind(this);
  }
}
