/**
 * 
 */
package pagecode.pages.publiques;

import pagecode.PageCodeBase;
import javax.faces.component.html.HtmlOutcomeTargetButton;

/**
 * @author vtriquell
 *
 */
public class Logout extends PageCodeBase {

	protected HtmlOutcomeTargetButton btnCancela;

	protected HtmlOutcomeTargetButton getBtnCancela() {
		if (btnCancela == null) {
			btnCancela = (HtmlOutcomeTargetButton) findComponentInRoot("btnCancela");
		}
		return btnCancela;
	}

}