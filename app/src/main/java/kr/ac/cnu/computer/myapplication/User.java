package kr.ac.cnu.computer.myapplication;
import com.google.firebase.database.IgnoreExtraProperties;
@IgnoreExtraProperties
public class User {
    private String username;
    private String email;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
    public String getUsername(){
        return this.username;
    }
    public void setUsername(String newUsername){
        this.username = newUsername;
    }
    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
