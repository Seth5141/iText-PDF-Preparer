import javax.swing.*;
import java.awt.*;
//creates a paired label and field so only one object need be instantiated.
public class LabelAndFields extends JPanel {
	private JLabel label;
	private JTextField textfield1;
	private JTextField textfield2;
	//constructor creates two fields and a label that are paired side by side in a grid layout. 
	public LabelAndFields (String label)
	{
		this.label = new JLabel(label.trim());
		textfield1 = new JTextField(15);
		textfield2 = new JTextField(15);
		setLayout(new GridLayout(1,3));
		add(this.label);
		add(textfield1);
		add (textfield2);
	}
	
	public String getText1()
	{
		return textfield1.getText();
	}
	public void setText1(String Text)
	{
		textfield1.setText(Text);
	}
	public void setText1(int w)
	{
		textfield1.setText(Integer.toString(w));
	}
	
	public String getText2()
	{
		return textfield2.getText();
	}
	public void setText2(String Text)
	{
		textfield2.setText(Text);
	}
	public void setText2(int w)
	{
		textfield2.setText(Integer.toString(w));
	}
}
