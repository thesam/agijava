package agijava.logic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import agijava.io.RawByteArray;
import agijava.logic.ILogic;
import agijava.logic.commands.*;
import agijava.main.ILogicCommand;

public class Logic implements ILogic {

	private RawByteArray raw;
	private Map<Integer, ILogicCommand> commandMap = new HashMap<Integer, ILogicCommand>();
	private int startOffset;
	private Map<Integer,String> messages;
	private int entryNumber;

	public Logic(int logicNumber, RawByteArray raw, Map<Integer, String> messages) {
		initCommandMap();
		this.raw = raw;
		this.messages = messages;
		this.entryNumber = logicNumber;
		startOffset = raw.getNextOffsetToBeRead();
	}

	private void initCommandMap() {
		commandMap.put(0x00, new ReturnCommand());
		commandMap.put(0x01, new IncrementCommand());
		commandMap.put(0x02, new DecrementCommand());
		commandMap.put(0x03, new AssignnCommand());
		commandMap.put(0x04, new AssignvCommand());
		commandMap.put(0x05, new AddnCommand());
		commandMap.put(0x06, new AddvCommand());
		commandMap.put(0x07, new SubnCommand());
		commandMap.put(0x08, new SubvCommand());
		commandMap.put(0x09, new LindirectvCommand());
		commandMap.put(0x0A, new RindirectCommand());
		commandMap.put(0x0B, new LindirectnCommand());
		commandMap.put(0x0C, new SetCommand());
		commandMap.put(0x0D, new ResetCommand());
		commandMap.put(0x0E, new ToggleCommand());
		commandMap.put(0x0F, new SetvCommand());
		commandMap.put(0x10, new ResetvCommand());
		commandMap.put(0x11, new TogglevCommand());
		commandMap.put(0x12, new NewRoomCommand());
		commandMap.put(0x13, new NewRoomvCommand());
		commandMap.put(0x14, new LoadLogicsCommand());
		commandMap.put(0x15, new LoadLogicsvCommand());
		commandMap.put(0x16, new CallCommand());
		commandMap.put(0x17, new CallvCommand());
		commandMap.put(0x18, new LoadPicCommand());
		commandMap.put(0x19, new DrawPicCommand());
		commandMap.put(0x1A, new ShowPicCommand());
		commandMap.put(0x1B, new DiscardPicCommand());
		commandMap.put(0x1C, new OverlayPicCommand());
		commandMap.put(0x1D, new ShowPriScreenCommand());
		commandMap.put(0x1E, new LoadViewCommand());
		commandMap.put(0x1F, new LoadViewvCommand());
		commandMap.put(0x20, new DiscardViewCommand());
		commandMap.put(0x21, new AnimateObjCommand());
		commandMap.put(0x22, new UnanimateAllCommand());
		commandMap.put(0x23, new DrawCommand());
		commandMap.put(0x24, new EraseCommand());
		commandMap.put(0x25, new PositionCommand());
		commandMap.put(0x26, new PositionvCommand());
		commandMap.put(0x27, new GetPosnCommand());
		commandMap.put(0x28, new RepositionCommand());
		commandMap.put(0x29, new SetViewCommand());
		commandMap.put(0x2A, new SetViewvCommand());
		commandMap.put(0x2B, new SetLoopCommand());
		commandMap.put(0x2C, new SetLoopvCommand());
		commandMap.put(0x2D, new FixLoopCommand());
		commandMap.put(0x2E, new ReleaseLoopCommand());
		commandMap.put(0x2F, new SetCelCommand());
		commandMap.put(0x30, new SetCelvCommand());
		commandMap.put(0x31, new LastCelCommand());
		commandMap.put(0x32, new CurrentCelCommand());
		commandMap.put(0x33, new CurrentLoopCommand());
		commandMap.put(0x34, new CurrentViewCommand());
		commandMap.put(0x35, new NumberOfLoopsCommand());
		commandMap.put(0x36, new SetPriorityCommand());
		commandMap.put(0x37, new SetPriorityvCommand());
		commandMap.put(0x38, new ReleasePriorityCommand());
		commandMap.put(0x39, new GetPriorityCommand());
		commandMap.put(0x3A, new StopUpdateCommand());
		commandMap.put(0x3B, new StartUpdateCommand());
		commandMap.put(0x3C, new ForceUpdateCommand());
		commandMap.put(0x3D, new IgnoreHorizonCommand());
		commandMap.put(0x3E, new ObserveHorizonCommand());
		commandMap.put(0x3F, new SetHorizonCommand());
		commandMap.put(0x40, new ObjectOnWaterCommand());
		commandMap.put(0x41, new ObjectOnLandCommand());
		commandMap.put(0x42, new ObjectOnAnythingCommand());
		commandMap.put(0x43, new IgnoreObjsCommand());
		commandMap.put(0x44, new ObserveObjsCommand());
		commandMap.put(0x45, new DistanceCommand());
		commandMap.put(0x46, new StopCyclingCommand());
		commandMap.put(0x47, new StartCyclingCommand());
		commandMap.put(0x48, new NormalCycleCommand());
		commandMap.put(0x49, new EndOfLoopCommand());
		commandMap.put(0x4A, new ReverseCycleCommand());
		commandMap.put(0x4B, new ReverseLoopCommand());
		commandMap.put(0x4C, new CycleTimeCommand());
		commandMap.put(0x4D, new StopMotionCommand());
		commandMap.put(0x4E, new StartMotionCommand());
		commandMap.put(0x4F, new StepSizeCommand());
		commandMap.put(0x50, new StepTimeCommand());
		commandMap.put(0x51, new MoveObjCommand());
		commandMap.put(0x52, new MoveObjvCommand());
		commandMap.put(0x53, new FollowEgoCommand());
		commandMap.put(0x54, new WanderCommand());
		commandMap.put(0x55, new NormalMotionCommand());
		commandMap.put(0x56, new SetDirCommand());
		commandMap.put(0x57, new GetDirCommand());
		commandMap.put(0x58, new IgnoreBlocksCommand());
		commandMap.put(0x59, new ObserveBlocksCommand());
		commandMap.put(0x5A, new BlockCommand());
		commandMap.put(0x5B, new UnblockCommand());
		commandMap.put(0x5C, new GetCommand());
		commandMap.put(0x5D, new GetvCommand());
		commandMap.put(0x5E, new DropCommand());
		commandMap.put(0x5F, new PutCommand());
		commandMap.put(0x60, new PutvCommand());
		commandMap.put(0x61, new GetRoomvCommand());
		commandMap.put(0x62, new LoadSoundCommand());
		commandMap.put(0x63, new SoundCommand());
		commandMap.put(0x64, new StopSoundCommand());
		commandMap.put(0x65, new PrintCommand());
		commandMap.put(0x66, new PrintvCommand());
		commandMap.put(0x67, new DisplayCommand());
		commandMap.put(0x68, new DisplayvCommand());
		commandMap.put(0x69, new ClearLinesCommand());
		commandMap.put(0x6A, new TextScreenCommand());
		commandMap.put(0x6B, new GraphicsCommand());
		commandMap.put(0x6C, new SetCursorCommand());
		commandMap.put(0x6D, new SetTextAttributeCommand());
		commandMap.put(0x6E, new ShakeScreenCommand());
		commandMap.put(0x6F, new ConfigureScreenCommand());
		commandMap.put(0x70, new StatusLineOnCommand());
		commandMap.put(0x71, new StatusLineOffCommand());
		commandMap.put(0x72, new SetStringCommand());
		commandMap.put(0x73, new GetStringCommand());
		commandMap.put(0x74, new WordToStringCommand());
		commandMap.put(0x75, new ParseCommand());
		commandMap.put(0x76, new GetNumCommand());
		commandMap.put(0x77, new PreventInputCommand());
		commandMap.put(0x78, new AcceptInputCommand());
		commandMap.put(0x79, new SetKeyCommand());
		commandMap.put(0x7A, new AddToPicCommand());
		commandMap.put(0x7B, new AddToPicvCommand());
		commandMap.put(0x7C, new StatusCommand());
		commandMap.put(0x7D, new SaveGameCommand());
		commandMap.put(0x7E, new RestoreGameCommand());
		commandMap.put(0x7F, new InitDiskCommand());
		commandMap.put(0x80, new RestartGameCommand());
		commandMap.put(0x81, new ShowObjCommand());
		commandMap.put(0x82, new RandomCommand());
		commandMap.put(0x83, new ProgramControlCommand());
		commandMap.put(0x84, new PlayerControlCommand());
		commandMap.put(0x85, new ObjStatusvCommand());
		commandMap.put(0x86, new QuitCommand());
		commandMap.put(0x87, new ShowMemCommand());
		commandMap.put(0x88, new PauseCommand());
		commandMap.put(0x89, new EchoLineCommand());
		commandMap.put(0x8A, new CancelLineCommand());
		commandMap.put(0x8B, new InitJoyCommand());
		commandMap.put(0x8C, new ToggleMonitorCommand());
		commandMap.put(0x8D, new VersionCommand());
		commandMap.put(0x8E, new ScriptSizeCommand());
		commandMap.put(0x8F, new SetGameIdCommand());
		commandMap.put(0x90, new LogCommand());
		commandMap.put(0x91, new SetScanStartCommand());
		commandMap.put(0x92, new ResetScanStartCommand());
		commandMap.put(0x93, new RepositionToCommand());
		commandMap.put(0x94, new RepositionTovCommand());
		commandMap.put(0x95, new TraceOnCommand());
		commandMap.put(0x96, new TraceInfoCommand());
		commandMap.put(0x97, new PrintAtCommand());
		commandMap.put(0x98, new PrintAtvCommand());
		commandMap.put(0x99, new DiscardViewvCommand());
		commandMap.put(0x9A, new ClearTextRectCommand());
		commandMap.put(0x9B, new SetUpperLeftCommand());
		commandMap.put(0x9C, new SetMenuCommand());
		commandMap.put(0x9D, new SetMenuMemberCommand());
		commandMap.put(0x9E, new SubmitMenuCommand());
		commandMap.put(0xA0, new DisableMemberCommand());
		commandMap.put(0x9F, new EnableMemberCommand());
		commandMap.put(0xA1, new MenuInputCommand());
		commandMap.put(0xA2, new ShowObjvCommand());
		commandMap.put(0xA3, new OpenDialogueCommand());
		commandMap.put(0xA4, new CloseDialogueCommand());
		commandMap.put(0xA5, new MulnCommand());
		commandMap.put(0xA6, new MulvCommand());
		commandMap.put(0xA7, new DivnCommand());
		commandMap.put(0xA8, new DivvCommand());
		commandMap.put(0xA9, new CloseWindowCommand());
		commandMap.put(0xAA, new SetSimpleCommand());
		commandMap.put(0xAB, new PushScriptCommand());
		commandMap.put(0xAC, new PopScriptCommand());
		commandMap.put(0xAD, new HoldKeyCommand());
		commandMap.put(0xAE, new SetPriBaseCommand());
		commandMap.put(0xAF, new DiscardSoundCommand());
		commandMap.put(0xB0, new HideMouseCommand());
		commandMap.put(0xB1, new AllowMenuCommand());
		commandMap.put(0xB2, new ShowMouseCommand());
		commandMap.put(0xB3, new FenceMouseCommand());
		commandMap.put(0xB4, new MousePosnCommand());
		commandMap.put(0xB5, new ReleaseKeyCommand());
		commandMap.put(0xB6, new AdjEgoMoveToXYCommand());
		commandMap.put(0xFE, new GotoCommand());
		commandMap.put(0xFF, new IfCommand());
	}

