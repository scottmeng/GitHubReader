package com.garena.android.githubreader.data;

/**
 * Created by kaizhimeng on 20/9/16
 *
 * @author kaizhimeng
 *         since version tw1.0
 */
public class Repo {

  public final String repoName;

  public final String ownerName;

  public Repo(String repoName, String ownerName) {
    this.repoName = repoName;
    this.ownerName = ownerName;
  }
}
