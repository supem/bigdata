package de.innosystec.xinxichaxun.unpack.ppm;import de.innosystec.xinxichaxun.io.Raw;public class FreqData extends Pointer{  public static final int size = 6;  public FreqData(byte[] mem)  {    super(mem);  }  public FreqData init(byte[] mem) {    this.mem = mem;    this.pos = 0;    return this;  }  public int getSummFreq() {    return Raw.readShortLittleEndian(this.mem, this.pos) & 0xFFFF;  }  public void setSummFreq(int summFreq) {    Raw.writeShortLittleEndian(this.mem, this.pos, (short)summFreq);  } public SEE2Context[][] getSEE2Cont()  {    return this.SEE2Cont;  }  public SEE2Context getDummySEE2Cont()  {    return this.dummySEE2Cont;  }  public int getInitRL()  {    return this.initRL;  }} public void init(OutputStream outputStream) {    this.outputStream = outputStream;    this.unpPackedSize = 0L;    this.testMode = false;    this.skipUnpCRC = false;    this.packVolume = false;    this.unpVolume = false;    this.nextVolumeMissing = false;    this.encryption = 0;    this.decryption = 0;    this.totalPackRead = 0L;    this.curPackRead = (this.curPackWrite = this.curUnpRead = this.curUnpWrite = 0L);    this.packFileCRC = (this.unpFileCRC = this.packedCRC = -1L);    this.lastPercent = -1;    this.subHead = null;    this.currentCommand = '\000';    this.processedArcSize = (this.totalArcSize = 0L);  }private long curPackWrite;  private long curUnpRead;  private long curUnpWrite;  private long processedArcSize;  private long totalArcSize;  private long packFileCRC;  private long unpFileCRC;  private long packedCRC;  private int encryption;  private int decryption;  private int lastPercent;  private char currentCommand;  public ComprDataIO(Archive arc)  {    this.archive = arc;  }      if (this.unpBlockType == BlockTypes.BLOCK_PPM) {        int Ch = this.ppm.decodeChar();        if (Ch == -1) {          this.ppmError = true;          break;        }        if (Ch == this.ppmEscChar) {          int NextCh = this.ppm.decodeChar();          if (NextCh == 0) {            if (!readTables())              break;          }  public void setCurPackRead(long curPackRead)  {    this.curPackRead = curPackRead;  }  public long getCurPackWrite()  {    return this.curPackWrite;  }  public void setCurPackWrite(long curPackWrite)  {    this.curPackWrite = curPackWrite;  }  public long getCurUnpRead()  {    return this.curUnpRead;  }  public void setCurUnpRead(long curUnpRead)  {    this.curUnpRead = curUnpRead;  }  public long getCurUnpWrite()  {    return this.curUnpWrite;  }  public void setCurUnpWrite(long curUnpWrite)  {    this.curUnpWrite = curUnpWrite;  }  public int getDecryption()  {    return this.decryption;  }  public void setDecryption(int decryption)  {    this.decryption = decryption;  }  public int getEncryption()  {    return this.encryption;  }  public void setEncryption(int encryption)  {    this.encryption = encryption;  }  public boolean isNextVolumeMissing()  {    return this.nextVolumeMissing;  }  public void setNextVolumeMissing(boolean nextVolumeMissing)  {    this.nextVolumeMissing = nextVolumeMissing;  }  public long getPackedCRC() {    return this.packedCRC;  }  public void setPackedCRC(long packedCRC) {    this.packedCRC = packedCRC;  }  public long getPackFileCRC()  {  return this.packFileCRC;  }}  protected int wrPtr;  protected int oldDistPtr;  protected int[] ChSet = new int[256]; protected int[] ChSetA = new int[256];  protected int[] ChSetB = new int[256]; protected int[] ChSetC = new int[256];  protected int[] Place = new int[256]; protected int[] PlaceA = new int[256];  protected int[] PlaceB = new int[256]; protected int[] PlaceC = new int[256];  protected int[] NToPl = new int[256]; protected int[] NToPlB = new int[256];  protected int[] NToPlC = new int[256];  protected int FlagBuf;  protected int AvrPlc;  protected int AvrPlcB;  protected int AvrLn1;  protected int AvrLn2;  protected int AvrLn3;  protected int Buf60;  protected int NumHuf;  protected int StMode;  protected int LCount;  protected int FlagsCnt;  protected int Nhfb;  public FileHeader nextFileHeader()    int n = this.headers.size();    while (this.currentHeaderIndex < n) {      BaseBlock block = (BaseBlock)this.headers.get(this.currentHeaderIndex++);    return null;  }  public xinxichaxunCallback getxinxichaxunCallback()    return this.xinxichaxunCallback;  }  public boolean isEncrypted()  {    if (this.newMhd != null) {      return this.newMhd.isEncrypted();    }    throw new NullPointerException("mainheader is null");  }  private void readHeaders()    throws IOException, RarException  {    this.markHead = null;    this.newMhd = null;    this.endHeader = null;    this.headers.clear();    this.currentHeaderIndex = 0;    int toRead = 0;    long fileLength = this.file.length();    while (true)    {      int size = 0;      long newpos = 0L;      byte[] baseBlockBuffer = new byte[7];      long position = this.rof.getPosition();      if (position >= fileLength)      {        break;      }      size = this.rof.readFully(baseBlockBuffer, 7);      if (size == 0) {        break;      }      BaseBlock block = new BaseBlock(baseBlockBuffer);      block.setPositionInFile(position);      switch ($SWITCH_TABLE$de$innosystec$xinxichaxun$rarfile$xinxichaxunHeadertype()[block.getHeaderType().ordinal()])      {      case 2:        this.markHead = new MarkHeader(block);        if (!this.markHead.isSignature()) {          throw new RarException(            RarException.RarExceptionType.badRarArchive);        }        this.headers.add(this.markHead);        break;      case 1:        int mainHeaderSize = 0;        toRead = block.hasEncryptVersion() ?           7 :           6;        byte[] mainbuff = new byte[toRead];        mainHeaderSize = this.rof.readFully(mainbuff, toRead);        MainHeader mainhead = new MainHeader(block, mainbuff);        case 8:        }        break;        throw new RarException(RarException.RarExceptionType.crcError);      }    }    catch (Exception e)    {      this.unpack.cleanUp();      if ((e instanceof RarException))      {        throw ((RarException)e);      }      throw new RarException(e);    }  }public void setCurUnpRead(long curUnpRead)  {    this.curUnpRead = curUnpRead;  }  public long getCurUnpWrite()  {    return this.curUnpWrite;  } protected void makeDecodeTables(byte[] lenTab, int offset, Decode dec, int size)  {  private final State tempState2 = new State(null);  private final State tempState3 = new State(null);  private final State tempState4 = new State(null);  private final StateRef tempStateRef1 = new StateRef();  private final StateRef tempStateRef2 = new StateRef();  private final PPMContext tempPPMContext1 = new PPMContext(null);  private final PPMContext tempPPMContext2 = new PPMContext(null);  private final PPMContext tempPPMContext3 = new PPMContext(null);  private final PPMContext tempPPMContext4 = new PPMContext(null);  private final int[] ps = new int[64];  public ModelPPM()  {    this.minContext = null;    this.maxContext = null;    this.medContext = null;  }  public void setPackFileCRC(long packFileCRC)  {    this.packFileCRC = packFileCRC;  }  public boolean isPackVolume()  {    return this.packVolume;  }  public void setPackVolume(boolean packVolume)  {    this.packVolume = packVolume;  }  public long getProcessedArcSize()  {    return this.processedArcSize;  }  public void setProcessedArcSize(long processedArcSize)  {    this.processedArcSize = processedArcSize;  }  public long getTotalArcSize()  {    return this.totalArcSize;  }  public void setTotalArcSize(long totalArcSize)  {    this.totalArcSize = totalArcSize;  }  public long getTotalPackRead()  {    return this.totalPackRead;  }  public void setTotalPackRead(long totalPackRead)  {    this.totalPackRead = totalPackRead;  }  public long getUnpArcSize()  {    return this.unpArcSize;  }  public void setUnpArcSize(long unpArcSize)  {    this.unpArcSize = unpArcSize;  }  public long getUnpFileCRC()  {    return this.unpFileCRC;  }  public void setUnpFileCRC(long unpFileCRC)  {    this.unpFileCRC = unpFileCRC;  }  public boolean isUnpVolume()  {    return this.unpVolume;  }  public void setUnpVolume(boolean unpVolume)  {    this.unpVolume = unpVolume;  }  public FileHeader getSubHeader()  {    return this.subHead;  }}