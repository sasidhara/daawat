package Daawat.Feedback;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class asyncstreamsobject extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "Daawat.Feedback.asyncstreamsobject");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", Daawat.Feedback.asyncstreamsobject.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public Object _target = null;
public String _event = "";
public anywheresoftware.b4a.randomaccessfile.AsyncStreams _astream = null;
public String _tempfolder = "";
public anywheresoftware.b4a.objects.collections.Map _filetokey = null;
public byte _type_object = (byte)0;
public byte _type_bitmap = (byte)0;
public byte _type_file = (byte)0;
public boolean _waitingforanotherstream = false;
public String _waitingforkey = "";
public String _waitingforname = "";
public byte _waitingfortype = (byte)0;
public boolean _connected = false;
public Daawat.Feedback.main _main = null;
public String  _astream_error() throws Exception{
 //BA.debugLineNum = 156;BA.debugLine="Private Sub astream_Error";
 //BA.debugLineNum = 157;BA.debugLine="Log(\"astream_Error: \" & LastException)";
__c.LogImpl("52818049","astream_Error: "+BA.ObjectToString(__c.LastException(getActivityBA())),0);
 //BA.debugLineNum = 158;BA.debugLine="astream.Close";
_astream.Close();
 //BA.debugLineNum = 159;BA.debugLine="astream_Terminated 'manually call the terminated";
_astream_terminated();
 //BA.debugLineNum = 160;BA.debugLine="End Sub";
return "";
}
public String  _astream_newdata(byte[] _buffer) throws Exception{
String _filename = "";
String _key = "";
 //BA.debugLineNum = 139;BA.debugLine="Private Sub astream_NewData (Buffer() As Byte)";
 //BA.debugLineNum = 140;BA.debugLine="Dim fileName As String = BytesToString(Buffer, 0,";
_filename = __c.BytesToString(_buffer,(int) (0),_buffer.length,"UTF8");
 //BA.debugLineNum = 141;BA.debugLine="File.Delete(TempFolder, fileName)";
__c.File.Delete(_tempfolder,_filename);
 //BA.debugLineNum = 142;BA.debugLine="File.Delete(TempFolder, \"~\" & fileName) 'delete t";
__c.File.Delete(_tempfolder,"~"+_filename);
 //BA.debugLineNum = 143;BA.debugLine="Dim key As String = FileToKey.Remove(fileName)";
_key = BA.ObjectToString(_filetokey.Remove((Object)(_filename)));
 //BA.debugLineNum = 144;BA.debugLine="CallSub2(target, event & \"_ObjectSent\", key)";
__c.CallSubNew2(ba,_target,_event+"_ObjectSent",(Object)(_key));
 //BA.debugLineNum = 145;BA.debugLine="End Sub";
return "";
}
public String  _astream_newstream(String _dir,String _filename) throws Exception{
Object _value = null;
boolean _deletefile = false;
anywheresoftware.b4a.randomaccessfile.RandomAccessFile _raf = null;
 //BA.debugLineNum = 102;BA.debugLine="Private Sub astream_NewStream (Dir As String, File";
 //BA.debugLineNum = 103;BA.debugLine="Dim value As Object";
_value = new Object();
 //BA.debugLineNum = 104;BA.debugLine="Dim deleteFile As Boolean = True";
_deletefile = __c.True;
 //BA.debugLineNum = 105;BA.debugLine="If waitingForAnotherStream Then";
if (_waitingforanotherstream) { 
 //BA.debugLineNum = 106;BA.debugLine="waitingForAnotherStream = False";
_waitingforanotherstream = __c.False;
 //BA.debugLineNum = 108;BA.debugLine="Select waitingForType";
switch (BA.switchObjectToInt(_waitingfortype,_type_bitmap,_type_file)) {
case 0: {
 //BA.debugLineNum = 110;BA.debugLine="value = LoadBitmap(Dir, FileName)";
_value = (Object)(__c.LoadBitmap(_dir,_filename).getObject());
 break; }
case 1: {
 //BA.debugLineNum = 112;BA.debugLine="value = FileName 'just return the file name";
_value = (Object)(_filename);
 //BA.debugLineNum = 113;BA.debugLine="deleteFile = False";
_deletefile = __c.False;
 break; }
}
;
 }else {
 //BA.debugLineNum = 116;BA.debugLine="Dim raf As RandomAccessFile";
_raf = new anywheresoftware.b4a.randomaccessfile.RandomAccessFile();
 //BA.debugLineNum = 117;BA.debugLine="raf.Initialize(Dir, FileName, True)";
_raf.Initialize(_dir,_filename,__c.True);
 //BA.debugLineNum = 118;BA.debugLine="waitingForKey = raf.ReadObject(raf.CurrentPositi";
_waitingforkey = BA.ObjectToString(_raf.ReadObject(_raf.CurrentPosition));
 //BA.debugLineNum = 119;BA.debugLine="waitingForName = raf.ReadObject(raf.CurrentPosit";
_waitingforname = BA.ObjectToString(_raf.ReadObject(_raf.CurrentPosition));
 //BA.debugLineNum = 120;BA.debugLine="waitingForType = raf.ReadSignedByte(raf.CurrentP";
_waitingfortype = _raf.ReadSignedByte(_raf.CurrentPosition);
 //BA.debugLineNum = 121;BA.debugLine="Select waitingForType";
switch (BA.switchObjectToInt(_waitingfortype,_type_object,_type_file,_type_bitmap)) {
case 0: {
 //BA.debugLineNum = 123;BA.debugLine="value = raf.ReadObject(raf.CurrentPosition)";
_value = _raf.ReadObject(_raf.CurrentPosition);
 //BA.debugLineNum = 124;BA.debugLine="raf.Close";
_raf.Close();
 break; }
case 1: 
case 2: {
 //BA.debugLineNum = 127;BA.debugLine="waitingForAnotherStream = True";
_waitingforanotherstream = __c.True;
 //BA.debugLineNum = 128;BA.debugLine="raf.Close";
_raf.Close();
 //BA.debugLineNum = 129;BA.debugLine="File.delete(Dir, FileName)";
__c.File.Delete(_dir,_filename);
 //BA.debugLineNum = 130;BA.debugLine="Return '<--- return!!!";
if (true) return "";
 break; }
}
;
 };
 //BA.debugLineNum = 133;BA.debugLine="waitingForAnotherStream = False";
_waitingforanotherstream = __c.False;
 //BA.debugLineNum = 134;BA.debugLine="If deleteFile Then File.delete(Dir, FileName)";
if (_deletefile) { 
__c.File.Delete(_dir,_filename);};
 //BA.debugLineNum = 135;BA.debugLine="CallSub3(target, event & \"_NewObject\", waitingFor";
__c.CallSubNew3(ba,_target,_event+"_NewObject",(Object)(_waitingforkey),_value);
 //BA.debugLineNum = 136;BA.debugLine="astream.Write(waitingForName.GetBytes(\"UTF8\"))";
_astream.Write(_waitingforname.getBytes("UTF8"));
 //BA.debugLineNum = 137;BA.debugLine="End Sub";
return "";
}
public String  _astream_terminated() throws Exception{
 //BA.debugLineNum = 162;BA.debugLine="Private Sub astream_Terminated";
 //BA.debugLineNum = 163;BA.debugLine="Log(\"astream_Terminated\")";
__c.LogImpl("52883585","astream_Terminated",0);
 //BA.debugLineNum = 164;BA.debugLine="connected = False";
_connected = __c.False;
 //BA.debugLineNum = 165;BA.debugLine="CallSub(target, event & \"_Terminated\")";
__c.CallSubNew(ba,_target,_event+"_Terminated");
 //BA.debugLineNum = 166;BA.debugLine="End Sub";
return "";
}
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 5;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 6;BA.debugLine="Private target As Object";
_target = new Object();
 //BA.debugLineNum = 7;BA.debugLine="Private event As String";
