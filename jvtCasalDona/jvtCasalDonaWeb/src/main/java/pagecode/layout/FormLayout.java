/**
 * 
 */
package pagecode.layout;

import pagecode.PageCodeBase;
import javax.faces.component.html.HtmlOutputFormat;
import javax.faces.component.html.HtmlOutputText;

/**
 * @author vtriquell
 *
 */
public class FormLayout extends PageCodeBase {

	protected HtmlOutputFormat formatLogin;
	protected HtmlOutputText lblSiad;

	protected HtmlOutputFormat getFormatLogin() {
		if (formatLogin == null) {
			formatLogin = (HtmlOutputFormat) findComponentInRoot("formatLogin");
		}
		return formatLogin;
	}

	protected HtmlOutputText getLblSiad() {
		if (lblSiad == null) {
			lblSiad = (HtmlOutputText) findComponentInRoot("lblSiad");
		}
		return lblSiad;
	}


}