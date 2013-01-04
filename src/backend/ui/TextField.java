package backend.ui;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

/**
 * Simple editable text line
 * @author Marc
 *
 */
public class TextField extends BasicWidget
{
	private String text;
	private Font font;
	private Color color;
	private boolean focused;
	private ArrayList<IActionListener> validateListeners;
	
	public TextField(Widget parent, int width, int height)
	{
		super(parent, width, height);
		validateListeners = new ArrayList<IActionListener>();
		color = new Color(Color.black);
		text = "";
	}
	
	public void addValidateListener(IActionListener l) {
		validateListeners.add(l);
	}
		
	public String getText() {
		return text;
	}
	
	public void setText(String s) {
		text = s;
	}
	
	public Color getColor() {
		return color;
	}
	
	public Font getFont() {
		return font;
	}
	
	public boolean isFocused() {
		return focused;
	}
	
	public void setFocused(boolean f) {
		focused = f;
	}
	
	public void setColor(Color clr ) {
		color = new Color(clr);
	}
	
	public void setFont(Font f) {
		font = f;
	}

	@Override
	public boolean mousePressed(int button, int x, int y)
	{
		if(contains(x, y))
			focused = true;
		else
			focused = false;
		return super.mousePressed(button, x, y);
	}
	
	protected boolean isEnterable(char c)
	{
		return (c >= '0' && c <= '9') ||
			(c >= 'A' && c <= 'Z') || 
			(c >= 'a' && c <= 'z') ||
			c == ' ';
	}
	
	private void validate()
	{
		for(IActionListener l : validateListeners)
			l.actionPerformed(this);
	}
	
	private void delChar()
	{
		if(!text.isEmpty())
			text = text.substring(0, text.length() - 1);
	}
	
	@Override
	public boolean keyPressed(int key, char c)
	{
		if(key == Input.KEY_ENTER)
		{
			validate();
		}
		else if(key == Input.KEY_BACK)
		{
			delChar();
		}
		else if(focused && isEnterable(c))
		{
			String newText = text + c;
			if(font != null)
			{
				// TODO horrible fix : remove the /2
				if(font.getWidth(newText)/2 < getWidth())
					text = newText;
			}
			else
				text = newText;
			return true;
		}
		
		return super.keyPressed(key, c);
	}

	@Override
	public void render(GameContainer gc, Graphics gfx)
	{
		UIRenderer.getTheme().renderTextField(gfx, this);
		
		if(font == null)
			font = gfx.getFont();
	}

}



