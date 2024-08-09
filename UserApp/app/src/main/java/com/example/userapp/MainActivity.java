package com.example.userapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.userapp.database.AppDatabase;
import com.example.userapp.entities.UserEntity;
import com.example.userapp.models.User;
import com.example.userapp.models.UserResponse;
import com.example.userapp.services.RandomUserService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ImageView userImage;
    private TextView firstName, lastName, age, email, city, country;
    private Button btnSeeNextUser, btnAddUser, btnViewCollection;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userImage = findViewById(R.id.user_image);
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        age = findViewById(R.id.age);
        email = findViewById(R.id.email);
        city = findViewById(R.id.city);
        country = findViewById(R.id.country);

        btnSeeNextUser = findViewById(R.id.btn_see_next_user);
        btnAddUser = findViewById(R.id.btn_add_user);
        btnViewCollection = findViewById(R.id.btn_view_collection);

        btnSeeNextUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchRandomUser();
            }
        });

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUserToCollection();
            }
        });

        btnViewCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewUserCollection();
            }
        });

        fetchRandomUser();
    }

    private void fetchRandomUser() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me/api")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RandomUserService service = retrofit.create(RandomUserService.class);

        Call<UserResponse> call = service.getRandomUser();
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    currentUser = response.body().getResults().get(0);
                    updateUIWithUser(currentUser);
                } else {
                    showError();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                showError();
            }
        });
    }

    private void updateUIWithUser(User user) {
        Picasso.get().load(user.getPicture().getLarge()).into(userImage);
        firstName.setText(user.getName().getFirst());
        lastName.setText(user.getName().getLast());
        age.setText(String.valueOf(user.getDob().getAge()));
        email.setText(user.getEmail());
        city.setText(user.getLocation().getCity());
        country.setText(user.getLocation().getCountry());
    }

    private void showError() {
        firstName.setText("error");
        lastName.setText("error");
        age.setText("error");
        email.setText("error");
        city.setText("error");
        country.setText("error");
        userImage.setImageResource(R.drawable.error_image); // תמונה שנמצאת בתיקיית drawable
    }

    // הוסף את הפונקציה הזו במחלקת MainActivity
    private void addUserToCollection() {
        if (currentUser == null) {
            Toast.makeText(MainActivity.this, "Error: Cannot add user", Toast.LENGTH_SHORT).show();
            return;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setId(currentUser.getId().getValue());
        userEntity.setFirstName(currentUser.getName().getFirst());
        userEntity.setLastName(currentUser.getName().getLast());
        userEntity.setAge(currentUser.getDob().getAge());
        userEntity.setEmail(currentUser.getEmail());
        userEntity.setCity(currentUser.getLocation().getCity());
        userEntity.setCountry(currentUser.getLocation().getCountry());
        userEntity.setPictureUrl(currentUser.getPicture().getLarge());

        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = ((MyApplication) getApplicationContext()).getDatabase();
                db.userDao().insert(userEntity);
            }
        }).start();
    }

    private void viewUserCollection() {
        Intent intent = new Intent(MainActivity.this, UsersActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchRandomUser();
    }
}
