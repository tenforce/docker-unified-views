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
package cz.cuni.mff.xrg.odcs.frontend.container.rdf;

import org.vaadin.addons.lazyquerycontainer.LazyQueryDefinition;

import cz.cuni.mff.xrg.odcs.commons.app.dpu.DPUInstanceRecord;
import cz.cuni.mff.xrg.odcs.commons.app.execution.context.DataUnitInfo;
import cz.cuni.mff.xrg.odcs.commons.app.execution.context.ExecutionInfo;

/**
 * Modification of {@link LazyQueryDefinition} to work with RDF queries.
 * All data needed for creating query are supplied in constructor.
 * 
 * @author Bogo
 */
public class RDFQueryDefinition extends LazyQueryDefinition {

    private String query;

    private ExecutionInfo info;

    private DPUInstanceRecord dpu;

    private DataUnitInfo dataUnitInfo;

    /**
     * Constructor.
     * 
     * @param batchSize
     * @param propertyId
     * @param query
     * @param info
     * @param dpu
     * @param dataUnitInfo
     */
    public RDFQueryDefinition(int batchSize, String propertyId, String query, ExecutionInfo info, DPUInstanceRecord dpu, DataUnitInfo dataUnitInfo) {
        super(true, batchSize, propertyId);
        this.query = query;
        this.info = info;
        this.dpu = dpu;
        this.dataUnitInfo = dataUnitInfo;
    }

    String getBaseQuery() {
        return query;
    }

    ExecutionInfo getInfo() {
        return info;
    }

    DPUInstanceRecord getDpu() {
        return dpu;
    }

    DataUnitInfo getDataUnit() {
        return dataUnitInfo;
    }
}
