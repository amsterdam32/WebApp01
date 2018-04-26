package accounts;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionIdProfile;

    public AccountService(){
        loginToProfile = new HashMap<>();
        sessionIdProfile = new HashMap<>();
    }

    public void addNewUser(UserProfile userProfile){
        loginToProfile.put(userProfile.getLogin(), userProfile);
    }

    public UserProfile getUserByLogin(String loging) {
        return loginToProfile.get(loging);
    }

    public UserProfile getUserBySessionId(String sessionId){
        return sessionIdProfile.get(sessionId);
    }

    public void addSession(String sessionId, UserProfile userProfile) {
        sessionIdProfile.put(sessionId, userProfile);
    }

    public void deleteSession(String sessionId) {
        sessionIdProfile.remove(sessionId);
    }

}
