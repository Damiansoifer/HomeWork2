package models;

import java.util.List;

public class UserResponse {
    private List<com.example.usercollectionapp.models.User> results;

    public List<com.example.usercollectionapp.models.User> getResults() {
        return results;
    }

    public void setResults(List<com.example.usercollectionapp.models.User> results) {
        this.results = results;
    }
}