_event = "";
 //BA.debugLineNum = 8;BA.debugLine="Private astream As AsyncStreams";
_astream = new anywheresoftware.b4a.randomaccessfile.AsyncStreams();
 //BA.debugLineNum = 9;BA.debugLine="Public TempFolder As String";
_tempfolder = "";
 //BA.debugLineNum = 10;BA.debugLine="Private FileToKey As Map";
_filetokey = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 11;BA.debugLine="Private TYPE_OBJECT = 1, TYPE_BITMAP = 2, TYPE_FI";
_type_object = (byte) (1);
_type_bitmap = (byte) (2);
_type_file = (byte) (3);
 //BA.debugLineNum = 12;BA.debugLine="Private waitingForAnotherStream As Boolean";
_waitingforanotherstream = false;
 //BA.debugLineNum = 13;BA.debugLine="Private waitingForKey, waitingForName As String";
_waitingforkey = "";
_waitingforname = "";
 //BA.debugLineNum = 14;BA.debugLine="Private waitingForType As Byte";
_waitingfortype = (byte)0;
 //BA.debugLineNum = 15;BA.debugLine="Private connected As Boolean";
_connected = false;
 //BA.debugLineNum = 16;BA.debugLine="End Sub";
return "";
}
public String  _close() throws Exception{
 //BA.debugLineNum = 41;BA.debugLine="Public Sub Close";
 //BA.debugLineNum = 42;BA.debugLine="If astream.IsInitialized Then";
if (_astream.IsInitialized()) { 
 //BA.debugLineNum = 43;BA.debugLine="astream.Close";
_astream.Close();
 //BA.debugLineNum = 44;BA.debugLine="astream_Terminated";
_astream_terminated();
 };
 //BA.debugLineNum = 46;BA.debugLine="End Sub";
return "";
}
public String  _getavailablefile(String _key) throws Exception{
int _i = 0;
 //BA.debugLineNum = 147;BA.debugLine="Private Sub GetAvailableFile (key As String) As St";
 //BA.debugLineNum = 148;BA.debugLine="Dim i As Int = 1";
_i = (int) (1);
 //BA.debugLineNum = 149;BA.debugLine="Do While FileToKey.ContainsKey(\"ao\" & i)";
while (_filetokey.ContainsKey((Object)("ao"+BA.NumberToString(_i)))) {
 //BA.debugLineNum = 150;BA.debugLine="i = i + 1";
_i = (int) (_i+1);
 }
;
 //BA.debugLineNum = 152;BA.debugLine="FileToKey.Put(\"ao\" & i, key)";
_filetokey.Put((Object)("ao"+BA.NumberToString(_i)),(Object)(_key));
 //BA.debugLineNum = 153;BA.debugLine="Return \"ao\" & i";
if (true) return "ao"+BA.NumberToString(_i);
 //BA.debugLineNum = 154;BA.debugLine="End Sub";
return "";
}
public boolean  _getisconnected() throws Exception{
 //BA.debugLineNum = 28;BA.debugLine="Public Sub getIsConnected As Boolean";
 //BA.debugLineNum = 29;BA.debugLine="Return connected";
if (true) return _connected;
 //BA.debugLineNum = 30;BA.debugLine="End Sub";
return false;
}
public String  _initialize(anywheresoftware.b4a.BA _ba,Object _targetmodule,String _eventname) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 18;BA.debugLine="Public Sub Initialize (TargetModule As Object, Eve";
 //BA.debugLineNum = 19;BA.debugLine="target = TargetModule";