	@Override
	public ILogicCommand getNextCommand() {
		int nextByte = raw.getNextAndStep();
		ILogicCommand command = getCommand(nextByte);
		feedArgumentsToCommand(command);
		return command;
	}

	private void feedArgumentsToCommand(ILogicCommand command) {
		while (!command.hasAllNeededArgs()) {
			feedBytesToCommand(command);
		}
	}

	private void feedBytesToCommand(ILogicCommand command) {
		int numberOfArgs = command.getArgsSizeInBytes();
		List<Integer> args = raw.getBytes(numberOfArgs);
		command.setArgs(args);
	}

	private ILogicCommand getCommand(int nextByte) {
		ILogicCommand command = commandMap.get(nextByte);
//		System.err.println(command.getClass());
		command.reset();
		return command;
	}

	@Override
	public void increaseOffset(int blockSize) {
		raw.step(blockSize);
		
	}

	@Override
	public void reset() {
		raw.setOffset(startOffset);
	}

	@Override
	public String getMessage(int messageNo) {
		return messages.get(messageNo);
	}

	@Override
	public int getCurrentOffset() {
		return raw.getNextOffsetToBeRead();
	}

	@Override
	public int getEntryNumber() {
		return entryNumber;
	}

	@Override
	public void setOffset(int scanStart) {
		raw.setOffset(scanStart);
	}

}
