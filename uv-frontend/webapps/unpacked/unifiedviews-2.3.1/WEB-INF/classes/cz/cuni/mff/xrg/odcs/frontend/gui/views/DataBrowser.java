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
package cz.cuni.mff.xrg.odcs.frontend.gui.views;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Label;

import cz.cuni.mff.xrg.odcs.frontend.gui.ViewComponent;
import cz.cuni.mff.xrg.odcs.frontend.i18n.Messages;

/**
 * @author Petyr
 * @deprecated will be removed soon
 */
@Deprecated
class DataBrowser extends ViewComponent {

    private AbsoluteLayout mainLayout;

    private Label label;

    public DataBrowser() {
    }

    @Override
    public void enter(ViewChangeEvent event) {
        buildMainLayout();
        setCompositionRoot(mainLayout);
    }

    @Override
    public boolean isModified() {
        //There are no editable fields.
        return false;
    }

    private AbsoluteLayout buildMainLayout() {
        // common part: create layout
        mainLayout = new AbsoluteLayout();
        mainLayout.setImmediate(false);
        mainLayout.setWidth("600px");
        mainLayout.setHeight("800px");

        // top-level component properties
        setWidth("600px");
        setHeight("800px");

        // label
        label = new Label();
        label.setImmediate(false);
        label.setWidth("-1px");
        label.setHeight("-1px");
        label.setValue(Messages.getString("DataBrowser.browser"));
        label.setContentMode(ContentMode.HTML);
        mainLayout.addComponent(label, "top:100.0px;left:80.0px;");

        return mainLayout;
    }

}
