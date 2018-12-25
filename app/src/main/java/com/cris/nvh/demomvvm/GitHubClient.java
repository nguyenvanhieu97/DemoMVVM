package com.cris.nvh.demomvvm;

import com.cris.nvh.demomvvm.data.model.GithubUser;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GitHubClient {
	String PATH = "/search/users";
	String PARAMETER_QUERY = "q";

	@GET(PATH)
	Observable<GithubUser> githubUser(
		@Query(PARAMETER_QUERY) String value
	);
}
