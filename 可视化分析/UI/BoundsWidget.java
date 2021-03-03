package com.fr.form.ui.container;import com.fr.dashuju.dashujuXMLUtils;import com.fr.dashuju.DynamicNumberList;import com.fr.form.ui.Widget;import com.fr.general.ComparatorUtils;import com.fr.general.Inter;import com.fr.json.JSONArray;import com.fr.json.JSONException;import com.fr.json.JSONObject;import com.fr.script.Calculator;import com.fr.stable.web.Repository;import com.fr.stable.xml.XMLPrintWriter;import com.fr.stable.xml.XMLableReader;import java.awt.Dimension;import java.util.List;public final class WHorizontalBoxLayout extends WLayout{  public static final int LEFT = 0;  public static final int CENTER = 1;  public static final int RIGHT = 2;  private static final int DEFAULT_WIDTH = 80;  public static final int DEFAULT_HGAP = 0;  public static final int DEFAULT_VGAP = 0;  private int alignment;  private DynamicNumberList widgetsWidthList_DEC;  public WHorizontalBoxLayout()  {    this(1);  }  public WHorizontalBoxLayout(int paramInt)  {    this(paramInt, 0, 0);  }  public WHorizontalBoxLayout(int paramInt1, int paramInt2, int paramInt3)  {    setAlignment(paramInt1);    setHgap(paramInt2);    setVgap(paramInt3);    this.widgetsWidthList_DEC = new DynamicNumberList(80);  }  public int getAlignment()  {    return this.alignment;  }  public void setAlignment(int paramInt)  {    this.alignment = paramInt;  }  public String getXType()  {    return "horizontal";  }  public void addWidget(Widget paramWidget)  {    addWidget(paramWidget, -1);  }  public void addWidget(Widget paramWidget, int paramInt)  {    if ((paramInt > -1) && (paramInt < this.widgetList.size()))    {      this.widgetList.add(paramInt, paramWidget);      this.widgetsWidthList_DEC.insert(paramInt);    }    else    {      this.widgetList.add(paramWidget);    }  }  public void addWidthWidget(Widget paramWidget, int paramInt)  {    addWidget(paramWidget);    this.widgetsWidthList_DEC.set(this.widgetList.size() - 1, paramInt);  }  public static class BoundsWidget extends Widget    implements Comparable  {    private Widget widget;    private Rectangle bounds;    public BoundsWidget()    {    }    public BoundsWidget(Widget paramWidget, Rectangle paramRectangle)    {      this.widget = paramWidget;      this.bounds = paramRectangle;    }    public Rectangle getBounds()    {      return this.bounds;    }    public void setBounds(Rectangle paramRectangle)    {      this.bounds = paramRectangle;    }    public Widget getWidget()    {      return this.widget;    }    public String getWidgetName()    {      return this.widget.getWidgetName();    }    public String getXType()    {      return this.widget.getXType();    }    public boolean isEditor()    {      return this.widget.isEditor();    }    public String[] supportedEvents()    {      return this.widget.supportedEvents();    }    public JSONObject createJSONConfig(Repository paramRepository, Calculator paramCalculator)      throws JSONException    {      JSONObject localJSONObject = this.widget.createJSONConfig(paramRepository, paramCalculator);      localJSONObject.put("x", this.bounds.x);      localJSONObject.put("y", this.bounds.y);      localJSONObject.put("width", this.bounds.width);      localJSONObject.put("height", this.bounds.height);      return localJSONObject;    }    public int compareTo(Object paramObject)    {      BoundsWidget localBoundsWidget = (BoundsWidget)paramObject;      if ((localBoundsWidget.bounds.y < this.bounds.y) || ((localBoundsWidget.bounds.y == this.bounds.y) && (localBoundsWidget.bounds.x < this.bounds.x)))        return 1;      if ((localBoundsWidget.bounds.y == this.bounds.y) && (localBoundsWidget.bounds.x == this.bounds.x))        return 0;      return -1;    }    public void readXML(XMLableReader paramXMLableReader)    {      if (paramXMLableReader.isChildNode())      {        String str = paramXMLableReader.getTagName();        if ("InnerWidget".equals(str))          this.widget = EdashujuXMLUtils.readWidget(paramXMLableReader);        else if ("BoundsAttr".equals(str))          this.bounds = new Rectangle(paramXMLableReader.getAttrAsInt("x", 0), paramXMLableReader.getAttrAsInt("y", 0), paramXMLableReader.getAttrAsInt("width", 0), paramXMLableReader.getAttrAsInt("height", 0));      }    }    public void writeXML(XMLPrintWriter paramXMLPrintWriter)    {      if (this.widget != null)        GeneralXMLTools.writeXMLable(paramXMLPrintWriter, this.widget, "InnerWidget");      if (this.bounds != null)        paramXMLPrintWriter.startTAG("BoundsAttr").attr("x", this.bounds.x).attr("y", this.bounds.y).attr("width", this.bounds.width).attr("height", this.bounds.height).end();    }    public boolean equals(Object paramObject)    {      return ((paramObject instanceof BoundsWidget)) && (ComparatorUtils.equals(((BoundsWidget)paramObject).widget, this.widget));    }    public Object clone()      throws CloneNotSupportedException    {      BoundsWidget localBoundsWidget = (BoundsWidget)super.clone();      if (this.widget != null)        localBoundsWidget.widget = ((Widget)this.widget.clone());      if (this.bounds != null)        localBoundsWidget.bounds = ((Rectangle)this.bounds.clone());      return localBoundsWidget;    }  }}