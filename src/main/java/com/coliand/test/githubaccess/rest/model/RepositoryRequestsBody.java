package com.coliand.test.githubaccess.rest.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RepositoryRequestsBody {

    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private boolean autoInit;
    @NotNull
    @NotBlank
    private boolean privateRepository;
    @NotNull
    @NotBlank
    private String accessToken;

    public RepositoryRequestsBody() {
    }

    public RepositoryRequestsBody(String name, boolean autoInit, boolean privateRepository, String accessToken) {
        this.name = name;
        this.autoInit = autoInit;
        this.privateRepository = privateRepository;
        this.accessToken = accessToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAutoInit() {
        return autoInit;
    }

    public void setAutoInit(boolean autoInit) {
        this.autoInit = autoInit;
    }

    public boolean isPrivateRepository() {
        return privateRepository;
    }

    public void setPrivateRepository(boolean privateRepository) {
        this.privateRepository = privateRepository;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "RepositoryRequestsBody [accessToken=" + accessToken + ", autoInit=" + autoInit + ", name=" + name
                + ", privateRepository=" + privateRepository + "]";
    }

}
