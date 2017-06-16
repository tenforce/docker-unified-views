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
package cz.cuni.mff.xrg.odcs.frontend.gui.components;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import cz.cuni.mff.xrg.odcs.frontend.i18n.Messages;

public class RelationalQueryView extends QueryView {

    private static final long serialVersionUID = 8249279688953599096L;

    public RelationalQueryView() {
        final VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setHeight("240px");

        final Label label = new Label(Messages.getString("RelationalQueryView.browser.not.available"), ContentMode.HTML);
        mainLayout.addComponent(label);
        mainLayout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);

        setCompositionRoot(mainLayout);
    }

    @Override
    public void browseDataUnit() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setQueryingEnabled(boolean enabled) {
        // TODO Auto-generated method stub

    }

    @Override
    public void reset() {
        // TODO Auto-generated method stub

    }

}
