package Daawat.Feedback.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_1{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
//BA.debugLineNum = 3;BA.debugLine="txtIP.HorizontalCenter=25%x"[1/General script]
views.get("txtip").vw.setLeft((int)((25d / 100 * width) - (views.get("txtip").vw.getWidth() / 2)));
//BA.debugLineNum = 4;BA.debugLine="txtIP.VerticalCenter=15%y"[1/General script]
views.get("txtip").vw.setTop((int)((15d / 100 * height) - (views.get("txtip").vw.getHeight() / 2)));
//BA.debugLineNum = 5;BA.debugLine="txtIP.Width=40%x"[1/General script]
views.get("txtip").vw.setWidth((int)((40d / 100 * width)));
//BA.debugLineNum = 6;BA.debugLine="txtIP.Height=12%x"[1/General script]
views.get("txtip").vw.setHeight((int)((12d / 100 * width)));
//BA.debugLineNum = 8;BA.debugLine="btnConnect.HorizontalCenter=55%x"[1/General script]
views.get("btnconnect").vw.setLeft((int)((55d / 100 * width) - (views.get("btnconnect").vw.getWidth() / 2)));
//BA.debugLineNum = 9;BA.debugLine="btnConnect.VerticalCenter=15%y"[1/General script]
views.get("btnconnect").vw.setTop((int)((15d / 100 * height) - (views.get("btnconnect").vw.getHeight() / 2)));
//BA.debugLineNum = 10;BA.debugLine="btnConnect.Width=45%x"[1/General script]
views.get("btnconnect").vw.setWidth((int)((45d / 100 * width)));
//BA.debugLineNum = 11;BA.debugLine="btnConnect.Height=12%x"[1/General script]
views.get("btnconnect").vw.setHeight((int)((12d / 100 * width)));
//BA.debugLineNum = 13;BA.debugLine="lblStatus.HorizontalCenter=26%x"[1/General script]
views.get("lblstatus").vw.setLeft((int)((26d / 100 * width) - (views.get("lblstatus").vw.getWidth() / 2)));
//BA.debugLineNum = 14;BA.debugLine="lblStatus.VerticalCenter=20%y"[1/General script]
views.get("lblstatus").vw.setTop((int)((20d / 100 * height) - (views.get("lblstatus").vw.getHeight() / 2)));
//BA.debugLineNum = 15;BA.debugLine="lblStatus.Width=45%x"[1/General script]
views.get("lblstatus").vw.setWidth((int)((45d / 100 * width)));
//BA.debugLineNum = 16;BA.debugLine="lblStatus.Height=20%x"[1/General script]
views.get("lblstatus").vw.setHeight((int)((20d / 100 * width)));
//BA.debugLineNum = 18;BA.debugLine="lblMyIP.HorizontalCenter=26%x"[1/General script]
views.get("lblmyip").vw.setLeft((int)((26d / 100 * width) - (views.get("lblmyip").vw.getWidth() / 2)));
//BA.debugLineNum = 19;BA.debugLine="lblMyIP.VerticalCenter=30%y"[1/General script]
views.get("lblmyip").vw.setTop((int)((30d / 100 * height) - (views.get("lblmyip").vw.getHeight() / 2)));
//BA.debugLineNum = 20;BA.debugLine="lblMyIP.Width=45%x"[1/General script]
views.get("lblmyip").vw.setWidth((int)((45d / 100 * width)));
//BA.debugLineNum = 21;BA.debugLine="lblMyIP.Height=20%x"[1/General script]
views.get("lblmyip").vw.setHeight((int)((20d / 100 * width)));
//BA.debugLineNum = 23;BA.debugLine="btnFeedback.HorizontalCenter=35%x"[1/General script]
views.get("btnfeedback").vw.setLeft((int)((35d / 100 * width) - (views.get("btnfeedback").vw.getWidth() / 2)));
//BA.debugLineNum = 24;BA.debugLine="btnFeedback.VerticalCenter=42%y"[1/General script]
views.get("btnfeedback").vw.setTop((int)((42d / 100 * height) - (views.get("btnfeedback").vw.getHeight() / 2)));
//BA.debugLineNum = 25;BA.debugLine="btnFeedback.Width=45%x"[1/General script]
views.get("btnfeedback").vw.setWidth((int)((45d / 100 * width)));
//BA.debugLineNum = 26;BA.debugLine="btnFeedback.Height=15%x"[1/General script]
views.get("btnfeedback").vw.setHeight((int)((15d / 100 * width)));

}
}