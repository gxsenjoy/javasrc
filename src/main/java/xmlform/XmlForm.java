import java.io.*;
import org.xml.sax.*;
import org.w3c.dom.*;
import com.sun.xml.tree.*;

/** Convert a simple XML file to text.
 * @author Ian Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class XmlToText {
	protected Reader is;
	protected String fileName;

	static PrintWriter msg = new PrintWriter(System.out, true);

	/** Construct a converter given an input filename */
	public XmlToText(String fn) {
		fileName = fn;
	}

	/** Convert the file */
	public void convert(boolean verbose) {
		try {
			if (verbose)
				msg.println(">>>Parsing " + fileName + "...");
			// Make the document a URL so relative DTD works.
			String uri = "file:" + new File(fileName).getAbsolutePath();
			XmlDocumentBuilder  builder = new XmlDocumentBuilder();
			Parser instance = new com.sun.xml.parser.Parser();
			instance.setDocumentHandler(builder);
			builder.setParser(instance);
			builder.setDisableNamespaces(false);
			instance.parse(uri);
			XmlDocument doc = builder.getDocument();
			if (verbose)
				msg.println(">>>Walking " + fileName + "...");
			new ConvertToMif(doc).walk();

		} catch (Exception ex) {
			System.err.println(ex);
		}
		if (verbose)
			msg.println(">>>Done " + fileName + "...");
	}

	public static void main(String av[]) {
		if (av.length == 0) {
			System.err.println("Usage: XmlForm file");
			return;
		}
		for (int i=0; i<av.length; i++) {
			String name = av[i];
			new XmlToText(name).convert(true);
		}
	}
}
