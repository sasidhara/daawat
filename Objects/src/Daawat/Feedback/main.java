package Daawat.Feedback;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = true;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "Daawat.Feedback", "Daawat.Feedback.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
				p.finish();
			}
		}
        processBA.setActivityPaused(true);
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(this, processBA, wl, true))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "Daawat.Feedback", "Daawat.Feedback.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "Daawat.Feedback.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return main.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null)
            return;
        if (this != mostCurrent)
			return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
            main mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
            if (mc != mostCurrent)
                return;
		    processBA.raiseEvent(mc._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }

public anywheresoftware.b4a.keywords.Common __c = null;
public static Daawat.Feedback.asyncstreamsobject _astreamo = null;
public static anywheresoftware.b4a.objects.SocketWrapper.ServerSocketWrapper _server = null;
public static anywheresoftware.b4a.objects.SocketWrapper _client = null;
public static int _port = 0;
public static String _ip = "";
public anywheresoftware.b4a.objects.ButtonWrapper _btnfeedback = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtip = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstatus = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel1 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtanimal = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtlast = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtfirst = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnsendfile = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnsenddrawing = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnconnect = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblmyip = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnsendform = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnsendnumber = null;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper _cvs = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imgpoor = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imgnotsatisfied = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imgsatisfied = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imgverysatisfied = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imgdelighted = null;
public anywheresoftware.b4a.objects.EditTextWrapper _fbtxt = null;
public anywheresoftware.b4a.objects.EditTextWrapper _fbval = null;
public anywheresoftware.b4a.objects.EditTextWrapper _edtxtname = null;
public anywheresoftware.b4a.objects.EditTextWrapper _edmob = null;
public static String _fdbtxt = "";
public static String _mobnum = "";
public static String _name = "";

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
return vis;}
public static class _person{
public boolean IsInitialized;
public String First;
public String Last;
public String Animal;
public void Initialize() {
IsInitialized = true;
First = "";
Last = "";
Animal = "";
}
@Override
		public String toString() {
			return BA.TypeToString(this, false);
		}}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 54;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 55;BA.debugLine="Activity.LoadLayout(\"1\")";
mostCurrent._activity.LoadLayout("1",mostCurrent.activityBA);
 //BA.debugLineNum = 56;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 57;BA.debugLine="astreamO.Initialize(Me, \"astreamO\")";
_astreamo._initialize /*String*/ (processBA,main.getObject(),"astreamO");
 //BA.debugLineNum = 58;BA.debugLine="server.Initialize(port, \"server\")";
_server.Initialize(processBA,_port,"server");
 //BA.debugLineNum = 59;BA.debugLine="client.Initialize(\"client\")";
_client.Initialize("client");
 };
 //BA.debugLineNum = 61;BA.debugLine="lblMyIP.Text = \"My IP: \" & server.GetMyIP";
mostCurrent._lblmyip.setText(BA.ObjectToCharSequence("My IP: "+_server.GetMyIP()));
 //BA.debugLineNum = 62;BA.debugLine="cvs.Initialize(Panel1)";
mostCurrent._cvs.Initialize((android.view.View)(mostCurrent._panel1.getObject()));
 //BA.debugLineNum = 63;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 72;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 73;BA.debugLine="If UserClosed Then";
if (_userclosed) { 
 //BA.debugLineNum = 74;BA.debugLine="astreamO.Close";
_astreamo._close /*String*/ ();
 };
 //BA.debugLineNum = 76;BA.debugLine="ip = txtIP.Text";
_ip = mostCurrent._txtip.getText();
 //BA.debugLineNum = 77;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 66;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 67;BA.debugLine="server.Listen";
_server.Listen();
 //BA.debugLineNum = 68;BA.debugLine="SetUIState";
_setuistate();
 //BA.debugLineNum = 69;BA.debugLine="txtIP.Text = ip";
mostCurrent._txtip.setText(BA.ObjectToCharSequence(_ip));
 //BA.debugLineNum = 70;BA.debugLine="End Sub";
