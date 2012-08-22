package agijava.main;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import agijava.main.impl.ObjectUpdater;
import agijava.main.impl.Position;
import static org.mockito.Mockito.*;

public class ObjectUpdaterTest {
	private ObjectUpdater objectUpdater;
	private IGameState gameState;
	private IAnimatedObject object;
	
	@Before
	public void setup() {
		object = mock(IAnimatedObject.class);
		gameState = mock(IGameState.class);
		objectUpdater = new ObjectUpdater(gameState, null);
	}

	@Test	public void canBeCreated() throws Exception {
		assertNotNull(objectUpdater);
	}
	
	@Test
	public void canStopMovingObjectIfDestinationIsReached() throws Exception {
		IAnimatedObject object = mock(IAnimatedObject.class);
		
		Position position1 = new Position(0, 0);
		
		when(object.getCurrentPosition()).thenReturn(position1);
		when(object.getDestination()).thenReturn(position1);
		when(object.isMovingToDestination()).thenReturn(true);
		
		objectUpdater.stopIfDestinationIsReached(object);
		
		verify(object).setMoving(false);
		verify(object).setMovingToDestination(false);
	}
	
	@Test
	public void doesNotStopObjectIfDestinationIsNotReached() throws Exception {
		Position position1 = new Position(0, 0);
		Position position2 = new Position(0, 1);
		
		when(object.getCurrentPosition()).thenReturn(position1);
		when(object.getDestination()).thenReturn(position2);
		when(object.isMovingToDestination()).thenReturn(true);
		
		objectUpdater.stopIfDestinationIsReached(object);
		
		verify(object,never()).setMoving(false);
		verify(object,never()).setMovingToDestination(false);
	}
	
	@Test
	public void doesNotStopObjectsThatAreNotMovingToDestination() throws Exception {
		when(object.isMovingToDestination()).thenReturn(false);
		
		objectUpdater.stopIfDestinationIsReached(object);
		
		verify(object,never()).setMoving(false);
		verify(object,never()).setMovingToDestination(false);
	}
	
//	@Test
//	public void canUpdateSingleLoopObject() throws Exception {
//		when(object.isInSingleloop()).thenReturn(true);
//		
//		objectUpdater.updateSingleLoop(object);
//	}
	
//	@Test
//	public void canUpdateCel() throws Exception {
//		objectUpdater.updateLoopAndCel(object)
//	}
	
}
