package Utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Configs.Setting;

public class Utils {
	private static final Pattern REMOVE_TAGS = Pattern.compile("<.+?>");

	public static void main(String[] args) {
		System.out.println(Utils.readXML(Setting.settingFileName, "tableLog",
				"skip", "value"));
	}

	public static boolean checkKey(String filename) {
		File f = new File(filename);
		return f.exists();
	}

	public static String removeTags(String string) {
		if (string == null || string.length() == 0) {
			return string;
		}

		Matcher m = REMOVE_TAGS.matcher(string);
		return m.replaceAll("");
	}

	public static String specialFilter(String text, String pattern, String key) {

		// Create a Pattern object
		Pattern r = Pattern.compile(pattern);

		// Now create matcher object.
		Matcher m = r.matcher(text);
		int i = 1;
		String t = "";
		m.find();
		// System.out.println(m.group(0));
		while (true) {
			try {
				t = m.group(i++);
				// System.out.println(t);
				if (t.equalsIgnoreCase(key)) {
					return m.group(i);
				}
			} catch (Exception e) {
				break;

			}
		}
		return "";
	}

	public static boolean writeExcel(String sheetName, String saveAt,
			JTable table, int rowBegin, int columBegin, int rowEnd,
			int columnEnd) {
		if (!saveAt.endsWith(".xlsx") && !saveAt.endsWith(".xls")) {
			saveAt += ".xlsx";
		}
		File path = new File(saveAt);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		rowBegin = rowBegin - 1;
		columBegin = columBegin - 1;

		if (rowBegin < 0) {
			rowBegin = 0;
		}
		if (columBegin < 0) {
			columBegin = 0;
		}
		if (rowEnd < 0) {
			rowEnd = 1;
		}
		if (columnEnd < 0) {
			columnEnd = 1;
		}

		if (rowBegin > model.getRowCount()) {
			rowBegin = model.getRowCount() - 1;
		}
		if (columBegin > model.getColumnCount()) {
			columBegin = model.getColumnCount() - 1;
		}
		if (rowEnd > model.getRowCount()) {
			rowEnd = model.getRowCount();
		}
		if (columnEnd > model.getColumnCount()) {
			columnEnd = model.getColumnCount();
		}

		boolean rs = false;
		// Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet sheet = workbook.createSheet(sheetName);

		// header
		JTableHeader th = table.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		Row rHeader = sheet.createRow(0);
		for (int x = columBegin; x < columnEnd; x++) {
			Cell cell = rHeader.createCell(x);
			TableColumn tc = tcm.getColumn(x);
			cell.setCellValue((String) tc.getHeaderValue());
			// System.out.println(tc.getHeaderValue() + " - " + x);
		}

		// data
		for (int row = rowBegin; row < rowEnd; row++) {
			Row r = sheet.createRow(row + 1);
			for (int col = columBegin; col < columnEnd; col++) {
				Cell cell = r.createCell(col);
				if (model.getValueAt(row, col) instanceof String) {
					cell.setCellValue((String) model.getValueAt(row, col));
				} else if (model.getValueAt(row, col) instanceof Integer) {
					cell.setCellValue((Integer) model.getValueAt(row, col));
				}
			}
		}

		try {
			// Write the workbook in file system
			FileOutputStream out = new FileOutputStream(path);
			workbook.write(out);
			out.close();

			System.out.println("written successfully on disk.");
			rs = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public static boolean writeExcelFromVector(String sheetName, String saveAt,
			Vector<Vector<String>> list) {
		if (!saveAt.endsWith(".xlsx") && !saveAt.endsWith(".xls")) {
			saveAt += ".xlsx";
		}
		File path = new File(saveAt);

		boolean rs = false;
		// Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet sheet = workbook.createSheet(sheetName);

		// header
		Row rHeader = sheet.createRow(0);
		for (int x = 0; x < list.get(0).size(); x++) {
			Cell cell = rHeader.createCell(x);
			cell.setCellValue(list.get(0).get(x));
			// System.out.println(tc.getHeaderValue() + " - " + x);
		}

		// // data
		for (int i = 1; i < list.size(); i++) {
			Vector<String> v = list.get(i);
			Row r = sheet.createRow(i);
			for (int j = 0; j < v.size(); j++) {
				Cell cell = r.createCell(j);
				cell.setCellValue(v.get(j));
			}
		}

		try {
			// Write the workbook in file system
			FileOutputStream out = new FileOutputStream(path);
			workbook.write(out);
			out.close();

			System.out.println("written successfully on disk.");
			rs = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public static void showExport(String sheetName, Vector<Vector<String>> list) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");

		int userSelection = fileChooser.showSaveDialog(null);
		String rs = "Failed";
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			writeExcelFromVector(sheetName, fileToSave.getAbsoluteFile()
					.toString(), list);
			rs = "Success";
		}
		JOptionPane.showMessageDialog(null, rs);
	}

	public static List<String> importLink(String text) {
		List<String> list = new ArrayList<String>();
		StringTokenizer strTok = new StringTokenizer(text, "\r\n");
		while (strTok.hasMoreElements()) {
			list.add((String) strTok.nextElement());
		}
		return list;
	}

	public static List<HashMap<String, String>> filterList(String text,
			String pattern, HashMap<String, Integer> indexList) {

		// Create a Pattern object
		Pattern r = Pattern.compile(pattern);

		// Now create matcher object.
		Matcher m = r.matcher(text);
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		while (m.find()) {
			String get = "";
			HashMap<String, String> listTemp = new HashMap<String, String>();
			for (String key : indexList.keySet()) {
				int index = indexList.get(key);
				get = m.group(index);
				listTemp.put(key, get);
			}
			if (listTemp.size() > 0) {
				list.add(listTemp);
			}
		}

		return list;
	}

	public static String filter(String text, String pattern, int index) {

		// Create a Pattern object
		Pattern r = Pattern.compile(pattern);

		// Now create matcher object.
		Matcher m = r.matcher(text);
		m.find();
		return m.group(index);

	}

	public static String readFile(String fileLink) {
		System.out.println("Read file from: " + fileLink);
		BufferedReader br = null;
		String content = "";
		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(fileLink));

			while ((sCurrentLine = br.readLine()) != null) {
				content += sCurrentLine + "\r\n";
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return content;
	}

	public static void writeFile(String fileLink, String content) {
		System.out.println("Write file into: " + fileLink);
		try {
			File file = new File(fileLink);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static boolean doPing(String ipstr) {
		try {
			// make a URL to a known source
			URL url = new URL("http://" + ipstr);

			// open a connection to that source
			HttpURLConnection urlConnect = (HttpURLConnection) url
					.openConnection();

			// trying to retrieve data from the source. If there
			// is no connection, this line will fail
			try {
				urlConnect.getContent();
			} catch (Exception e) {
				return false;
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;

	}

	public static boolean createFolder(String directoryName) {
		boolean result = false;
		try {
			String strManyDirectories = directoryName;

			// Create multiple directories
			result = (new File(strManyDirectories)).mkdirs();
			if (result) {
				System.out.println("Directories: " + strManyDirectories
						+ " created");
			}

		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}

		return result;
	}

	public static void delete(File file) throws IOException {
		// File file = new File(SRC_FOLDER);
		// make sure directory exists
		// !file.exists()
		if (file.isDirectory()) {

			// directory is empty, then delete it
			if (file.list().length == 0) {

				file.delete();
				System.out.println("Directory is deleted : "
						+ file.getAbsolutePath());

			} else {

				// list all the directory contents
				String files[] = file.list();

				for (String temp : files) {
					// construct the file structure
					File fileDelete = new File(file, temp);

					// recursive delete
					delete(fileDelete);
				}

				// check the directory again, if empty then delete it
				if (file.list().length == 0) {
					file.delete();
					System.out.println("Directory is deleted : "
							+ file.getAbsolutePath());
				}
			}

		} else {
			// if file, then delete it
			file.delete();
			System.out.println("File is deleted : " + file.getAbsolutePath());
		}
	}

	public static void deleteSubDirectory(File directory) {
		if (directory.exists()) {
			File[] files = directory.listFiles();
			if (null != files) {
				if (files.length == 0) {
					try {
						Utils.delete(new File(directory.getAbsolutePath()));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					for (int i = 0; i < files.length; i++) {
						System.out.println(files[i].getName());
						if (files[i].isDirectory()) {
							deleteSubDirectory(files[i]);
						} else {
							try {
								System.out.println("=>>delete file: "
										+ directory.getAbsolutePath() + "/"
										+ files[i].getName());
								Utils.delete(new File(directory
										.getAbsolutePath()
										+ "/"
										+ files[i].getName()));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}

	public static void copyDirectory(File sourceLocation, File targetLocation)
			throws IOException {
		System.out.println("Copy Directory");
		if (sourceLocation.isDirectory()) {
			if (!targetLocation.exists()) {
				targetLocation.mkdir();
			}

			String[] children = sourceLocation.list();
			for (int i = 0; i < children.length; i++) {
				copyDirectory(new File(sourceLocation, children[i]), new File(
						targetLocation, children[i]));
			}
		} else {

			InputStream in = new FileInputStream(sourceLocation);
			OutputStream out = new FileOutputStream(targetLocation);

			// Copy the bits from instream to outstream
			byte[] buf = new byte[512];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
		}
	}

	public static void copyFileUsingStream(File source, File dest)
			throws IOException {
		System.out.println("Copy File");
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		} finally {
			is.close();
			os.close();
		}
	}

	public static void writeXML(String fileName, String root) {
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement(root);
			doc.appendChild(rootElement);

			// staff elements
			// Element log = doc.createElement(element);
			// rootElement.appendChild(log);

			// set attribute to staff element
			// Attr attr = doc.createAttribute("id");
			// attr.setValue("1");
			// staff.setAttributeNode(attr);

			// shorten way
			// staff.setAttribute("id", "1");

			// Element skip = doc.createElement(row);
			// skip.appendChild(doc.createTextNode(value));
			// log.appendChild(skip);

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(fileName));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			// System.out.println("XML File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	public static void modifyXML(String fileName, String parent,
			String element, String row, String column, String value) {
		try {
			String filepath = fileName;
			File file = new File(fileName);
			if (!file.isFile()) {
				Utils.writeXML(fileName, parent);
			}
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			// Get the root element
			Node root = doc.getFirstChild();

			// Get the staff element by tag name directly
			Node el = doc.getElementsByTagName(element).item(0);
			if (el == null) {
				Element log = doc.createElement(element);
				root.appendChild(log);
				Element r = doc.createElement(row);
				r.setAttribute(column, value);
				log.appendChild(r);
			} else {
				NodeList list = el.getChildNodes();
				boolean has = false;
				for (int i = 0; i < list.getLength(); i++) {
					Node node = list.item(i);
					if (node.getNodeName().equalsIgnoreCase(row)) {
						has = true;
						Element r = (Element) node;
						r.setAttribute(column, value);
						break;
					}
				}
				if (!has) {
					Element newEl = doc.createElement(row);
					el.appendChild(newEl);
					newEl.setAttribute(column, value);
				}
			}
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);

			// System.out.println("Done");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	public static String readXML(String fileName, String element, String row,
			String column) {
		String value = "";
		try {
			String filepath = fileName;
			File file = new File(fileName);
			if (!file.isFile()) {
				return "";
			}
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			// Get the root element
			Node root = doc.getFirstChild();

			// Get the staff element , it may not working if tag has spaces, or
			// whatever weird characters in front...it's better to use
			// getElementsByTagName() to get it directly.
			// Node staff = company.getFirstChild();

			// Get the staff element by tag name directly
			Node el = doc.getElementsByTagName(element).item(0);
			try {
				if (el == null) {
					return "";
				} else {
					NodeList list = el.getChildNodes();

					for (int i = 0; i < list.getLength(); i++) {
						Node node = list.item(i);
						if (node.getNodeName().equalsIgnoreCase(row)) {
							Element e = (Element) node;
							value = e.getAttribute(column);
							break;
						}
					}
				}
			} catch (Exception e) {
				return "";
			}

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
		return value;
	}
}
