package com.cris.nvh.demomvvm.screen.search;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.cris.nvh.demomvvm.R;
import com.cris.nvh.demomvvm.adapter.GithubUserAdapter;
import com.cris.nvh.demomvvm.data.TasksRemoteDataSource;
import com.cris.nvh.demomvvm.data.model.GithubItems;
import com.cris.nvh.demomvvm.databinding.ActivitySearchBinding;

import static com.cris.nvh.demomvvm.screen.userdetails.UserDetails.getUserDetailsIntent;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener,
	GithubUserAdapter.ClickItemListener, ActivityNavigator {

	public static final String EXTRA_USER = "com.cris.nvh.mvpdemo.extra.EXTRA_USER";
	private EditText mEditSearch;
	private RecyclerView mRecyclerView;
	private GithubUserAdapter mAdapter;
	private SearchViewModel mSearchViewModel;
	private ActivitySearchBinding mSearchBinding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mSearchBinding = DataBindingUtil.setContentView(this, R.layout.activity_search);
		initView();
		initViewModel();
	}

	@Override
	public void openDetailActivity(GithubItems githubItems) {
		startActivity(getUserDetailsIntent(this, githubItems));
	}

	@Override
	public void onClickItem(GithubItems item) {
		mSearchViewModel.openDetailActivity(item);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.button_search:
				mSearchViewModel.getData(mEditSearch.getText().toString());
				break;
			default:
				break;
		}
	}

	private void initView() {
		mRecyclerView = findViewById(R.id.list_item);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		mAdapter = new GithubUserAdapter(this);
		mRecyclerView.setAdapter(mAdapter);
		mEditSearch = findViewById(R.id.edit_search);
		mEditSearch.setOnClickListener(this);
		mEditSearch.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
			}

			@Override
			public void onTextChanged(CharSequence input, int i, int i1, int i2) {
				mSearchViewModel.getData(input.toString());
			}

			@Override
			public void afterTextChanged(Editable editable) {
			}
		});
	}

	private void initViewModel() {
		mSearchViewModel = new SearchViewModel(TasksRemoteDataSource.getInstance());
		mSearchViewModel.setNavigator(this);
		mSearchViewModel.setAdapter(mAdapter);
		mSearchBinding.setViewmodel(mSearchViewModel);
	}
}
