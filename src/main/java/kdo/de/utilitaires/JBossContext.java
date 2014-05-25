package kdo.de.utilitaires;

import java.util.Properties; 
import javax.naming.*; 

public class JBossContext {
	public static Context getInitialContext() throws NamingException{ 
		Properties prop = new Properties();
		prop.put(Context.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
		prop.put(Context.URL_PKG_PREFIXES,"org.jboss.naming:org.jnp.interfaces");
	// Par deÌ�faut, le serveur Jboss utilize le port 1099 
		prop.put(Context.PROVIDER_URL,"jnp://localhost:1099");
		return new InitialContext(prop); 
	}
}