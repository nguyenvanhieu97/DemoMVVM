package com.cris.nvh.demomvvm.screen.search;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;

import com.cris.nvh.demomvvm.BR;
import com.cris.nvh.demomvvm.adapter.GithubUserAdapter;
import com.cris.nvh.demomvvm.data.TasksDataSouce;
import com.cris.nvh.demomvvm.data.TasksRemoteDataSource;
import com.cris.nvh.demomvvm.data.model.GithubItems;
import com.cris.nvh.demomvvm.data.model.GithubUser;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class SearchViewModel extends BaseObservable implements TasksDataSouce.CallBack {
	public final ObservableField<String> messageObservable = new ObservableField<>();
	public final ObservableField<GithubUser> githubUserObservable = new ObservableField<>();
	private final TasksRemoteDataSource mTasksRemoteDataSource;
	private ActivityNavigator mNavigator;
	private GithubUserAdapter mAdapter;

	@Bindable
	public ObservableField<String> getMessageObservable() {
		return messageObservable;
	}

	@Bindable
	public ObservableField<GithubUser> getGithubUserObservable() {
		return githubUserObservable;
	}

	public SearchViewModel(TasksRemoteDataSource tasksRemoteDataSource) {
		mTasksRemoteDataSource = tasksRemoteDataSource;
	}

	@Override
	public void onLoadDataSuccessful(GithubUser githubUser) {
		githubUserObservable.set(githubUser);
		mAdapter.setsGithubUser(githubUser);
		notifyChange();
	}

	@Override
	public void onLoadDataFailed(String message) {
		messageObservable.set(message);
		notifyPropertyChanged(BR.messageObservable);
	}

	public void setNavigator(ActivityNavigator navigator) {
		mNavigator = navigator;
	}

	public void setAdapter(GithubUserAdapter adapter) {
		mAdapter = adapter;
	}

	public GithubUserAdapter getAdapter() {
		return mAdapter;
	}

	public void getData(String input) {
		mTasksRemoteDataSource.getData(input, this);
	}

	public void openDetailActivity(GithubItems item) {
		mNavigator.openDetailActivity(item);
	}
}
