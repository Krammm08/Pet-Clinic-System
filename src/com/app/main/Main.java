import com.app.model.User;
import com.app.view.LoginView;
import com.app.view.MainMenuView;


public static void main(String[] args) {
    LoginView loginView = new LoginView();
    MainMenuView mainMenuView = new MainMenuView();

    while (true) {
        User loggedInUser = loginView.login();

        if (loggedInUser != null) {
            // This starts the dashboard loop
            mainMenuView.showMenu(loggedInUser);
        }
    }
}