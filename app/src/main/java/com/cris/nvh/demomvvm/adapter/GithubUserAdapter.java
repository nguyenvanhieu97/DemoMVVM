package com.cris.nvh.demomvvm.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cris.nvh.demomvvm.BR;
import com.cris.nvh.demomvvm.R;
import com.cris.nvh.demomvvm.data.model.GithubItems;
import com.cris.nvh.demomvvm.data.model.GithubUser;
import com.cris.nvh.demomvvm.databinding.ViewHolderBinding;

public class GithubUserAdapter extends RecyclerView.Adapter<GithubUserAdapter.ViewHolder> {
	private static GithubUser sGithubUser;
	private ClickItemListener mListener;

	public GithubUserAdapter(ClickItemListener listener) {
		mListener = listener;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
		ViewHolderBinding viewHolderBinding = DataBindingUtil
			.inflate(layoutInflater, R.layout.view_holder, viewGroup, false);
		return new ViewHolder(viewHolderBinding, mListener);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
		viewHolder.bindData(sGithubUser.getGithubItems().get(i));
	}

	@Override
	public int getItemCount() {
		return sGithubUser == null ? 0 : sGithubUser.getGithubItems().size();
	}

	public void setsGithubUser(GithubUser githubUser) {
		sGithubUser = githubUser;
	}

	public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		private ClickItemListener mListener;
		private ViewDataBinding mDataBinding;

		public ViewHolder(ViewHolderBinding dataBinding, ClickItemListener listener) {
			super(dataBinding.getRoot());
			mDataBinding = dataBinding;
			mListener = listener;
			itemView.setOnClickListener(this);
		}

		public void bindData(GithubItems item) {
			mDataBinding.setVariable(BR.item, item);
			mDataBinding.notifyChange();
		}

		@Override
		public void onClick(View view) {
			mListener.onClickItem(sGithubUser.getGithubItems().get(getAdapterPosition()));
		}
	}

	public interface ClickItemListener {
		void onClickItem(GithubItems item);
	}
}
