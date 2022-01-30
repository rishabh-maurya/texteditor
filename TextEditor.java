
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;

class TextEditor extends JFrame implements ActionListener {
	// Text component
	JTextArea t;

	// Frame
	JFrame f;

	// Constructor
	TextEditor()
	{
		// Create a frame
		f = new JFrame("TextEditor");

		try {
			// Set metal look and feel
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

			// Set theme to ocean
			MetalLookAndFeel.setCurrentTheme(new OceanTheme());
		}
		    catch (Exception e) {
		}

		// Text component
		t = new JTextArea("Welcome to AccioJob");

		// Create a menubar
		JMenuBar mb = new JMenuBar();

		// Create a menu for menu
		JMenu file = new JMenu("File");

		// Create menu items
		JMenuItem New = new JMenuItem("New");
		JMenuItem open = new JMenuItem("Open");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem print = new JMenuItem("Print");

		// Add action listener
		New.addActionListener(this);
		open.addActionListener(this);
		save.addActionListener(this);
		print.addActionListener(this);

		file.add(New);
		file.add(open);
		file.add(save);
		file.add(print);

		// Create a menu for menu
		JMenu edit = new JMenu("Edit");

		// Create menu items
		JMenuItem cut = new JMenuItem("cut");
		JMenuItem copy = new JMenuItem("copy");
		JMenuItem paste = new JMenuItem("paste");

		// Add action listener
		cut.addActionListener(this);
		copy.addActionListener(this);
		paste.addActionListener(this);

		edit.add(cut);
		edit.add(copy);
		edit.add(paste);

		JMenuItem close = new JMenuItem("Close");

		close.addActionListener(this);

		mb.add(file);
		mb.add(edit);
		mb.add(close);

		f.setJMenuBar(mb);
		f.add(t);
		f.setSize(500, 500);
        f.setVisible(true);
	}

	// If a button is pressed
	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();

		if (s.equals("cut")) {
			t.cut();
		}
		else if (s.equals("copy")) {
			t.copy();
		}
		else if (s.equals("paste")) {
			t.paste();
		}
		else if (s.equals("Save")) {
			// Create an object of JFileChooser class
			JFileChooser j = new JFileChooser("f:");

			// Invoke the showsSaveDialog function to show the save dialog
			int r = j.showSaveDialog(null);

			if (r == JFileChooser.APPROVE_OPTION) {

				// Set the label to the path of the selected directory
				File fi = new File(j.getSelectedFile().getAbsolutePath());

				try {
					// Create a file writer
					FileWriter wr = new FileWriter(fi, false);

					// Create buffered writer to write
					BufferedWriter w = new BufferedWriter(wr);

					// Write
					w.write(t.getText());

					w.flush();
					w.close();
				}
				catch (Exception evt) {
					JOptionPane.showMessageDialog(f, evt.getMessage());
				}
			}
			// If the user cancelled the operation
			else
				JOptionPane.showMessageDialog(f, "the user cancelled the operation");
		}
		else if (s.equals("Print")) {
			try {
				// print the file
				t.print();
			}
			catch (Exception evt) {
				JOptionPane.showMessageDialog(f, evt.getMessage());
			}
		}
		else if (s.equals("Open")) {
			// Create an object of JFileChooser class
			JFileChooser j = new JFileChooser("f:");

			// Invoke the showsOpenDialog function to show the save dialog
			int r = j.showOpenDialog(null);

			// If the user selects a file
			if (r == JFileChooser.APPROVE_OPTION) {
				// Set the label to the path of the selected directory
				File fi = new File(j.getSelectedFile().getAbsolutePath());

				try {
					// String
					String s1 = "", sl = "";

					// File reader
					FileReader fr = new FileReader(fi);

					// Buffered reader
					BufferedReader br = new BufferedReader(fr);

					// Initialize sl
					sl = br.readLine();

					// Take the input from the file
					while ((s1 = br.readLine()) != null) {
						sl = sl + "\n" + s1;
					}

					// Set the text
					t.setText(sl);
                    br.close();
				}
				catch (Exception evt) {
					JOptionPane.showMessageDialog(f, evt.getMessage());
				}
			}
			// If the user cancelled the operation
			else
				JOptionPane.showMessageDialog(f, "the user cancelled the operation");
		}
		else if (s.equals("New")) {
			t.setText("");
		}
		else if (s.equals("Close")) {
			f.setVisible(false);
		}
	}

	// Main class
	public static void main(String args[])
	{
	    new TextEditor();
	}
}
