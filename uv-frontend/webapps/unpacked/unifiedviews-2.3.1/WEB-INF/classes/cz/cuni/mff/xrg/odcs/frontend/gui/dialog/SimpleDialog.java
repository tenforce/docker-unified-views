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
package cz.cuni.mff.xrg.odcs.frontend.gui.dialog;

import com.vaadin.ui.Component;
import com.vaadin.ui.Window;

public class SimpleDialog extends Window {
    private static final long serialVersionUID = -6088542008168348352L;

    public SimpleDialog(Component content) {
        // set dialog properties
        setModal(true);
        setResizable(true);
        // set initial size
        setWidth("640px");
        setHeight("640px");
        setContent(content);
    }

}
