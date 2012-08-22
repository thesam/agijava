package agijava.main;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import agijava.main.IViewRepository;
import agijava.main.impl.AnimatedObject;
import agijava.main.impl.Position;
import agijava.view.IView;

public class AnimatedObjectTest {

	private AnimatedObject animatedObject;
	private IViewRepository viewRepository;

	private void anAnimatedObject() {
		viewRepository = mock(IViewRepository.class);
		animatedObject = new AnimatedObject(viewRepository);
	}
	
	@Test
	public void canRememberToObserveObjects() throws Exception {
		anAnimatedObject();
		
		assertFalse(animatedObject.getObserveObjects());
		
		animatedObject.setObserveObjects(true);
		
		assertTrue(animatedObject.getObserveObjects());
	}
	
	@Test
	public void canStoreCurrentPosition() throws Exception {
		anAnimatedObject();
		
		Position newPos = new Position(5,5);
		animatedObject.setPosition(newPos);
		
		Position currentPosition = animatedObject.getCurrentPosition();
		
		assertEquals(newPos,currentPosition);
		
	}
	
	@Test
	public void canSetView() throws Exception {
		anAnimatedObject();
		
		IView fakeView = mock(IView.class);
		when(viewRepository.getView(100)).thenReturn(fakeView);
		
		animatedObject.setView(100);
		
		verify(viewRepository).getView(100);
		assertEquals(fakeView,animatedObject.getView());
		
	}
	
}
