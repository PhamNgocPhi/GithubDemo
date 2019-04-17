package com.systena.githupdemo.data.model;

import com.google.gson.annotations.SerializedName;
import com.systena.githupdemo.util.common.Define;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = Define.Database.Repo.TABLE_NAME)
public class Repo {
    @PrimaryKey
    @ColumnInfo(name = Define.Database.Repo.ID)
    @SerializedName(Define.Database.Repo.ID)
    private Long id;

    @ColumnInfo(name = Define.Database.Repo.NAME)
    @SerializedName(Define.Database.Repo.NAME)
    private String name;

    @ColumnInfo(name = Define.Database.Repo.FULL_NAME)
    @SerializedName(Define.Database.Repo.FULL_NAME)
    private String fullName;

    @ColumnInfo(name = Define.Database.Repo.DESCRIPTION)
    @SerializedName(Define.Database.Repo.DESCRIPTION)
    private String description;

    @ColumnInfo(name = Define.Database.Repo.CONTRIBUTORS_URL)
    @SerializedName(Define.Database.Repo.CONTRIBUTORS_URL)
    private String contributorsUrl;

    @ColumnInfo(name = Define.Database.Repo.STARGAZERS_COUNT)
    @SerializedName(Define.Database.Repo.STARGAZERS_COUNT)
    private Integer stargazersCount;

    public Repo() {
    }

    @Ignore
    public Repo(Long id, String name, String fullName, String description, String contributorsUrl, Integer stargazersCount) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.description = description;
        this.contributorsUrl = contributorsUrl;
        this.stargazersCount = stargazersCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContributorsUrl() {
        return contributorsUrl;
    }

    public void setContributorsUrl(String contributorsUrl) {
        this.contributorsUrl = contributorsUrl;
    }

    public Integer getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(Integer stargazersCount) {
        this.stargazersCount = stargazersCount;
    }
}

