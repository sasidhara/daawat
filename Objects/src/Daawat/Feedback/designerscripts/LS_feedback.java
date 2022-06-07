package Daawat.Feedback.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_feedback{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("lblfeed").vw.setLeft((int)((15d / 100 * width) - (views.get("lblfeed").vw.getWidth() / 2)));
views.get("lblfeed").vw.setTop((int)((13d / 100 * height) - (views.get("lblfeed").vw.getHeight() / 2)));
views.get("lblfeed").vw.setWidth((int)((80d / 100 * width)));
views.get("lblfeed").vw.setHeight((int)((12d / 100 * width)));
views.get("imgdawat").vw.setLeft((int)((80d / 100 * width) - (views.get("imgdawat").vw.getWidth() / 2)));
views.get("imgdawat").vw.setTop((int)((16d / 100 * height) - (views.get("imgdawat").vw.getHeight() / 2)));
views.get("imgdawat").vw.setWidth((int)((22d / 100 * width)));
views.get("imgdawat").vw.setHeight((int)((22d / 100 * width)));
views.get("lbname").vw.setLeft((int)((15d / 100 * width) - (views.get("lbname").vw.getWidth() / 2)));
views.get("lbname").vw.setTop((int)((15d / 100 * height) - (views.get("lbname").vw.getHeight() / 2)));
views.get("lbname").vw.setWidth((int)((25d / 100 * width)));
views.get("lbname").vw.setHeight((int)((25d / 100 * height)));
views.get("edtxtname").vw.setLeft((int)((35d / 100 * width) - (views.get("edtxtname").vw.getWidth() / 2)));
views.get("edtxtname").vw.setTop((int)((20d / 100 * height) - (views.get("edtxtname").vw.getHeight() / 2)));
views.get("edtxtname").vw.setWidth((int)((50d / 100 * width)));
views.get("edtxtname").vw.setHeight((int)((10d / 100 * height)));
views.get("lbmob").vw.setLeft((int)((15d / 100 * width) - (views.get("lbmob").vw.getWidth() / 2)));
views.get("lbmob").vw.setTop((int)((25d / 100 * height) - (views.get("lbmob").vw.getHeight() / 2)));
views.get("lbmob").vw.setWidth((int)((25d / 100 * width)));
//BA.debugLineNum = 29;BA.debugLine="LbMob.Height=25%y"[feedback/General script]
views.get("lbmob").vw.setHeight((int)((25d / 100 * height)));
//BA.debugLineNum = 31;BA.debugLine="EdMob.HorizontalCenter=35%x"[feedback/General script]
views.get("edmob").vw.setLeft((int)((35d / 100 * width) - (views.get("edmob").vw.getWidth() / 2)));
//BA.debugLineNum = 32;BA.debugLine="EdMob.VerticalCenter=30%y"[feedback/General script]
views.get("edmob").vw.setTop((int)((30d / 100 * height) - (views.get("edmob").vw.getHeight() / 2)));
//BA.debugLineNum = 33;BA.debugLine="EdMob.Width=50%x"[feedback/General script]
views.get("edmob").vw.setWidth((int)((50d / 100 * width)));
//BA.debugLineNum = 34;BA.debugLine="EdMob.Height=10%y"[feedback/General script]
views.get("edmob").vw.setHeight((int)((10d / 100 * height)));
//BA.debugLineNum = 40;BA.debugLine="ImgPoor.HorizontalCenter=12%x"[feedback/General script]
views.get("imgpoor").vw.setLeft((int)((12d / 100 * width) - (views.get("imgpoor").vw.getWidth() / 2)));
//BA.debugLineNum = 41;BA.debugLine="ImgPoor.VerticalCenter=50%y"[feedback/General script]
views.get("imgpoor").vw.setTop((int)((50d / 100 * height) - (views.get("imgpoor").vw.getHeight() / 2)));
//BA.debugLineNum = 42;BA.debugLine="ImgPoor.Width=12%x"[feedback/General script]
views.get("imgpoor").vw.setWidth((int)((12d / 100 * width)));
//BA.debugLineNum = 43;BA.debugLine="ImgPoor.Height=12%x"[feedback/General script]
views.get("imgpoor").vw.setHeight((int)((12d / 100 * width)));
//BA.debugLineNum = 46;BA.debugLine="TxtPoor.HorizontalCenter=18%x"[feedback/General script]
views.get("txtpoor").vw.setLeft((int)((18d / 100 * width) - (views.get("txtpoor").vw.getWidth() / 2)));
//BA.debugLineNum = 47;BA.debugLine="TxtPoor.VerticalCenter=45%y"[feedback/General script]
views.get("txtpoor").vw.setTop((int)((45d / 100 * height) - (views.get("txtpoor").vw.getHeight() / 2)));
//BA.debugLineNum = 48;BA.debugLine="TxtPoor.Width=25%x"[feedback/General script]
views.get("txtpoor").vw.setWidth((int)((25d / 100 * width)));
//BA.debugLineNum = 49;BA.debugLine="TxtPoor.Height=25%y"[feedback/General script]
views.get("txtpoor").vw.setHeight((int)((25d / 100 * height)));
//BA.debugLineNum = 52;BA.debugLine="imgNotSatisfied.HorizontalCenter=32%x"[feedback/General script]
views.get("imgnotsatisfied").vw.setLeft((int)((32d / 100 * width) - (views.get("imgnotsatisfied").vw.getWidth() / 2)));
//BA.debugLineNum = 53;BA.debugLine="imgNotSatisfied.VerticalCenter=50%y"[feedback/General script]
views.get("imgnotsatisfied").vw.setTop((int)((50d / 100 * height) - (views.get("imgnotsatisfied").vw.getHeight() / 2)));
//BA.debugLineNum = 54;BA.debugLine="imgNotSatisfied.Width=12%x"[feedback/General script]
views.get("imgnotsatisfied").vw.setWidth((int)((12d / 100 * width)));
//BA.debugLineNum = 55;BA.debugLine="imgNotSatisfied.Height=12%x"[feedback/General script]
views.get("imgnotsatisfied").vw.setHeight((int)((12d / 100 * width)));
//BA.debugLineNum = 57;BA.debugLine="TxtNotSatisfied.HorizontalCenter=27%x"[feedback/General script]
views.get("txtnotsatisfied").vw.setLeft((int)((27d / 100 * width) - (views.get("txtnotsatisfied").vw.getWidth() / 2)));
//BA.debugLineNum = 58;BA.debugLine="TxtNotSatisfied.VerticalCenter=45%y"[feedback/General script]
views.get("txtnotsatisfied").vw.setTop((int)((45d / 100 * height) - (views.get("txtnotsatisfied").vw.getHeight() / 2)));
//BA.debugLineNum = 59;BA.debugLine="TxtNotSatisfied.Width=25%x"[feedback/General script]
views.get("txtnotsatisfied").vw.setWidth((int)((25d / 100 * width)));
//BA.debugLineNum = 60;BA.debugLine="TxtNotSatisfied.Height=25%y"[feedback/General script]
views.get("txtnotsatisfied").vw.setHeight((int)((25d / 100 * height)));
//BA.debugLineNum = 64;BA.debugLine="imgSatisfied.HorizontalCenter=49%x"[feedback/General script]
views.get("imgsatisfied").vw.setLeft((int)((49d / 100 * width) - (views.get("imgsatisfied").vw.getWidth() / 2)));
//BA.debugLineNum = 65;BA.debugLine="imgSatisfied.VerticalCenter=52%y"[feedback/General script]
views.get("imgsatisfied").vw.setTop((int)((52d / 100 * height) - (views.get("imgsatisfied").vw.getHeight() / 2)));
//BA.debugLineNum = 66;BA.debugLine="imgSatisfied.Width=12%x"[feedback/General script]
views.get("imgsatisfied").vw.setWidth((int)((12d / 100 * width)));
//BA.debugLineNum = 67;BA.debugLine="imgSatisfied.Height=12%x"[feedback/General script]
views.get("imgsatisfied").vw.setHeight((int)((12d / 100 * width)));
//BA.debugLineNum = 70;BA.debugLine="TxtSatisfied.HorizontalCenter=53%x"[feedback/General script]
views.get("txtsatisfied").vw.setLeft((int)((53d / 100 * width) - (views.get("txtsatisfied").vw.getWidth() / 2)));
//BA.debugLineNum = 71;BA.debugLine="TxtSatisfied.VerticalCenter=45%y"[feedback/General script]
views.get("txtsatisfied").vw.setTop((int)((45d / 100 * height) - (views.get("txtsatisfied").vw.getHeight() / 2)));
//BA.debugLineNum = 72;BA.debugLine="TxtSatisfied.Width=25%x"[feedback/General script]
views.get("txtsatisfied").vw.setWidth((int)((25d / 100 * width)));
//BA.debugLineNum = 73;BA.debugLine="TxtSatisfied.Height=25%y"[feedback/General script]
views.get("txtsatisfied").vw.setHeight((int)((25d / 100 * height)));
//BA.debugLineNum = 77;BA.debugLine="ImgVerySatisfied.HorizontalCenter=70%x"[feedback/General script]
views.get("imgverysatisfied").vw.setLeft((int)((70d / 100 * width) - (views.get("imgverysatisfied").vw.getWidth() / 2)));
//BA.debugLineNum = 78;BA.debugLine="ImgVerySatisfied.VerticalCenter=50%y"[feedback/General script]
views.get("imgverysatisfied").vw.setTop((int)((50d / 100 * height) - (views.get("imgverysatisfied").vw.getHeight() / 2)));
//BA.debugLineNum = 79;BA.debugLine="ImgVerySatisfied.Width=12%x"[feedback/General script]
views.get("imgverysatisfied").vw.setWidth((int)((12d / 100 * width)));
//BA.debugLineNum = 80;BA.debugLine="ImgVerySatisfied.Height=12%x"[feedback/General script]
views.get("imgverysatisfied").vw.setHeight((int)((12d / 100 * width)));
//BA.debugLineNum = 82;BA.debugLine="TxtVerySatisfied.HorizontalCenter=67%x"[feedback/General script]
views.get("txtverysatisfied").vw.setLeft((int)((67d / 100 * width) - (views.get("txtverysatisfied").vw.getWidth() / 2)));
//BA.debugLineNum = 83;BA.debugLine="TxtVerySatisfied.VerticalCenter=45%y"[feedback/General script]
views.get("txtverysatisfied").vw.setTop((int)((45d / 100 * height) - (views.get("txtverysatisfied").vw.getHeight() / 2)));
//BA.debugLineNum = 84;BA.debugLine="TxtVerySatisfied.Width=25%x"[feedback/General script]
views.get("txtverysatisfied").vw.setWidth((int)((25d / 100 * width)));
//BA.debugLineNum = 85;BA.debugLine="TxtVerySatisfied.Height=25%y"[feedback/General script]
views.get("txtverysatisfied").vw.setHeight((int)((25d / 100 * height)));
//BA.debugLineNum = 89;BA.debugLine="ImgDelighted.HorizontalCenter=90%x"[feedback/General script]
views.get("imgdelighted").vw.setLeft((int)((90d / 100 * width) - (views.get("imgdelighted").vw.getWidth() / 2)));
//BA.debugLineNum = 90;BA.debugLine="ImgDelighted.VerticalCenter=50%y"[feedback/General script]
views.get("imgdelighted").vw.setTop((int)((50d / 100 * height) - (views.get("imgdelighted").vw.getHeight() / 2)));
//BA.debugLineNum = 91;BA.debugLine="ImgDelighted.Width=12%x"[feedback/General script]
views.get("imgdelighted").vw.setWidth((int)((12d / 100 * width)));
//BA.debugLineNum = 92;BA.debugLine="ImgDelighted.Height=12%x"[feedback/General script]
views.get("imgdelighted").vw.setHeight((int)((12d / 100 * width)));
//BA.debugLineNum = 94;BA.debugLine="TxtDelighted.HorizontalCenter=88%x"[feedback/General script]
views.get("txtdelighted").vw.setLeft((int)((88d / 100 * width) - (views.get("txtdelighted").vw.getWidth() / 2)));
//BA.debugLineNum = 95;BA.debugLine="TxtDelighted.VerticalCenter=45%y"[feedback/General script]
views.get("txtdelighted").vw.setTop((int)((45d / 100 * height) - (views.get("txtdelighted").vw.getHeight() / 2)));
//BA.debugLineNum = 96;BA.debugLine="TxtDelighted.Width=25%x"[feedback/General script]
views.get("txtdelighted").vw.setWidth((int)((25d / 100 * width)));
//BA.debugLineNum = 97;BA.debugLine="TxtDelighted.Height=25%y"[feedback/General script]
views.get("txtdelighted").vw.setHeight((int)((25d / 100 * height)));
//BA.debugLineNum = 102;BA.debugLine="lblCompany.HorizontalCenter=25%x"[feedback/General script]
views.get("lblcompany").vw.setLeft((int)((25d / 100 * width) - (views.get("lblcompany").vw.getWidth() / 2)));
//BA.debugLineNum = 103;BA.debugLine="lblCompany.VerticalCenter=95%y"[feedback/General script]
views.get("lblcompany").vw.setTop((int)((95d / 100 * height) - (views.get("lblcompany").vw.getHeight() / 2)));
//BA.debugLineNum = 104;BA.debugLine="lblCompany.Width=80%x"[feedback/General script]
views.get("lblcompany").vw.setWidth((int)((80d / 100 * width)));
//BA.debugLineNum = 105;BA.debugLine="lblCompany.Height=5%y"[feedback/General script]
views.get("lblcompany").vw.setHeight((int)((5d / 100 * height)));

}
}