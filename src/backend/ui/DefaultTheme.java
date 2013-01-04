package backend.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 * Simple UI theme without textures
 * @author Marc
 *
 */
public class DefaultTheme implements ITheme
{
	private Color backColor1;
	private Color backColor2;
	private Color borderColor;
	private Color textColor;
	
	public DefaultTheme()
	{
		backColor1 = new Color(64, 64, 64, 192);
		backColor2 = new Color(96, 96, 96, 192);
		borderColor = new Color(192, 192, 192, 192);
		textColor = new Color(255, 255, 255);
	}
		
	@Override
	public void renderPanel(Graphics gfx, Panel w)
	{
		int x = w.getAbsoluteX();
		int y = w.getAbsoluteY();
		// TODO Widget: pre-translate into render() method
		
		gfx.setColor(backColor1);
		gfx.fillRect(x, y, w.getWidth(), w.getHeight());
		gfx.setColor(borderColor);
		gfx.setLineWidth(2);
		gfx.drawRect(x, y, w.getWidth(), w.getHeight());
	}

	@Override
	public void renderWindow(Graphics gfx, Window w)
	{
		int x = w.getAbsoluteX();
		int y = w.getAbsoluteY();
		
		gfx.setColor(backColor1);
		gfx.fillRect(x, y, w.getWidth(), w.getHeight());
		gfx.setColor(borderColor);
		gfx.setLineWidth(2);
		gfx.drawRect(x, y, w.getWidth(), w.getHeight());		
	}

	@Override
	public void renderWindowTitleBar(Graphics gfx, WindowTitleBar w)
	{
		int x = w.getAbsoluteX();
		int y = w.getAbsoluteY();
		
		gfx.setColor(backColor2);
		gfx.fillRect(x, y, w.getWidth(), w.getHeight());
		gfx.setColor(borderColor);
		gfx.setLineWidth(2);
		gfx.drawRect(x, y, w.getWidth(), w.getHeight());		
		
		// Title
		if(w.getText() != null)
		{
			gfx.setColor(textColor);
			gfx.drawString(w.getText(), x + 8, y + 2);
		}
	}

	@Override
	public void renderWindowCloseButton(Graphics gfx, Button w)
	{
		int x = w.getAbsoluteX();
		int y = w.getAbsoluteY();
		
		gfx.setColor(borderColor);
		gfx.setLineWidth(2);
		gfx.drawRect(x, y, w.getWidth(), w.getHeight());
		
		if(w.isMouseOver())
			gfx.setColor(Color.white);
		else
			gfx.setColor(Color.black);
		
		gfx.drawLine(x, y, x + w.getWidth(), y + w.getHeight());
		gfx.drawLine(x, y + w.getHeight(), x + w.getWidth(), y);
	}

	public void renderButton(Graphics gfx, Button b, String text, Image icon)
	{
		int x = b.getAbsoluteX();
		int y = b.getAbsoluteY();
		
		if(b.isPressed())
			gfx.setColor(backColor1);
		else
			gfx.setColor(backColor2);
		gfx.fillRect(x, y, b.getWidth(), b.getHeight());
		
		if(b.isMouseOver())
			gfx.setColor(Color.white);
		else
			gfx.setColor(borderColor);
		gfx.setLineWidth(2);
		gfx.drawRect(x, y, b.getWidth(), b.getHeight());
		
		if(icon != null)
			gfx.drawImage(icon, x, y);

		if(text != null)
		{
			if(b.isEnabled())
				gfx.setColor(Color.white);
			else
				gfx.setColor(Color.black);
			gfx.drawString(text, x + 4, y + 2);
		}
	}

	@Override
	public void renderMenuItem(Graphics gfx, MenuItem w)
	{
		renderButton(gfx, w, w.getText(), null);
	}

	@Override
	public void renderToolButton(Graphics gfx, ToolButton w)
	{
		renderButton(gfx, w, null, w.icon);
	}

	@Override
	public void renderPushButton(Graphics gfx, PushButton w)
	{
		renderButton(gfx, w, w.getText(), null);
	}

	@Override
	public void renderMenuBarButton(Graphics gfx, MenuBarButton w)
	{
		renderButton(gfx, w, null, null);
		if(w.getLabel() != null)
			renderLabel(gfx, w.getLabel());
	}

	@Override
	public void renderProgressBar(Graphics gfx, ProgressBar w)
	{
		int x = w.getAbsoluteX();
		int y = w.getAbsoluteY();
		int t = (int) (w.getProgress() * (float)(w.getWidth()));
		
		gfx.setColor(Color.green);
		gfx.fillRect(x, y, t, w.getHeight());
		gfx.setColor(Color.gray);
		gfx.fillRect(x + t, y, w.getWidth() - t, w.getHeight());
	}

	@Override
	public void renderLabel(Graphics gfx, Label label)
	{
		int x = label.getAbsoluteX();
		int y = label.getAbsoluteY();
		
		if(label.getImage() != null)
			gfx.drawImage(label.getImage(), x, y);
		
		if(label.getText() != null)
		{
			if(label.getFont() != null)
				gfx.setFont(label.getFont());
			gfx.setColor(label.getTextColor());
			label.getText().render(gfx, x, y);
		}
	}

	@Override
	public void renderNotification(Graphics gfx, Notification n)
	{
		// TODO DefaultTheme: renderNotification
	}

	@Override
	public Font getFont()
	{
		return null;
	}

	@Override
	public void renderCheckBox(Graphics gfx, Checkbox checkBox)
	{
		// TODO DefaultTheme: renderCheckBox
	}

	@Override
	public void playButtonHoverSound()
	{
	}

	@Override
	public void playButtonPressSound()
	{
	}

	@Override
	public void renderTextField(Graphics gfx, TextField tf)
	{
		int x = tf.getAbsoluteX();
		int y = tf.getAbsoluteY();
		
		gfx.setLineWidth(2);
		gfx.setColor(Color.white);
		gfx.drawRect(x, y, x+tf.getWidth(), tf.getHeight());
		
		gfx.setColor(Color.green);
		
		int caretX = x;
		
		if(tf.getText() != null)
		{
			if(tf.getFont() != null)
				gfx.setFont(tf.getFont());
			
			caretX = x + gfx.getFont().getWidth(tf.getText());
					
			gfx.setColor(tf.getColor());
			gfx.drawString(tf.getText(), x + 4, y);
		}
		
		if(System.currentTimeMillis() / 300 % 2 == 0)
			gfx.drawLine(caretX + 8, y + 6, caretX + 8, y + tf.getHeight()+8);
	}

}


