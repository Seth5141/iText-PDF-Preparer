import javax.swing.*;
import java.awt.*;

//Combines a jlabel and a jtextfield to shorten my code in the classes that use graphical user interface
public class LabelAndField extends JPanel {
	private JLabel label;
	private JTextField textfield;
	
	public LabelAndField (String label)
	{
		this.label = new JLabel(label);
		textfield = new JTextField(20);
		setLayout(new GridLayout(1,2));
		add(this.label);
		add(textfield);
		
	}
	
	public String getText()
	{
		return textfield.getText();
	}
	public void setText(String Text)
	{
		textfield.setText(Text);
	}
	public void setText(int w)
	{
		textfield.setText(Integer.toString(w));
	}
}
