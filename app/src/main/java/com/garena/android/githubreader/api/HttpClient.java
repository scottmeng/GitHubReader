package com.garena.android.githubreader.api;

import android.text.TextUtils;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by kaizhimeng on 18/9/16
 *
 * @author kaizhimeng
 *         since version tw1.0
 */
public class HttpClient extends OkHttpClient {

  private static OkHttpClient mClient;

  private static AuthInterceptor mInterceptor;

  public static OkHttpClient getClient() {
    if (mClient == null) {
      mInterceptor = new AuthInterceptor();
      mClient = new OkHttpClient.Builder().addNetworkInterceptor(mInterceptor).build();
    }

    return mClient;
  }

  public static void setToken(String token) {
    mInterceptor.setToken(token);
  }

  private static class AuthInterceptor implements Interceptor {

    private String mToken;

    public AuthInterceptor() {
    }

    public void setToken(String token) {
      mToken = token;
    }

    @Override public Response intercept(Chain chain) throws IOException {
      Request originalRequest = chain.request();
      Request newRequest;
      if (!TextUtils.isEmpty(mToken)) {
        // rewrite header
        newRequest = new Request.Builder().headers(originalRequest.headers())
            .addHeader("Authorization", "Bearer " + mToken)
            .method(originalRequest.method(), originalRequest.body())
            .url(originalRequest.url())
            .build();
      } else {
        newRequest = originalRequest;
      }
      return chain.proceed(newRequest);
    }
  }
}
