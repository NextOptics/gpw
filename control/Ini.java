package gpw.control;

import java.util.*;
import java.io.*;
import javafx.collections.*;


public class Ini {
	static Ini props;
	static int item;
	static String section, subSection;

/*
public static ObservableList<String> loadProps(String section, String subSection)
*/	
	
	public static ObservableList<String> loadProps(String section, String subSection) {
		item = 0;
		String valor = "";
		ObservableList<String> resultadosIndices = FXCollections.observableArrayList();
		try {
			Properties p = new Properties();
			p.load(new FileInputStream("gpw/control/user.props"));
			while (true){
				valor = p.getProperty(section + "." + subSection + item);
				if (valor == null) {
					break;
				}
				else {
					resultadosIndices.add(valor);
					item++;
				}	
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		return resultadosIndices;
	}
}