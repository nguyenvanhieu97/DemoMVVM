package com.cris.nvh.demomvvm.data;

import com.cris.nvh.demomvvm.GitHubClient;
import com.cris.nvh.demomvvm.data.model.GithubUser;

import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public class TasksRemoteDataSource {
	private static final String BASE_URL = "https://api.github.com/";
	private static TasksRemoteDataSource sInstance;

	public static TasksRemoteDataSource getInstance() {
		if (sInstance == null)
			sInstance = new TasksRemoteDataSource();
		return sInstance;
	}

	public void getData(String input, TasksDataSouce.CallBack callBack) {
		OkHttpClient httpClient = new OkHttpClient.Builder().build();
		Retrofit.Builder builder = new Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
			.addConverterFactory(GsonConverterFactory.create());
		Retrofit retrofit = builder.client(httpClient).build();
		GitHubClient client = retrofit.create(GitHubClient.class);
		client.githubUser(input)
			.subscribeOn(Schedulers.io())
			.observeOn(Schedulers.newThread())
			.distinct()
			.subscribeWith(getObserver(callBack));
	}

	private DisposableObserver<GithubUser> getObserver(final TasksDataSouce.CallBack callBack) {
		return new DisposableObserver<GithubUser>() {
			@Override
			public void onNext(GithubUser githubUser) {
				callBack.onLoadDataSuccessful(githubUser);
			}

			@Override
			public void onError(Throwable e) {
				callBack.onLoadDataFailed(e.getMessage());
			}

			@Override
			public void onComplete() {
			}
		};
	}
}
