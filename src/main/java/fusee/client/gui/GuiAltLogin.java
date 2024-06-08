package fusee.client.gui;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.Proxy;

import com.mojang.authlib.Agent;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiErrorScreen;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.Session;

public class GuiAltLogin extends GuiScreen
{
    private GuiTextField usernameField, passwordField, usernamePasswordField;
    private GuiScreen prev;
    
    public GuiAltLogin(GuiScreen prev)
    {
        this.prev = prev;
    }
    
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        drawDefaultBackground();
        this.mc.fontRendererObj.drawStringWithShadow("Connected as : " + this.mc.getSession().getUsername(), 2, 2, -1);
        ScaledResolution sr = new ScaledResolution(this.mc);
        drawString(this.mc.fontRendererObj, "E-Mail : ", this.width / 2 - 125, sr.getScaledHeight() / 2 - 92, -1);
        this.usernameField.drawTextBox();
        drawString(this.mc.fontRendererObj, "Password : ", this.width / 2 - 125, sr.getScaledHeight() / 2 - 92 + 40, -1);
        this.passwordField.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    protected void actionPerformed(GuiButton button)
    {
        if (button.id == 3)
        {
            login();
        }
        
        if (button.id == 5)
        {
            this.mc.displayGuiScreen(this.prev);
        }
        
        try {
            
            super.actionPerformed(button);
        } catch (IOException e) {}
    }
    
    private void login()
    {
        if (this.usernamePasswordField.getText().length() != 0 && this.usernamePasswordField.getText().contains(":") && !this.usernamePasswordField.getText().endsWith(":"))
        {
            this.usernameField.setText(this.usernamePasswordField.getText().split(":")[0]);
            this.passwordField.setText(this.usernamePasswordField.getText().split(":")[1]);
            this.usernamePasswordField.setText("");
        }
        
        try {
        
            Session session = createSession(usernameField.getText(), passwordField.getText(), Proxy.NO_PROXY);
            Field field = Minecraft.class.getDeclaredField("session");
            field.setAccessible(true);
            field.set(mc, session);
            
        } catch (Exception e) {
            mc.displayGuiScreen(new GuiErrorScreen("Login Error", e.getMessage()));
        }
    }
    
    protected void keyTyped(char typedChar, int keyCode)
    {
        this.usernameField.textboxKeyTyped(typedChar, keyCode);
        this.passwordField.textboxKeyTyped(typedChar, keyCode);
        this.usernamePasswordField.textboxKeyTyped(typedChar, keyCode);
        
        try {
            
            super.keyTyped(typedChar, keyCode);
        } catch (IOException e) {}
    }
    
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton)
    {
        this.usernameField.mouseClicked(mouseX, mouseY, mouseButton);
        this.passwordField.mouseClicked(mouseX, mouseY, mouseButton);
        this.usernamePasswordField.mouseClicked(mouseX, mouseY, mouseButton);
        
        try {
        
            super.mouseClicked(mouseX, mouseY, mouseButton);
        } catch (IOException e) {}
    }
    
    public void initGui()
    {
        ScaledResolution sr = new ScaledResolution(this.mc);
        
        (this.usernameField = new GuiTextField(0, this.mc.fontRendererObj, this.width / 2 - 125, sr.getScaledHeight() / 2 - 80, 250, 20)).setMaxStringLength(100);
        (this.passwordField = new GuiTextField(1, this.mc.fontRendererObj, this.width / 2 - 125, sr.getScaledHeight() / 2 - 40, 250, 20)).setMaxStringLength(100);
        (this.usernamePasswordField = new GuiTextField(2, this.mc.fontRendererObj, this.width / 2 - 125, sr.getScaledHeight() / 2, 250, 20)).setMaxStringLength(100);
        
        this.buttonList.add(new GuiButton(3, this.width / 2 - 125, sr.getScaledHeight() / 2 + 25, 250, 20, "Connect"));
        this.buttonList.add(new GuiButton(5, this.width / 2 - 125, sr.getScaledHeight() / 2 + 50, 250, 20, "Cancel"));
        
        super.initGui();
    }
    
    public void updateScreen()
    {
        super.updateScreen();
    }
    
    public Session createSession(String username, String password, Proxy proxy) throws Exception
    {
        YggdrasilAuthenticationService service = new YggdrasilAuthenticationService(proxy, "");
        YggdrasilUserAuthentication auth = (YggdrasilUserAuthentication) service.createUserAuthentication(Agent.MINECRAFT);
            
        auth.setUsername(username);
        auth.setPassword(password);
        auth.logIn();
            
        return new Session(auth.getSelectedProfile().getName(), auth.getSelectedProfile().getId().toString(), auth.getAuthenticatedToken(), "mojang");
    }
}