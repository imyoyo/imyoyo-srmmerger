package net.imyoyo.srmmerger;


import java.util.Collection;
import java.util.Scanner;
import java.util.TreeSet;

import javax.swing.JOptionPane;

import net.imyoyo.preprocessor.CppPreprocessor;
import net.imyoyo.preprocessor.JavaPreprocessor;

import com.topcoder.client.contestApplet.common.LocalPreferences;
import com.topcoder.shared.language.Language;

public class Preprocessor {
	
	private CppPreprocessor cp = new CppPreprocessor();
	private JavaPreprocessor jp = new JavaPreprocessor();
	private Collection<String> searchDirs = new TreeSet<String>();
	
	public Preprocessor() {
		Scanner s = new Scanner(LocalPreferences.getInstance().getProperty(PROP_KEY, ""));
		while(s.hasNextLine())
			searchDirs.add(s.nextLine());
	}

	public String postProcess(String source, Language lang) {
		StringBuffer sb = new StringBuffer();
		if(lang.getName().equals("C++")) {
			for(String line : cp.processSourceCode(source, searchDirs, true).getPreprocessedCode()) {
				sb.append(line);
				sb.append('\n');
			}
		} else if(lang.getName().equals("Java")) {
			for(String line : jp.processSourceCode(source, searchDirs, true).getPreprocessedCode()) {
				sb.append(line);
				sb.append('\n');
			}
		} else 
			sb.append(source);
		return sb.toString();
	}
	
	public void configure() {
		ConfigurationDialog configurationDialog = new ConfigurationDialog();
		configurationDialog.setSearchDirectories(searchDirs);
		configurationDialog.setVisible(true);
		
		if(configurationDialog.getModalResult()) {
			searchDirs = configurationDialog.getSearchDirectories();
			String all = "";
			for(String item : searchDirs)
				all += item + "\n";
			LocalPreferences.getInstance().setProperty(PROP_KEY, all);
			JOptionPane.showMessageDialog(null, "Preferences were saved successfully", "Save", 1);
		}		
	}
	
	static public String PROP_KEY = "net.suhwan.tcp.searchdir";
	
	static public void main(String[] s) {
		Preprocessor p = new Preprocessor();
		p.configure();
	}

}
