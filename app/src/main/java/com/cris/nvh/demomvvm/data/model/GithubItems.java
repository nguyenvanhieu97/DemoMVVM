package com.cris.nvh.demomvvm.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GithubItems implements Parcelable {

	@SerializedName("login")
	@Expose
	private String mLogin;
	@SerializedName("id")
	@Expose
	private int mId;
	@SerializedName("avatar_url")
	@Expose
	private String mAvatarUrl;
	@SerializedName("site_admin")
	@Expose
	private boolean mSiteAdmin;
	@SerializedName("score")
	@Expose
	private double mScore;

	protected GithubItems(Parcel in) {
		this.mLogin = in.readString();
		this.mId = in.readInt();
		this.mAvatarUrl = in.readString();
		this.mSiteAdmin = in.readInt() != 0 ? true : false;
		this.mScore = in.readDouble();
	}

	public static final Creator<GithubItems> CREATOR = new Creator<GithubItems>() {
		@Override
		public GithubItems createFromParcel(Parcel in) {
			return new GithubItems(in);
		}

		@Override
		public GithubItems[] newArray(int size) {
			return new GithubItems[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int i) {
		dest.writeString(mLogin);
		dest.writeInt(mId);
		dest.writeString(mAvatarUrl);
		dest.writeInt(mSiteAdmin ? 1 : 0);
		dest.writeDouble(mScore);
	}

	public int getId() {
		return mId;
	}

	public void setId(int id) {
		mId = id;
	}

	public String getAvatarUrl() {
		return mAvatarUrl;
	}

	public double getScore() {
		return mScore;
	}

	public String getLogin() {
		return mLogin;
	}
}
