package framework.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvReader {

	private BufferedReader reader;

	public CsvReader(String path) throws FileNotFoundException {
		// path = "/Users/meher/Desktop/Java_tools/Logs/d.csv";
		reader = new BufferedReader(new FileReader(path), ',');
	}

	public Object[][] getData() {
		Object[][] data = null;
		String line = "";
		String[] columns = null;
		String[] rowData = null;
		int linenum = 0;
		List<Map<String, String>> listData = new ArrayList<Map<String, String>>();
		try {
			while ((line = reader.readLine()) != null) {
				if (!line.equals("")) {
					linenum++;
					Map<String, String> columnList = new HashMap<String, String>();

					if (linenum == 1) {
						columns = line.split(",");
					} else {
						rowData = line.split(",");
						for (int i = 0; i < columns.length; i++) {
							columnList.put(columns[i], rowData[i]);
						}
						listData.add(columnList);
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		data = new Object[listData.size()][1];

		for (int i = 0; i < listData.size(); i++) {
			Object[] _rowData = new Object[1];
			_rowData[0] = listData.get(i);
			data[i] = _rowData;
		}

		return data;
	}

}
