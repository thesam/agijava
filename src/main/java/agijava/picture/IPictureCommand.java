package agijava.picture;



public interface IPictureCommand {

	void run(IPicture picture, int currentByte);

	boolean needsArguments();

}
