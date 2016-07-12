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

import com.vaadin.ui.CustomComponent;

import cz.cuni.mff.xrg.odcs.commons.app.dpu.DPUInstanceRecord;
import cz.cuni.mff.xrg.odcs.commons.app.execution.context.DataUnitInfo;
import cz.cuni.mff.xrg.odcs.commons.app.execution.context.ExecutionInfo;

/**
 * @author Bogo
 */
public abstract class QueryView extends CustomComponent {

    private DataUnitInfo dataUnitInfo;

    private ExecutionInfo executionInfo;

    private DPUInstanceRecord selectedDpu;

    /**
     * Browse data unit.
     */
    public abstract void browseDataUnit();

    /**
     * Set querying enabled.
     * 
     * @param enabled
     */
    public abstract void setQueryingEnabled(boolean enabled);

    /**
     * Reset view.
     */
    public abstract void reset();

    /**
     * Set execution info.
     * 
     * @param executionInfo
     */
    public void setExecutionInfo(ExecutionInfo executionInfo) {
        this.executionInfo = executionInfo;
    }

    /**
     * Set data unit info.
     * 
     * @param duInfo
     */
    public void setDataUnitInfo(DataUnitInfo duInfo) {
        dataUnitInfo = duInfo;
    }

    /**
     * Set selected DPU.
     * 
     * @param dpu
     */
    public void setSelectedDpu(DPUInstanceRecord dpu) {
        selectedDpu = dpu;
    }

    /**
     * Get execution info.
     * 
     * @return execution info
     */
    protected ExecutionInfo getExecutionInfo() {
        return executionInfo;
    }

    /**
     * Get data unit info.
     * 
     * @return data unit info
     */
    protected DataUnitInfo getDataUnitInfo() {
        return dataUnitInfo;
    }

    /**
     * Get selected DPU.
     * 
     * @return selected DPU
     */
    protected DPUInstanceRecord getSelectedDpu() {
        return selectedDpu;
    }
}
