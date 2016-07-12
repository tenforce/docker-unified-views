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
 * Event for closing of detail in PipelineEdit.
 * 
 * @author Bogo
 */
public class DetailClosedEvent extends Event {

    private Class detailClass;

    /**
     * Default constructor.
     * 
     * @param source
     *            Source component of the event.
     * @param klass
     *            Class of the object, which detail was closed.
     */
    public DetailClosedEvent(Component source, Class klass) {
        super(source);
        detailClass = klass;
    }

    /**
     * Get class of detail.
     * 
     * @return class of detail
     */
    public Class getDetailClass() {
        return detailClass;
    }
}