_target = _targetmodule;
 //BA.debugLineNum = 20;BA.debugLine="event = EventName";
_event = _eventname;
 //BA.debugLineNum = 21;BA.debugLine="If File.ExternalWritable Then";
if (__c.File.getExternalWritable()) { 
 //BA.debugLineNum = 22;BA.debugLine="TempFolder = File.DirDefaultExternal";
_tempfolder = __c.File.getDirDefaultExternal();
 }else {
 //BA.debugLineNum = 24;BA.debugLine="TempFolder = File.DirInternalCache";
_tempfolder = __c.File.getDirInternalCache();
 };
 //BA.debugLineNum = 26;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4a.randomaccessfile.RandomAccessFile  _sharedwrite(String _key,String[] _outname) throws Exception{
anywheresoftware.b4a.randomaccessfile.RandomAccessFile _raf = null;
String _name = "";
 //BA.debugLineNum = 84;BA.debugLine="Private Sub sharedWrite(Key As String, outName() A";
 //BA.debugLineNum = 85;BA.debugLine="Dim raf As RandomAccessFile";
_raf = new anywheresoftware.b4a.randomaccessfile.RandomAccessFile();
 //BA.debugLineNum = 86;BA.debugLine="Dim name As String = GetAvailableFile(Key)";
_name = _getavailablefile(_key);
 //BA.debugLineNum = 87;BA.debugLine="File.Delete(TempFolder, name)";
