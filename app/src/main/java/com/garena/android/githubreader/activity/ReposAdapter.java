package com.garena.android.githubreader.activity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.garena.android.githubreader.R;
import com.garena.android.githubreader.data.Repo;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaizhimeng on 19/9/16
 *
 * @author kaizhimeng
 *         since version tw1.0
 */
public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.RepoItemViewHolder> {

  private final List<Repo> repos = new ArrayList<>();

  @Override public ReposAdapter.RepoItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.view_repo_item, parent, false);
    return new RepoItemViewHolder(itemView);
  }

  @Override public void onBindViewHolder(RepoItemViewHolder holder, int position) {
    holder.tvRepoName.setText(repos.get(position).repoName);
    holder.tvRepoOwner.setText(repos.get(position).ownerName);
  }

  @Override public int getItemCount() {
    return repos.size();
  }

  public void addRepos(List<Repo> newRepos) {
    repos.addAll(newRepos);
    notifyDataSetChanged();
  }

  protected static class RepoItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_repo_name) TextView tvRepoName;

    @BindView(R.id.tv_repo_owner) TextView tvRepoOwner;

    public RepoItemViewHolder(View itemView) {
      super(itemView);

      ButterKnife.bind(this, itemView);
    }
  }
}
