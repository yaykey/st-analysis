package com.poi;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.w3c.dom.Document;

/**
 * @author: Chembo Huang
 * @since: May 3, 2012
 * @modified: May 3, 2012
 * @version:
 */
public class Word2Html {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(Word2Html.class);

	private static String basePath = "src/main/webapp/helper";

	public static void main(String argv[]) {
		if (logger.isDebugEnabled()) {
			logger.debug("main(String[]) - start");
		}

		try {
			convert2Html(basePath + "/1.doc", basePath + "/1.html");
		} catch (Exception e) {
			logger.error("main(String[])", e);

			e.printStackTrace();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("main(String[]) - end");
		}
	}

	public static void writeFile(String content, String path) {
		if (logger.isDebugEnabled()) {
			logger.debug("writeFile(String, String) - start");
		}

		FileOutputStream fos = null;
		BufferedWriter bw = null;
		try {
			File file = new File(path);
			fos = new FileOutputStream(file);
			bw = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
			bw.write(content);
		} catch (FileNotFoundException fnfe) {
			logger.error("writeFile(String, String)", fnfe);

			fnfe.printStackTrace();
		} catch (IOException ioe) {
			logger.error("writeFile(String, String)", ioe);

			ioe.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fos != null)
					fos.close();
			} catch (IOException ie) {
				logger.error("writeFile(String, String) - exception ignored",
						ie);
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("writeFile(String, String) - end");
		}
	}

	public static void convert2Html(String fileName, String outPutFile)
			throws TransformerException, IOException,
			ParserConfigurationException {
		if (logger.isDebugEnabled()) {
			logger.debug("convert2Html(String, String) - start");
		}

		HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(
				fileName));// WordToHtmlUtils.loadDoc(new
							// FileInputStream(inputFile));
		WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
				DocumentBuilderFactory.newInstance().newDocumentBuilder()
						.newDocument());
		wordToHtmlConverter.setPicturesManager(new PicturesManager() {
			public String savePicture(byte[] content, PictureType pictureType,
					String suggestedName, float widthInches, float heightInches) {
				if (logger.isDebugEnabled()) {
					logger.debug("$PicturesManager.savePicture(byte[], PictureType, String, float, float) - start");
				}

				String returnString = "/images/" + suggestedName;
				if (logger.isDebugEnabled()) {
					logger.debug("$PicturesManager.savePicture(byte[], PictureType, String, float, float) - end");
				}
				return returnString;
			}
		});
		wordToHtmlConverter.processDocument(wordDocument);
		// save pictures
		
		//String imgBasePath = "D:/eclipse/eclipse-jee-helios-SR2-x86_64/workspace/fdc-uda-web/src/main/webapp/helper/";
		
		List pics = wordDocument.getPicturesTable().getAllPictures();
		if (pics != null) {
			for (int i = 0; i < pics.size(); i++) {
				Picture pic = (Picture) pics.get(i);
				System.out.println();
				try {
					
					pic.writeImageContent(new FileOutputStream(basePath
							+ pic.suggestFullFileName()));
				} catch (FileNotFoundException e) {
					logger.error("convert2Html(String, String)", e);

					e.printStackTrace();
				}
			}
		}
		Document htmlDocument = wordToHtmlConverter.getDocument();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DOMSource domSource = new DOMSource(htmlDocument);
		StreamResult streamResult = new StreamResult(out);

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer serializer = tf.newTransformer();
		serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		serializer.setOutputProperty(OutputKeys.INDENT, "yes");
		serializer.setOutputProperty(OutputKeys.METHOD, "html");
		serializer.transform(domSource, streamResult);
		out.close();
		writeFile(new String(out.toByteArray()), outPutFile);

		if (logger.isDebugEnabled()) {
			logger.debug("convert2Html(String, String) - end");
		}
	}
}