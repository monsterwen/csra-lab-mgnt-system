package controllers;

import models.*;
import play.*;
import play.mvc.*;
import static play.data.Form.*;

import play.data.*;

import views.html.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class LoginController extends Controller
{

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    
    public Result login() 
    {
        return ok(login.render(form(Login.class)));
    }
    
    public Result authenticate()
    {
        Form<Login> loginForm = form(Login.class).bindFromRequest();
        if (loginForm.hasErrors())
        {
            return badRequest(login.render(loginForm));
        }
        else
        {
            session().clear();
            session("email", loginForm.get().email);
            return redirect(
                routes.HomeController.index()
            );
        }
        
    }
    
    public static class Login
    {
        public String email;
        public String password;
        
        public String validate()
        {
            if (User.authenticate(email, password) == null)
            {
                return "Invalid user or password";
            }
            
            return null;
        }
    
    }   
   
}

