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

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import cz.cuni.mff.xrg.odcs.frontend.i18n.Messages;

/**
 * Component for holding the DPU's configuration dialog. The component show message if the configuration
 * component is null.
 *
 * @author Škoda Petr
 */
public class DPUConfigHolder extends CustomComponent {

    /**
     * Layout for no-configuration dialog.
     */
    private VerticalLayout noConfigLayout;

    public DPUConfigHolder() {
        buildLayout();
    }

    private void buildLayout() {
        setSizeFull();
        // Build layout for no configuration.
        final Label infoLabel = new Label();
        infoLabel.setSizeUndefined();
        infoLabel.setValue(Messages.getString("DPUConfigHolder.configuration"));
        noConfigLayout = new VerticalLayout();
        noConfigLayout.setSizeFull();
        noConfigLayout.addComponent(infoLabel);
        noConfigLayout.setComponentAlignment(infoLabel, Alignment.MIDDLE_CENTER);
    }

    /**
     * Set the component representing the dialog.
     *
     * @param confDialog
     *            Can be null.
     */
    public void setConfigComponent(Component confDialog) {
        if (confDialog == null) {
            setCompositionRoot(noConfigLayout);
        } else {
            setCompositionRoot(confDialog);
        }
    }

}
