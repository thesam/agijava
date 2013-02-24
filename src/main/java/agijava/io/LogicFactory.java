package agijava.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import agijava.logic.Logic;

public class LogicFactory {

	private final char[] MESSAGE_DECRYPTION_STRING = {'A','v','i','s',' ','D','u','r','g','a','n'};
	private final Resource resource;
	
	public LogicFactory(Resource resource) throws IOException {
		this.resource = resource;
	}

	public Logic getLogic() {
		RawByteArray raw = new RawByteArray(resource.getRawData(), 0);
		int messageOffset1 = raw.getNextAndStep();
		int messageOffset2 = raw.getNextAndStep();
		int messageOffset = messageOffset2 << 8 | messageOffset1;
		// This needs a 2 added since the offset is counted starting from AFTER the message offset value itself
		messageOffset = messageOffset+2;
		raw.setStopOffset(messageOffset);
		Map<Integer,String> messages = parseMessages(resource.getRawData(),messageOffset);
		return new Logic(resource.getEntryNumber(),raw,messages);
	}

	//TODO: Find crash reason and fix!
	private Map<Integer,String> parseMessages(List<Integer> rawdata, int messagesSectionOffset) {
		Map<Integer,String> messages = new HashMap<Integer,String>();
		
		int currentOffset = messagesSectionOffset;
		int numberOfMessages = rawdata.get(currentOffset++)&0xff;
		// All the following offsets are relative to the point after the number of messages byte
		messagesSectionOffset++;
		
		int endOfMessagesOffset1 = rawdata.get(currentOffset++)&0xff;
		int endOfMessagesOffset2 = rawdata.get(currentOffset++)&0xff;
		int endMessagesOffset = endOfMessagesOffset2<<8 | endOfMessagesOffset1;
		
		int decodedChars = 0;
		List<Integer> msgOffsets = new ArrayList<Integer>();
		for (int msg = 0; msg < numberOfMessages; msg++) {
			int msgOffset1 = rawdata.get(currentOffset++)&0xff;
			int msgOffset2 = rawdata.get(currentOffset++)&0xff;
			int msgOffset = msgOffset2<<8 | msgOffset1;
			msgOffsets.add(msgOffset);
		}
		for (int msg = 0; msg < numberOfMessages; msg++) {
			int msgOffset = msgOffsets.get(msg);
			if (msgOffset == 0) {
				continue;
			}
			msgOffset += messagesSectionOffset;
			int stopOffset = 0;
			if (msg != numberOfMessages - 1) {
				int nextOffset = 0;
				int offsetSteps = 0;
				while (nextOffset == 0) {
					nextOffset = msgOffsets.get(msg+1+offsetSteps);
					offsetSteps++;
				}
				stopOffset = nextOffset+messagesSectionOffset;
			} else {
				stopOffset =endMessagesOffset + messagesSectionOffset;
			}
			
			String message = "";
			while (msgOffset < stopOffset) {
				int nextEncryptedChar = rawdata.get(msgOffset++)&0xff;
				int decodingChar = MESSAGE_DECRYPTION_STRING[decodedChars % MESSAGE_DECRYPTION_STRING.length];
				int decodedChar = nextEncryptedChar ^ decodingChar;
				message += Character.toString((char)decodedChar);
				decodedChars++;
				if (decodedChar == 0x00) {
					break;
				}
			}
			messages.put(msg+1,message);
		}
		
//		0	 Number of messages
//		1-2	 Pointer to the end of the messages
//		3-4	 Array of message pointers
//		...	 Array of message pointers
//		 ?	 Start of the text data. From this point the messages are encrypted with Avis Durgan (in their unencrypted form, each message is separated by a 0x00 value)

		
		
		
		return messages;
	}

}
