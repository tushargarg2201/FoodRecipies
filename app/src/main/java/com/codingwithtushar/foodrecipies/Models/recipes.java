package com.codingwithtushar.foodrecipies.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class recipes implements Parcelable {

    @SerializedName("ingredients")
    @Expose
    private String[] ingredients;

    @SerializedName("image_url")
    @Expose
    private String image_url;

    @SerializedName("social_rank")
    @Expose
    private String social_rank;

    @SerializedName("_id")
    @Expose
    private String _id;

    @SerializedName("publisher")
    @Expose
    private String publisher;

    @SerializedName("source_url")
    @Expose
    private String source_url;

    @SerializedName("recipe_id")
    @Expose
    private String recipe_id;

    @SerializedName("publisher_url")
    @Expose
    private String publisher_url;

    @SerializedName("title")
    @Expose
    private String title;

    public recipes() {

    }

    protected recipes(Parcel in) {
        ingredients = in.createStringArray();
        image_url = in.readString();
        social_rank = in.readString();
        _id = in.readString();
        publisher = in.readString();
        source_url = in.readString();
        recipe_id = in.readString();
        publisher_url = in.readString();
        title = in.readString();
    }

    public static final Creator<recipes> CREATOR = new Creator<recipes>() {
        @Override
        public recipes createFromParcel(Parcel in) {
            return new recipes(in);
        }

        @Override
        public recipes[] newArray(int size) {
            return new recipes[size];
        }
    };

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getSocial_rank() {
        return social_rank;
    }

    public void setSocial_rank(String social_rank) {
        this.social_rank = social_rank;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    public String getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(String recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getPublisher_url() {
        return publisher_url;
    }

    public void setPublisher_url(String publisher_url) {
        this.publisher_url = publisher_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(ingredients);
        dest.writeString(image_url);
        dest.writeString(social_rank);
        dest.writeString(_id);
        dest.writeString(publisher);
        dest.writeString(source_url);
        dest.writeString(recipe_id);
        dest.writeString(publisher_url);
        dest.writeString(title);
    }
}
