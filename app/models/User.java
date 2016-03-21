package models;

import play.db.ebean.*;
import play.db.ebean.Model;

import static org.junit.Assert.*;

import javax.persistence.*;

import org.junit.Test;

import com.avaje.ebean.*;

@Entity
public class User extends Model
{

    @Id
    public String email;
    public String name;
    public String password;
    public String userId;
    
    public User(String email, String name, String password)
    {
      this.email = email;
      this.name = name;
      this.password = password;
    }

    public static Finder<String,User> find = new Finder<String,User>(
        String.class, User.class
    ); 
    
    public static User authenticate(String email, String password)
    {
        User user = null;
        String tempId = find.where().eq("email", email)
            .eq("password", password).findUnique().userId;
        
        if (tempId != null)
        {
            user = find.ref(tempId);
        }

        return user;
    }
    
 
    public void tryAuthenticateUser() {
        new User("bob@gmail.com", "Bob", "secret").save();
        
        assertNotNull(User.authenticate("bob@gmail.com", "secret"));
        assertNull(User.authenticate("bob@gmail.com", "badpassword"));
        assertNull(User.authenticate("tom@gmail.com", "secret"));
    }
}