__c.File.Delete(_tempfolder,_name);
 //BA.debugLineNum = 88;BA.debugLine="File.Delete(TempFolder, \"~\" & name) 'delete the s";
__c.File.Delete(_tempfolder,"~"+_name);
 //BA.debugLineNum = 89;BA.debugLine="raf.Initialize(TempFolder, name , False)";
_raf.Initialize(_tempfolder,_name,__c.False);
 //BA.debugLineNum = 90;BA.debugLine="raf.WriteObject(Key, False, raf.CurrentPosition)";
_raf.WriteObject((Object)(_key),__c.False,_raf.CurrentPosition);
 //BA.debugLineNum = 91;BA.debugLine="raf.WriteObject(name, False, raf.CurrentPosition)";
_raf.WriteObject((Object)(_name),__c.False,_raf.CurrentPosition);
 //BA.debugLineNum = 92;BA.debugLine="outName(0) = name";
_outname[(int) (0)] = _name;
 //BA.debugLineNum = 93;BA.debugLine="Return raf";
if (true) return _raf;
 //BA.debugLineNum = 94;BA.debugLine="End Sub";
return null;
}
public String  _sharedwritecomplete(anywheresoftware.b4a.randomaccessfile.RandomAccessFile _raf,String _name) throws Exception{
long _length = 0L;
 //BA.debugLineNum = 96;BA.debugLine="Private Sub sharedWriteComplete(raf As RandomAcces";
 //BA.debugLineNum = 97;BA.debugLine="Dim length As Long = raf.CurrentPosition";
_length = _raf.CurrentPosition;
 //BA.debugLineNum = 98;BA.debugLine="raf.Close";
_raf.Close();
 //BA.debugLineNum = 99;BA.debugLine="astream.WriteStream(File.OpenInput(TempFolder, na";
_astream.WriteStream((java.io.InputStream)(__c.File.OpenInput(_tempfolder,_name).getObject()),_length);
 //BA.debugLineNum = 100;BA.debugLine="End Sub";
return "";
}
public String  _start(anywheresoftware.b4a.objects.streams.File.InputStreamWrapper _in,anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper _out) throws Exception{
 //BA.debugLineNum = 33;BA.debugLine="Public Sub Start(In As InputStream, Out As OutputS";
 //BA.debugLineNum = 34;BA.debugLine="FileToKey.Initialize";
_filetokey.Initialize();
 //BA.debugLineNum = 35;BA.debugLine="waitingForAnotherStream = False";
_waitingforanotherstream = __c.False;
 //BA.debugLineNum = 36;BA.debugLine="astream.InitializePrefix(In, True, Out, \"astream\"";
_astream.InitializePrefix(ba,(java.io.InputStream)(_in.getObject()),__c.True,(java.io.OutputStream)(_out.getObject()),"astream");
 //BA.debugLineNum = 37;BA.debugLine="connected = True";
_connected = __c.True;
 //BA.debugLineNum = 38;BA.debugLine="End Sub";
return "";
}
public String  _writebitmap(String _key,anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _value) throws Exception{
String[] _name = null;
anywheresoftware.b4a.randomaccessfile.RandomAccessFile _raf = null;
String _secondpart = "";
anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper _out = null;
 //BA.debugLineNum = 71;BA.debugLine="Public Sub WriteBitmap (Key As String, Value As Bi";
 //BA.debugLineNum = 72;BA.debugLine="Dim name(1) As String";
_name = new String[(int) (1)];
java.util.Arrays.fill(_name,"");
 //BA.debugLineNum = 73;BA.debugLine="Dim raf As RandomAccessFile = sharedWrite(Key, na";
_raf = _sharedwrite(_key,_name);
 //BA.debugLineNum = 74;BA.debugLine="raf.WriteByte(TYPE_BITMAP, raf.CurrentPosition)";
_raf.WriteByte(_type_bitmap,_raf.CurrentPosition);
 //BA.debugLineNum = 75;BA.debugLine="sharedWriteComplete(raf, name(0))";
_sharedwritecomplete(_raf,_name[(int) (0)]);
 //BA.debugLineNum = 76;BA.debugLine="Dim secondPart As String = \"~\" & name(0)";
_secondpart = "~"+_name[(int) (0)];
 //BA.debugLineNum = 77;BA.debugLine="Dim out As OutputStream = File.OpenOutput(TempFol";
_out = new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper();
_out = __c.File.OpenOutput(_tempfolder,_secondpart,__c.False);
 //BA.debugLineNum = 78;BA.debugLine="Value.WriteToStream(out, 100, \"PNG\")";
_value.WriteToStream((java.io.OutputStream)(_out.getObject()),(int) (100),BA.getEnumFromString(android.graphics.Bitmap.CompressFormat.class,"PNG"));
 //BA.debugLineNum = 79;BA.debugLine="out.Close";
_out.Close();
 //BA.debugLineNum = 80;BA.debugLine="astream.WriteStream(File.OpenInput(TempFolder, se";
_astream.WriteStream((java.io.InputStream)(__c.File.OpenInput(_tempfolder,_secondpart).getObject()),__c.File.Size(_tempfolder,_secondpart));
 //BA.debugLineNum = 82;BA.debugLine="End Sub";
return "";
}
public String  _writefile(String _key,String _dir,String _filename) throws Exception{
long _len = 0L;
String[] _name = null;
anywheresoftware.b4a.randomaccessfile.RandomAccessFile _raf = null;
 //BA.debugLineNum = 58;BA.debugLine="Public Sub WriteFile (Key As String, Dir As String";
 //BA.debugLineNum = 59;BA.debugLine="Dim len As Long = File.Size(Dir, FileName)";
_len = __c.File.Size(_dir,_filename);
 //BA.debugLineNum = 60;BA.debugLine="If len = 0 Then";
if (_len==0) { 
 //BA.debugLineNum = 61;BA.debugLine="Log(\"Error getting file size.\")";
__c.LogImpl("52359299","Error getting file size.",0);
 //BA.debugLineNum = 62;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 64;BA.debugLine="Dim name(1) As String";
_name = new String[(int) (1)];
java.util.Arrays.fill(_name,"");
 //BA.debugLineNum = 65;BA.debugLine="Dim raf As RandomAccessFile = sharedWrite(Key, na";
_raf = _sharedwrite(_key,_name);
 //BA.debugLineNum = 66;BA.debugLine="raf.WriteByte(TYPE_FILE, raf.CurrentPosition)";
_raf.WriteByte(_type_file,_raf.CurrentPosition);
 //BA.debugLineNum = 67;BA.debugLine="sharedWriteComplete(raf, name(0))";
_sharedwritecomplete(_raf,_name[(int) (0)]);
 //BA.debugLineNum = 68;BA.debugLine="astream.WriteStream(File.OpenInput(Dir, FileName)";
_astream.WriteStream((java.io.InputStream)(__c.File.OpenInput(_dir,_filename).getObject()),_len);
 //BA.debugLineNum = 69;BA.debugLine="End Sub";
return "";
}
public String  _writeobject(String _key,Object _value) throws Exception{
String[] _name = null;
anywheresoftware.b4a.randomaccessfile.RandomAccessFile _raf = null;
 //BA.debugLineNum = 49;BA.debugLine="Public Sub WriteObject (Key As String, Value As Ob";
 //BA.debugLineNum = 50;BA.debugLine="Dim name(1) As String";
_name = new String[(int) (1)];
java.util.Arrays.fill(_name,"");
 //BA.debugLineNum = 51;BA.debugLine="Dim raf As RandomAccessFile = sharedWrite(Key, na";
_raf = _sharedwrite(_key,_name);
 //BA.debugLineNum = 52;BA.debugLine="raf.WriteByte(TYPE_OBJECT, raf.CurrentPosition)";
_raf.WriteByte(_type_object,_raf.CurrentPosition);
 //BA.debugLineNum = 53;BA.debugLine="raf.WriteObject(Value, True, raf.CurrentPosition)";
_raf.WriteObject(_value,__c.True,_raf.CurrentPosition);
 //BA.debugLineNum = 54;BA.debugLine="sharedWriteComplete(raf, name(0))";
_sharedwritecomplete(_raf,_name[(int) (0)]);
 //BA.debugLineNum = 55;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
