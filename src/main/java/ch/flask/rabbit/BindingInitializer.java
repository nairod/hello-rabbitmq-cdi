package ch.flask.rabbit;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.ejb.Singleton;

import org.slf4j.LoggerFactory;

@Singleton
@Startup
public class BindingInitializer {
	@Inject
	private RabbitBinder binder;

	@PostConstruct
	public void initialize() {
		try {
			binder.configuration() //
					.addHost("localhost") //
					.setUsername("dori") //
					.setPassword("dori");
			binder.initialize();
		} catch (IOException e) {
			LoggerFactory.getLogger(getClass()).error("Unable to initialize", e);
		}
	}
}