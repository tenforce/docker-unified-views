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
package cz.cuni.mff.xrg.odcs.frontend.gui.tables;

import com.vaadin.ui.Component;
import com.vaadin.ui.Component.Event;

/**
 * Event for passing request to show logs with preselected DPU.
 * 
 * @author Bogo
 */
public class OpenLogsEvent extends Event {

    private Long dpuId;

    /**
     * Constructor.
     * 
     * @param source
     *            Source of the event.
     * @param dpuId
     *            Id of DPU which logs should be opened.
     */
    public OpenLogsEvent(Component source, Long dpuId) {
        super(source);
        this.dpuId = dpuId;
    }

    /**
     * Get Id of DPU which logs should be opened.
     * 
     * @return Id of DPU which logs should be opened
     */
    public Long getDpuId() {
        return dpuId;
    }

}
