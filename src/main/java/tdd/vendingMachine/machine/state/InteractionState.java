package tdd.vendingMachine.machine.state;

import com.google.common.collect.Maps;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import tdd.vendingMachine.machine.cli.util.CommandLabelDecorator;

import java.util.List;
import java.util.Map;

@Service
public class InteractionState {

	static final String QUIT = CommandLabelDecorator.keyLegend("q", "quit");

	enum StateName {
		HELLO,
		PAYING,
		PICKING,
		CANCEL
	}

	private static Map<StateName, Class<? extends State>> states = Maps.newHashMap();

	static {
		states.put(StateName.HELLO, HelloState.class);
	}

	@Getter
	private StateName stateName;

	private ApplicationContext applicationContext;

	@Autowired
	public InteractionState(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
		this.stateName = StateName.HELLO;
	}

	void changeState(StateName stateName) {
		if (this.stateName.equals(stateName)) {
			return;
		}

		doStateTransition(stateName);
	}

	public List<String> getDescription() {
		return getStateService().getDescription();
	}

	public void executeCommand(String command) {
		getStateService().executeCommand(command, this);
	}

	private State getStateService() {
		return getStateService(this.getStateName());
	}

	private State getStateService(StateName stateName) {
		return applicationContext.getBean(states.get(stateName));
	}

	private void doStateTransition(StateName futureStateName) {
		this.stateName = futureStateName;
	}

}