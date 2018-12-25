package com.cris.nvh.demomvvm.screen.userdetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cris.nvh.demomvvm.R;
import com.cris.nvh.demomvvm.data.model.GithubItems;

import static com.cris.nvh.demomvvm.screen.search.SearchActivity.EXTRA_USER;

public class UserDetails extends AppCompatActivity implements View.OnClickListener {
	private ImageView mButtonBack;
	private TextView mTextLogin;
	private ImageView mImageAvatar;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_details);
		mButtonBack = findViewById(R.id.image_back);
		mTextLogin = findViewById(R.id.text_login);
		mImageAvatar = findViewById(R.id.image_avatar);
		mButtonBack.setOnClickListener(this);
		getData();
	}

	private void getData() {
		GithubItems item = getIntent().getParcelableExtra(EXTRA_USER);
		mTextLogin.setText(item.getLogin());
		Glide.with(this).load(item.getAvatarUrl())
			.apply(RequestOptions.centerCropTransform()).into(mImageAvatar);
	}

	@Override
	public void onClick(View view) {
		super.onBackPressed();
	}

	public static Intent getUserDetailsIntent(Context context, GithubItems items) {
		Intent intent = new Intent(context, UserDetails.class);
		intent.putExtra(EXTRA_USER, items);
		return intent;
	}
}