return "";
}
public static String  _astreamo_newobject(String _key,Object _value) throws Exception{
Daawat.Feedback.main._person _p = null;
String _number = "";
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bmp = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _r = null;
String _filename = "";
 //BA.debugLineNum = 147;BA.debugLine="Sub astreamO_NewObject(Key As String, Value As Obj";
 //BA.debugLineNum = 148;BA.debugLine="Select Key";
switch (BA.switchObjectToInt(_key,"form","simple value","image","file")) {
case 0: {
 //BA.debugLineNum = 150;BA.debugLine="Dim p As Person = Value";
_p = (Daawat.Feedback.main._person)(_value);
 //BA.debugLineNum = 151;BA.debugLine="txtFirst.Text = p.First";
mostCurrent._txtfirst.setText(BA.ObjectToCharSequence(_p.First /*String*/ ));
 //BA.debugLineNum = 152;BA.debugLine="txtLast.Text = p.Last";
mostCurrent._txtlast.setText(BA.ObjectToCharSequence(_p.Last /*String*/ ));
 //BA.debugLineNum = 153;BA.debugLine="txtAnimal.Text = p.Animal";
mostCurrent._txtanimal.setText(BA.ObjectToCharSequence(_p.Animal /*String*/ ));
 break; }
case 1: {
 //BA.debugLineNum = 155;BA.debugLine="Dim number As String = Value";
_number = BA.ObjectToString(_value);
 //BA.debugLineNum = 156;BA.debugLine="ToastMessageShow(\"Received lucky number: \" & nu";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Received lucky number: "+_number),anywheresoftware.b4a.keywords.Common.False);
 break; }
case 2: {
 //BA.debugLineNum = 158;BA.debugLine="Dim bmp As Bitmap = Value";
_bmp = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_bmp.setObject((android.graphics.Bitmap)(_value));
 //BA.debugLineNum = 159;BA.debugLine="Dim r As Rect";
_r = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 160;BA.debugLine="r.Initialize(0, 0, Panel1.Width, Panel1.Height)";
_r.Initialize((int) (0),(int) (0),mostCurrent._panel1.getWidth(),mostCurrent._panel1.getHeight());
 //BA.debugLineNum = 161;BA.debugLine="cvs.DrawBitmap(bmp, Null, r)";
mostCurrent._cvs.DrawBitmap((android.graphics.Bitmap)(_bmp.getObject()),(android.graphics.Rect)(anywheresoftware.b4a.keywords.Common.Null),(android.graphics.Rect)(_r.getObject()));
 //BA.debugLineNum = 162;BA.debugLine="Panel1.Invalidate";
mostCurrent._panel1.Invalidate();
 break; }
case 3: {
 //BA.debugLineNum = 164;BA.debugLine="Dim fileName As String = Value";
_filename = BA.ObjectToString(_value);
 //BA.debugLineNum = 165;BA.debugLine="Log(\"Received file. File size: \" & File.Size(as";
anywheresoftware.b4a.keywords.Common.LogImpl("51441810","Received file. File size: "+BA.NumberToString(anywheresoftware.b4a.keywords.Common.File.Size(_astreamo._tempfolder /*String*/ ,_filename)),0);
 break; }
}
;
 //BA.debugLineNum = 167;BA.debugLine="End Sub";
return "";
}
public static String  _astreamo_objectsent(String _key) throws Exception{
 //BA.debugLineNum = 169;BA.debugLine="Sub astreamO_ObjectSent (Key As String)";
 //BA.debugLineNum = 170;BA.debugLine="Log(\"Object sent: \" & Key)";
anywheresoftware.b4a.keywords.Common.LogImpl("51507329","Object sent: "+_key,0);
 //BA.debugLineNum = 171;BA.debugLine="End Sub";
return "";
}
public static String  _astreamo_terminated() throws Exception{
 //BA.debugLineNum = 97;BA.debugLine="Sub AstreamO_Terminated";
 //BA.debugLineNum = 98;BA.debugLine="SetUIState";
_setuistate();
 //BA.debugLineNum = 99;BA.debugLine="server.Listen";
_server.Listen();
 //BA.debugLineNum = 100;BA.debugLine="client.Close";
_client.Close();
 //BA.debugLineNum = 101;BA.debugLine="client.Initialize(\"client\")";
_client.Initialize("client");
 //BA.debugLineNum = 102;BA.debugLine="End Sub";
return "";
}
public static String  _btnconnect_click() throws Exception{
 //BA.debugLineNum = 79;BA.debugLine="Sub btnConnect_Click";
 //BA.debugLineNum = 80;BA.debugLine="client.Connect(txtIP.Text,port, 30000)";
_client.Connect(processBA,mostCurrent._txtip.getText(),_port,(int) (30000));
 //BA.debugLineNum = 81;BA.debugLine="End Sub";
return "";
}
public static String  _btnfeedback_click() throws Exception{
 //BA.debugLineNum = 173;BA.debugLine="Sub btnFeedback_click";
 //BA.debugLineNum = 174;BA.debugLine="Activity.RemoveAllViews";
mostCurrent._activity.RemoveAllViews();
 //BA.debugLineNum = 175;BA.debugLine="Activity.LoadLayout(\"feedback\")";
mostCurrent._activity.LoadLayout("feedback",mostCurrent.activityBA);
 //BA.debugLineNum = 177;BA.debugLine="End Sub";
return "";
}
public static String  _btnsenddrawing_click() throws Exception{
 //BA.debugLineNum = 120;BA.debugLine="Sub btnSendDrawing_Click";
 //BA.debugLineNum = 121;BA.debugLine="astreamO.WriteBitmap(\"image\", cvs.Bitmap)";
_astreamo._writebitmap /*String*/ ("image",mostCurrent._cvs.getBitmap());
 //BA.debugLineNum = 122;BA.debugLine="End Sub";
return "";
}
public static String  _btnsendfile_click() throws Exception{
 //BA.debugLineNum = 124;BA.debugLine="Sub btnSendFile_Click";
 //BA.debugLineNum = 125;BA.debugLine="Log(\"Not implemented...\") 'uncomment the next lin";
anywheresoftware.b4a.keywords.Common.LogImpl("51179649","Not implemented...",0);
 //BA.debugLineNum = 127;BA.debugLine="End Sub";
return "";
}
public static String  _btnsendform_click() throws Exception{
Daawat.Feedback.main._person _p = null;
 //BA.debugLineNum = 129;BA.debugLine="Sub btnSendForm_Click";
 //BA.debugLineNum = 130;BA.debugLine="Dim p As Person";
_p = new Daawat.Feedback.main._person();
 //BA.debugLineNum = 131;BA.debugLine="p.First = txtFirst.Text";
_p.First /*String*/  = mostCurrent._txtfirst.getText();
 //BA.debugLineNum = 132;BA.debugLine="p.Last = txtLast.Text";
_p.Last /*String*/  = mostCurrent._txtlast.getText();
 //BA.debugLineNum = 133;BA.debugLine="p.Animal = txtAnimal.Text";
_p.Animal /*String*/  = mostCurrent._txtanimal.getText();
 //BA.debugLineNum = 134;BA.debugLine="astreamO.WriteObject(\"form\", p)";
_astreamo._writeobject /*String*/ ("form",(Object)(_p));
 //BA.debugLineNum = 135;BA.debugLine="End Sub";
return "";
}
public static String  _btnsendnumber_click() throws Exception{
 //BA.debugLineNum = 143;BA.debugLine="Sub btnSendNumber_Click";
 //BA.debugLineNum = 144;BA.debugLine="astreamO.WriteObject(\"simple value\",txtAnimal.Tex";
_astreamo._writeobject /*String*/ ("simple value",(Object)(mostCurrent._txtanimal.getText()));
 //BA.debugLineNum = 145;BA.debugLine="End Sub";
return "";
}
public static String  _client_connected(boolean _successful) throws Exception{
 //BA.debugLineNum = 104;BA.debugLine="Sub client_Connected (Successful As Boolean)";
 //BA.debugLineNum = 105;BA.debugLine="If Successful Then";
if (_successful) { 
 //BA.debugLineNum = 106;BA.debugLine="StartAstream(client)";
_startastream(_client);
 }else {
 //BA.debugLineNum = 108;BA.debugLine="ToastMessageShow(\"Error: \" & LastException, True";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Error: "+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA))),anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 110;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 25;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 26;BA.debugLine="Dim btnFeedback As Button";
mostCurrent._btnfeedback = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Dim txtIP As EditText";
mostCurrent._txtip = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Dim lblStatus As Label";
mostCurrent._lblstatus = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Dim Panel1 As Panel";
mostCurrent._panel1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Dim txtAnimal As EditText";
mostCurrent._txtanimal = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Dim txtLast As EditText";
mostCurrent._txtlast = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Dim txtFirst As EditText";
mostCurrent._txtfirst = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Dim btnSendFile As Button";
mostCurrent._btnsendfile = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Dim btnSendDrawing As Button";
mostCurrent._btnsenddrawing = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Dim btnConnect As Button";
mostCurrent._btnconnect = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Dim lblMyIP As Label";
mostCurrent._lblmyip = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Dim btnSendForm As Button";
mostCurrent._btnsendform = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Dim btnSendNumber As Button";
mostCurrent._btnsendnumber = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private cvs As Canvas";
mostCurrent._cvs = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Dim imgpoor As ImageView";
mostCurrent._imgpoor = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Dim imgNotSatisfied As ImageView";
mostCurrent._imgnotsatisfied = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Dim imgSatisfied As ImageView";
mostCurrent._imgsatisfied = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Dim ImgVerySatisfied As ImageView";
mostCurrent._imgverysatisfied = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Dim ImgDelighted As ImageView";
mostCurrent._imgdelighted = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 45;BA.debugLine="Dim FbTxt As EditText";
mostCurrent._fbtxt = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 46;BA.debugLine="Dim FbVal As EditText";
mostCurrent._fbval = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 47;BA.debugLine="Dim EdTxtName As EditText";
mostCurrent._edtxtname = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 48;BA.debugLine="Dim EdMob As EditText";
mostCurrent._edmob = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 49;BA.debugLine="Dim fdbtxt As String";
mostCurrent._fdbtxt = "";
 //BA.debugLineNum = 50;BA.debugLine="Dim mobnum As String";
mostCurrent._mobnum = "";
 //BA.debugLineNum = 51;BA.debugLine="Dim name As String";
mostCurrent._name = "";
 //BA.debugLineNum = 52;BA.debugLine="End Sub";
return "";
}
public static String  _imgdelighted_click() throws Exception{
 //BA.debugLineNum = 288;BA.debugLine="Sub ImgDelighted_click";
 //BA.debugLineNum = 289;BA.debugLine="FbTxt.Text=\"Delighted\"";
mostCurrent._fbtxt.setText(BA.ObjectToCharSequence("Delighted"));
 //BA.debugLineNum = 290;BA.debugLine="FbVal.Text=\"5\"";
mostCurrent._fbval.setText(BA.ObjectToCharSequence("5"));
 //BA.debugLineNum = 291;BA.debugLine="mobnum=EdMob.Text";
mostCurrent._mobnum = mostCurrent._edmob.getText();
 //BA.debugLineNum = 292;BA.debugLine="name=EdTxtName.Text";
mostCurrent._name = mostCurrent._edtxtname.getText();
 //BA.debugLineNum = 293;BA.debugLine="If lblStatus.Text = \"Connected\" Then";
if ((mostCurrent._lblstatus.getText()).equals("Connected")) { 
 //BA.debugLineNum = 294;BA.debugLine="If name.Length=0 Then";
if (mostCurrent._name.length()==0) { 
 //BA.debugLineNum = 295;BA.debugLine="MsgboxAsync(\"Please Enter valid Name\",\"Alert\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Please Enter valid Name"),BA.ObjectToCharSequence("Alert"),processBA);
 //BA.debugLineNum = 296;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 298;BA.debugLine="If mobnum.Length =10 Then";
if (mostCurrent._mobnum.length()==10) { 
 //BA.debugLineNum = 300;BA.debugLine="fdbtxt= \"INSERT INTO counter VALUES(\" & \"\"\"\" &";
mostCurrent._fdbtxt = "INSERT INTO counter VALUES("+"\""+mostCurrent._edtxtname.getText()+"\""+","+"\""+mostCurrent._edmob.getText()+"\""+","+"\""+mostCurrent._fbtxt.getText()+"\""+","+mostCurrent._fbval.getText()+")";
 //BA.debugLineNum = 301;BA.debugLine="astreamO.WriteObject(\"simple value\",fdbtxt)";
_astreamo._writeobject /*String*/ ("simple value",(Object)(mostCurrent._fdbtxt));
 //BA.debugLineNum = 302;BA.debugLine="MsgboxAsync(\"Thanks for Sharing Feedback\",\"Than";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Thanks for Sharing Feedback"),BA.ObjectToCharSequence("Thanks"),processBA);
 //BA.debugLineNum = 303;BA.debugLine="EdTxtName.Text=\"\"";
mostCurrent._edtxtname.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 304;BA.debugLine="EdMob.Text=\"\"";
mostCurrent._edmob.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 305;BA.debugLine="Return";
if (true) return "";
 }else {
 //BA.debugLineNum = 307;BA.debugLine="MsgboxAsync(\"Please Enter 10 Digit Mobile numbe";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Please Enter 10 Digit Mobile number"),BA.ObjectToCharSequence("Alert"),processBA);
 };
 }else {
 //BA.debugLineNum = 310;BA.debugLine="MsgboxAsync(\"Please Connect to WIFI\",\"Alert\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Please Connect to WIFI"),BA.ObjectToCharSequence("Alert"),processBA);
 //BA.debugLineNum = 311;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 313;BA.debugLine="End Sub";
return "";
}
public static String  _imgnotsatisfied_click() throws Exception{
 //BA.debugLineNum = 207;BA.debugLine="Sub imgNotSatisfied_click";
 //BA.debugLineNum = 208;BA.debugLine="FbTxt.Text=\"NotSatisfied\"";
mostCurrent._fbtxt.setText(BA.ObjectToCharSequence("NotSatisfied"));
 //BA.debugLineNum = 209;BA.debugLine="FbVal.Text=\"2\"";
mostCurrent._fbval.setText(BA.ObjectToCharSequence("2"));
 //BA.debugLineNum = 210;BA.debugLine="mobnum=EdMob.Text";
mostCurrent._mobnum = mostCurrent._edmob.getText();
 //BA.debugLineNum = 211;BA.debugLine="name=EdTxtName.Text";
mostCurrent._name = mostCurrent._edtxtname.getText();
 //BA.debugLineNum = 212;BA.debugLine="If lblStatus.Text = \"Connected\" Then";
if ((mostCurrent._lblstatus.getText()).equals("Connected")) { 
 //BA.debugLineNum = 213;BA.debugLine="If name.Length=0 Then";
if (mostCurrent._name.length()==0) { 
 //BA.debugLineNum = 214;BA.debugLine="MsgboxAsync(\"Please Enter valid Name\",\"Alert\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Please Enter valid Name"),BA.ObjectToCharSequence("Alert"),processBA);
 //BA.debugLineNum = 215;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 217;BA.debugLine="If mobnum.Length =10 Then";
if (mostCurrent._mobnum.length()==10) { 
 //BA.debugLineNum = 219;BA.debugLine="fdbtxt= \"INSERT INTO counter VALUES(\" & \"\"\"\" &";
mostCurrent._fdbtxt = "INSERT INTO counter VALUES("+"\""+mostCurrent._edtxtname.getText()+"\""+","+"\""+mostCurrent._edmob.getText()+"\""+","+"\""+mostCurrent._fbtxt.getText()+"\""+","+mostCurrent._fbval.getText()+")";
 //BA.debugLineNum = 220;BA.debugLine="astreamO.WriteObject(\"simple value\",fdbtxt)";
_astreamo._writeobject /*String*/ ("simple value",(Object)(mostCurrent._fdbtxt));
 //BA.debugLineNum = 221;BA.debugLine="MsgboxAsync(\"Thanks for Sharing Feedback\",\"Than";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Thanks for Sharing Feedback"),BA.ObjectToCharSequence("Thanks"),processBA);
 //BA.debugLineNum = 222;BA.debugLine="EdTxtName.Text=\"\"";
mostCurrent._edtxtname.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 223;BA.debugLine="EdMob.Text=\"\"";
mostCurrent._edmob.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 224;BA.debugLine="Return";
if (true) return "";
 }else {
 //BA.debugLineNum = 226;BA.debugLine="MsgboxAsync(\"Please Enter 10 Digit Mobile numbe";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Please Enter 10 Digit Mobile number"),BA.ObjectToCharSequence("Alert"),processBA);
 };
 }else {
 //BA.debugLineNum = 229;BA.debugLine="MsgboxAsync(\"Please Connect to WIFI\",\"Alert\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Please Connect to WIFI"),BA.ObjectToCharSequence("Alert"),processBA);
 //BA.debugLineNum = 230;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 232;BA.debugLine="End Sub";
return "";
}
public static String  _imgpoor_click() throws Exception{
 //BA.debugLineNum = 179;BA.debugLine="Sub imgpoor_click";
 //BA.debugLineNum = 180;BA.debugLine="FbTxt.Text=\"Poor\"";
mostCurrent._fbtxt.setText(BA.ObjectToCharSequence("Poor"));
 //BA.debugLineNum = 181;BA.debugLine="FbVal.Text=\"1\"";
mostCurrent._fbval.setText(BA.ObjectToCharSequence("1"));
 //BA.debugLineNum = 182;BA.debugLine="mobnum=EdMob.Text";
mostCurrent._mobnum = mostCurrent._edmob.getText();
 //BA.debugLineNum = 183;BA.debugLine="name=EdTxtName.Text";
mostCurrent._name = mostCurrent._edtxtname.getText();
 //BA.debugLineNum = 185;BA.debugLine="If lblStatus.Text = \"Connected\" Then";
if ((mostCurrent._lblstatus.getText()).equals("Connected")) { 
 //BA.debugLineNum = 186;BA.debugLine="If name.Length=0 Then";
if (mostCurrent._name.length()==0) { 
 //BA.debugLineNum = 187;BA.debugLine="MsgboxAsync(\"Please Enter valid Name\",\"Alert\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Please Enter valid Name"),BA.ObjectToCharSequence("Alert"),processBA);
 //BA.debugLineNum = 188;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 190;BA.debugLine="If mobnum.Length =10 Then";
if (mostCurrent._mobnum.length()==10) { 
 //BA.debugLineNum = 192;BA.debugLine="fdbtxt= \"INSERT INTO counter VALUES(\" & \"\"\"\" &";
mostCurrent._fdbtxt = "INSERT INTO counter VALUES("+"\""+mostCurrent._edtxtname.getText()+"\""+","+"\""+mostCurrent._edmob.getText()+"\""+","+"\""+mostCurrent._fbtxt.getText()+"\""+","+mostCurrent._fbval.getText()+")";
 //BA.debugLineNum = 193;BA.debugLine="astreamO.WriteObject(\"simple value\",fdbtxt)";
_astreamo._writeobject /*String*/ ("simple value",(Object)(mostCurrent._fdbtxt));
 //BA.debugLineNum = 194;BA.debugLine="MsgboxAsync(\"Thanks for Sharing Feedback\",\"Than";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Thanks for Sharing Feedback"),BA.ObjectToCharSequence("Thanks"),processBA);
 //BA.debugLineNum = 195;BA.debugLine="EdTxtName.Text=\"\"";
mostCurrent._edtxtname.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 196;BA.debugLine="EdMob.Text=\"\"";
mostCurrent._edmob.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 197;BA.debugLine="Return";
if (true) return "";
 }else {
 //BA.debugLineNum = 199;BA.debugLine="MsgboxAsync(\"Please Enter 10 Digit Mobile numbe";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Please Enter 10 Digit Mobile number"),BA.ObjectToCharSequence("Alert"),processBA);
 };
 }else {
 //BA.debugLineNum = 202;BA.debugLine="MsgboxAsync(\"Please Connect to WIFI\",\"Alert\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Please Connect to WIFI"),BA.ObjectToCharSequence("Alert"),processBA);
 //BA.debugLineNum = 203;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 205;BA.debugLine="End Sub";
return "";
}
public static String  _imgsatisfied_click() throws Exception{
 //BA.debugLineNum = 234;BA.debugLine="Sub imgSatisfied_click";
 //BA.debugLineNum = 235;BA.debugLine="FbTxt.Text=\"Satisfied\"";
mostCurrent._fbtxt.setText(BA.ObjectToCharSequence("Satisfied"));
 //BA.debugLineNum = 236;BA.debugLine="FbVal.Text=\"3\"";
mostCurrent._fbval.setText(BA.ObjectToCharSequence("3"));
 //BA.debugLineNum = 237;BA.debugLine="mobnum=EdMob.Text";
mostCurrent._mobnum = mostCurrent._edmob.getText();
 //BA.debugLineNum = 238;BA.debugLine="name=EdTxtName.Text";
mostCurrent._name = mostCurrent._edtxtname.getText();
 //BA.debugLineNum = 239;BA.debugLine="If lblStatus.Text = \"Connected\" Then";
if ((mostCurrent._lblstatus.getText()).equals("Connected")) { 
 //BA.debugLineNum = 240;BA.debugLine="If name.Length=0 Then";
if (mostCurrent._name.length()==0) { 
 //BA.debugLineNum = 241;BA.debugLine="MsgboxAsync(\"Please Enter valid Name\",\"Alert\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Please Enter valid Name"),BA.ObjectToCharSequence("Alert"),processBA);
 //BA.debugLineNum = 242;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 244;BA.debugLine="If mobnum.Length =10 Then";
if (mostCurrent._mobnum.length()==10) { 
 //BA.debugLineNum = 246;BA.debugLine="fdbtxt= \"INSERT INTO counter VALUES(\" & \"\"\"\" &";
mostCurrent._fdbtxt = "INSERT INTO counter VALUES("+"\""+mostCurrent._edtxtname.getText()+"\""+","+"\""+mostCurrent._edmob.getText()+"\""+","+"\""+mostCurrent._fbtxt.getText()+"\""+","+mostCurrent._fbval.getText()+")";
 //BA.debugLineNum = 247;BA.debugLine="astreamO.WriteObject(\"simple value\",fdbtxt)";
_astreamo._writeobject /*String*/ ("simple value",(Object)(mostCurrent._fdbtxt));
 //BA.debugLineNum = 248;BA.debugLine="MsgboxAsync(\"Thanks for Sharing Feedback\",\"Than";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Thanks for Sharing Feedback"),BA.ObjectToCharSequence("Thanks"),processBA);
 //BA.debugLineNum = 249;BA.debugLine="EdTxtName.Text=\"\"";
mostCurrent._edtxtname.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 250;BA.debugLine="EdMob.Text=\"\"";
mostCurrent._edmob.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 251;BA.debugLine="Return";
if (true) return "";
 }else {
 //BA.debugLineNum = 253;BA.debugLine="MsgboxAsync(\"Please Enter 10 Digit Mobile numbe";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Please Enter 10 Digit Mobile number"),BA.ObjectToCharSequence("Alert"),processBA);
 };
 }else {
 //BA.debugLineNum = 256;BA.debugLine="MsgboxAsync(\"Please Connect to WIFI\",\"Alert\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Please Connect to WIFI"),BA.ObjectToCharSequence("Alert"),processBA);
 //BA.debugLineNum = 257;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 259;BA.debugLine="End Sub";
return "";
}
public static String  _imgverysatisfied_click() throws Exception{
 //BA.debugLineNum = 261;BA.debugLine="Sub ImgVerySatisfied_click";
 //BA.debugLineNum = 262;BA.debugLine="FbTxt.Text=\"VerySatisfied\"";
mostCurrent._fbtxt.setText(BA.ObjectToCharSequence("VerySatisfied"));
 //BA.debugLineNum = 263;BA.debugLine="FbVal.Text=\"4\"";
mostCurrent._fbval.setText(BA.ObjectToCharSequence("4"));
 //BA.debugLineNum = 264;BA.debugLine="mobnum=EdMob.Text";
mostCurrent._mobnum = mostCurrent._edmob.getText();
 //BA.debugLineNum = 265;BA.debugLine="name=EdTxtName.Text";
mostCurrent._name = mostCurrent._edtxtname.getText();
 //BA.debugLineNum = 266;BA.debugLine="If lblStatus.Text = \"Connected\" Then";
if ((mostCurrent._lblstatus.getText()).equals("Connected")) { 
 //BA.debugLineNum = 267;BA.debugLine="If name.Length=0 Then";
if (mostCurrent._name.length()==0) { 
 //BA.debugLineNum = 268;BA.debugLine="MsgboxAsync(\"Please Enter valid Name\",\"Alert\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Please Enter valid Name"),BA.ObjectToCharSequence("Alert"),processBA);
 //BA.debugLineNum = 269;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 271;BA.debugLine="If mobnum.Length =10 Then";
if (mostCurrent._mobnum.length()==10) { 
 //BA.debugLineNum = 273;BA.debugLine="fdbtxt= \"INSERT INTO counter VALUES(\" & \"\"\"\" &";
mostCurrent._fdbtxt = "INSERT INTO counter VALUES("+"\""+mostCurrent._edtxtname.getText()+"\""+","+"\""+mostCurrent._edmob.getText()+"\""+","+"\""+mostCurrent._fbtxt.getText()+"\""+","+mostCurrent._fbval.getText()+")";
 //BA.debugLineNum = 274;BA.debugLine="astreamO.WriteObject(\"simple value\",fdbtxt)";
_astreamo._writeobject /*String*/ ("simple value",(Object)(mostCurrent._fdbtxt));
 //BA.debugLineNum = 275;BA.debugLine="MsgboxAsync(\"Thanks for Sharing Feedback\",\"Than";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Thanks for Sharing Feedback"),BA.ObjectToCharSequence("Thanks"),processBA);
 //BA.debugLineNum = 276;BA.debugLine="EdTxtName.Text=\"\"";
mostCurrent._edtxtname.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 277;BA.debugLine="EdMob.Text=\"\"";
mostCurrent._edmob.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 278;BA.debugLine="Return";
if (true) return "";
 }else {
 //BA.debugLineNum = 280;BA.debugLine="MsgboxAsync(\"Please Enter 10 Digit Mobile numbe";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Please Enter 10 Digit Mobile number"),BA.ObjectToCharSequence("Alert"),processBA);
 };
 }else {
 //BA.debugLineNum = 283;BA.debugLine="MsgboxAsync(\"Please Connect to WIFI\",\"Alert\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Please Connect to WIFI"),BA.ObjectToCharSequence("Alert"),processBA);
 //BA.debugLineNum = 284;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 286;BA.debugLine="End Sub";
return "";
}
public static String  _panel1_touch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 137;BA.debugLine="Sub Panel1_Touch (Action As Int, X As Float, Y As";
 //BA.debugLineNum = 138;BA.debugLine="cvs.DrawCircle(X, Y, 10dip, Colors.RGB(Rnd(0, 256";
mostCurrent._cvs.DrawCircle(_x,_y,(float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),anywheresoftware.b4a.keywords.Common.Colors.RGB(anywheresoftware.b4a.keywords.Common.Rnd((int) (0),(int) (256)),anywheresoftware.b4a.keywords.Common.Rnd((int) (0),(int) (256)),anywheresoftware.b4a.keywords.Common.Rnd((int) (0),(int) (256))),anywheresoftware.b4a.keywords.Common.True,(float) (0));
 //BA.debugLineNum = 139;BA.debugLine="Panel1.Invalidate";
mostCurrent._panel1.Invalidate();
 //BA.debugLineNum = 140;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 17;BA.debugLine="Private astreamO As AsyncStreamsObject";
_astreamo = new Daawat.Feedback.asyncstreamsobject();
 //BA.debugLineNum = 18;BA.debugLine="Private server As ServerSocket";
_server = new anywheresoftware.b4a.objects.SocketWrapper.ServerSocketWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Private client As Socket";
_client = new anywheresoftware.b4a.objects.SocketWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private port As Int = 32118";
_port = (int) (32118);
 //BA.debugLineNum = 21;BA.debugLine="Private ip As String";
_ip = "";
 //BA.debugLineNum = 22;BA.debugLine="Type Person (First As String, Last As String, Ani";
;
 //BA.debugLineNum = 23;BA.debugLine="End Sub";
return "";
}
public static String  _server_newconnection(boolean _successful,anywheresoftware.b4a.objects.SocketWrapper _newsocket) throws Exception{
 //BA.debugLineNum = 111;BA.debugLine="Sub Server_NewConnection (Successful As Boolean, N";
 //BA.debugLineNum = 112;BA.debugLine="If Successful Then";
if (_successful) { 
 //BA.debugLineNum = 113;BA.debugLine="StartAstream(NewSocket)";
_startastream(_newsocket);
 }else {
 //BA.debugLineNum = 115;BA.debugLine="ToastMessageShow(\"Error: \" & LastException, True";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Error: "+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA))),anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 117;BA.debugLine="End Sub";
return "";
}
public static String  _setuistate() throws Exception{
 //BA.debugLineNum = 83;BA.debugLine="Sub SetUIState";
 //BA.debugLineNum = 84;BA.debugLine="btnConnect.Enabled = Not(astreamO.IsConnected)";
mostCurrent._btnconnect.setEnabled(anywheresoftware.b4a.keywords.Common.Not(_astreamo._getisconnected /*boolean*/ ()));
 //BA.debugLineNum = 85;BA.debugLine="btnSendDrawing.Enabled = astreamO.IsConnected";
mostCurrent._btnsenddrawing.setEnabled(_astreamo._getisconnected /*boolean*/ ());
 //BA.debugLineNum = 86;BA.debugLine="btnSendFile.Enabled = astreamO.IsConnected";
mostCurrent._btnsendfile.setEnabled(_astreamo._getisconnected /*boolean*/ ());
 //BA.debugLineNum = 87;BA.debugLine="btnSendForm.Enabled = astreamO.IsConnected";
mostCurrent._btnsendform.setEnabled(_astreamo._getisconnected /*boolean*/ ());
 //BA.debugLineNum = 88;BA.debugLine="btnSendNumber.Enabled = astreamO.IsConnected";
mostCurrent._btnsendnumber.setEnabled(_astreamo._getisconnected /*boolean*/ ());
 //BA.debugLineNum = 89;BA.debugLine="If astreamO.IsConnected Then lblStatus.Text = \"Co";
if (_astreamo._getisconnected /*boolean*/ ()) { 
mostCurrent._lblstatus.setText(BA.ObjectToCharSequence("Connected"));}
else {
mostCurrent._lblstatus.setText(BA.ObjectToCharSequence("Disconnected"));};
 //BA.debugLineNum = 90;BA.debugLine="End Sub";
return "";
}
public static String  _startastream(anywheresoftware.b4a.objects.SocketWrapper _s) throws Exception{
 //BA.debugLineNum = 92;BA.debugLine="Sub StartAstream(s As Socket)";
 //BA.debugLineNum = 93;BA.debugLine="astreamO.Start(s.InputStream, s.OutputStream)";
_astreamo._start /*String*/ ((anywheresoftware.b4a.objects.streams.File.InputStreamWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.streams.File.InputStreamWrapper(), (java.io.InputStream)(_s.getInputStream())),(anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper(), (java.io.OutputStream)(_s.getOutputStream())));
 //BA.debugLineNum = 94;BA.debugLine="SetUIState";
_setuistate();
 //BA.debugLineNum = 95;BA.debugLine="End Sub";
return "";
}
}
