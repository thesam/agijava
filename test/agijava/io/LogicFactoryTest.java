package agijava.io;

import static org.junit.Assert.*;

import org.junit.Test;

public class LogicFactoryTest {
@Test
public void canBeCreated() throws Exception {
	LogicFactory logicFactory = new LogicFactory(null);
	assertNotNull(logicFactory);
}
}
