package com.garena.android.githubreader.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by kaizhimeng on 18/9/16
 *
 * @author kaizhimeng
 *         since version tw1.0
 */
public class ReposWithGraphQLActivity extends AppCompatActivity {

  public static void start(Context context) {
      Intent starter = new Intent(context, ReposWithGraphQLActivity.class);
      context.startActivity(starter);
  }
}
