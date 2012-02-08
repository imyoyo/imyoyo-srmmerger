package net.imyoyo.srmmerger;


import java.awt.*;
import java.awt.event.*;
import java.util.Collection;
import java.util.Scanner;
import java.util.TreeSet;

import javax.swing.*;

@SuppressWarnings("serial")
public class ConfigurationDialog extends JDialog {
	
	private TextArea searchDirTextArea;
	private boolean modalResult = false;
	
	public boolean getModalResult() {
		return modalResult;
	}
	
	public void setSearchDirectories(Collection<String> searchDirs) {
		String r = "";
		for(String line : searchDirs)
			r += line + "\n";
		searchDirTextArea.setText(r);		
	}
	
	public Collection<String> getSearchDirectories() {
		Collection<String> r = new TreeSet<String>();
		Scanner s = new Scanner(searchDirTextArea.getText());
		while(s.hasNextLine())
			r.add(s.nextLine());		
		return r;				
	}
	
	public ConfigurationDialog() {
		super((Dialog) null, "Imyoyo Preprocessor Configuration", true);

		setMinimumSize(new Dimension(400, 200));

		JButton okButton = new JButton("OK");
		JButton cancelButton = new JButton("Cancel");
		Label label = new Label("Search Directories");
		label.setForeground(Color.white);
		label.setMaximumSize(new Dimension(10000,10));

		Box buttonPanel = Box.createHorizontalBox();
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		
		Box mainPanel = Box.createVerticalBox();
		searchDirTextArea = new TextArea(1, 10);
		mainPanel.add(searchDirTextArea);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.add(label);
		contentPane.add(Box.createVerticalStrut(10));
		contentPane.add(mainPanel);
		contentPane.add(Box.createVerticalStrut(10));
		contentPane.add(buttonPanel);
		contentPane.add(Box.createVerticalStrut(10));
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modalResult = true;
				dispose();
			}
		});
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modalResult = false;
				dispose();
			}
		});
		setDefaultCloseOperation(0);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				modalResult = false;
				dispose();
			}
		});
		pack();
	}

	public static void main(String args[]) {
		ConfigurationDialog ff = new ConfigurationDialog();
		ff.setVisible(true);
	}

}
