package com.garena.android.githubreader.data;

/**
 * Created by kaizhimeng on 20/9/16
 *
 * @author kaizhimeng
 *         since version tw1.0
 */
public class Commit {

  public final String hash;

  public final String commitMsg;

  public final String  committerName;

  public Commit(String hash, String commitMsg, String committerName) {
    this.hash = hash;
    this.commitMsg = commitMsg;
    this.committerName = committerName;
  }
}
