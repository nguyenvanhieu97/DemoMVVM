package com.cris.nvh.demomvvm.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GithubUser implements Parcelable {

	@SerializedName("total_count")
	@Expose
	private int mTotalCount;
	@SerializedName("items")
	@Expose
	private List<GithubItems> mGithubItems;

	public final static Creator<GithubUser> CREATOR = new Creator<GithubUser>() {

		@SuppressWarnings({"unchecked"})
		public GithubUser createFromParcel(Parcel in) {
			return new GithubUser(in);
		}

		public GithubUser[] newArray(int size) {
			return (new GithubUser[size]);
		}

	};

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeList(mGithubItems);
		dest.writeInt(mTotalCount);
	}

	public List<GithubItems> getGithubItems() {
		return mGithubItems;
	}

	protected GithubUser(Parcel in) {
		this.mGithubItems = in.readArrayList(GithubItems.class.getClassLoader());
		this.mTotalCount = in.readInt();
	}
}
