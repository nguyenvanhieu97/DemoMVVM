package com.cris.nvh.demomvvm.data;

import com.cris.nvh.demomvvm.data.model.GithubUser;

/**
 * Created by nvh
 * Contact: toiyeuthethao1997@gmail.com
 */

public interface TasksDataSouce {

	interface CallBack{
		void onLoadDataSuccessful(GithubUser githubUser);

		void onLoadDataFailed(String message);
	}
}
