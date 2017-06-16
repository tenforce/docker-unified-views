/**
 * This file is part of UnifiedViews.
 *
 * UnifiedViews is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * UnifiedViews is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with UnifiedViews.  If not, see <http://www.gnu.org/licenses/>.
 */
package cz.cuni.mff.xrg.odcs.frontend.navigation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Panel;
import com.vaadin.ui.SingleComponentContainer;

import cz.cuni.mff.xrg.odcs.frontend.i18n.Messages;

/**
 * Can display {@lnk View} as well as {@link PresenterWrap} in {@link ComponentContainer}.
 * 
 * @author Petyr
 */
class PresenterViewDisplay implements ViewDisplay {

    private static final Logger LOG =
            LoggerFactory.getLogger(PresenterViewDisplay.class);

    private SingleComponentContainer container = null;

    public PresenterViewDisplay(SingleComponentContainer container) {
        this.container = container;
    }

    @Override
    public void showView(View view) {
        if (container == null) {
            // nothing happen
            LOG.error("container is null, showView ignored");
            return;
        }
        if (container instanceof Panel) {
            ((Panel) container).setScrollLeft(0);
        }
        if (view instanceof Component) {
            container.setContent((Component) view);
        } else if (view instanceof PresenterWrap) {
            PresenterWrap wrap = (PresenterWrap) view;
            // enter presenter
            Object presenterView = wrap.enterPresenter();
            if (presenterView instanceof Component) {
                container.setContent((Component) presenterView);
            } else {
                // unknown class
                throw new IllegalArgumentException(Messages.getString("PresenterViewDisplay_no.component")
                        + wrap.getPresenter());
            }
        } else {
            // unknown class
            throw new IllegalArgumentException(Messages.getString("PresenterViewDisplay_view.not.component")
                    + view);
        }
    }

}
