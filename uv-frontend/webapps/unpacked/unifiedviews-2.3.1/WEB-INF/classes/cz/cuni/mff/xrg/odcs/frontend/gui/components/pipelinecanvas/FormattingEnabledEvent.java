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
package cz.cuni.mff.xrg.odcs.frontend.gui.components.pipelinecanvas;

import com.vaadin.ui.Component;
import com.vaadin.ui.Component.Event;

/**
 * Event for enabling/disabling of formatting tool bar.
 * 
 * @author Bogo
 */
public class FormattingEnabledEvent extends Event {

    private boolean isEnabled;

    /**
     * Constructor.
     * 
     * @param source
     * @param isEnabled
     */
    public FormattingEnabledEvent(Component source, boolean isEnabled) {
        super(source);
        this.isEnabled = isEnabled;
    }

    /**
     * Is formatting enabled.
     * 
     * @return If formatting is enabled
     */
    public boolean isEnabled() {
        return isEnabled;
    }
}
