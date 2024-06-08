package fusee.setting;

import java.util.ArrayList;

import fusee.module.Module;

public class Setting
{
    private String name, mode, sValue;
    private Module parent;
    private ArrayList<String> options;
    private boolean bValue, onlyInt = false;
    private double dValue, minValue, maxValue;
    
    public Setting(String name, Module parent, String sVal, ArrayList<String> options)
    {
        this.name = name;
        this.parent = parent;
        this.sValue = sVal;
        this.options = options;
        this.mode = "Combo";
    }
    
    public Setting(String name, Module parent, boolean bVal)
    {
        this.name = name;
        this.parent = parent;
        this.bValue = bVal;
        this.mode = "Check";
    }
    
    public Setting(String name, Module parent, double dVal, double min, double max, boolean onlyInt)
    {
        this.name = name;
        this.parent = parent;
        this.dValue = dVal;
        this.minValue = min;
        this.maxValue = max;
        this.onlyInt = onlyInt;
        this.mode = "Slider";
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public Module getParentModule()
    {
        return this.parent;
    }
    
    public String getValueString()
    {
        return this.sValue;
    }
    
    public void setValueString(String in)
    {
        this.sValue = in;
    }
    
    public ArrayList<String> getOptions()
    {
        return this.options;
    }
    
    public boolean getValueBoolean()
    {
        return this.bValue;
    }
    
    public void setValueBoolean(boolean in)
    {
        this.bValue = in;
    }
    
    public double getValueDouble()
    {
        if (this.onlyInt)
        {
            this.dValue = (int) this.dValue;
        }
        
        return this.dValue;
    }
    
    public void setValueDouble(double in)
    {
        this.dValue = in;
    }
    
    public double getMin()
    {
        return this.minValue;
    }
    
    public double getMax()
    {
        return this.maxValue;
    }
    
    public boolean isCombo()
    {
        return this.mode.equalsIgnoreCase("Combo");
    }
    
    public boolean isCheck()
    {
        return this.mode.equalsIgnoreCase("Check");
    }
    
    public boolean isSlider()
    {
        return this.mode.equalsIgnoreCase("Slider");
    }
    
    public boolean onlyInt()
    {
        return this.onlyInt;
    }
}