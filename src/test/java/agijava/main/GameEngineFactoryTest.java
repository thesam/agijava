package agijava.main;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameEngineFactoryTest {
	@Test
	public void canBeCreated() throws Exception {
		assertNotNull(new GameEngineFactory());
	}
}
