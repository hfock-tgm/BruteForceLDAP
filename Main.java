import javax.naming.*;
import javax.naming.directory.*;

import java.util.Hashtable;

/**
 * Demonstrates how to create an initial context to an LDAP server
 * using simple authentication.
 *
 * usage: java Simple
 */
class Simple {
    public static final String HOST = "ldap://192.168.56.101:389";
    public static final String AUTH_METHOD = "simple";
    public static final String USER_NAME = "USER";
    public static final String USER_DATA = "cn=" + USER_NAME + ",dc=nodomain,dc=com";
    public static final String USER_PASSWORD = "PASSWORD";
    public static void main(String[] args) {


        // Set up environment for creating initial context
        Hashtable<String, Object> env = new Hashtable<String, Object>(11);

        env.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, HOST);
        // Authenticate as hagen.fock and password "1234"
        env.put(Context.SECURITY_AUTHENTICATION, AUTH_METHOD);
        env.put(Context.SECURITY_PRINCIPAL, USER_DATA);
        env.put(Context.SECURITY_CREDENTIALS, USER_PASSWORD);

        try {
            // Create initial context
            DirContext ctx = new InitialDirContext(env);

            System.out.println("Authentifizierung OK");

            // Close the context when we're done
            ctx.close();
        } catch (NamingException e) {
            e.printStackTrace();
            System.out.println("Authentifizierung NOK");
        }
    }
}