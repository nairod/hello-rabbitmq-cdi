package ch.flask.rabbit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.ejb.Singleton;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;

@Singleton
@Named
public class EventDemoBean {
	private String receivedEvent;
	@Inject
	private Event<EventOne> eventDispatcher;

	public String submitEvent() throws InterruptedException {

		EventOne eventOne = new EventOne();
		eventOne.setValue("payload");
		eventDispatcher.fire(eventOne);

		return receivedEvent;
	}

	public void receiveEvent(@Observes EventTwo eventTwo) {
		receivedEvent = eventTwo.getValue();
	}

	public String getReceivedEvent() {
		return receivedEvent;
	}
}