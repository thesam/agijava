package agijava.io;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import agijava.picture.IPicture;
import agijava.picture.IPictureCommand;
import agijava.picture.impl.PictureCommandFactory;

public class PictureFactoryTest {
	private PictureFactory pictureFactory;
	private ArrayList<Integer> rawByteArray;
	private PictureCommandFactory cmdFactory;

	@Before
	public void setup() throws Exception {
		Resource resource = mock(Resource.class);
		cmdFactory = mock(PictureCommandFactory.class);
		
		rawByteArray = new ArrayList<Integer>();
		when(resource.getRawData()).thenReturn(rawByteArray);
		pictureFactory = new PictureFactory(resource,cmdFactory);
	}
	
	@Test
	public void canBeCreated() throws Exception {
		assertNotNull(pictureFactory);
	}
	
	@Test
	public void canGetPictureWithNoCommands() throws Exception {
		IPicture picture = pictureFactory.getPicture();
		
		assertNotNull(picture);
	}
	
	@Test
	public void canGetPictureWithOneCommandWithNoArguments() throws Exception {
		IPictureCommand cmd = mock(IPictureCommand.class);
		when(cmd.needsArguments()).thenReturn(false);
		
		rawByteArray.add(0x55);
		when(cmdFactory.isCommandNumber(0x55)).thenReturn(true);
		when(cmdFactory.getPictureCommand(0x55)).thenReturn(cmd);
		
		IPicture picture = pictureFactory.getPicture();
		
		assertNotNull(picture);
		verify(cmd).run(picture, 0x55);
	}
	
	@Test
	public void canGetPictureWithOneCommandWithOneArgumentByte() throws Exception {
		IPictureCommand cmd = mock(IPictureCommand.class);
		when(cmd.needsArguments()).thenReturn(true).thenReturn(false);
		
		rawByteArray.add(0x55);
		when(cmdFactory.isCommandNumber(0x55)).thenReturn(true);
		when(cmdFactory.getPictureCommand(0x55)).thenReturn(cmd);
		rawByteArray.add(0x66);
		
		IPicture picture = pictureFactory.getPicture();
		assertNotNull(picture);
		verify(cmd).run(picture, 0x66);
		
	}
	
	@Test
	public void ignoresBytesAfterFF() throws Exception {
		rawByteArray.add(0xff);
		rawByteArray.add(0x55);
		
		IPicture picture = pictureFactory.getPicture();
		
		assertNotNull(picture);
		verifyZeroInteractions(cmdFactory);;
	}
}
